import java.io.File;
import java.util.*;

public class CataloguerMP3 {
    private Map<String, Artist> artists;

    public CataloguerMP3(List<File> files) {
        artists = new TreeMap<String, Artist>();

        for (File f : files) {
            TrackTags track = new TrackTags(f);
            addArtist(track);
        }
    }

    private void addArtist(TrackTags track) {
        if (!artists.containsKey(track.getArtist())) {
            artists.put(track.getArtist(), new Artist(track.getArtist()));
        }
        artists.get(track.getArtist()).addAlbum(track);
    }

    public static void main(String[] args) throws Exception {

        String path = "d:/music/noname";
        File f = new File(path);


        List<File> files = searchDirectoryForMP3(f);
        CataloguerMP3 instance = new CataloguerMP3(files);
    }

    // sort all mp3 files to Artist Album Track
    private void createCatalogue(List<File> files) {

    }

    // return all files with mp3 extension from directory and subdirectories recursively
    private static List<File> searchDirectoryForMP3(File directory) {
        List<File> filesMP3 = new ArrayList<File>();
        for (File f : directory.listFiles()) {
            if (f.isDirectory()) {
                filesMP3.addAll(searchDirectoryForMP3(f));
            } else if (f.getName().toLowerCase().matches(".*\\.mp3$")) {
                filesMP3.add(f);
            }
        }
        return filesMP3;
    }
}
