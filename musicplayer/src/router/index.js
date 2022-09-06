import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Mymusic from '../views/Mymusic.vue'
import Router from 'vue-router'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/Mymusic',
    name: 'Mymusic',
    component: Mymusic
  },
 
]

const router = new VueRouter({
  routes
})
const originalPush = Router.prototype.push
      Router.prototype.push = function push(location) {
        return originalPush.call(this, location).catch(err => err)
      }
export default router
