window.onload = function() {
    document.querySelector("#welcome-button").onclick = function() {
        let content = document.querySelector("#name-input").value;
        document.querySelector("#welcome-div").innerHTML = "Hello " + content + "!";
        return false;
    };
}
