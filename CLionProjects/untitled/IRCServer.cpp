
const char * usage =
"                                                               \n"
"IRCServer:                                                   \n"
"                                                               \n"
"Simple server program used to communicate multiple users       \n"
"                                                               \n"
"To use it in one window type:                                  \n"
"                                                               \n"
"   IRCServer <port>                                          \n"
"                                                               \n"
"Where 1024 < port < 65536.                                     \n"
"                                                               \n"
"In another window type:                                        \n"
"                                                               \n"
"   telnet <host> <port>                                        \n"
"                                                               \n"
"where <host> is the name of the machine where talk-server      \n"
"is running. <port> is the port number you used when you run    \n"
"daytime-server.                                                \n"
"                                                               \n";

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <time.h>
#include <iostream>
#include <fstream>
#include <map>
#include <string>
using namespace std;

#include "IRCServer.h"
#include "nextword.h"

int QueueLength = 5;
int spaceCount;
int userCount = 0;
int roomCount;
const char * argsRoomName = (char *) malloc (100 * sizeof(char));
const char * currentMessage = (char *) malloc (1000 * sizeof(char));

struct userCheck {
    char * username;
    char * password;
    int rooms[50];
};

char username[100];
char password[100];

struct Rooms {
    char * users[100];
    char * roomName;
    char * messages[100];
    int userRoomCount;
    int currentMessageCount;
    int overDrive;
};

typedef Rooms Rooms;
Rooms * RoomArray;

typedef userCheck userCheck;
userCheck * userArray;

//test

int
IRCServer::open_server_socket(int port) {

	// Set the IP address and port for this server
	struct sockaddr_in serverIPAddress; 
	memset( &serverIPAddress, 0, sizeof(serverIPAddress) );
	serverIPAddress.sin_family = AF_INET;
	serverIPAddress.sin_addr.s_addr = INADDR_ANY;
	serverIPAddress.sin_port = htons((u_short) port);
  
	// Allocate a socket
	int masterSocket =  socket(PF_INET, SOCK_STREAM, 0);
	if ( masterSocket < 0) {
		perror("socket");
		exit( -1 );
	}

	// Set socket options to reuse port. Otherwise we will
	// have to wait about 2 minutes before reusing the sae port number
	int optval = 1; 
	int err = setsockopt(masterSocket, SOL_SOCKET, SO_REUSEADDR, 
			     (char *) &optval, sizeof( int ) );
	
	// Bind the socket to the IP address and port
	int error = bind( masterSocket,
			  (struct sockaddr *)&serverIPAddress,
			  sizeof(serverIPAddress) );
	if ( error ) {
		perror("bind");
		exit( -1 );
	}
	
	// Put socket in listening mode and set the 
	// size of the queue of unprocessed connections
	error = listen( masterSocket, QueueLength);
	if ( error ) {
		perror("listen");
		exit( -1 );
	}

	return masterSocket;
}

void
IRCServer::runServer(int port)
{
	int masterSocket = open_server_socket(port);

	initialize();
	
	while ( 1 ) {
		
		// Accept incoming connections
		struct sockaddr_in clientIPAddress;
		int alen = sizeof( clientIPAddress );
		int slaveSocket = accept( masterSocket,
					  (struct sockaddr *)&clientIPAddress,
					  (socklen_t*)&alen);
		
		if ( slaveSocket < 0 ) {
			perror( "accept" );
			exit( -1 );
		}
		
		// Process request.
		processRequest( slaveSocket );		
	}
}

int
main( int argc, char ** argv )
{
	// Print usage if not enough arguments
	if ( argc < 2 ) {
		fprintf( stderr, "%s", usage );
		exit( -1 );
	}
	
	// Get the port from the arguments
	int port = atoi( argv[1] );

	IRCServer ircServer;

	// It will never return
	ircServer.runServer(port);
	
}

//
// Commands:
//   Commands are started y the client.
//
//   Request: ADD-USER <USER> <PASSWD>\r\n
//   Answer: OK\r\n or DENIED\r\n
//
//   REQUEST: GET-ALL-USERS <USER> <PASSWD>\r\n
//   Answer: USER1\r\n
//            USER2\r\n
//            ...
//            \r\n
//
//   REQUEST: CREATE-ROOM <USER> <PASSWD> <ROOM>\r\n
//   Answer: OK\n or DENIED\r\n
//
//   Request: LIST-ROOMS <USER> <PASSWD>\r\n
//   Answer: room1\r\n
//           room2\r\n
//           ...
//           \r\n
//
//   Request: ENTER-ROOM <USER> <PASSWD> <ROOM>\r\n
//   Answer: OK\n or DENIED\r\n
//
//   Request: LEAVE-ROOM <USER> <PASSWD>\r\n
//   Answer: OK\n or DENIED\r\n
//
//   Request: SEND-MESSAGE <USER> <PASSWD> <MESSAGE> <ROOM>\n
//   Answer: OK\n or DENIED\n
//
//   Request: GET-MESSAGES <USER> <PASSWD> <LAST-MESSAGE-NUM> <ROOM>\r\n
//   Answer: MSGNUM1 USER1 MESSAGE1\r\n
//           MSGNUM2 USER2 MESSAGE2\r\n
//           MSGNUM3 USER2 MESSAGE2\r\n
//           ...\r\n
//           \r\n
//
//    REQUEST: GET-USERS-IN-ROOM <USER> <PASSWD> <ROOM>\r\n
//    Answer: USER1\r\n
//            USER2\r\n
//            ...
//            \r\n
//

void
IRCServer::processRequest( int fd )
{
	// Buffer used to store the comand received from the client
	const int MaxCommandLine = 1024;
	char commandLine[ MaxCommandLine + 1 ];
	int commandLineLength = 0;
	int n;
	
	// Currently character read
	unsigned char prevChar = 0;
	unsigned char newChar = 0;
	
	//
	// The client should send COMMAND-LINE\n
	// Read the name of the client character by character until a
	// \n is found.
	//

	// Read character by character until a \n is found or the command string is full.
	while ( commandLineLength < MaxCommandLine &&
		read( fd, &newChar, 1) > 0 ) {

		if (newChar == '\n' && prevChar == '\r') {
			break;
		}
		
		commandLine[ commandLineLength ] = newChar;
		commandLineLength++;

		prevChar = newChar;
	}
	
	// Add null character at the end of the string
	// Eliminate last \r
	commandLineLength--;
        commandLine[ commandLineLength ] = 0;

	printf("RECEIVED: %s\n", commandLine);

	printf("The commandLine has the following format:\n");
	printf("COMMAND <user> <password> <arguments>. See below.\n");
	printf("You need to separate the commandLine into those components\n");
	printf("For now, command, user, and password are hardwired.\n");

	const char * command = "ADD-USER";
	const char * user = "peter";
	const char * password = "spider";
	const char * args = "";

	printf("command=%s\n", command);
	printf("user=%s\n", user);
	printf( "password=%s\n", password );
	printf("args=%s\n", args);

	if (!strcmp(command, "ADD-USER")) {
		addUser(fd, user, password, args);
	}
	else if (!strcmp(command, "ENTER-ROOM")) {
		enterRoom(fd, user, password, args);
	}
	else if (!strcmp(command, "LEAVE-ROOM")) {
		leaveRoom(fd, user, password, args);
	}
	else if (!strcmp(command, "SEND-MESSAGE")) {
		sendMessage(fd, user, password, args);
	}
	else if (!strcmp(command, "GET-MESSAGES")) {
		getMessages(fd, user, password, args);
	}
	else if (!strcmp(command, "GET-USERS-IN-ROOM")) {
		getUsersInRoom(fd, user, password, args);
	}
	else if (!strcmp(command, "GET-ALL-USERS")) {
		getAllUsers(fd, user, password, args);
	}
	else {
		const char * msg =  "UNKNOWN COMMAND\r\n";
		write(fd, msg, strlen(msg));
	}

	// Send OK answer
	//const char * msg =  "OK\n";
	//write(fd, msg, strlen(msg));

	close(fd);	
}
#define MAXWORD 200
char word[MAXWORD];
int wordLength;
char * IRCServer::nextword(FILE * fd) {
    int c;
    int i = 0;
    wordLength = 0;
    // While it is not EOF read char
    while ((c= fgetc(fd)) != EOF) {
        if (c == ' '|| c == '\n' || c == '\t'|| c == '\r') {
            if (wordLength > 0)
                return word;
        }
        else {
            word[i] = c;
            i++;
            word[i]='\0';
            wordLength++;
        }
    }
    if (wordLength > 0)
        return word;
    else
        return NULL;
}


void
IRCServer::initialize()
{
    userCount=0;
    userArray = (userCheck * )malloc(100 * sizeof(userCheck));
    FILE *fp= fopen("password.txt", "w+");
    char * c = (char * )malloc(100*sizeof(char));
    int i=0;
    while ((c = nextword(fp))!='\0') {
        if (i==0) {
            userArray[userCount].username = strdup(c);
            i=1;
            continue;
        }
        else if(i==1){
            userArray[userCount].password = strdup(c);
            i=0;
            userCount++;
            continue;
        }
    }
    
    roomCount =0;
    RoomArray = (Rooms *)malloc(100 * sizeof(Rooms));
    for (int j=0; j<100; j++) {
        for (int k=0; k<100; k++) {
            RoomArray[j].messages[k] = (char *)malloc(1000 *sizeof(char));
        }
    }
    
    fclose(fp);
	// Open password file
   /* ifstream file;
    file.open ("password.txt", ifstream::in);
    string str;
    while (getline(file, str))
    {
        if (!str.empty()) {
             string pass;
            getline(file, pass);
            users.insert(pair<string, string> (str, pass));
        }
    }

*/
}

bool
IRCServer::checkPassword(int fd, const char * user, const char * password) {
	// Here check the password
    
    int i=0;
    for (; i<userCount; i++) {
        if (userArray[i].username == user) {
            if (userArray[i].password==password) {
                return true;
            }
        }
    }
    return false;
    
    /*string pass(password, 0, 30);
    if(users[user].compare(pass)){
        return true;
    }else{
        return false;
    }
    map<string,string>::iterator it;
    it = users.find(user);
    if (it != users.end()){
        char * checkpass = users.at(user);
        if (checkpass==password) {
            return true;
        }
    }
    
	return false;*/
}

void
IRCServer::addUser(int fd, const char * user, const char * password, const char * args)
{
	// Here add a new user. For now always return OK.
    int temp  =0;
    FILE * fp = fopen("password.txt", "w+");
    char * nameCheck = strdup(user);
    char * passCheck = strdup(password);
    int i=0;
    for (; i<userCount; i++) {
        printf("%s , %d\n", userArray[i].username, strcmp(userArray[i].username, nameCheck));
        if (strcmp(userArray[i].username, nameCheck) == 0) {
            temp = 1;
            break;
        }
    }
    if (temp !=1) {
        userArray[userCount].username= nameCheck;
        userArray[userCount].password= passCheck;
        userCount++;
        fprintf(fp, "%s\n %s\n", nameCheck, passCheck);
    }
    else{
        const char * msg = "DENIED\r\n";
        write(fd, msg, strlen(msg));
        return;
    }
    fclose(fp);
    const char *msg = "OK\r\n";
    write(fd, msg, strlen(msg));
    return;
    
    
    /*string checkUser(user, 0, 30);
    string checkPass(password, 0, 30);
    map<string,string>::iterator it;
    const char * msg =  "DENIED\r\n";
   // it = users.find(user);
    //if (it==users.end()) {
        users.insert(pair<string, string> (checkUser, checkPass));
        msg="OK\r\n";
    //}
    
	
    write(fd, msg, strlen(msg));*/
	
}

void
IRCServer::enterRoom(int fd, const char * user, const char * password, const char * args)
{
    int roomPos =0;
    int flag = 0;
    int temp =0;
    char * message;
    int i=0;
    if (checkPassword(fd, user, password)) {
        for (; i<roomCount; i++) {
            if (!strcmp(RoomArray[i].roomName, argsRoomName)) {
                roomPos = i;
                flag=1;
                break;
            }
        }
        if (flag == 1) {
             i=0;
            for (; i<RoomArray[roomPos].userRoomCount ; i++) {
                if (!strcmp(RoomArray[roomPos].users[i], user)) {
                    roomPos = i;
                    temp =1;
                    break;
                }
            }
            if (temp == 1) {
                for (i=roomPos; i<RoomArray[roomPos].userRoomCount; i++) {
                    RoomArray[roomPos].users[i] = RoomArray[roomPos].users[i+1];
                }
                RoomArray[roomPos].userRoomCount--;
                const char *msg = "OK\r\n";
                write(fd, msg, strlen(msg));
            }
            else{
                const char * message = "DENIED- NOUSER\r\n";
                write(fd, message, strlen(message));
            
            }
        }
    }
    else{
        const char * message2 = "DENIED-Worng pass\r\n";
        write(fd, message2, strlen(message2));
    }
    
}

void
IRCServer::leaveRoom(int fd, const char * user, const char * password, const char * args)
{
}

void
IRCServer::sendMessage(int fd, const char * user, const char * password, const char * args)
{
}

void
IRCServer::getMessages(int fd, const char * user, const char * password, const char * args)
{
}

void
IRCServer::getUsersInRoom(int fd, const char * user, const char * password, const char * args)
{
}

void
IRCServer::getAllUsers(int fd, const char * user, const char * password,const  char * args)
{
    char * tempname = (char *)malloc(100 * sizeof(char));
    char * temppass = (char *)malloc(100 * sizeof(char));
    if (checkPassword(fd, user, password)) {
        for (int i=0; i<userCount; i++) {
            write(fd, userArray[i].username, strlen(userArray[i].username));
            write(fd, "\r\n", 2);
        }
        write(fd, "\r\n", 2);
        close(fd);
    }else{
        const char * msg ="Error - Wrong password";
        write(fd, msg, strlen(msg));
    }
    
    
}

