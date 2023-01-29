

console.log(window.innerWidth);
window.addEventListener("scroll", function () {
    var header = document.querySelector("nav");
    header.classList.toggle("sticky", window.scrollY > 0);
    header.classList.toggle("bg-danger", window.scrollY > 0)
})



