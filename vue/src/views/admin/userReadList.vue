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
          <el-input v-model="query.name" placeholder="请输入id" class="handle-input mr10"></el-input>
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
        <el-table-column prop="uid" label="ID" width="130" align="center"></el-table-column>
        <el-table-column prop="uname" label="用户名" align="center"></el-table-column>

        <el-table-column prop="ucount" label="借阅数" align="center"></el-table-column>
        <el-table-column prop="ustate" label="可借次数" align="center"></el-table-column>

        <el-table-column prop="reputation" label="信誉度" align="center"></el-table-column>
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


export default {
  name: "userReadList",
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
          uid: 1,   //编号
          uname: 'java',
          ucount: 1001,
          ustate: '3/5',
          reputation:100
        },
        {
          bid: 2,   //编号
          bname: 'java',
          count: 100,
          tagList: ['科学', '计算机'],
          state: '3/5'
        }

      ],
      pno:1,
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
  methods: {
    getData() {
      let that = this;
      that.$http.get("http://localhost:8081/api/userRanking?pno=" + that.pno)
              .then(res => {
                console.log(res)
                that.tableData = res.data.list.records
                console.log(that.tableData)
                that.query.pageIndex = Number(res.data.list.current)  //当前是第几页
                that.pageTotal = Number(res.data.list.total) //总共条数
                that.query.pageSize = Number(res.data.list.size)//每页多少条
              })
    },
    handleSearch(){
      this.search()
    },
    // 分页导航
    handlePageChange(val) {
      this.pno = val
      console.log(val)
      if (this.query.name == "") {
        this.getData();
      } else {
        this.search()
      }
    },
    search() {
      let that = this;
      that.$http.get("http://localhost:8081/api/userSearch?key=" + this.query.name + "&pno=" + this.pno)
              .then(res => {
                console.log(res)
                that.tableData = res.data.list
                console.log(that.tableData)
                that.tableData = res.data.list.records
                that.query.pageIndex = Number(res.data.list.current)  //当前是第几页
                that.pageTotal = Number(res.data.list.total) //总共条数
                that.query.pageSize = Number(res.data.list.size)//每页多少条
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
