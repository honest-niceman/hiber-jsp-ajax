package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_artist_id")
    @SequenceGenerator(name = "seq_artist_id", sequenceName = "seq_artist_id")
    @Column(name = "id_artist", nullable = false)
    private Integer id;

    @Column(name = "name_artist", nullable = false)
    private String nameArtist;

    @OneToMany
    @JoinColumn
    private List<Album> albums = new ArrayList<>();

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}