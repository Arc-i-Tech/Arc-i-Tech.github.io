includeHtmlFile = function (tagId, fileName) {
    const includeHtml = document.querySelector(tagId);
    const xhr = new XMLHttpRequest();
    xhr.open("GET", fileName, true);
    xhr.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            includeHtml.innerHTML = this.responseText;
        }
    };
    xhr.send();
    return true;
}
document.addEventListener("DOMContentLoaded", function () {

    // Include Navbar
    var result = includeHtmlFile("#navbar", "./navbar.html");

    // Include Footer
    result = includeHtmlFile("#footer", "./footer.html");

    // Include status bar
    result = includeHtmlFile("#statusbar", "./statusbar.html");
});