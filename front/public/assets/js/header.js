

function init() {
    document.querySelector(".head-menu:first-child").addEventListener("mouseenter", (event)=>{
        document.getElementById("board-menu").setAttribute("style","display: block;");    
    });
    
    document.querySelector(".head-menu:first-child").addEventListener("mouseleave", (event)=> {
        document.getElementById("board-menu").setAttribute("style","display: none;");    
    });
    
    
    document.querySelector("#profile-li").addEventListener("mouseenter", (event)=>{
        document.getElementById("profile-menu").setAttribute("style","display: block;"); 
    });
    
    document.querySelector("#profile-li").addEventListener("mouseleave", (event)=> {
        document.getElementById("profile-menu").setAttribute("style","display: none;");    
    });
}
init();

// profile-li
// profile-menu




// document.querySelectorAll(".drop-down-btn").forEach((el)=>{
//     el.addEventListener('mouseenter', (event) => {
//         event.target.nextElementSibling.setAttribute("style","display: block;");
//     });
    
// })


// document.querySelectorAll(".drop-down-menu").forEach((el)=>{
//     el.addEventListener('mouseleave', (event) => {
//         event.target.children[1].setAttribute("style","display: none;");
//     })
// });


//id="user-profile"