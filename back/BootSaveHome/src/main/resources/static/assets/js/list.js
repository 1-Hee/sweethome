function giveSelect(self){
    document.querySelectorAll(".page-tab>li>a").forEach((el)=>{
        el.parentNode.classList.remove('select');
    });
    self.parentNode.classList.add('select');
}
