<template>
    <el-container>
        <el-header>
            <el-menu
                    class="el-menu-demo"
                    mode="horizontal"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b">
                <div class="lay head">

                    <el-menu-item index="1" @click="toindex"><i class="el-icon-s-home icon"></i>首页</el-menu-item>
                    <el-menu-item index="2"><i class="el-icon-s-data icon"></i> 排行</el-menu-item>
                    <el-menu-item index="3"><i class="el-icon-edit icon"></i>借书流程</el-menu-item>
                    <el-menu-item index="4"><i class="el-icon-question icon"></i>帮助</el-menu-item>
                    <el-menu-item index="5"><i class="el-icon-bell icon"></i>消息</el-menu-item>
                    <el-submenu index="6">
                        <template #title><i class="el-icon-user icon"></i>用户</template>
                        <el-menu-item index="2-1">
                            <router-link to="/center" style="width: 180px;display: block">个人中心</router-link>
                        </el-menu-item>
                        <el-menu-item index="2-3">
                            <router-link to="/login" style="width: 180px;display: block" @click="signOut">退出登录
                            </router-link>
                        </el-menu-item>
                    </el-submenu>
                </div>
            </el-menu>
        </el-header>
<!--        <el-image-->
<!--                style="width: 100%; height:100%"-->
<!--                :src="item.src"-->
<!--        ></el-image>-->
        <el-main>
            <div class="lay" >
                <div class="scroll" >
                    <el-carousel height="350px">
                        <el-carousel-item  v-for="item in imgList" :key="item">
                              <span style="line-height: 350px;font-size: 70px">广告位待租...</span>
                        </el-carousel-item>
                    </el-carousel>
                </div>

                <div class="recommend">
                    <span>新书推荐</span>
                    <el-divider></el-divider>
                </div>

                <el-scrollbar :view-style="act" @mouseover="out" @mouseout="scroll(),test()">
                    <el-row :gutter="10" class="scrollBox">
                        <el-col :span="6" v-for="item in table" :key="item">
                            <el-card :body-style="{padding: '0px' }">
                                <img :src="require('../../assets/booksupload/' + item.pic)" class="image">
                                <div style="padding: 14px;color: #999;">
                                    书名：<span style="font-weight: normal;color:black">{{item.bname}}</span>
                                    <br>
                                    作者：<span style="font-weight: normal;color:black">{{item.author}}</span>
                                    <div class="bottom">
                                        <el-button type="text" @click="borrowBooks(item.bid)" class="button">借书
                                        </el-button>
                                    </div>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-scrollbar>

                <div class="recommend">
                    <span>热门图书</span>
                    <el-divider></el-divider>
                </div>
                <el-row :gutter="10">
                    <el-col :span="6" v-for="item in tableData" :key="item">
                        <el-card :body-style="{padding: '0px' }">
                            <img :src="require('../../assets/booksupload/' + item.pic)" class="image">
                            <div style="padding: 14px;color: #999;">
                                书名：<span style="font-weight: normal;color:black">{{item.bname}}</span>
                                <br>
                                作者：<span style="font-weight: normal;color:black">{{item.author}}</span>
                                <div class="bottom">
                                    <el-button type="text" @click="borrowBooks(item.bid)" class="button">借书</el-button>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <div class="page">
                        <!--                :hide-on-single-page="value"-->
                        <el-pagination
                                background
                                layout="total, prev, pager, next"
                                :current-page="query.pageIndex"
                                :page-size="query.pageSize"
                                :total="query.pageTotal"
                                @current-change="handlePageChange"
                        ></el-pagination>
                    </div>
                </el-row>


                <div class="recommend">
                    <span>借书流程</span>
                    <el-divider></el-divider>
                    <div style="height: 300px;">
                        <el-steps :active="active" finish-status="success">
                            <el-step title="步骤 1" description="先这样"></el-step>
                            <el-step title="步骤 2" description="再这样"></el-step>
                            <el-step title="步骤 3" description="最后这样"></el-step>
                        </el-steps>
                        <div class="next">
                            <el-button @click="next">下一步</el-button>
                        </div>
                    </div>
                </div>

                <div class="about">2021期末作品©图书管理系统</div>
            </div>
        </el-main>
    </el-container>
</template>
<script>
    import {ElMessage} from "element-plus";

    export default {

        data() {
            return {
                query: {
                    pageIndex: 1,  //当前页码
                    pageTotal: 0,   //总共条数
                    pageSize: 4,   //每页多少条
                },
                pno: 1,
                x: 0,  //图片的滚动
                a: 0,   //滚动条的滚动
                time: '', //图片的定时器
                time2: '', //滚动条的定时器
                act: '',
                active: 0,
                // bar:'',  //滚动条
                tableData: '',//热门书籍容器
                table: '',//新书推荐容器
                imgList: [
                    {
                        src: require('../../assets/img.png')
                    },
                    {
                        src: require('../../assets/img.png')
                    },
                    {
                        src: require('../../assets/img.png')
                    },
                    {
                        src: require('../../assets/img.png')
                    },
                    {
                        src: require('../../assets/img.png')
                    },
                    {
                        src: require('../../assets/img.png')
                    }
                ],
                url: '../../assets/booksupload/'
            }
        },
        created() {
            // this.test()
            this.scroll()
            this.getData()
        },
        mounted() {
            this.bar = document.querySelector('.el-scrollbar__thumb')
            this.bar.style.width = '50%'
            console.log(this.bar)
        },
        methods: {
            scroll() {
                console.log(this.src = "0d4e2e2e36784a4c885036ade2aadc7f.jpg")
                this.time = setInterval(() => {
                    if (this.x < -1200) {
                        this.x = 0;
                    }
                    this.act = 'transform: translateX(' + this.x-- + 'px)'
                }, 10)
            },
            out() {
                clearInterval(this.time2);
                this.bar.style.transform = 'translateX(' + this.a++ + 'px)'
                console.log(this.bar)
                clearInterval(this.time);
            },
            test() {
                this.time2 = setInterval(() => {
                    if (this.a > 600) {
                        this.a = 0;
                    }
                    this.bar.style.transform = 'translateX(' + this.a++ + 'px)'
                }, 20)
            },
            next() {
                if (this.active++ > 2) this.active = 0;
            },
            //退出登录
            signOut() {
                this.$store.dispatch("del_token")
            },
            getData() {
                let that = this;
                this.$http.get("http://localhost:8081/api/index/popularBooks?pno=" + that.pno)
                    .then(res => {
                        console.log(res)
                        if (res.code == 20000) {
                            that.tableData = res.data.list.records
                            console.log(that.tableData)
                            that.query.pageIndex = Number(res.data.list.current)  //当前是第几页
                            that.query.pageTotal = Number(res.data.list.total) //总共条数
                            that.query.pageSize = Number(res.data.list.size)//每页多少条
                        } else {
                            ElMessage.error("暂无数据")
                        }
                        console.log(this.query)
                    })
                    ,
                    that.$http.get("http://localhost:8081/api/index/newBooks")
                        .then(res => {
                            console.log("新书==", res)
                            if (res.code == 20000) {
                                that.table = res.data.list
                            } else {
                                ElMessage.error("借书失败")
                            }
                            console.log(this.query)
                        })
            },
            //借书事件
            borrowBooks(e) {
                console.log(e)
                console.log(this.$store.state.uid);
                let that = this
                if (!this.$store.state.uid) {
                    ElMessage.error("还没登录，请登录")
                    that.$router.push("/login")
                    return;
                }
                that.$http.get("http://localhost:8081/api/getBook?uid=" + that.$store.state.uid + "&bid=" + e)
                    .then(res => {
                        console.log(res)
                        if (res.code == 20000) {
                            ElMessage.success("借书成功")
                        } else {
                            ElMessage.error("借书失败")
                        }
                        console.log(this.query)
                    })
            },
            //分页导航
            handlePageChange(val) {
                this.pno = val
                this.getData();

            },
            toindex(){
                this.$router.push("/index")
            }

        },


    }
</script>

<style>
    .el-header {
        background-color: #545c64;
        color: #333;
        text-align: center;
    }

    .head {
        display: flex;
        justify-content: space-between;
    }

    .lay {
        width: 1200px;
        margin: 0 auto;
    }

    .el-menu-item, .el-submenu {
        width: 200px;
        height: 70px;
        line-height: 70px;

    }

    .el-submenu {
        margin: auto 0;
    }

    .el-header .icon {
        color: #fff;
    }

    .el-menu.el-menu--horizontal {
        border: 0;
    }

    .el-main {
        left: 0;
        background-color: #E9EEF3;
        text-align: center;
    }

    .el-submenu__title {
        height: 70px;
        line-height: 70px;
    }

    .lay .scroll {
        width: 1200px;
    }

    .recommend {
        margin-top: 40px;
        text-align: left;
    }

    .recommend span {
        /*border: red solid 1px;*/
        font-size: 30px;
        font-weight: 550;
    }

    /*新书推荐*/
    .el-card {
        margin-bottom: 20px;
        height: 450px;
    }

    .scrollBox {
        display: flex;
        flex-wrap: nowrap;
    }


    .image {
        height: 350px;
    }

    .page {
        display: flex;
        justify-content: flex-end;
        width: 100%;
    }

    .next {
        display: flex;
        justify-content: flex-end;
        margin-top: 20px;
    }

    .recommend span {
        font-size: 20px !important;
    }
</style>
