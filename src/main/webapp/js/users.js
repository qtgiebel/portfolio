const changeValue = event => {
    event.target.value = !event.target.value;
}

const listUsers = () => {
    const userList = document.querySelector(`#user-list`);
    userCollection.users.forEach(user => {
            const userBox = document.createElement(`div`);

            userBox.classList.add(`col`);
            userBox.classList.add(`py-2`);
            userBox.classList.add(`pr-2`);

            const userBtn = document.createElement(`button`);
            userBtn.style.all = `unset`;
            userBtn.style.cursor = `pointer`;
            userBtn.innerText = user.email;
            userBtn.dataset.userId = user.id;

            userBtn.addEventListener(`click`, focusUser);

            userBox.appendChild(userBtn);
            userList.appendChild(userBox);
        }
    );
}

const focusUser = event => {
    const currentUser = userCollection.users.filter(user => user.email === event.target.innerText)[0];
    const heroUser = document.querySelector(`#hero-user`);
    const view = document.querySelector(`#view`);
    const admin = document.querySelector(`#admin`);

    heroUser.innerHTML = currentUser.email;
    heroUser.dataset.userId = currentUser.id;
    currentUser.viewPermission ? view.checked = true : view.checked = false;
    currentUser.adminPermission ? admin.checked = true : admin.checked = false;
}

const displayDialog = () => {
    document.querySelector(`#new-user-dialog`).style.display = `block`;
}

const closeDialog = () => {
    document.querySelector(`#new-user-dialog`).style.display = `none`;
}

const outClick = event => {
    if (event.target === document.querySelector(`#new-user-dialog`)) {
        document.querySelector(`#new-user-dialog`).style.display = `none`;
    }
}

const init = () => {
    const ckBoxes = document.getElementsByName(`permission`);
    ckBoxes.forEach(node => node.addEventListener(`change`, changeValue));

    //TODO:01 use current user to set initial hero value

    listUsers();

    document.querySelector(`#new-user-dialog-btn`).addEventListener(`click`, displayDialog);
    document.querySelector(`#close-btn`).addEventListener(`click`, closeDialog);
    window.addEventListener(`click`, outClick);
}

window.onload = init;