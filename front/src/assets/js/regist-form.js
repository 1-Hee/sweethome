export default {
    init() {
        // console.log("정상 init....");
        document.getElementById("background2").addEventListener("click", function(){
            document.getElementById("regist-modal-form").setAttribute("style", "display: none");
            document.getElementById("background2").setAttribute("style", "display: none");
        });
        // regist-modal-form
        // background2
    }
}