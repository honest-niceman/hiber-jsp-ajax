package entity;

import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_song_id")
    @SequenceGenerator(name = "seq_song_id", sequenceName = "seq_song_id")
    @Column(name = "id_song", nullable = false)
    private Integer id;

    @Column(name = "name_song", nullable = false, length = 63)
    private String nameSong;

    @Column(name = "duration_song", nullable = false)
    private Integer durationSong;

    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album idAlbum;

    public Album getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Album idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Integer getDurationSong() {
        return durationSong;
    }

    public void setDurationSong(Integer durationSong) {
        this.durationSong = durationSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}