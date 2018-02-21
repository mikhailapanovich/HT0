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

    public String getName() {
        return name;
    }

    public Map<String, TrackTags> getTracks() {
        return tracks;
    }

    public void addTrack(TrackTags track) {
        // time for log4j!
        Logger logger = LogManager.getLogger(CataloguerMP3.class);
        String title = track.getTitle();
        if (tracks.containsKey(title)) {
            if (tracks.get(title).getMd5().equals(track.getMd5())) {     // checksum the same
                logger.warn("has the same md5 " + track.getPath() + "\n" + tracks.get(title).getPath());
            } else {        // artist album and name are the same
                logger.info(track.getArtist() + " " + track.getAlbum() + " " + track.getTitle() +
                        " are the same in " + track.getPath() + "\n" + tracks.get(title).getPath());
            }
        } else {
            tracks.put(title, track);
        }
    }
}
