import axios from "axios";

axios.defaults.baseURL="htpp://localhost:8080"
axios.defaults.headers.post["Content-type"]='application/json'

export const request = (method,url,data) =>{
    return axios({
        method: method,
        url: url,
        data: data
    })
};