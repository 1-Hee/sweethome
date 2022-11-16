export default {
    init() {
        document.getElementById("background3").addEventListener("click", function(){
            document.getElementById("user-inquiry-div").setAttribute("style", "display: none");
            document.getElementById("background3").setAttribute("style", "display: none");
        });
    }
}