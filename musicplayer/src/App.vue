<template>
  <div id="app">
    <div class="dh">
      <ul>
        <div class="logo"><img src="./assets/logo.jpg" @click="main"></div>
        <li class="nav-text" @click="main">发现音乐</li>
        <li class="nav-text" @click="mymusic">我的音乐</li>
        <div class="nav-search">
          <el-input
            placeholder="请输入内容"
            v-model="input"
            clearable>
          </el-input>
        </div>
        <li class="nav-btn"><el-button icon="el-icon-search" circle @click="search"></el-button></li>
        
        <li class="nav-text" @click="login" v-show="d3">登录</li>
        <li class="nav-text"  v-show="d4">
          欢迎您:{{username}}
          <el-link type="danger" @click="exit()">退出</el-link>
          </li>
        <li class="nav-btn-search" @click="upload"><el-button type="primary">歌曲上传<i class="el-icon-upload el-icon--right"></i></el-button></li>
      </ul>
    </div>
     <router-view/>
    <Dialog v-if="d"></Dialog>
    <Upload v-if="d2"></Upload>
  </div>
 
</template>
<script>
import Upload from './components/Upload.vue'
import Dialog from './components/Dialog.vue'

  export default {
     data() {
      return {
        input: '',
        
      }
    },
    computed:{
     d() {
       return this.$store.state. dialogLogineVisible
     },
     d2() {
       return this.$store.state. dialogUploadVisible
     },
     d3(){
       if(this.$store.state.username!=null){
         return false
       }else{
         return true
       }
     },
     d4(){
        if(this.$store.state.username==null){
         return false
       }else{
         return true
       }
     },
     username(){
       return this.$store.state.username
     }
    },
    methods:{
      login(){
        this.$store.commit('dialog',true)
        
      },
      upload(){
        if(this.username==null){
           alert("请登录!")
          return
        }
        this.$store.commit('dialog2',true)
      },
      exit(){
        sessionStorage.clear()
         this.$store.commit('getuserName')
         this.$router.push('/')
      },
      search(){
        this.$store.commit('getmusicData',this.input)
        this.$router.push('/')
      },
      mymusic(){
        if(sessionStorage.getItem('username')==null){
          alert("请登录")
          return
        }
        this.$router.push('/Mymusic')
      },
      main(){
        this.$router.push('/')
      }
    },
    components:{
      Dialog,
      Upload
    },
    mounted(){
      this.$store.commit('getmusicData','')
      this.$store.commit('getuserName')
    }
  }
</script>
<style scope>
body {
    margin: 0px;
    padding: 0px;
}
#app{
  position: relative;
   
    height: 70px;
    box-sizing: border-box;
    background: #242424;
    border-bottom: 1px solid #000;
    color: #333;
    font-family: Arial, Helvetica, sans-serif;
    -webkit-font-smoothing:antialiased;
    -moz-osx-font-smoothing:grayscale;
}
.dh{
  width: 1400px;
   margin: 0 auto;
   height: 75px;
}
.logo{
  float: left;
  position: relative;
  width: 176px;
  height: 69px;
  top: 0px;
  background-position: 0 0;
  cursor: pointer;
}
.nav-text{
  position: relative;
  float: left;
  color: #cccccc;
  list-style: none;
  height: 70px;
  line-height: 70px;
  padding: 0 19px;
    font-size: 18px;
    margin-left: 30px;
  background-position: 0 0;
  cursor: pointer;
}
.nav-text:hover{
  color: #ffffff;
  background: black;
}
.nav-search{
  float: left;
  position: relative;
  margin-left: -30px;
  top: 13px;
  left: 50px;
   width: 300px;
}
.nav-btn{
  position: relative;
  float: left;
  list-style: none;
  top: 13px;
  left: 70px;
  margin-right: 50px;
}
.nav-btn-search{
  position: relative;
  float: left;
  list-style: none;
  top: 13px;
  left: 20px;
}

</style>
