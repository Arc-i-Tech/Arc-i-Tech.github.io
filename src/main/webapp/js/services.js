var pointers = document.querySelectorAll("#point");
var progress = document.querySelectorAll("#overall-progress");
const observer = new IntersectionObserver(entries => {
  // Loop over the entries
  entries.forEach(entry => {
    // If the element is visible
    if (entry.isIntersecting) {
      // Add the animation class
      // entry.target.classList.add('square-animation');
      pointers.forEach(ele => {
        ele.classList.add("overall-progress-point");
      });
      progress.forEach(ele => {
        ele.classList.add("overall-progress");
      });
      return;
    }

    pointers.forEach(ele => {
      ele.classList.remove("overall-progress-point");
    });
    progress.forEach(ele => {
      ele.classList.remove("overall-progress");
    });
  });
});

observer.observe(document.querySelector('#progress-bar'));