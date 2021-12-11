<%--
  Created by IntelliJ IDEA.
  User: vlasov
  Date: 10.12.2021
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="entity.Artist" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <meta http-equiv="refresh">
    <title>
        Artists
    </title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="div-center">
    <div class="aParent">
        <ul class="hr">
            <li>
                <a class="links" href="artists">Artists</a>
            </li>
            <li>
                <a class="links" href="albums">Albums</a>
            </li>
            <li>
                <a class="links" href="songs">Songs</a>
            </li>
        </ul>
    </div>
</div>

<div class="div-center">
    <p class="p">All artists</p>
</div>

<div>
    <table class="content-table">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col" class="text-center">Name</th>
            <th class="text-center">Actions</th>
        </tr>
        </thead>
    </table>
</div>

<div class="tableFixHead">
    <form action="artists"
          method="post">
        <table class="content-table">
            <%
                List<Artist> artists = (List<Artist>) request.getAttribute("artists-list");
                for (Artist artist : artists) {
            %>
            <form action="artists"
                  method="post">
                <tbody>
                <tr>
                    <td>
                        <input type="text"
                               name="id"
                               value="<%=artist.getId()%>"
                               title="<%=Integer.toString(artist.getId())%>" readonly>
                    </td>

                    <td>
                        <input type="text"
                               name="name"
                               value="<%=artist.getNameArtist()%>"
                               title="<%=artist.getNameArtist()%>">
                    </td>

                    <td>
                        <div class="div-center">
                            <button type="submit"
                                    name="action"
                                    value="edit"
                                    class="btn">
                                <i>Edit</i>
                            </button>
                            <button type="submit"
                                    name="action"
                                    value="delete"
                                    class="btn">
                                <i>Delete</i>
                            </button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </form>
            <%
                }
            %>
        </table>
    </form>
</div>

<div class="div-center">
    <p class="p">Add new artist</p>
</div>

<div>
    <table class="content-table">
        <thead>
        <tr>
            <th scope="col" class="text-center">Name</th>
            <th class="text-center">Action</th>
        </tr>
        </thead>
    </table>
</div>

<div>
    <form action="artists"
          method="post">
        <table class="content-table">
            <tbody>
            <tr>
                <td>
                    <input type="text"
                           name="name"
                           placeholder="Enter name"
                           title="Enter name"
                           required="required">
                </td>

                <td>
                    <div class="div-centered">
                        <button type="submit"
                                name="action"
                                value="add"
                                class="btn">
                            Add
                        </button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div>
    <form id="names"
          action="artists"
          method="post">

        <div class="div-center">
            <p class="p">All artists, which name started with...</p>
        </div>

        <div>
            <table class="content-table">
                <thead>
                <tr>
                    <th scope="col" class="text-center">
                        <input type="text"
                               name="name"
                               placeholder="Enter name"
                               title="Enter name">
                    </th>
                    <th scope="col" class="text-center">
                        <button type="submit"
                                name="action"
                                value="show-names"
                                class="special-btn">
                            <i>Show</i>
                        </button>
                    </th>
                </tr>
                </thead>
            </table>
        </div>

        <div class="div-center">
            <p class="p"></p>
        </div>

        <div>
            <table class="content-table">
                <thead>
                <tr>
                    <th scope="col" class="text-center">ID</th>
                    <th scope="col" class="text-center">Name</th>
                    <th class="text-center">Actions</th>
                </tr>
                </thead>
            </table>
        </div>

        <div class="tableFixHead">
            <table class="content-table">
                <%
                    if (request.getAttribute("artists-name-list") != null) {
                        List<Artist> artistsBrand = (List<Artist>) request.getAttribute("artists-name-list");
                        for (Artist artist : artistsBrand) {
                %>
                <form id="artists-name"
                      action="artists"
                      method="post">
                    <tbody>
                    <tr>
                        <td>
                            <input type="text"
                                   name="id"
                                   value="<%=artist.getId()%>"
                                   title="<%=Integer.toString(artist.getId())%>" readonly>
                        </td>

                        <td>
                            <input type="text"
                                   name="name"
                                   value="<%=artist.getNameArtist()%>"
                                   title="<%=artist.getNameArtist()%>">
                        </td>

                        <td>
                            <div class="div-center">
                                <button type="submit"
                                        name="action"
                                        value="edit"
                                        class="btn">
                                    <i>Edit</i>
                                </button>
                                <button type="submit"
                                        name="action"
                                        value="delete"
                                        class="btn">
                                    <i>Delete</i>
                                </button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </form>
                <%
                    }
                %>
                <%
                    }
                %>
            </table>
        </div>
    </form>
</div>

</body>
</html>
