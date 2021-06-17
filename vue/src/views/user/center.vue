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
                        <el-menu-item
                                index="2-3">
                            <router-link to="/login" style="width: 180px;display: block" @click="signOut">退出登录
                            </router-link>
                        </el-menu-item>
                    </el-submenu>
                </div>
            </el-menu>
        </el-header>
        <el-main>
            <div class="wrapper">
                <div class="container">
                    <el-card shadow="hover" style="height: 160px;">
                        <div class="user-info">
                            <img src="../../assets/img.png" class="user-avator" alt/>
                            <div class="user-info-cont ">
                                <div class="user-info-name"><span class="user-info-imp">{{ user.uname }}</span></div>
                                <div class="user-info-count ">借书总次数：<span class="user-info-imp">{{ user.ucount }}</span>
                                </div>
                                <div class="user-info-rep">信誉度：<span class="user-info-imp">{{ user.reputation }}</span>
                                </div>
                                <div class="user-info-rep">可借次数：<span class="user-info-imp">{{ user.num }}/5</span>
                                </div>
                            </div>
                        </div>
                    </el-card>
                    <el-table
                            :data="tableData"
                            border
                            stripe
                            class="table"
                            header-cell-class-name="table-header"
                    >
                        <el-table-column prop="bid" label="ID" width="55" align="center"></el-table-column>
                        <el-table-column prop="bname" label="书名" align="center"></el-table-column>
                        <el-table-column prop="author" label="作者" align="center"></el-table-column>
                        <el-table-column label="标签" align="center">
                            <template #default="scope">
                                <el-tag
                                        :key="tag"
                                        v-for="tag in scope.row.taglist"
                                        :disable-transitions="false">
                                    {{ tag }}
                                </el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="180" align="center">
                            <template #default="scope">
                                <el-button
                                        type="text"
                                        icon="el-icon-sell"
                                        class="red"
                                        @click="handleDelete(scope.$index, scope.row)"
                                >还书
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        name: "center",
        data() {
            return {
                tableData: [],
                user: '',
                uid: this.$store.state.uid || 1
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData() {
                let that = this;
                this.$http.get("http://localhost:8081/api/user/getBookByUid/" + that.uid)
                    .then(res => {
                        console.log(res)
                        that.tableData = res.data.books
                        that.user = res.data.user
                        for (let i = 0; i < res.data.books.length; i++) {
                            this.tableData[i].taglist = res.data.books[i].taglist.split(",")
                        }
                    })
            },
            // 删除操作
            handleDelete(index, row) {
                let that = this;
                // 二次确认删除
                this.$confirm("确定要还书吗？", "提示", {
                    type: "warning"
                })
                    .then(() => {
                        const data = new FormData();
                        data.append("bid", row.bid)
                        data.append("uid", this.user.uid)
                        this.$http.post("http://localhost:8081/api/returnBook", data).then(res => {
                            console.log(res)
                            if (res.code === "20000") {
                                that.$message.success("还书成功");
                                that.getData()
                            } else {
                                that.$message.error("还书失败");
                            }
                        })

                    })
                    .catch(() => {
                    });
            },
            //退出登录
            signOut() {
                this.$store.dispatch("del_token")
                // this.$router.push("/login")
            },
            toindex() {
                this.$router.push("/index")
            }
        }
    }
</script>

<style scoped>

    .wrapper {
        width: 1200px;
        margin: 0 auto;
    }

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

    /*中心*/
    .el-main {
        left: 0;
        background-color: #E9EEF3;
    }

    /*卡片*/

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-imp {
        font-size: 27px;
        color: #222;
    }

    .user-info {
        display: flex;
        align-items: center;
    }

    .user-info-cont {
        display: flex;
        /*padding-left: 50px;*/
        flex: 1;
        font-size: 14px;
        color: #999;
        justify-content: space-around;
    }


    .table {
        margin-top: 30px;
        width: 100%;
        font-size: 14px;
    }

    .red {
        color: #ff0000;
    }

    .el-tag {
        margin: 0 2px;
    }


</style>
