console.log(window.innerWidth);
window.addEventListener("scroll", function () {
    var header = document.querySelector("#menubar");
    header.classList.toggle("bg-danger", window.scrollY > 0)
})



