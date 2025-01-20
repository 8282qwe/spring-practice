package soundsystem;

import org.springframework.stereotype.Component;

@Component
public class HighSchoolRapper2Final implements CompactDisc {
    private final static String ARTIST = "김하온";
    private final static String TITLE = "붕붕";

    @Override
    public String play() {
        return "Playing " + TITLE + " by " + ARTIST;
    }
}
