package videosystem;

public class DVDPlayer {
    private DigitalVideoDisc disc;

    public DVDPlayer() {

    }

    public DVDPlayer(DigitalVideoDisc disc) {
        this.disc = disc;
    }

    public void setDvd(DigitalVideoDisc dvd) {
        this.disc = dvd;
    }

    public String play() {
        return disc.play();
    }
}
