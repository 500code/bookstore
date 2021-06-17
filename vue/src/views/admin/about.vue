<template>
  <v-chart class="chart" :option="option"/>
</template>

<style>

.chart {
  width: 90%;
  height: 90%;
}
</style>

<script>
import 'echarts/lib/component/title'
import 'echarts/lib/component/legend'

export default {
  data() {
    return {
      option: {
        title: {
          text: '借-还7天曲线图'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['借书', '还书']
        },
        xAxis: {
          type: 'category',
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '今天']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: "借书",
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line',
          smooth: true
        },
          {
            name: "还书",
            data: [82, 92, 91, 93, 120, 130, 130],
            type: 'line',
            smooth: true
          }
        ]
      },
      date: new Date()
    }
  },
  created() {
    this.getData();
  },
  methods: {
    getData() {
      this.$http.get("http://localhost:8081/api/getEchats").then(res => {
        console.log(res)
      })
    }
  }
}
</script>
