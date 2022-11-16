


// fetch(`http://localhost:8080/board/notice/list`)
// .then((response) => response.json())
// .then((data) => {        	
//     makeNoticeList(data);          
// });


// function makeNoticeList(data = "") {
//     const table = document.getElementById("table");
//     table.innerHTML = ``;

//     let thead = document.createElement("thead");
//     let headTr = document.createElement("tr");
//     let ThNum = document.createElement("th");
//     ThNum.appendChild(document.createTextNode("번호"));
//     let ThTitle = document.createElement("th");
//     ThTitle.appendChild(document.createTextNode("제목"));
//     let ThWriter = document.createElement("th");
//     ThWriter.appendChild(document.createTextNode("작성자"));
//     let ThHit = document.createElement("th");
//     ThHit.appendChild(document.createTextNode("조회수"));
// //    let ThDate = document.createElement("th");
// //    ThDate.appendChild(document.createTextNode("작성일"));
    
//     headTr.appendChild(ThNum);
//     headTr.appendChild(ThTitle);
//     headTr.appendChild(ThWriter);
//     headTr.appendChild(ThHit);
// //    headTr.appendChild(ThDate);
//     thead.appendChild(headTr)    
//     table.appendChild(thead);

//     data.forEach((item) => {
//     	let tbody = document.createElement("tbody");
//         let Tr = document.createElement("tr");
//         let Num = document.createElement("td");
//         Num.appendChild(document.createTextNode(`${item.articleNo}`));
//         let Title = document.createElement("td");
//         Title.appendChild(document.createTextNode(`${item.title}`));
//         let Writer = document.createElement("td");
//         Writer.appendChild(document.createTextNode(`${item.userId}`));
//         let Hit = document.createElement("td");
//         Hit.appendChild(document.createTextNode(`${item.hit}`));
// //        let Date = document.createElement("td");
// //        Date.appendChild(document.createTextNode(`${item.registDate}`));
    
//         Tr.appendChild(Num);
//         Tr.appendChild(Title);
//         Tr.appendChild(Writer);
//         Tr.appendChild(Hit);
// //        Tr.appendChild(Date);    
        
//         Tr.setAttribute("onclick", `hitArticleNo(${item.articleNo},${item.hit})`);        
        
//         tbody.appendChild(Tr);
//         table.appendChild(tbody);
        
//     })



// }

// function hitArticleNo(no, hitNo){
//     localStorage.setItem("articleNo", no);    
//     fetch(`http://localhost:8080/board/notice/hit?articleNo=${no}&hit=${hitNo}`)
//     .then((response) => response.json())
//     .then((data) => {        	
//     		console.log(data);
//     });
    
//     location.href='http://localhost:8080/notice/view';
// }


// function writeNotice() {
//     location.href='http://localhost:8080/notice/write';
// }