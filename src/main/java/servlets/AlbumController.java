package servlets;

import dao.AlbumDAO;
import dao.ArtistDAO;
import dao.SongDAO;
import entity.Album;
import entity.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/albums")
public class AlbumController extends HttpServlet {
    private AlbumDAO albumDAO;
    private ArtistDAO artistDAO;
    private SongDAO songDAO;

    @Override
    public void init() {
        albumDAO = new AlbumDAO();
        artistDAO = new ArtistDAO();
        songDAO = new SongDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("albums-list", albumDAO.selectAlbums());
        request.getRequestDispatcher("album.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                insertAlbum(request);
                response.sendRedirect(request.getContextPath() + "/albums");
                break;
            }
            case "edit": {
                editAlbum(request);
                response.sendRedirect(request.getContextPath() + "/albums");
                break;
            }
            case "delete": {
                if (!albumDAO.deleteAlbum(Integer.parseInt(request.getParameter("id")))) {
                    request.setAttribute("album-not-deleted", "Album is not deleted");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/albums");
                }
                break;
            }
            case "show-names": {
                request.setAttribute("albums-list", albumDAO.selectAlbums());
                request.setAttribute("albums-name-list", albumDAO.selectAlbumsByName(request.getParameter("name")));
                request.getRequestDispatcher("album.jsp").forward(request, response);
                break;
            }
            case "complete": {
                String targetId = request.getParameter("id");
                doCompletion(targetId, response);
                break;
            }
            case "show-album-songs": {
                request.setAttribute("albums-list", albumDAO.selectAlbums());
                request.setAttribute("songs-list", songDAO.songsByAlbumName(request.getParameter("name")));
                request.getRequestDispatcher("album.jsp").forward(request, response);
                break;
            }
        }
    }

    private void editAlbum(HttpServletRequest request) {
        Album album = albumDAO.selectAlbum(Integer.parseInt(request.getParameter("id")));

        album.setNameAlbum(request.getParameter("name"));
        album.setGenreAlbum(request.getParameter("genre"));
        album.setIdArtist(artistDAO.selectArtist(Integer.parseInt(request.getParameter("artistId"))));

        albumDAO.updateAlbum(album);
    }

    private void insertAlbum(HttpServletRequest request) {
        Album album = new Album();

        album.setNameAlbum(request.getParameter("name"));
        album.setGenreAlbum(request.getParameter("genre"));
        album.setIdArtist(artistDAO.selectArtist(Integer.parseInt(request.getParameter("artistId"))));

        albumDAO.insertAlbum(album);
    }

    private void doCompletion(String targetId, HttpServletResponse response) {
        boolean namesAdded = false;
        List<Artist> artists = null;
        StringBuilder sb = null;
        // check if user sent empty string
        if (!targetId.equals("")) {
            artists = artistDAO.selectArtists();
            sb = new StringBuilder();
            for (Artist a : artists) {
                if (a.getNameArtist().toLowerCase().startsWith(targetId.toLowerCase())) {
                    sb.append("<artist>");
                    sb.append("<id>").append(a.getId()).append("</id>");
                    sb.append("<name>").append(a.getNameArtist()).append("</name>");
                    sb.append("</artist>");
                    namesAdded = true;
                }
            }
        }
        if (namesAdded) {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            System.out.println(sb);
            try {
                response.getWriter().write("<artists>" + sb + "</artists>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //nothing to show
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
