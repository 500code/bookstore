<template>
  <el-scrollbar :view-style="act"  @mouseover="tt" @mouseout="scroll">
    <el-row :gutter="10" class="scrollBox">
      <el-col :span="6" v-for="o in 8" :key="o">
        <el-card>
          <div style="padding: 14px;">
            <span>{{ o }} {{ actt }}</span>
            <div class="bottom">
              <el-button type="text" class="button">操作按钮</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </el-scrollbar>
</template>

<script>
export default {
  name: "test",
  data() {
    return {
      x: 10,
      time: '',
      act: ''
    }
  },
  computed: {
    actt() {
      return this.act
    }
  },
  created() {
    this.scroll()
  },
  methods: {
    scroll() {
      console.log(this.x)
      this.time = setInterval(() => {
        if (this.x < -1500) {
          this.x=0;
          // clearInterval(this.time)
        }
        this.act = {
          transform: 'translateX(' + this.x-- + 'px)'
        }
      },10)
    },
    tt(){
      clearInterval(this.time)
      console.log(1)
    }
  }
}
</script>

<style scoped>
.scrollBox {
  display: flex;
  flex-wrap: nowrap;
}
</style>
