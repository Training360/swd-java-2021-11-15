window.onload = function() {        
    document.querySelector("#new-tab-link").onclick = function() {
        window.open("newtab.html");
        return false;
    };

    document.querySelector("#new-window-link").onclick = function() {
        let params = `scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no,
width=0,height=0,left=-1000,top=-1000`;
        window.open("newwindow.html", "_blank", params);
        return false;
    };


}