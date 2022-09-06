import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';
import qs from 'qs'
Vue.use(Vuex)
Vue.prototype.$axios=axios
Vue.prototype.$qs=qs
export default new Vuex.Store({
  state: {
    dialogLogineVisible:false,
    dialogUploadVisible:false,
    username:'',
    musicData:[]
  },
  mutations: {
    dialog(state,item){
      state.dialogLogineVisible=item
    },
    dialog2(state,item){
      state.dialogUploadVisible=item 
    },
    islogin(state,name){
      state.username=name
      sessionStorage.setItem('username',name)
    },
    getuserName(state){
      state.username=sessionStorage.getItem('username')
    },
    getmusicData(state,item){
     axios({
        url:"/MusicDataServlet",
        method:"get",
        params:{item:item}
      }).then(function (resp){
        state.musicData=resp.data
      });
    },
    
  },
  actions: {
  },
  modules: {
  }
})
