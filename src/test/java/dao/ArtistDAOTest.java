package dao;

import entity.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistDAOTest {
    @Test
    void test() {
        ArtistDAO artistDAO = new ArtistDAO();
        Artist artist = new Artist();
        Artist artist1 = new Artist();

        artist.setNameArtist("John1");
        artist1.setNameArtist("John2");

        assertTrue(artistDAO.insertArtist(artist));
        assertTrue(artistDAO.insertArtist(artist1));

        assertNotNull(artistDAO.selectArtist(artist.getId()));

        artist.setNameArtist("i30");

        assertTrue(artistDAO.updateArtist(artist));

        assertNotNull(artistDAO.selectArtists());

        assertFalse(artistDAO.selectArtistsByName("i30").isEmpty());

        assertTrue(artistDAO.selectArtistsByName("not existed").isEmpty());

        assertTrue(artistDAO.deleteArtist(artist.getId()));
        assertTrue(artistDAO.deleteArtist(artist1.getId()));
    }
}