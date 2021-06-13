import {createStore} from 'vuex'


export default createStore({
    state:
        localStorage.getItem('state')
            ? JSON.parse(localStorage.getItem('state'))
            : {
                token: '',
            },

    mutations: {
        set_token(state, ltoken) { // 第一个参数是拿到state对象
            localStorage.setItem('token', ltoken)
            state.token = ltoken
        },
        del_token(state) {
            localStorage.removeItem('token')
            state.token = ''
        }
    },
    actions: {
        set_token(context, token) {
            context.commit('set_token', token)
        }
        ,
        del_token(context) {
            context.commit('del_token')
        }
        ,
    }
})
