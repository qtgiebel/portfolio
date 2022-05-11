const changeValue = event => {
    event.target.value = !event.target.value;
}

const listUsers = () => {
    document.querySelectorAll(`.user-btn`).forEach(user => {
        user.addEventListener(`click`, focusUser);
    });
}

const focusUser = event => {
    const currentUser = userCollection.users.filter(user => user.email === event.target.innerText)[0];
    const heroUser = document.querySelector(`#hero-user`);
    const userId = document.querySelector(`#user-id`);
    const view = document.querySelector(`#view`);
    const admin = document.querySelector(`#admin`);

    heroUser.innerHTML = currentUser.email;
    userId.value = currentUser.id;
    currentUser.viewPermission ? view.checked = true : view.checked = false;
    currentUser.adminPermission ? admin.checked = true : admin.checked = false;
}



const init = () => {
    const ckBoxes = document.getElementsByName(`permission`);
    ckBoxes.forEach(node => node.addEventListener(`change`, changeValue));

    listUsers();

    dialogInit();
}

window.onload = init;