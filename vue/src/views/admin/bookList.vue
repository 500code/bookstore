<template>
    <div>
        <div class="container">
            <div class="crumbs">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item>
                        <i class="el-icon-menu"></i> 基础表格
                    </el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <el-col :offset="15">
                <div class="handle-box">
                    <el-input v-model="key" placeholder="书名、作者、标签" class="handle-input mr10"></el-input>
                    <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                </div>
            </el-col>
            <el-table
                    :data="tableData"
                    border
                    stripe
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
            >
                <el-table-column prop="bid" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="bname" label="书名" align="center"></el-table-column>
                <el-table-column label="价格" width="130" align="center">
                    <template #default="scope">￥{{ scope.row.money }}</template>

                </el-table-column>
                <el-table-column prop="author" label="作者" align="center"></el-table-column>
                <el-table-column prop="total" label="总数" align="center"></el-table-column>
                <el-table-column label="剩余" prop="num" align="center"></el-table-column>

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
                                icon="el-icon-edit"
                                @click="handleEdit(scope.$index, scope.row)"
                        >编辑
                        </el-button>
                        <el-button
                                type="text"
                                icon="el-icon-delete"
                                class="red"
                                @click="handleDelete(scope.$index, scope.row)"
                        >删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div class="pagination">
                <el-pagination
                        background
                        layout="total, prev, pager, next"
                        :current-page="query.pageIndex"
                        :page-size="query.pageSize"
                        :total="query.pageTotal"
                        @current-change="handlePageChange"
                ></el-pagination>
            </div>

        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" v-model="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="书名">
                    <el-input v-model="form.bname"></el-input>
                </el-form-item>
                <el-form-item label="作者">
                    <el-input v-model="form.author"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit">确 定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import {ElMessage} from "element-plus";

    export default {
        name: "bookList",
        data() {
            return {
                query: {
                    pageIndex: 1,  //当前页码
                    pageTotal: 0,   //总共条数
                    pageSize: 4,   //每页多少条
                },
                tableData: [
                    {
                        bid: 1,   //编号
                        bname: 'java',
                        money: 100,
                        total: 100,
                        num: 2,
                        taglist: ['科学', '计算机'],
                        author: '拉德斯基'
                    }

                ],
                pageshow: true,
                pno: 1,
                key: "",
                editVisible: false,
                form: {},
                idx: -1,
                id: -1
            }
        },
        created() {
            this.getData();
        },

        methods: {
            getData() {
                let that = this;
                this.$http.get("http://localhost:8081/api/getBookList?pno=" + that.pno)
                    .then(res => {
                        console.log(res)
                        if(res.code==20000){
                            that.tableData = res.data.list.records
                            that.query.pageIndex = Number(res.data.list.current)  //当前是第几页
                            that.query.pageTotal = Number(res.data.list.total) //总共条数
                            that.query.pageSize = Number(res.data.list.size)//每页多少条
                            for (let i = 0; i < res.data.list.records.length; i++) {
                                this.tableData[i].taglist = res.data.list.records[i].taglist.split(",")
                            }
                        }else {
                            ElMessage.error(res.message)
                        }
                        console.log(this.query)
                    })
            },
            // 删除操作
            handleDelete(index, row) {
                let that=this;
                // 二次确认删除
                this.$confirm("确定要删除吗？", "提示", {
                    type: "warning"
                })
                    .then(() => {
                        const data = new FormData();
                        data.append("bid", row.bid)
                        this.$http.post("http://localhost:8081/api/deleteBook", data).then(res => {
                            if (res.code==20000){
                                that.$message.success("删除成功");
                                that.getData()
                            }else {
                                that.$message.success("删除失败");
                            }
                            console.log(res)
                        })

                    })
                    .catch(() => {
                    });
            },

            // 编辑操作
            handleEdit(index, row) {
                console.log(index, row)
                this.idx = index;
                this.form = row;
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit() {
                let that = this;
                that.editVisible = false;
                console.log(that.form.bid)
                const data = new FormData();
                data.append("bid", this.form.bid)
                data.append("bname", this.form.bname)
                data.append("author", this.form.author)
                this.$http.post("http://localhost:8081/api/update", data).then(
                    res => {
                        console.log(res)
                        if (res.code == 20000){
                            that.$message.success(`修改第 ${this.idx + 1} 行成功`);
                        }else {
                            ElMessage.error(res.message);
                        }
                    }
                )
            },
            // 分页导航

            handlePageChange(val) {
                this.pno = val
                console.log(val)
                if (this.key == "") {
                    this.getData();
                } else {
                    this.search()
                }

            },
            search() {
                let that = this;
                console.log(that.key)
                that.$http.get("http://localhost:8081/api/search?key=" + this.key + "&pno=" + this.pno)
                    .then(res => {
                        console.log(res)
                        if(res.code==20000){
                            that.tableData = res.data.list
                            that.tableData = res.data.list.records
                            that.query.pageIndex = Number(res.data.list.current)  //当前是第几页
                            that.query.pageTotal = Number(res.data.list.total) //总共条数
                            that.query.pageSize = Number(res.data.list.size)//每页多少条
                            for (let i = 0; i < res.data.list.records.length; i++) {
                                this.tableData[i].taglist = res.data.list.records[i].taglist.split(",")
                            }
                        }else {
                            ElMessage.error("未搜索到");
                        }

                        console.log(this.query)
                    })
            },
            //搜索
            handleSearch() {
                if (this.key != "") {
                    this.search()
                } else {
                    this.getData()
                }

            }
        }
    }
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

    .red {
        color: #ff0000;
    }

    .mr10 {
        margin-right: 10px;
    }

    .table-td-thumb {
        display: block;
        margin: auto;
        width: 40px;
        height: 40px;
    }

    .el-tag {
        margin: 0 2px;
    }
</style>
