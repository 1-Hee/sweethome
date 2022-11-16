import axios from 'axios';

export default{

    async doAxios(API_URL, method){
        let json = null;
        let data = null;
        json = await axios({
            url : API_URL,
            method : method
        }).then(({data})=>{
            // json = data;
            // console.dir(data);
        });

        return json;
    },

}