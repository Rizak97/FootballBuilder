"use strict";

// Selectors

// Divs
let resultsDiv = document.querySelector("#results-div");

// Inputs
let tNameInput = document.querySelector("#teamNameInput");
let fNameInput = document.querySelector("#firstNameInput");
let lNameInput = document.querySelector("#lastNameInput");
let pNameInput = document.querySelector("#positionInput");
let idInput = document.querySelector("#idInput");

// Buttons
let createBtn = document.querySelector("#createBtn");
let updateBtn = document.querySelector("#updateBtn");
let deleteBtn = document.querySelector("#deleteBtn");

// Functions

let printResults = (result) => {
    let entryParent = document.createElement("div");
    entryParent.setAttribute("class", "entry-parent");

    let entryDiv = document.createElement("div");
    entryDiv.setAttribute("class", "entry-div");
    entryDiv.textContent = `${result.id} | ${result.teamName} ${result.firstName} ${result.lastName} | ${result.position}`;

    let delBtn = document.createElement("button");
    delBtn.textContent = "Delete";
    delBtn.type = "button";
    delBtn.setAttribute("Class", "btn btn-danger btn-sm");
    delBtn.setAttribute("onClick", `del(${result.id})`);

    let editBtn = document.createElement("button");
    editBtn.textContent = "Edit";
    editBtn.type = "button";
    editBtn.setAttribute("Class", "btn btn-success btn-sm");
    editBtn.setAttribute("onClick", `edit(${result.id})`);

    entryParent.appendChild(entryDiv);
    entryParent.appendChild(delBtn);
    entryParent.appendChild(editBtn);
    resultsDiv.appendChild(entryParent);
}

let getAll = () => {
    axios.get("http://localhost:8080/team/getAll")
    .then(res => {
        resultsDiv.innerHTML = "";

        let results = res.data;

        for (let result of results) {
            printResults(result);
        }
    }).catch(err => {console.log(err);});
}

let create = () => {

    let obj = {
        "teamName": tNameInput.value,
        "firstName": fNameInput.value,
        "lastName": lNameInput.value,
        "position": pNameInput.value,
    }

    axios.post("http://localhost:8080/team/create", obj)
    .then(res => {
        getAll();
    }).catch(err => {console.log(err);});
}

let update = () => {

    let obj = {
        "teamName": tNameInput.value,
        "firstName": fNameInput.value,
        "lastName": lNameInput.value,
        "position": pNameInput.value,
    }
    
    axios.put(`http://localhost:8080/team/update/${idInput.value}`, obj)
        .then((resp) => {
            getAll();
        }).catch((err) => {console.error(err);})
}
let edit = (id) => {

    let obj = {
        "teamName": tNameInput.value,
        "firstName": fNameInput.value,
        "lastName": lNameInput.value,
        "position": pNameInput.value,
    }
    
    axios.put(`http://localhost:8080/team/update/${id}`, obj)
        .then((resp) => {
            getAll();
        }).catch((err) => {console.error(err);})
}

let del = (id) => {

    // if (!validateDelete()) {
    //     return;
    // }

    axios.delete(`http://localhost:8080/team/delete/${id}`)
        .then((resp) => {
            getAll();
        }).catch((err) => {console.log(err);})
}

let validateDelete = () => {
    if (idInput.value === "") {
        alert("ID is required for this operation");
        return false;
    } else {
        return true;
    }
}

// Listeners
createBtn.addEventListener("click", create);
updateBtn.addEventListener("click", update);
deleteBtn.addEventListener("click", del);