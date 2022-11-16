
export default {
    init() {
        document.getElementById("background1").addEventListener("click", function(){
            document.getElementById("login-modal-form").setAttribute("style", "display: none");
            document.getElementById("background1").setAttribute("style", "display: none");
        });

        // TODO : 회원가입 누르면 본인과 본인의 백그라운드 안보이게 하고, 회원가입 컴포넌트 보이게 하기
        document.getElementById("do-regist-btn").addEventListener("click", function(){
            document.getElementById("regist-modal-form").setAttribute("style", "display: block");
            document.getElementById("background2").setAttribute("style", "display: block");
            document.getElementById("login-modal-form").setAttribute("style", "display: none");
            document.getElementById("background1").setAttribute("style", "display: none");
        });

        document.getElementById("do-find-user-btn").addEventListener("click", function(){
            document.getElementById("user-inquiry-div").setAttribute("style", "display: block");
            document.getElementById("background3").setAttribute("style", "display: block");
            document.getElementById("login-modal-form").setAttribute("style", "display: none");
            document.getElementById("background1").setAttribute("style", "display: none");
        });

        // // 로그인 요청
        // document.getElementById("do-login-btn").addEventListener("click", function(){

        // });


        // regist-modal-form
        // background2
        // user-inquiry-form
        
    },
    giveAnimation(){
        
    }
    
}

