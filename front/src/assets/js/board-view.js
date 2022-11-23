export default {
  init() {
    document.getElementById("modify-btn").addEventListener("click", function () {
      document.getElementById("view").setAttribute("style", "display:none;");
      document.getElementById("modiform").setAttribute("style", "display:block;");
      document.getElementById("modify-btn").setAttribute("style", "display:none;");
      document.getElementById("modify-cancel-btn").setAttribute("style", "display:block;");
    });

    document.getElementById("modify-cancel-btn").addEventListener("click", function () {
      document.getElementById("modiform").setAttribute("style", "display:none;");
      document.getElementById("view").setAttribute("style", "display:block;");
      document.getElementById("modify-btn").setAttribute("style", "display:block;");
      document.getElementById("modify-cancel-btn").setAttribute("style", "display:none;");
    });
  },
};

// document.getElementById("delete-btn", new function(){

// });

// view

// modiform
