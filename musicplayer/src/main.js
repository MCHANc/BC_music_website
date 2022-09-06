import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import qs from 'qs'
Vue.config.productionTip = false
Vue.prototype.$axios=axios
Vue.prototype.$qs=qs
Vue.use(ElementUI);

//全局配置
axios.defaults.baseURL='http://localhost:8090/musicApi'
axios.defaults.timeout=3000;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
