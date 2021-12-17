var req;
var isIE;

function init() {
    completeField = document.getElementById("complete-field");
    completeTable = document.getElementById("complete-table");
    autoRow = document.getElementById("auto-row");
    completeTable.style.top = getElementY(autoRow) + "px";
}

function doCompletion() {
    var url = "songs?action=complete&id=" + escape(completeField.value);
    req = initRequest();
    req.open("POST", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function callback() {
    clearTable();

    if (req.readyState == 4) {
        if (req.status == 200) {
            parseMessages(req.responseXML);
        }
    }
}

function appendAlbum(name, albumId) {

    var row;
    var cell;
    var linkElement;
    var textNode;

    if (isIE) {
        completeTable.style.display = 'block';
        row = completeTable.insertRow(completeTable.rows.length);
        cell = row.insertCell(0);
    } else {
        completeTable.style.display = 'table';
        row = document.createElement("tr");
        cell = document.createElement("td");
        row.appendChild(cell);
        completeTable.appendChild(row);
    }

    linkElement = document.createElement("a");
    linkElement.setAttribute("href", "#");
    linkElement.setAttribute("onclick", "document.getElementById('complete-field').value='" + albumId + "'")
    linkElement.appendChild(document.createTextNode(name));
    cell.appendChild(linkElement);
}

function getElementY(element) {

    var targetTop = 0;

    if (element.offsetParent) {
        while (element.offsetParent) {
            targetTop += element.offsetTop;
            element = element.offsetParent;
        }
    } else if (element.y) {
        targetTop += element.y;
    }
    return targetTop;
}

function clearTable() {
    if (completeTable.getElementsByTagName("tr").length > 0) {
        completeTable.style.display = 'none';
        for (loop = completeTable.childNodes.length - 1; loop >= 0; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
    }
}

function parseMessages(responseXML) {
    // no matches returned
    if (responseXML == null) {
        return false;
    } else {
        var albums = responseXML.getElementsByTagName("albums")[0];

        if (albums.childNodes.length > 0) {
            completeTable.setAttribute("bordercolor", "black");
            completeTable.setAttribute("border", "1");

            for (loop = 0; loop < albums.childNodes.length; loop++) {
                var album = albums.childNodes[loop];
                var name = album.getElementsByTagName("name")[0];
                var albumId = album.getElementsByTagName("id")[0];
                appendAlbum(name.childNodes[0].nodeValue,
                    albumId.childNodes[0].nodeValue);
            }
        }
    }
}
