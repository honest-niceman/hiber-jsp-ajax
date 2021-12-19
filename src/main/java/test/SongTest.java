package test;

import dao.AlbumDAO;
import dao.ArtistDAO;
import dao.SongDAO;
import entity.Album;
import entity.Artist;
import entity.Song;

public class SongTest {
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

        Song song = new Song();
        song.setIdAlbum(album);
        song.setDurationSong(154);
        song.setNameSong("Test");

        SongDAO songDAO = new SongDAO();

        songDAO.insertSong(song);

        songDAO.selectSong(song.getId());
        songDAO.selectSongs();

        song.setNameSong("New Name");
        songDAO.updateSong(song);

        songDAO.deleteSong(song.getId());
        albumDAO.deleteAlbum(album.getId());
        artistDAO.deleteArtist(artist.getId());
    }
}
