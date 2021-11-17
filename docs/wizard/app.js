window.onload = function() {    
    const s = moment(new Date()).format('YYYY-MM-DD hh:mm:ss');
    document.querySelector("#datetime-div").innerHTML = s;

    let counter = localStorage.getItem("counter");
    if (counter == null) {
        
        counter = 1
    }
    else {
        counter++;
    }
    localStorage.setItem("counter", counter);
    document.querySelector("#counter-div").innerHTML = counter;
}