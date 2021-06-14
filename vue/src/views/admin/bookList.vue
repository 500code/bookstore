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
          header-cell-class-name="table-header"
          @selection-change="handleSelectionChange"
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
            >下架
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
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="标签">
          <el-input v-model="form.address"></el-input>
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
      multipleSelection: [],
      delList: [],
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
//     // 获取 easy-mock 的模拟数据
    getData() {
      let that = this;
      const pno = this.query.pageIndex
      this.$http.get("http://localhost:8081/api/getBookList?pno=" + pno)
          .then(res => {
            console.log(res)
            that.tableData = res.data.list.records
            that.query.pageIndex=res.data.list.current  //当前是第几页
            that.query.pageTotal=res.data.list.total  //总共条数
            that.query.pageSize=res.data.list.size    //每页多少条
            for (let i = 0; i < res.data.list.records.length; i++) {
              this.tableData[i].taglist = res.data.list.records[i].taglist.split(",")
            }
            console.log(this.query)
          })
    },
//     // 触发搜索按钮
//     handleSearch() {
//       this.$set(this.query, "pageIndex", 1);
//       this.getData();
//     },
    // 删除操作
    handleDelete(index) {
      // 二次确认删除
      this.$confirm("确定要删除吗？", "提示", {
        type: "warning"
      })
          .then(() => {
            this.$message.success("删除成功");
            this.tableData.splice(index, 1);
          })
          .catch(() => {
          });
    },

    // 编辑操作
    handleEdit(index, row) {
      this.idx = index;
      this.form = row;
      this.editVisible = true;
    },
//     // 保存编辑
//     saveEdit() {
//       this.editVisible = false;
//       this.$message.success(`修改第 ${this.idx + 1} 行成功`);
//       this.$set(this.tableData, this.idx, this.form);
//     },
    // 分页导航

    handlePageChange(val) {
      // this.$set(this.query, "pageIndex", val);
      console.log(val)
      this.getData();
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
