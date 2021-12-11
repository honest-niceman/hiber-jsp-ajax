package servlets;

import dao.AlbumDAO;
import dao.SongDAO;
import entity.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/songs")
public class SongController extends HttpServlet {
    private AlbumDAO albumDAO;
    private SongDAO songDAO;

    @Override
    public void init() {
        albumDAO = new AlbumDAO();
        songDAO = new SongDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("songs-list", songDAO.selectSongs());
        request.getRequestDispatcher("song.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                insertSong(request);
                response.sendRedirect(request.getContextPath() + "/songs");
                break;
            }
            case "edit": {
                editSong(request);
                response.sendRedirect(request.getContextPath() + "/songs");
                break;
            }
            case "delete": {
                if (!songDAO.deleteSong(Integer.parseInt(request.getParameter("id")))) {
                    request.setAttribute("song-not-deleted", "Song is not deleted");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/songs");
                }
                break;
            }
            case "show-names": {
                request.setAttribute("songs-list", songDAO.selectSongs());
                request.setAttribute("songs-name-list", songDAO.selectSongsByName(request.getParameter("name")));
                request.getRequestDispatcher("song.jsp").forward(request, response);
                break;
            }
        }
    }

    private void editSong(HttpServletRequest request) {
        Song song = songDAO.selectSong(Integer.parseInt(request.getParameter("id")));

        song.setNameSong(request.getParameter("name"));
        song.setDurationSong(Integer.parseInt(request.getParameter("duration")));
        song.setIdAlbum(albumDAO.selectAlbum(Integer.parseInt(request.getParameter("albumId"))));

        songDAO.updateSong(song);
    }

    private void insertSong(HttpServletRequest request) {
        Song song = new Song();

        song.setNameSong(request.getParameter("name"));
        song.setDurationSong(Integer.parseInt(request.getParameter("duration")));
        song.setIdAlbum(albumDAO.selectAlbum(Integer.parseInt(request.getParameter("albumId"))));

        songDAO.insertSong(song);
    }
}
