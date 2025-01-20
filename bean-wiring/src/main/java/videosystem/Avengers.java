package videosystem;

public class Avengers implements DigitalVideoDisc {
    private static final String TITLE = "Avengers";
    private static final String STUDIO = "Marvel";

    @Override
    public String play() {
        return "Playing Movie " + STUDIO + "'s " + TITLE;
    }
}
