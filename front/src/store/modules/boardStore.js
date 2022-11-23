import { selectBoardList, selectBoard, createBoard, 
  updateBoard, deleteBoard, selectTop4BoardList  } from "@/api/board";

const userStore = {
  namespaced: true,
  state: {
    BoardList: [],
    Top4BoardList : [],
    Board: {},
    pgInfo: {},
  },
  getters: {
    getBoardList(state) {
      return state.BoardList;
    },
    getBoard(state) {
      return state.Board;
    },
    getPgInfo(state) {
      return state.pgInfo;
    },
    getTop4BoardList(state){
      return state.Top4BoardList;
    }
  },
  actions: {
    setBoardList(context, params) {
      selectBoardList(
        params,
        ({ data }) => {
          context.commit("SET_BOARD_LIST", data);
        },
        (err) => {
          console.log(err);
        }
      );
    },

    setBoard(context, articleNo) {
      selectBoard(
        articleNo,
        ({ data }) => {
          context.commit("SET_BOARD", data);
        },
        (err) => {
          console.log(err);
        }
      );
    },

    addBoard({ commit }, board) {
      createBoard(
        board,
        ({ data }) => {
          console.log(data);
          commit("ADD_BOARD", board);
        },
        (err) => {
          console.log(err);
        }
      );
    },

    modifyBoard(context, board) {
      updateBoard(
        board,
        ({ data }) => {
          console.log(data);
          context.commit("MODIFY_BOARD", board);
        },
        (err) => {
          console.log(err);
        }
      );
    },

    removeBoard(context, articleNo) {
      deleteBoard(
        articleNo,
        ({ data }) => {
          console.log(data);
          context.commit("REMOVE_BOARD");
        },
        (err) => {
          console.log(err);
        }
      );
    },

    // 상위 4개 글을 불러오는 메서드
    setFourBoardList(context){
      selectTop4BoardList(
        ({data}) => { 
          // console.dir(data);
          context.commit("SET_TOP4_BOARD_LIST", data);
        },
        (err) => {console.log(err)}
      )

    },
  },
  mutations: {
    SET_BOARD_LIST(state, data) {
      state.BoardList = data.list;
      state.pgInfo = data;
    },

    SET_BOARD(state, board) {
      state.Board = board;
    },

    ADD_BOARD(state, board) {
      state.Board = board;
      state.BoardList.push(board);
    },

    MODIFY_BOARD(state, board) {
      state.Board = board;
    },

    REMOVE_BOARD(state) {
      state.board = null;
    },

    // 상위 4개글
    SET_TOP4_BOARD_LIST(state, data){
      state.Top4BoardList = data;
    },
  },
};

export default userStore;
