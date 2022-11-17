// import jwtDecode from "jwt-decode";
// import router from "@/router";

const userStore = {

    namespaced: true,
    state: {
        loginUser : {},        

    },
    getters : {
        checkUserInfo: function (state) {
            return state.loginUser;
        },
    },
    actions: { 

    },
    mutations: { 

    },


}

export default userStore;