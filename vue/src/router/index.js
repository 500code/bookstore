import {createRouter, createWebHistory} from 'vue-router'
import index from '../views/admin/index'
import {ElMessage} from "element-plus";

const routes = [
    {
        path: '/',
        redirect: {name: 'index'}
    },
    {
        path: '/index',
        name: "index",
        component: () => import("../views/user/index")
    }, {
        path: '/test',
        name: "test",
        component: () => import("../views/user/test"),
        meta: {requiresAuth: true}
    },
    {
        path: '/login',
        name: "login",
        component: () => import("../views/user/login")
    },
    {
        path: '/center',
        name: "center",
        component: () => import("../views/user/center"),
        meta: {requiresAuth: true}
    },
    {
        path: '/register',
        name: "register",
        component: () => import("../views/user/register")
    },
    {
        path: '/admin',
        redirect: {name: 'admin-home'}
    },
    {
        path: '/admin/index',
        name: 'admin',
        component: index,
        redirect: {name: 'admin-home'},
        children: [
            {
                path: '/admin/home',
                name: 'admin-home',
                component: () => import("../views/admin/home"),
                meta: {requiresAuth: true}
            },
            {
                path: '/admin/bookList',
                name: 'admin-bookList',
                component: () => import("../views/admin/bookList"),
                meta: {requiresAuth: true}

            },
            {
                path: '/admin/addBook',
                name: 'admin-addBook',
                component: () => import("../views/admin/addBook"),
                meta: {requiresAuth: true}

            },
            {
                path: '/admin/userReadList',
                name: 'admin-userReadList',
                component: () => import("../views/admin/userReadList"),
                meta: {requiresAuth: true}

            },
            {
                path: '/admin/readList',
                name: 'admin-readList',
                component: () => import("../views/admin/readList"),
                meta: {requiresAuth: true}

            },
            {
                path: '/admin/log',
                name: 'admin-log',
                component: () => import("../views/admin/log"),
                meta: {requiresAuth: true}

            },
            {
                path: '/admin/about',
                name: 'admin-about',
                component: () => import("../views/admin/about"),
                meta: {requiresAuth: true}

            }
        ]
    },
    {
        path: '/admin/login',
        name: '/admin/login',
        component: () => import('../views/admin/login.vue')
    },
    {
        path: '/:pathMatch(.*)*',
        name: '404',
        component: () => import('../views/404')
    }

]

const router = createRouter({
    mode: 'history',
    history: createWebHistory(),
    routes
})

export default router

router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth) { // 判断该路由是否需要登录权限
        if (to.path.indexOf("admin") > -1) { //后台
            if (sessionStorage.getItem("aid")) {
                next()  //本地缓存有aid就放行
            } else {
                // 未登录,跳转到登陆页面
                console.log("给我滚", to)
                ElMessage.error("请重新登录")
                next({
                    path: '/admin/login'
                })
            }
        } else {  //前台
            if (localStorage.getItem("token")) { // 判断本地是否存在token
                next()
            } else {
                // 未登录,跳转到登陆页面
                console.log("给我滚", to)
                ElMessage.error("请重新登录")
                next({
                    path: '/login'
                })
            }

        }
    } else {
        next();
    }
});
