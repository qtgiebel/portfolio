
const focus = event => {
    const img = event.target;
    const title = img.alt;
    const archived = event.currentTarget.classList.contains("archived");
    const category = img.dataset.category;

    removeCurrentFocus();
    setCategory(category)
    setTitle(title, archived);
    setImage(img, archived);

    checkFocused();
}

const removeCurrentFocus = () => {
    if (document.querySelector(`#hero-image-container`).children.length > 0) {
        document.querySelector(`#focus-image`).remove();
    }
}

const setCategory = category => {
    document.querySelector(`#hero-category`).innerHTML = category;
}

const setTitle = (title, archived) => {
    const heroTitle = document.querySelector(`#hero-title`);

    archived ? heroTitle.innerHTML = "Archived - " : heroTitle.innerHTML = "";
    heroTitle.innerHTML += title;
}

const setImage = (img, archived) => {
    const imageWrapper = document.querySelector(`#hero-image-container`);

    const displayImage = document.createElement(`img`);

    displayImage.setAttribute(`src`, img.src);
    displayImage.setAttribute(`alt`, img.alt);
    displayImage.classList.add(`rounded`, `mx-auto`, `d-block`);
    if (archived) {displayImage.classList.add(`archived-fn`);}
    displayImage.setAttribute(`id`, `focus-image`);

    imageWrapper.appendChild(displayImage);

    appendIdToArchive(img);
    appendIdToDelete(img);
}

const appendIdToArchive = img => {
    if (document.querySelector(`#archive-submit-id`))
        {document.querySelector(`#archive-submit-id`).remove();}
    const archiveForm = document.getElementById(`archive-form`);
    const archiveInput = document.createElement(`input`);

    archiveInput.setAttribute(`type`, `hidden`);
    archiveInput.setAttribute(`name`, `pieceId`);
    archiveInput.setAttribute(`value`, img.dataset.pieceid);
    archiveInput.setAttribute(`id`, `archive-submit-id`);

    archiveForm.appendChild(archiveInput);
}

const appendIdToDelete = img => {
    if (document.querySelector(`#delete-submit-id`))
        {document.querySelector(`#delete-submit-id`).remove();}
    const deleteForm = document.getElementById(`delete-form`);
    const deleteInput = document.createElement(`input`);

    deleteInput.setAttribute(`type`, `hidden`);
    deleteInput.setAttribute(`name`, `pieceId`);
    deleteInput.setAttribute(`value`, img.dataset.pieceid);
    deleteInput.setAttribute(`id`, `delete-submit-id`);

    deleteForm.appendChild(deleteInput);
}

const checkFocused = () => {
    document.querySelector(`#focus-image`) != null ? enableEditors() : disableEditors();
}

const enableEditors = () => {
    document.querySelector(`#delete-btn`).disabled = false;
    const archiveBtn = document.querySelector(`#archive-btn`)
    archiveBtn.disabled = false;
    if (document.querySelector(`#focus-image`).classList.contains(`archived-fn`)) { archiveBtn.innerText = `Publish`; }
}

const disableEditors = () => {
    document.querySelector(`#delete-btn`).disabled = true;
    const archiveBtn = document.querySelector(`#archive-btn`);
    archiveBtn.disabled = true;
    archiveBtn.innerText = `Archive`;

}

const enableArchive = event => {
    document.querySelector(`#archive-submit`).disabled = false;
}

const enableDelete = event => {
    document.querySelector(`#delete-submit`).disabled = false;
}

const enableNewCategory = event => {
    const submitBtn = document.querySelector(`#${event.currentTarget.id}-submit`);
    submitBtn.disabled ? submitBtn.disabled = true : submitBtn.disabled = false;
}

const init = () => {
    const display = document.querySelectorAll(`.focus`);
    display.forEach(button => { button.addEventListener(`click`, focus); });

    const ckBoxes = document.getElementsByName(`permission`);
    ckBoxes.forEach(node => node.addEventListener(`change`, enableNewCategory));

    const archiveBox = document.querySelector(`#confirm-archive`);
    archiveBox.addEventListener(`change`, enableArchive);

    const deleteBox = document.querySelector(`#confirm-delete`);
    deleteBox.addEventListener(`change`, enableDelete);

    dialogInit();
}

window.onload = init;