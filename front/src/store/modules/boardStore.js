
import { getBoardList,  writeBoard, getBoard, modifyBoard, deleteBoard } from "@/api/board";

const userStore = {

    namespaced: true,
    state: {
        BoardList : [],
        Board : {},
    },
    getters : {
        // getBoardList(state) {
        //     return state.BoardList;
        // },
        // getBoard(state) {
        //     return state.Board;
        // },
    },
    actions: { 
        searchBoardList(param){
            getBoardList(param,  
            ({ data }) => {
                localStorage.setItem("board", JSON.stringify(data)); // 이부분 store(vuex) 로 바꿀 것.
            },
              (err) => {
                console.log(err);
              })
        },
        searchBoard(){

        },
        addBoard() {

        },
        modifyBoard(){

        },
        removeBroad(){
            
        }

    },
    mutations: { 

    },


}

export default userStore;