

console.log(window.innerWidth);
window.addEventListener("scroll", function () {
    var header = document.querySelector("nav");
    header.classList.toggle("bg-danger", window.scrollY > 0)
})



