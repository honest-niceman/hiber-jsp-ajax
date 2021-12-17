package servlets;

import dao.AlbumDAO;
import dao.SongDAO;
import entity.Album;
import entity.Artist;
import entity.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
            case "complete": {
                String targetId = request.getParameter("id");
                doCompletion(targetId, response);
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

    private void doCompletion(String targetId, HttpServletResponse response) {
        boolean namesAdded = false;
        List<Album> albums = null;
        StringBuilder sb = null;
        // check if user sent empty string
        if (!targetId.equals("")) {
            albums = albumDAO.selectAlbums();
            sb = new StringBuilder();
            for (Album a : albums) {
                if (a.getNameAlbum().toLowerCase().contains(targetId.toLowerCase())) {
                    sb.append("<album>");
                    sb.append("<id>").append(a.getId()).append("</id>");
                    sb.append("<name>").append(a.getNameAlbum()).append("</name>");
                    sb.append("</album>");
                    namesAdded = true;
                }
            }
        }
        if (namesAdded) {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            System.out.println(sb);
            try {
                response.getWriter().write("<albums>" + sb + "</albums>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //nothing to show
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
