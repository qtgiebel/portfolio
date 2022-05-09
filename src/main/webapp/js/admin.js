
/**
 * Replicate this
 * 
 *  |<div class="navbar bg-light">
    |    <div class="container">
    |        <div class="float-start">
    |            <p class="h2 nav-link text-dark float-start col">
    |                <span class="row small text-secondary">Category</span><span class="row h2">Necronomicon
    |                    Doodle</span>
    |            </p>
    |        </div>
    |        <div class="float-end">
    |            <button class="btn btn-secondary">Archive</button>
    |            <button class="btn btn-danger">Delete</button>
    |        </div>
    |    </div>
    |</div>
    |<div class="bg-secondary run-to-bottom px-4 py-5">
    |    <img class="rounded mx-auto d-block"
    |        src="https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg"
    |        alt="Necronomicon Doodle" />
    |</div>
 * 
 * @param {*} event 
 */
const focus = (event) => {
    let img = event.target;
    let title = img.alt;
    let categoryIndex = img.dataset.catIndex;

    removeCurrentFocus();
    setCategory(categoryIndex);
    setTitle(title);
    setImage(img);
}

const removeCurrentFocus = () => {
    if (document.querySelector(`#hero-image`).children.length > 0) {
        document.querySelector(`#focus-image`).remove();
    }
}

const setCategory = categoryIndex => {
    let list = document.querySelectorAll(`.category-name`);
    let category;

    list.forEach(categoryNode => {
        if (categoryNode.dataset.categoryIndex === categoryIndex) {
            let displayCategory = document.querySelector(`#hero-category`);

            category = categoryNode.innerHTML;
            displayCategory.innerHTML = category;

            return;
        }
    });
}

const setTitle = title => {
    let displayTitle = document.querySelector(`#hero-title`);

    displayTitle.innerHTML = title;
}

const setImage = img => {
    let imageWrapper = document.querySelector(`#hero-image`);

    let displayImage = document.createElement(`img`);

    displayImage.setAttribute(`src`, img.src);
    displayImage.setAttribute(`alt`, img.alt);
    displayImage.classList.add(`rounded`, `mx-auto`, `d-block`);
    displayImage.setAttribute(`id`, `focus-image`);
    imageWrapper.appendChild(displayImage);
}

const init = () => {
    let display = document.querySelectorAll(`.focus`);
    if (document.querySelector(`#hero-image`).hasChildNodes()) {
        console.log();
    }
    display.forEach(button => {
        button.addEventListener(`click`, focus);
    });

    dialogInit();
}

window.onload = init;