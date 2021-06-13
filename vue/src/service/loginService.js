import http from '@/axios/http'

let loginService = {
    ckLogin() {
        http.post("http://localhost:8081/api/user/login", {
            uname: '肥宝',
            upwd: '123123'
        })
            .then(res => {
                console.log(res)
            })
    }
};

export default loginService;
