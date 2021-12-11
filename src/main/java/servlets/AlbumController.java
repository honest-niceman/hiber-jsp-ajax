package servlets;

import dao.AlbumDAO;
import dao.ArtistDAO;
import entity.Album;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/albums")
public class AlbumController extends HttpServlet {
    private AlbumDAO albumDAO;
    private ArtistDAO artistDAO;

    @Override
    public void init() {
        albumDAO = new AlbumDAO();
        artistDAO = new ArtistDAO();
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
}
