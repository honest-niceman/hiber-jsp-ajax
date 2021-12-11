package dao;

import entity.Album;
import entity.Artist;
import entity.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongDAOTest {
    @Test
    void test() {
        Artist artist = new Artist();

        artist.setNameArtist("John1");

        ArtistDAO artistDAO = new ArtistDAO();
        artistDAO.insertArtist(artist);

        Album album = new Album();
        album.setGenreAlbum("Test");
        album.setNameAlbum("Test");
        album.setIdArtist(artist);

        AlbumDAO albumDAO = new AlbumDAO();
        albumDAO.insertAlbum(album);

        Song song = new Song();
        song.setIdAlbum(album);
        song.setDurationSong(154);
        song.setNameSong("Test");

        SongDAO songDAO = new SongDAO();

        assertTrue(songDAO.insertSong(song));

        assertNotNull(songDAO.selectSong(song.getId()));
        assertFalse(songDAO.selectSongs().isEmpty());

        song.setNameSong("New Name");
        assertTrue(songDAO.updateSong(song));

        assertTrue(songDAO.deleteSong(1));
    }
}