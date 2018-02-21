import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.Mp3File;

import java.io.File;

public class TrackTags {
    private String artist = "unknown artist";
    private String title = "unknown title";
    private String album = "unknown album";
    private Long length;
    private String path;
    private String md5;

    public TrackTags(File file) {
        Mp3File fileMp3 = null;
        try {
            fileMp3 = new Mp3File(file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Cannot read " + file.getAbsolutePath());
        }

        if (fileMp3.hasId3v1Tag()) {
            ID3v1 tag = fileMp3.getId3v1Tag();
            artist = tag.getArtist();
            title = tag.getTitle();
            album = tag.getAlbum();
        } else if (fileMp3.hasId3v2Tag()) {
            ID3v1 tag = fileMp3.getId3v2Tag();
            artist = tag.getArtist();
            title = tag.getTitle();
            album = tag.getAlbum();
        }

        if (artist == null) {
            artist = "unknown artist";
        }
        if (title == null) {
            title = "unknown title";
        }
        if (album == null) {
            album = "unknown album";
        }

        length = fileMp3.getLengthInSeconds();
        path = file.getAbsolutePath();
        md5 = new GetMD5ForFile(path).getMD5();
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public Long getLength() {
        return length;
    }

    public String getPath() {
        return path;
    }

    public String getMd5() {
        return md5;
    }
}
