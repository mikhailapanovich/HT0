import java.util.Map;
import java.util.TreeMap;

public class Artist {
    String name;
    Map<String, Album> albums;

    public Artist(String name) {
        this.name = name;
        albums = new TreeMap<String, Album>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Album> getAlbums() {
        return albums;
    }

    public void addAlbum(TrackTags track) {
        if (!albums.containsKey(track.getAlbum())) {
            albums.put(track.getAlbum(), new Album(track.getAlbum()));
        }
        albums.get(track.getAlbum()).addTrack(track);
    }
}
