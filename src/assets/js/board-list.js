
export default {
    init() {
        let commons = document.querySelectorAll(".pg-common");
        document.querySelectorAll(".pg-common").forEach((el)=>{
            el.addEventListener("click", function(){
                document.querySelectorAll(".pg-common").forEach((el) => {el.classList.remove('select')} );
                el.classList.add('select');
            });    
        });
        // console.dir(commons);
    }
}

