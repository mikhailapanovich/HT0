import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Map;
import java.util.TreeMap;

public class Album {
    String name;
    Map<String, TrackTags> tracks;

    public Album(String name) {
        this.name = name;
        tracks = new TreeMap<String, TrackTags>();
    }

    public void addTrack(TrackTags track) {
        // time for log4j!
        if (tracks.containsKey(track.getTitle())) {

        }
    }

    public static void example(String[] args) {
        System.setProperty("log4j.configurationFile",
                "src/main/resources/log4j2.xml");

        Logger logger = LogManager.getRootLogger();
        logger.info("Information message");
        logger.debug("Debug message");
        logger.trace("Trace message");
        logger.error("Error message");
        logger.fatal("Fatal message");
        logger.warn("Warning message");

        Logger logger2 = LogManager.getLogger(CataloguerMP3.class);
        logger2.info("2 Information message");
        logger2.debug("2 Debug message");
        logger2.trace("2 Trace message");
        logger2.error("2 Error message");
        logger2.fatal("2 Fatal message");
        logger2.warn("2 Warning message");
    }
}
