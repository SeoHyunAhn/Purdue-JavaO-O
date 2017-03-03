import java.util.ArrayList;

/**
 * Class CDPlayer
 */
public class CDPlayer extends MusicPlayer {

    private int deviceID;
    private int thisTrack;

    /**
     * Constructor for CD-Player
     */
    public CDPlayer(int id) {
        deviceID=id;
    }

    /**
     * Over-ride Method: turnOn
     */
    public void turnOn() {
        status=ON;
        System.out.println("Player On");
    }

    /**
     * Over-ride Method: turnOff
     */
    public void turnOff() {
        status=OFF;
        System.out.println("Player OFF");
    }

    /**
     * Method to play next track in the playlist by
     * printing it to stdout and changing current
     */
    public void nextTrack() {
        if (thisTrack>5)
            System.out.println("Previous Playing "+playlist.get(0));
        else {
            thisTrack += 1;
            System.out.println("Previous Playing" +playlist.get(thisTrack));
        }
    }

    /**
     * Method to play previous track in the playlist by
     * printing it to stdout and changing current
     */
    public void previousTrack() {
        if (thisTrack<1)
        System.out.println("Previous Playing "+playlist.get(4));
        else {
            thisTrack -= 1;
            System.out.println("Previous Playing" +playlist.get(thisTrack));
        }
    }

    /**
     * Method to play current track
     */
    public void play() {
        status=PLAYING;
        thisTrack=0;
        System.out.println("Playing "+playlist.get(thisTrack));
    }

}
