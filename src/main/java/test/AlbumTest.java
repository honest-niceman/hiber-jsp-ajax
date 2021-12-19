package test;

import dao.AlbumDAO;
import dao.ArtistDAO;
import entity.Album;
import entity.Artist;

public class AlbumTest {
    public static void main(String[] args) {
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
        albumDAO.selectAlbum(album.getId());
        albumDAO.selectAlbums();

        album.setNameAlbum("New Name");
        albumDAO.updateAlbum(album);

        albumDAO.deleteAlbum(album.getId());
        artistDAO.deleteArtist(artist.getId());
    }
}
