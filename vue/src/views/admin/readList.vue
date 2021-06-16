<template>
    <div>
        <div class="container">
            <div class="crumbs">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item>
                        <i class="el-icon-menu"></i> 书籍借阅排行
                    </el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <el-col :offset="15">
                <div class="handle-box">
                    <el-input v-model="query.name" placeholder="书名" class="handle-input mr10"></el-input>
                    <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                </div>
            </el-col>
            <el-table
                    :data="tableData"
                    border
                    stripe
                    class="table"
                    ref="multipleTable"
                    @selection-change="handleSelectionChange"
            >
                <el-table-column width="50" align="center" label="排名">
                    <template #default="scope">
                        <span>{{(scope.$index+1)+(pno-1)*5}}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="bid" label="ID" width="130" align="center"></el-table-column>
                <el-table-column prop="bname" label="书名" align="center"></el-table-column>

                <el-table-column prop="author" label="作者" align="center"></el-table-column>
                <el-table-column prop="count" label="借次数" align="center"></el-table-column>
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
            </el-table>

            <div class="pagination">
                <el-pagination
                        background
                        layout="total, prev, pager, next"
                        :current-page="query.pageIndex"
                        :page-size="query.pageSize"
                        :total="pageTotal"
                        @current-change="handlePageChange"
                ></el-pagination>
            </div>

        </div>

    </div>
</template>

<script>


    import {ElMessage} from "element-plus";

    export default {
        name: "readList",
        data() {
            return {
                query: {
                    address: "",
                    name: "",
                    pageIndex: 1,
                    pageSize: 5
                },
                tableData: [
                    {
                        bid: 1,   //编号
                        bname: 'java',
                        count: 1001,
                        taglist: ['科学', '计算机'],
                        author: '拉德斯基'
                    },
                ],
                pno: 1,
                multipleSelection: [],
                delList: [],
                editVisible: false,
                pageTotal: 0,
                form: {},
                idx: -1,
                id: -1
            }
        },

        created() {
            this.getData();
        },
        computed: {
            bb(index) {
                return index + (this.pno - 1) * 5
            }
        },
        methods: {
            getData() {
                let that = this;
                that.$http.get("http://localhost:8081/api/bookRanking?pno=" + that.pno)
                    .then(res => {
                        console.log(res)
                        if (res.code == 20000){
                            that.tableData = res.data.list.records
                            that.query.pageIndex = Number(res.data.list.current)  //当前是第几页
                            that.pageTotal = Number(res.data.list.total) //总共条数
                            that.query.pageSize = Number(res.data.list.size)//每页多少条
                            for (let i = 0; i < res.data.list.records.length; i++) {
                                this.tableData[i].taglist = res.data.list.records[i].taglist.split(",")
                            }
                        }else {
                            ElMessage.error("暂无数据");
                        }
                    })

            },
            // 分页导航
            handlePageChange(val) {
                this.pno = val
                if (this.query.name == "") {
                    this.getData();
                } else {
                    this.search()
                }
            },
            handleSearch() {
                this.search()
            },
            search() {
                let that = this;
                console.log(that.key)
                that.$http.get("http://localhost:8081/api/search?key=" + this.query.name + "&pno=" + this.pno)
                    .then(res => {
                        console.log(res)
                        if (res.code == 20000) {
                            that.tableData = res.data.list
                            console.log(that.tableData)
                            that.tableData = res.data.list.records
                            that.query.pageIndex = Number(res.data.list.current)  //当前是第几页
                            that.pageTotal = Number(res.data.list.total) //总共条数
                            that.query.pageSize = Number(res.data.list.size)//每页多少条
                            for (let i = 0; i < res.data.list.records.length; i++) {
                                this.tableData[i].taglist = res.data.list.records[i].taglist.split(",")
                            }
                        }else {
                            ElMessage.error("未搜索到");
                        }
                        console.log(this.query)
                    })
            }
        }
    };
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }

    .table {
        width: 100%;
        font-size: 14px;
    }

    .mr10 {
        margin-right: 10px;
    }

    .el-tag {
        margin: 0 2px;
    }
</style>
