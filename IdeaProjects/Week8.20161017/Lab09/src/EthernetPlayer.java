import java.util.ArrayList;

/**
 * Class EthernetPlayer
 */

public class EthernetPlayer extends MusicPlayer {

    private int deviceID;
    private int connStatus;

    static final int CONNECTED = 1;
    static final int NOT_CONNECTED = 0;

    private ArrayList<String> downloadList = new ArrayList<String>();

    public EthernetPlayer(int id) {
        super(); // why do we need this?
        deviceID = id;
        connStatus = NOT_CONNECTED;

        downloadList.add("Dark Horse");
        downloadList.add("Royals");
        downloadList.add("Counting Stars");
        downloadList.add("Let Her Go");
        downloadList.add("The Fox");
    }

    /**
     *  @override turnOn and connect
     *
     */
    public void turnOn() {
        connStatus=CONNECTED;
        System.out.println("Player On");
    }

    /**
     *  @override turnOff and disconnect
     *
     */
    public void turnOff() {
        connStatus=NOT_CONNECTED;
        System.out.println("Player OFF");
    }


    /**
     *  addTrackToPlaylist: Adds mentioned track to the end of playlist array
     *  print appropriate messages to stdout
     *
     */
    public void addToPlaylist(String trackName) {
        playlist.add(trackName);
        System.out.println("Added "+trackName+" to playlist");
    }

    /**
     *  deleteFromPlaylist: deletes track of give name from the playlist
     *  print appropriate messages to stdout
     *
     */
    public void deleteFromPlaylist(String trackName) {
        playlist.remove(trackName);
        System.out.println("Removed "+trackName+" to playlist");
    }

    /**
     *  download: Downloads mentioned song from the given Download list and adds to playlist
     *  print appropriate messages to stdout
     *
     */
    public void download(String trackName) {
        //implement this section of code to lookup tracName in the downloadlist and
//        for (int i=0; i<downloadList.size(); i++){
        if (!downloadList.contains(trackName))
            System.out.println(trackName+ " not availabel for download");
        else
            playlist.add(trackName);
            System.out.println("Downloaded "+ trackName);
        }
//    }

}
