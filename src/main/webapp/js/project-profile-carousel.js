function slide(ele) {
    const carouselInner = document.querySelector('#project-profile-carousel .carousel-inner');

    if (carouselInner.lastElementChild === ele) {
        carouselInner.append(carouselInner.firstElementChild);
    }
    if (carouselInner.firstElementChild === ele) {
        carouselInner.prepend(carouselInner.lastElementChild);
    }

    ele.classList.remove('prev', 'next');
    carouselInner.querySelectorAll('.carousel-item').forEach(function (el) {
        el.classList.remove('prev', 'active', 'next');
    });

    ele.classList.add('active');
    ele.previousElementSibling.classList.add('prev');
    ele.nextElementSibling.classList.add('next');
}

const carouselSlideCount = document.querySelectorAll('#project-profile-carousel .carousel-item').length;
console.log(carouselSlideCount)
if (carouselSlideCount > 2) {
    const carouselInner = document.querySelector('#project-profile-carousel .carousel-inner');
    carouselInner.querySelectorAll('.carousel-item').forEach(function (el) {
        el.classList.remove('prev', 'active', 'next');
    });
    let targetIndex = 2;

    const activeCard = document.querySelector('.carousel-item:nth-child(' + targetIndex + ')');
    activeCard.classList.add('active');
    activeCard.previousElementSibling.classList.add('prev');
    activeCard.nextElementSibling.classList.add('next');
    document.querySelectorAll('.carousel-item').forEach(function (ci) {
        ci.addEventListener('click', function () {
            slide(this);
        });
    });
}

var projectCarousel = document.getElementById('project-profile-carousel');

projectCarousel.addEventListener('slide.bs.carousel', function (e) {
    slide(e.relatedTarget);
});
