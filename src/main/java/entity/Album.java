package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_album_id")
    @SequenceGenerator(name = "seq_album_id", sequenceName = "seq_album_id")
    @Column(name = "id_album", nullable = false)
    private Integer id;

    @Column(name = "name_album", nullable = false, length = 63)
    private String nameAlbum;

    @Column(name = "genre_album", length = 63)
    private String genreAlbum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_artist", nullable = false)
    private Artist idArtist;

    @OneToMany
    @JoinColumn
    private List<Song> songs = new ArrayList<>();

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Artist getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(Artist idArtist) {
        this.idArtist = idArtist;
    }

    public String getGenreAlbum() {
        return genreAlbum;
    }

    public void setGenreAlbum(String genreAlbum) {
        this.genreAlbum = genreAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}