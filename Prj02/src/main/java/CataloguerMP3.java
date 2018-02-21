import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void main(String[] args) throws Exception {
        String path = "d:/music/noname";
        String pathHTML = System.getProperty("user.dir") + "/src/main/resources/catalogue.html";
        File f = new File(path);


        List<File> files = searchDirectoryForMP3(f);
        CataloguerMP3 instance = new CataloguerMP3(files);
        instance.createHTML(pathHTML);
    }

    private void createHTML(String path) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(path));

            bw.write("<html><style>\n" +
                    "  h1 {\n" +
                    "    margin:10px;\n" +
                    "  }\n" +
                    "  h2 {\n" +
                    "    margin:20px;\n" +
                    "  }\n" +
                    "  h3 {\n" +
                    "    margin:30px;\n" +
                    "  }\n" +
                    "</style>");

            for (Artist artist : artists.values()) {
                bw.write("<h1>" + artist.getName() + "</h1>");
                for (Album album : artist.getAlbums().values()) {
                    bw.write("<h2>" + album.getName() + "</h2>");
                    for (TrackTags track : album.getTracks().values()) {
                        bw.write("<h3><a href=\"" + track.getPath() + "\">" +
                                track.getTitle() + " " + track.getLength() + " seconds </a></h3>");
                    }
                }
            }

            bw.write("/html>");
            bw.close();
        } catch (IOException e) {
            System.out.println("Cannot make html file!");
        }
    }

    private void addArtist(TrackTags track) {
        if (!artists.containsKey(track.getArtist())) {
            artists.put(track.getArtist(), new Artist(track.getArtist()));
        }
        artists.get(track.getArtist()).addAlbum(track);
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
