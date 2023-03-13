

const deleteUser = (elem, id) => {
    axios.delete(`/fees/delete/${id}`)
    .then(response => {
    console.log(`DELETE: user is removed`, id);
    // remove elem from DOM
    elem.remove();
    })
    .catch(error => console.error(error));
    };