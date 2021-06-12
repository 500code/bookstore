import axios from "axios";
import server from "./server";

let request = function (url, params, type) {
//    1、处理token 放到请求头中
    let token = "wlwl";
    let headers = {'token': token}

//    2、组装异步请求的参数
    let axiosObj = {
        method: type || "get",   //设置默认值为get
        url: server.url + url,
        headers
    }

//  3、处理post请求参数的问题
    if (type && type.toUpperCase() == 'POST') {
        headers["content-type"] = "application/x-www-form-urlencoded";
        axiosObj.data = params;
    } else {
        //    get请求传递参数
        axiosObj.params = params;
    }

//    4、返回参数
    return axios(axiosObj)
}
export default request;
