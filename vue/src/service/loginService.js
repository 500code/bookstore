import request from '@/common/request'

let loginService={
    ckLogin(){
        return request("wl")
    }
};

export  default loginService;
