
window.addEventListener("scroll", function () {
    var header = document.querySelector("#menubar");
    header.classList.toggle("navbar-scroll", window.scrollY > header.offsetHeight * 0);
})

includeHtmlFile = function (tagId, fileName, append, prepend) {
    const includeHtml = document.querySelector(tagId);
    const xhr = new XMLHttpRequest();
    xhr.open("GET", fileName, true);
    xhr.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            if (append) {
                includeHtml.innerHTML = includeHtml.innerHTML + this.responseText;
            }
            else if (prepend) {
                includeHtml.innerHTML = this.responseText + includeHtml.innerHTML;
            }
            else {
                includeHtml.innerHTML = this.responseText;
            }
        }
    };
    xhr.send();
    return true;
}
document.addEventListener("DOMContentLoaded", function () {

    // Include Navbar
    var result = includeHtmlFile("#navbar", "./navbar.html", false, false);

    // Include Footer
    result = includeHtmlFile("#footer", "./footer.html", false, false);

    // Include status bar
    result = includeHtmlFile("#statusbar", "./statusbar.html", false, false);

    // Head tag metadata
    result = includeHtmlFile("#head", "./head.html", false, true);
});

// Post data to webhook url
function postDataToWebHook(webHookUrl, payload) {

    var oReq = new XMLHttpRequest();

    //register method called after data has been sent method is executed
    // oReq.addEventListener("load", reqListener);
    oReq.open("POST", webHookUrl, true);
    oReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    oReq.send(JSON.stringify(payload));
    console.log("done");
    return true;
}
