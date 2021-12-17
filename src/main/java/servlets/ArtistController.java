package servlets;

import dao.AlbumDAO;
import dao.ArtistDAO;
import entity.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/artists")
public class ArtistController extends HttpServlet {
    private ArtistDAO artistDAO;
    private AlbumDAO albumDAO;

    @Override
    public void init() {
        artistDAO = new ArtistDAO();
        albumDAO = new AlbumDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("artists-list", artistDAO.selectArtists());
        request.getRequestDispatcher("artist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                insertArtist(request);
                response.sendRedirect(request.getContextPath() + "/artists");
                break;
            }
            case "edit": {
                editArtist(request);
                response.sendRedirect(request.getContextPath() + "/artists");
                break;
            }
            case "delete": {
                if (!artistDAO.deleteArtist(Integer.parseInt(request.getParameter("id")))) {
                    request.setAttribute("artist-not-deleted", "Artist is not deleted");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/artists");
                }
                break;
            }
            case "show-names": {
                request.setAttribute("artists-list", artistDAO.selectArtists());
                request.setAttribute("artists-name-list", artistDAO.selectArtistsByName(request.getParameter("name")));
                request.getRequestDispatcher("artist.jsp").forward(request, response);
                break;
            }
            case "show-albums": {
                request.setAttribute("artists-list", artistDAO.selectArtists());
                request.setAttribute("artists-albums", albumDAO.selectAlbumsByArtistName(request.getParameter("name")));
                request.getRequestDispatcher("artist.jsp").forward(request, response);
                break;
            }
        }
    }

    private void editArtist(HttpServletRequest request) {
        Artist artist = artistDAO.selectArtist(Integer.parseInt(request.getParameter("id")));

        artist.setNameArtist(request.getParameter("name"));

        artistDAO.updateArtist(artist);
    }

    private void insertArtist(HttpServletRequest request) {
        String name = request.getParameter("name");

        Artist artist = new Artist();
        artist.setNameArtist(name);

        artistDAO.insertArtist(artist);
    }
}
