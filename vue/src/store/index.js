import {createStore} from 'vuex'


export default createStore({
    state() {
        return {
            token: localStorage.getItem('token') || "",
            uid: localStorage.getItem('uid') || ""
        }
    },
    mutations: {
        set_token(state, token) { // 第一个参数是拿到state对象
            localStorage.setItem('token', token.token)
            localStorage.setItem('uid', token.uid)
            state.token = token.token
            state.uid = token.uid
        },
        del_token(state) {
            localStorage.removeItem('token')
            localStorage.removeItem('uid')
            state.token = ""
            state.uid = ""
            console.log("我是汉奸=》》", localStorage.removeItem('token'),
                localStorage.removeItem('uid'), state.token,
                state.uid)
        }
    },
    actions: {
        set_token(context, token) {
            context.commit('set_token', token)
        },
        del_token(context) {
            context.commit('del_token')
        }
    }
})
