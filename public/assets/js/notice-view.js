
fetch(`http://localhost:8080/board/notice/view/${localStorage.getItem("articleNo")}`)
.then((response) => response.json())
.then((data) => {        	
    //console.dir(data);
    fillNotice(data);
});


function fillNotice(data){
    document.getElementById("title").textContent = `${data.title}`;
    document.getElementById("writer").textContent = `${data.userId}`
    document.getElementById("date").textContent = `${data.registDate}`
    document.getElementById("hit").textContent = `(${data.hit})`
    document.getElementById("content").textContent = `${data.content}`

    document.getElementById("articleNoHidden").value = `${data.articleNo}`
    document.getElementById("mtitle").value = `${data.title}`;
    document.getElementById("mwriter").textContent = `${data.userId}`
    document.getElementById("mdate").textContent = `${data.registDate}`
    document.getElementById("mhit").textContent = `(${data.hit})`
    document.getElementById("mcontent").value = `${data.content}`

}

function deleteAlert(data){
    if(confirm("정말로 삭제하시겠습니까?")){
        fetch(`http://localhost:8080/board/notice/delete/${data}`)
        .then((response) => response.json())
        .then((data) => {        	
            //console.dir(data);
            localStorage.removeItem('articleNo');
        });
        location.href='http://localhost:8080/notice/list';
    }
}

