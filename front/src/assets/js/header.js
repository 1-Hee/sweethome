

export default {
    allMenuInit(){
        // 헤더에 메뉴 바 호버하면 메뉴 생기게 하기
        document.querySelector(".head-menu:first-child").addEventListener("mouseenter", (event)=>{
            document.getElementById("board-menu").setAttribute("style","display: block;");    
        });
        
        document.querySelector(".head-menu:first-child").addEventListener("mouseleave", (event)=> {
            document.getElementById("board-menu").setAttribute("style","display: none;");    
        });        
    },
    userProfileInit() {
        document.querySelector("#profile-li").addEventListener("mouseenter", (event)=>{
            document.getElementById("profile-menu").setAttribute("style","display: block;"); 
        });
        
        document.querySelector("#profile-li").addEventListener("mouseleave", (event)=> {
            document.getElementById("profile-menu").setAttribute("style","display: none;");    
        });
        
    },
    setLoginModal(){
        // 헤더의 로그인 버튼 누르면 로그인 컴포넌트가 보이게 하기
        document.getElementById("do-login-btn").addEventListener("click", function(){
            document.getElementById("login-modal-form").setAttribute("style", "display: block;");
            document.getElementById("background1").setAttribute("style", "display: block;");
            // alert("눌렀니?");
        });        
    },
}


