async function list(){
    let res = await fetch("/api/todos");
    data = await res.json();
    console.log(data);

    for (let i in data) {
        let li = document.createElement('li');
        li.id = data[i].id;
        if(data[i].done === "true"){
            li.className = "completed";
        }else{
            li.className = "";
        }

        let html = `
            ${data[i].todo}
    `
        li.innerHTML += html;
        document.querySelector("#todos").append(li);
    }

}
list();

document.querySelector('form').addEventListener("submit", (event)=> {
    event.preventDefault();

    let data = document.getElementById('input').value;
    addTodo(data);
})

async function addTodo(data){
    await fetch("/api/todos", {
        method: "POST",
        headers : {
          "content-type" : "application/json"
        },
        body: JSON.stringify({
            todo : data,
            done : false
        }),
    })
        .then((response) => response.json())
        .then((result) => console.log(result));

    document.querySelector(".todos").innerHTML = "";
    list();
}


async function finished(item){
    let id = item.id;
    let done;
    if(item.className == "completed"){
        done = false;
        item.className = "";
    }else{
        done = true;
        item.className = "completed";
    }

    await fetch("/api/todos/" + id, {
        method: "PUT",
        headers : {
            "content-type" : "application/json"
        },
        body: JSON.stringify({
            id : id,
            done : done
        }),
    })
        .then((response) => response.text())
        .then((result) => console.log(result));
}

async function deleteTodo(item){
    let id = item.id;

    await fetch("/api/todos/" + id, {
        method: "DELETE",
        headers : {
            "content-type" : "application/json"
        },
        body: JSON.stringify({
            id : id
        }),
    })
        .then((response) => response.text())
        .then((result) => console.log(result));
    item.remove();

}

document.querySelector('#todos').addEventListener('mousedown', (event) =>{
    if(event.which == 3 || event.button == 2){
        deleteTodo(event.target);
    }else{
        finished(event.target);
    }
});

window.oncontextmenu = function (){
    return false;
};
