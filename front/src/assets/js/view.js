function showModify(){
    document.getElementById("modify-view").setAttribute("style", "display: ;");
    document.getElementById("view").setAttribute("style", "display: none;");
    document.getElementById("modify-btn").setAttribute("style", "display: none;");
    document.getElementById("modify-cancel-btn").setAttribute("style", "display: ;");
}


function modifyCacel(){
    document.getElementById("modify-view").setAttribute("style", "display: none;");
    document.getElementById("view").setAttribute("style", "display: ;");
    document.getElementById("modify-btn").setAttribute("style", "display: ;");
    document.getElementById("modify-cancel-btn").setAttribute("style", "display: none;");
}
