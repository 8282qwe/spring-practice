package videosystem;

public class IronMan implements DigitalVideoDisc {
    private static final String TITLE = "Iron Man";
    private static final String STUDIO = "Marvel";

    @Override
    public String play() {
        return "Playing Movie " + STUDIO + "'s " + TITLE;
    }
}
