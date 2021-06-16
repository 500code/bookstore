<template>
    <div>
        <div class="container">
            <div class="crumbs">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item>
                        <i class="el-icon-menu"></i> 借还日志
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
                    :default-sort="{prop: 'count', order: 'descending'}"
                    border
                    stripe
                    class="table"
                    ref="multipleTable"
                    @selection-change="handleSelectionChange"
            >
                <el-table-column prop="id" label="日志编号" width="130" align="center"></el-table-column>
                <el-table-column prop="ldate" label="日期" sortable width="180" align="center"></el-table-column>
                <el-table-column prop="uid" label="用户ID" width="130" align="center"></el-table-column>
                <el-table-column prop="uname" label="用户名" align="center"></el-table-column>
                <el-table-column prop="bid" label="书籍ID" width="130" align="center"></el-table-column>
                <el-table-column prop="bname" label="书名" align="center"></el-table-column>
                <el-table-column prop="reputation" label="信誉值" align="center"></el-table-column>
                <el-table-column label="操作" align="center">
                    <template #default="scope">
                        <el-tag :type="[scope.row.log=='借书'?'success':'warning']">{{ scope.row.log }}</el-tag>
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
        name: "log",
        data() {
            return {
                query: {
                    address: "",
                    name: "",
                    pageIndex: 1,
                    pageSize: 10
                },
                tableData: [
                    {
                        bid: 1,   //编号
                        bname: 'java',
                        tagList: ['科学', '计算机'],
                        state: '3/5',
                        log: '还书',
                        date: '2016-05-02'
                    }
                ],
                pno: 1,
                multipleSelection: [],
                delList: [],
                editVisible: false,
                pageTotal: 0,
                form: {},
                idx: -1,
                id: -1,
            }
        },
//   },
        created() {
            this.getData();
        },
        computed: {
            tag(log) {
                console.log(log)
                return log == '还书' ? 'success' : 'warning'
            }
        },
        methods: {
//      获取 easy-mock 的模拟数据
            getData() {
                let that = this
                this.$http.get("http://localhost:8081/api/log?pno=" + this.pno)
                    .then(res => {
                        if (res.code == 20000) {
                            that.tableData = res.data.list.records
                            that.query.pageIndex = Number(res.data.list.current)  //当前是第几页
                            that.pageTotal = Number(res.data.list.total) //总共条数
                            that.query.pageSize = Number(res.data.list.size)//每页多少条
                            for (let i = 0; i < res.data.list.records.length; i++) {
                                if (res.data.list.records[i].operating == 1) {
                                    that.tableData[i].log = "借书"
                                } else {
                                    that.tableData[i].log = "还书"
                                }
                            }
                            console.log(res)
                        } else {
                            ElMessage.error("暂无数据");
                        }
                    })
            },
            // 触发搜索按钮
            handleSearch() {
                this.search();
            },

            search() {
                let that = this;
                console.log(that.key)
                that.$http.get("http://localhost:8081/api/searchLog?key=" + this.query.name + "&pno=" + this.pno)
                    .then(res => {
                        console.log(res)
                        console.log(this.query.name)
                        if (res.code == 20000) {
                            that.tableData = res.data.list.records
                            that.query.pageIndex = Number(res.data.list.current)  //当前是第几页
                            that.pageTotal = Number(res.data.list.total) //总共条数
                            that.query.pageSize = Number(res.data.list.size)//每页多少条
                            for (let i = 0; i < res.data.list.records.length; i++) {
                                if (res.data.list.records[i].operating == 1) {
                                    that.tableData[i].log = "借书"
                                } else {
                                    that.tableData[i].log = "还书"
                                }
                            }
                        } else {
                            ElMessage.error("未搜索到");
                        }
                        console.log(this.query)
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
