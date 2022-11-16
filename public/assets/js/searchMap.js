
function searchWithKeyWord(value) {
    if (window.event.keyCode == 13) {
    	// 엔터키가 눌렸을 때
        location.href="http://localhost:8080/map/gomap"
        localStorage.setItem("keyword", value);    
    }
}