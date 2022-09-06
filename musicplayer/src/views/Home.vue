<template>
  <div class="home">
<el-row>
  <el-col :span="24">
    <div class="grid-content ">
    <el-carousel :interval="3000" type="card" height="300px">
        <el-carousel-item v-for="item in imgList" :key="item.id">
          <img :src="item.idView" class="image">
        </el-carousel-item>
      </el-carousel>
    </div>
    </el-col>
</el-row>
<hr color=#f2f2f2/>
<el-row>
  <el-col :span="3">
    <div class="grid-content ">
    <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#f9f9f9">
       <el-menu-item index="1" @click="type('')">
        <i class="el-icon-headset"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-menu-item index="2" @click="type('流行')">
        <i class="el-icon-s-home"></i>
        <span slot="title">流行</span>
      </el-menu-item>
      <el-menu-item index="3" @click="type('摇滚')">
        <i class="el-icon-headset"></i>
        <span slot="title">摇滚</span>
      </el-menu-item>
      <el-menu-item index="4" @click="type('古典')">
        <i class="el-icon-headset" ></i>
        <span slot="title">古典</span>
      </el-menu-item>
      <el-menu-item index="5" @click="type('爵士')">
        <i class="el-icon-headset"></i>
        <span slot="title">爵士</span>
      </el-menu-item>
      <el-menu-item index="6" @click="type('民谣')">
        <i class="el-icon-headset"></i>
        <span slot="title">民谣</span>
      </el-menu-item>
      <el-menu-item index="7" @click="type('说唱')">
        <i class="el-icon-headset"></i>
        <span slot="title">说唱</span>
      </el-menu-item>
    </el-menu>
    </div>
  </el-col>
  <el-col :span="21">
    <div class="grid-content bg-purple-light">
       <el-table :data="tableData" :stripe="true" height="392px" style="width: 100%">
     <el-table-column type="index" label="序号" width="100px"></el-table-column>
    <el-table-column prop="musicName" label="歌曲名称" width="300px"></el-table-column>
    <el-table-column prop="singer" label="歌手" width="200px"></el-table-column>
    <el-table-column prop="album" label="专辑" width="250px"></el-table-column>
    <el-table-column prop="musicType" label="类型" width="200px"></el-table-column>
    <el-table-column prop="uploader" label="上传者" width="200px"> </el-table-column>
    <el-table-column prop="uploadTime" label="上传时间" width="200px"></el-table-column>
    <el-table-column width="200px" >
      <template slot-scope="scope">
        <i class="el-icon-video-play" @click="play(scope.row)"></i>
       <i class="el-icon-video-pause" @click="pause"></i>
      <i  class="el-icon-star-off" @click="like(scope.row)" ></i>    
      </template>
    </el-table-column>
  </el-table>
    </div>
  </el-col>
</el-row>
<el-row>
  <el-col :span="24">
    <div class="grid-content ">
      <Audio ref="play" class="Audio" v-show="open"></Audio>
    </div>
  </el-col>
</el-row>

  </div>
</template>

<script>
import Audio from '../components/Audio.vue'
export default {
  name:'Home',
  data() {
    return {
      imgList:[
        {id:0,idView:require('../assets/sd.jpg')},
         {id:1,idView:require('../assets/zxy.jpg')},
         {id:2,idView:require('../assets/h3m.jpg')},
      ],
      open:false,
      rowData:[],
      src:'',
    }
  },
  methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      type(type){
         this.$store.commit('getmusicData',type)
      },
      play(row){
        this.rowData=row;
        this.$refs.play.getSrc(this.$axios.defaults.baseURL+'/mp3/'+this.rowData.mp3File)
        this.open=true
      },
      like(row){
        if(sessionStorage.getItem('username')==null)
        {
          alert("请登录")
          return
        }
        let parms = new FormData()
        parms.append("userName",sessionStorage.getItem('username'))
        parms.append('musicName',row.musicName)
        parms.append('album',row.album)
        parms.append('singer',row.singer)
        parms.append("musicType",row.musicType)
        parms.append('uploader',row.uploader) 
        parms.append('mp3File',row.mp3File) 
        this.$axios.post('/LikeServlet',parms,{headers: {'Content-Type': 'multipart/form-data'}})
        .then(function(resp){
        alert(resp.data)
        }).catch(function(err){
          alert(err.message)
        })
      },
      pause(){
        this.$refs.play.pause();
      }
    },
    computed:{
      tableData(){
        return this.$store.state.musicData
      }
    },
    components:{
      Audio
    },
}
</script>
<style scoped>
.el-row {
    margin-bottom: 20px;
    
  }
  .el-row:last-child{
    margin-bottom: 0;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  .image{
    width: 100%;
    height: 100%;
  }
  .show-music{
    position: relative;
    border: 1px red solid;
    height: 1000px;
    width: 800px;
    left: 50%;
    margin-left: -400px;
    
  }
  .Audio{
    position: relative;
    top: 20px;
  }
  .el-icon-video-play{
    position: relative;
    font-size: 40px;
    top: 4px;
    margin-right: 20px;
    cursor: pointer;
  }
  .el-icon-video-play:hover{
    color: #e60026;
  }
  .el-icon-star-off{
     position: relative;
    font-size: 40px;
    top: 4px;
    cursor: pointer;
  }
  .el-icon-star-off:hover{
    color: #e60026;
  }
  
  .el-icon-video-pause{
     position: relative;
    font-size: 40px;
    top: 4px;
    margin-right: 20px;
    cursor: pointer;
  }
   .el-icon-video-pause:hover{
    color: #e60026;
  }
</style>

