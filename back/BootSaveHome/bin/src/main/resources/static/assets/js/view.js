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

function deleteAlert(no){
    if(confirm("정말로 삭제하시겠습니까?")){
    	location.href=`http://localhost:8080/notice/delete?no=${no}`;
    }
}
