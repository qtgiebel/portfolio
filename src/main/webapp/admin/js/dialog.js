const displayDialog = event => {
    document.querySelector(`#${event.currentTarget.dataset.dialogid}`).style.display = `block`;
}

const closeDialog = event => {
    document.querySelector(`#${event.currentTarget.dataset.dialogid}`).style.display = `none`;
}

const dialogInit = () => {
    document.querySelectorAll(`.new-dialog-btn`).forEach(node => node.addEventListener(`click`, displayDialog));
    document.querySelectorAll(`.close-btn`).forEach(node => node.addEventListener(`click`, closeDialog));
    document.querySelectorAll(`.close-btn-fn`).forEach(node => node.addEventListener(`click`, closeDialog));
}