
import { selectNoticeList,  createNotice, selectNotice, updateNotice, deleteNotice} from "@/api/notice";


const noticeStore = {

    namespaced: true,
    state: {
        NoticeList : [],
        Notice : {},
        pgInfo : {},
    },
    getters : {
        getNoticeList(state){
            return state.NoticeList;
        },
        getNotice(state) {
            return state.Notice;
        },
        getPgInfo(state){
            return state.pgInfo;
        },
    },
    actions: { 

        setNoticeList(context, params){     
            selectNoticeList(params,
                ({data})=> {
                    context.commit("SET_NOTICE_LIST", data);
                },
                (err)=> {
                    console.log(err);
                });
        },

        setNotice(context, articleNo){            
            selectNotice(articleNo,
                ({data})=>{
                    context.commit("SET_NOTICE", data);
                },
                (err)=>{
                    console.log(err);
                });
        },
        
        addNotice({commit}, notice){
            createNotice(
                notice,
                ({data})=>{
                    console.log(data);
                    commit("ADD_NOTICE", notice);
                },
                (err)=>{console.log(err);}
            );
        },

        modifyNotice(context, notice){
            updateNotice(
                notice,
            ({data})=>{
                console.log(data);
                context.commit("MODIFY_NOTICE", notice);
            },
            (err)=>{console.log(err);}
            );

        },

        removeNotice(context, articleNo){
            deleteNotice(
                articleNo,
                ({data})=>{
                    console.log(data);
                    context.commit("REMOVE_NOTICE");
                },
                (err)=>{console.log(err);}
            );
        },

    },
    mutations: { 

        SET_NOTICE_LIST(state, data){        
            state.NoticeList = data.list;
            state.pgInfo = data;
        },

        SET_NOTICE(state, notice){
            state.Notice= notice;           
        },
        
        ADD_NOTICE(state, notice){
            state.Notice=notice;
            state.NoticeList.push(notice);           
        },

        MODIFY_NOTICE(state, notice){
           state.Notice = notice;
        },

        REMOVE_NOTICE(state){
            state.notice = null;
        },

    },


}

export default noticeStore;