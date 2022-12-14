import {createApp} from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import router from './router'
import socket from "@/assets/js/io";

import 'element-plus/dist/index.css'


import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

// 屏蔽错误信息
// app.config.errorHandler = () => null;
// 屏蔽警告信息
app.config.warnHandler = () => null;
app.config.globalProperties.$socket = socket;

app.use(ElementPlus).use(router).mount('#app')
