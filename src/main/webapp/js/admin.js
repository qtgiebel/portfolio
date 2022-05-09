
/**
 * Focuses the image clicked on in the center of the screen
 * 
 * @param {*} event 
 */
const focus = (event) => {
    let img = event.target;
    let title = img.alt;
    let category = img.dataset.catIndex;

    removeCurrentFocus();
    setCategory(category)
    setTitle(title);
    setImage(img);
}

const removeCurrentFocus = () => {
    if (document.querySelector(`#hero-image-container`).children.length > 0) {
        document.querySelector(`#focus-image`).remove();
    }
}

const setCategory = category => {
    document.querySelector(`#hero-category`).innerHTML = category;
}

const setTitle = title => {
    document.querySelector(`#hero-title`).innerHTML = title;
}

const setImage = img => {
    let imageWrapper = document.querySelector(`#hero-image-container`);

    let displayImage = document.createElement(`img`);

    displayImage.setAttribute(`src`, img.src);
    displayImage.setAttribute(`alt`, img.alt);
    displayImage.classList.add(`rounded`, `mx-auto`, `d-block`);
    displayImage.setAttribute(`id`, `focus-image`);

    imageWrapper.appendChild(displayImage);
}

const init = () => {
    let display = document.querySelectorAll(`.focus`);

    display.forEach(button => { button.addEventListener(`click`, focus); });

    dialogInit();
}

window.onload = init;