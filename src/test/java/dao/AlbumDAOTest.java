package dao;

import entity.Album;
import entity.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumDAOTest {
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
        assertTrue(albumDAO.insertAlbum(album));
        assertNotNull(albumDAO.selectAlbum(1));
        assertFalse(albumDAO.selectAlbums().isEmpty());

        album.setNameAlbum("New Name");
        assertTrue(albumDAO.updateAlbum(album));

        assertTrue(albumDAO.deleteAlbum(1));
    }
}