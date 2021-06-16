/**
 * ajax请求配置
 */
import axios from 'axios'
import {ElMessage} from 'element-plus'
import store from '../store/'
import router from '../router/index'

// axios默认配置
axios.defaults.timeout = 10000 // 超时时间

// 整理数据

// 路由请求拦截
// http request 拦截器
axios.interceptors.request.use(
    config => {
        console.log(store)
        if (store.state.token) {
            if (config.url.indexOf('login') > -1) {
                localStorage.setItem('token', '')
                config.headers.Authorization = ''
            } else {
                config.headers.Authorization = store.state.token
                console.log("token==>", store.state.token)
            }
        }
        return config
    },
    error => {
        return Promise.reject(error.response)
    }
)

// 路由响应拦截
// http response 拦截器
axios.interceptors.response.use(
    response => {
        if (response.status) {
            console.log("地址=>", window.location.pathname)
            console.log("response==>", response)
            if (response.data.code == '401') {
                ElMessage.error('登录过期,请重新登录')
                router.replace({
                    path: 'login'
                })
            }
        }
        return response.data
    },
    error => {
        console.log(error)
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    // 返回 401 清除token信息并跳转到登录页面
                    this.$store.dispatch("del_token")
                    ElMessage.error('登录过期,请重新登录')
                    router.replace({
                        path: '/login'
                    })
                    break
                case 403:
                    if (error.response.data.msg) {
                        ElMessage.error(error.response.data.msg)
                    }
                    break
                case 404:
                    ElMessage.error('请求地址不存在')
                    break
                default:
                    ElMessage.error('请求失败')
                    break
            }
        }
        console.log('error', error.response)
        return Promise.reject(error.response) // 返回接口返回的错误信息
    }
)
export default axios
