/* <div id="filter" class="background"></div>
<div id="modal" class="modi-info-modal"> */


export default {
  init() {
    document.getElementById("modi-close-btn").addEventListener("click",function(){
      document.getElementById("modal").setAttribute("style", "display: none;");
      document.getElementById("filter").setAttribute("style", "display: none;");
    });
    document.getElementById("modishow").addEventListener("click", function(){
      document.getElementById("modal").setAttribute("style", "display: ;");
      document.getElementById("filter").setAttribute("style", "display: ;");
    });
  }
}



