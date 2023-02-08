console.log(window.innerWidth);
window.addEventListener("scroll", function () {
    var header = document.querySelector("#menubar");
    header.classList.toggle("navbar-scroll", window.scrollY > header.offsetHeight * 0);
})



