import {createRouter, createWebHistory} from 'vue-router'
import index from '../views/admin/index'

const routes = [
    {
        path: '/',
        redirect: {name: 'index'}
    },
    {
        path: '/index',
        name: "index",
        component: () => import("../views/user/index")
    },  {
        path: '/test',
        name: "test",
        component: () => import("../views/user/test")
    },
    {
        path: '/login',
        name: "login",
        component: () => import("../views/user/login")
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
                component: () => import("../views/admin/home")
            },
            {
                path: '/admin/bookList',
                name: 'admin-bookList',
                component: () => import("../views/admin/bookList")
            },
            {
                path: '/admin/addBook',
                name: 'admin-addBook',
                component: () => import("../views/admin/addBook")
            },
            {
                path: '/admin/userReadList',
                name: 'admin-userReadList',
                component: () => import("../views/admin/userReadList")
            },
            {
                path: '/admin/readList',
                name: 'admin-readList',
                component: () => import("../views/admin/readList")
            },
            {
                path: '/admin/log',
                name: 'admin-log',
                component: () => import("../views/admin/log")
            },
            {
                path: '/admin/about',
                name: 'admin-about',
                component: () => import("../views/admin/about")
            }
        ]
    },
    {
        path: '/admin/login',
        name: '/admin/login',
        component: () => import(/* webpackChunkName: "about" */ '../views/admin/login.vue')
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
