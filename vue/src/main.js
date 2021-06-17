import {createApp} from 'vue'
import App from './App.vue'

import router from './router'

import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';

import axios from '@/axios/http'
import VueAxios from 'vue-axios'

import store from './store'

import ECharts from 'vue-echarts'
import {use} from "echarts/core";

// 手动引入 ECharts 各模块来减小打包体积
import {
    CanvasRenderer
} from 'echarts/renderers'
import {
    BarChart,
    LineChart
} from 'echarts/charts'
import {
    GridComponent,
    TooltipComponent

} from 'echarts/components'

use([
    CanvasRenderer,
    BarChart,
    GridComponent,
    TooltipComponent,
    LineChart
]);

createApp(App).use(router).use(store).use(ElementPlus).use(VueAxios, axios).component('v-chart', ECharts).mount('#app')
