<%--
  Created by IntelliJ IDEA.
  User: vlasov
  Date: 10.12.2021
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="entity.Song" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <meta http-equiv="refresh">
    <title>
        Songs
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
    <p class="p">All songs</p>
</div>

<div>
    <table class="content-table">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col" class="text-center">Name</th>
            <th scope="col" class="text-center">Duration</th>
            <th scope="col" class="text-center">Album ID</th>
            <th class="text-center">Actions</th>
        </tr>
        </thead>
    </table>
</div>

<div class="tableFixHead">
    <form action="songs"
          method="post">
        <table class="content-table">
            <%
                List<Song> songs = (List<Song>) request.getAttribute("songs-list");
                for (Song song : songs) {
            %>
            <form action="songs"
                  method="post">
                <tbody>
                <tr>
                    <td>
                        <input type="text"
                               name="id"
                               value="<%=song.getId()%>"
                               title="<%=Integer.toString(song.getId())%>" readonly>
                    </td>

                    <td>
                        <input type="text"
                               name="name"
                               value="<%=song.getNameSong()%>"
                               title="<%=song.getNameSong()%>">
                    </td>

                    <td>
                        <input type="text"
                               name="duration"
                               value="<%=song.getDurationSong()%>"
                               title="<%=Integer.toString(song.getDurationSong())%>">
                    </td>

                    <td>
                        <input type="text"
                               name="albumId"
                               value="<%=song.getIdAlbum().getId()%>"
                               title="<%=song.getIdAlbum().getId()%>">
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
    <p class="p">Add new song</p>
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
    <form action="songs"
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
                    <input type="text"
                           name="duration"
                           placeholder="Enter duration"
                           title="Enter duration"
                           required="required">
                </td>

                <td>
                    <input type="text"
                           name="albumId"
                           placeholder="Enter album id"
                           title="Enter album id"
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
          action="songs"
          method="post">

        <div class="div-center">
            <p class="p">All songs, which name started with...</p>
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
                    <th scope="col" class="text-center">Genre</th>
                    <th scope="col" class="text-center">Artist ID</th>
                    <th class="text-center">Actions</th>
                </tr>
                </thead>
            </table>
        </div>

        <div class="tableFixHead">
            <table class="content-table">
                <%
                    if (request.getAttribute("songs-name-list") != null) {
                        List<Song> songsBrand = (List<Song>) request.getAttribute("songs-name-list");
                        for (Song song : songsBrand) {
                %>
                <form id="songs-name"
                      action="songs"
                      method="post">
                    <tbody>
                    <tr>
                        <td>
                            <input type="text"
                                   name="id"
                                   value="<%=song.getId()%>"
                                   title="<%=Integer.toString(song.getId())%>" readonly>
                        </td>

                        <td>
                            <input type="text"
                                   name="name"
                                   value="<%=song.getNameSong()%>"
                                   title="<%=song.getNameSong()%>">
                        </td>

                        <td>
                            <input type="text"
                                   name="duration"
                                   value="<%=Integer.toString(song.getDurationSong())%>"
                                   title="<%=Integer.toString(song.getDurationSong())%>">
                        </td>

                        <td>
                            <input type="text"
                                   name="albumId"
                                   value="<%=song.getIdAlbum().getId()%>"
                                   title="<%=song.getIdAlbum().getId()%>">
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
