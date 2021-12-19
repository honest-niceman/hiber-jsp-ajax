package test;

import dao.ArtistDAO;
import entity.Artist;

public class ArtistTest {
    public static void main(String[] args) {
        ArtistDAO artistDAO = new ArtistDAO();
        Artist artist = new Artist();
        Artist artist1 = new Artist();

        artist.setNameArtist("John1");
        artist1.setNameArtist("John2");

        artistDAO.insertArtist(artist);

        artistDAO.insertArtist(artist1);

        artistDAO.selectArtist(artist.getId());

        artist.setNameArtist("i30");

        artistDAO.updateArtist(artist);

        artistDAO.selectArtists();

        artistDAO.selectArtistsByName("i30");

        artistDAO.selectArtistsByName("not existed");

        artistDAO.deleteArtist(artist.getId());
        artistDAO.deleteArtist(artist1.getId());
    }
}
