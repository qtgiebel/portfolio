const displayDialog = event => {
    document.querySelector(`#${event.currentTarget.dataset.dialogid}`).style.display = `block`;
}

const closeDialog = event => {
    console.log(event.currentTarget);
    document.querySelector(`#${event.currentTarget.dataset.dialogid}`).style.display = `none`;
}

const outClick = event => {
    if (event.target === document.querySelector(`#${event.currentTarget.dataset.dialogid}`)) {
        document.querySelector(`#${event.currentTarget.dataset.dialogid}`).style.display = `none`;
    }
}

const dialogInit = () => {
    document.querySelectorAll(`.new-dialog-btn`).forEach(node => node.addEventListener(`click`, displayDialog));
    document.querySelectorAll(`.close-btn`).forEach(node => node.addEventListener(`click`, closeDialog));
    window.addEventListener(`click`, outClick);
}