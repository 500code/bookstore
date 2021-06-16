<template>
  <el-row :gutter="20">
    <el-col :span="8">
      <el-card shadow="hover" class="mgb20" style="height:252px;">
        <div class="user-info">
          <img src="../../assets/img.png" class="user-avator" alt/>
          <div class="user-info-cont">
            <div class="user-info-name">{{ name }}</div>
            <div>管理员</div>
          </div>
        </div>
        <div class="user-info-list">
          上次登录时间：
          <span>2021-06-07</span>
        </div>
        <div class="user-info-list">
          上次登录地点：
          <span>清远</span>
        </div>
      </el-card>
      <el-card shadow="hover" style="height:252px;">
        <el-carousel height="210px">
          <el-carousel-item v-for="item in imgList" :key="item">
            <el-image
                style="width: 100%; height: 210px"
                :src="item.src"
            ></el-image>
          </el-carousel-item>
        </el-carousel>
      </el-card>
    </el-col>
    <el-col :span="16">
      <el-row :gutter="20" class="mgb20">
        <el-col :span="8">
          <el-card shadow="hover" :body-style="{ padding: '0px' }">
            <div class="grid-content grid-con-1">
              <i class="el-icon-user-solid grid-con-icon"></i>
              <div class="grid-cont-right">
                <div class="grid-num">1234</div>
                <div>已借图书数量</div>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" :body-style="{ padding: '0px' }">
            <div class="grid-content grid-con-2">
              <i class="el-icon-message-solid grid-con-icon"></i>
              <div class="grid-cont-right">
                <div class="grid-num">321</div>
                <div>系统消息</div>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" :body-style="{ padding: '0px' }">
            <div class="grid-content grid-con-3">
              <i class="el-icon-notebook-2 grid-con-icon"></i>
              <div class="grid-cont-right">
                <div class="grid-num">5000</div>
                <div>剩余图书</div>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-card shadow="hover" style="height:403px;">
        <template #header>
          <div class="clearfix">
            <span>每日事项</span>
          </div>
        </template>

        <el-table :show-header="false" :data="todoList" style="width:100%;">
          <el-table-column width="40">
            <template #default="scope">
              <el-checkbox v-model="scope.row.status"></el-checkbox>
            </template>
          </el-table-column>
          <el-table-column>
            <template #default="scope">
              <div
                  class="todo-item"
                  :class="{
                                        'todo-item-del': scope.row.status,
                                    }"
              >{{ scope.row.title }}
              </div>
            </template>
          </el-table-column>
          <el-table-column width="60">
            <template>
              <i class="el-icon-edit"></i>
              <i class="el-icon-delete"></i>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      name: localStorage.getItem("admin"),
      todoList: [
        {
          title: "今天要修复100个bug",
          status: false
        },
        {
          title: "今天要修复100个bug",
          status: false
        },
        {
          title: "今天要写100行代码加几个bug吧",
          status: false
        },
        {
          title: "今天要修复100个bug",
          status: false
        },
        {
          title: "今天要修复100个bug",
          status: true
        },
        {
          title: "今天要写100行代码加几个bug吧",
          status: true
        }
      ],
      imgList: [
        {
          src: require('../../assets/100.png')
        },
        {
          src: require('../../assets/100.png')
        },
        {
          src: require('../../assets/100.png')
        },
        {
          src: require('../../assets/100.png')
        }
      ]
    }
  }
}
</script>

<style scoped>


.el-row {
  margin-bottom: 20px;
}

.grid-content {
  display: flex;
  align-items: center;
  height: 100px;
}

.grid-cont-right {
  flex: 1;
  text-align: center;
  font-size: 14px;
  color: #999;
}

.grid-num {
  font-size: 30px;
  font-weight: bold;
}

.grid-con-icon {
  font-size: 50px;
  width: 100px;
  height: 100px;
  text-align: center;
  line-height: 100px;
  color: #fff;
}

.grid-con-1 .grid-con-icon {
  background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
  background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
  background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
  color: rgb(242, 94, 67);
}


.user-avator {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}

.user-info {
  display: flex;
  align-items: center;
  padding-bottom: 20px;
  border-bottom: 2px solid #ccc;
  margin-bottom: 20px;
}

.user-avator {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}

.user-info-cont {
  padding-left: 50px;
  flex: 1;
  font-size: 14px;
  color: #999;
}

.user-info-cont div:first-child {
  font-size: 27px;
  color: #222;
}

.user-info-list {
  font-size: 14px;
  color: #999;
  line-height: 25px;
}

.user-info-list span {
  margin-left: 70px;
}


.todo-item {
  font-size: 14px;
}

.todo-item-del {
  text-decoration: line-through;
  color: #999;
}

.mgb20 {
  margin-bottom: 20px;
}

</style>
