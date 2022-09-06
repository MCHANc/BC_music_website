<template>
    <div id="mymusic">
        <div class="search">
        <el-input placeholder="请输入内容" v-model="input" clearable></el-input>
        <el-button icon="el-icon-search" circle @click.native="seacrch"></el-button>
        </div>
          <h1 class="head">歌曲列表</h1>
          <hr color=#f2f2f2/>
        <el-table :data="myMuiscData" stripe style="width: 100%">
          <el-table-column  type="index"  label="序号"  width="100"></el-table-column>
          <el-table-column  prop="musicName"  label="歌曲名称"  width="200"></el-table-column>
          <el-table-column  prop="singer"  label="歌手"  width="150"></el-table-column>
          <el-table-column  prop="album"  label="专辑"  width="150"></el-table-column>
          <el-table-column  prop="musicType"  label="类型"  width="100"></el-table-column>
          <el-table-column  prop="uploader"  label="上传者"  width="100"></el-table-column>
          <el-table-column  width="200">
            <template slot-scope="scope">
              <i class="el-icon-video-play" @click="play(scope.row)"></i>
              <i class="el-icon-video-pause" @click="pause"></i>
              <i class="el-icon-star-on" @click="like(scope.row)"></i>    
            </template>
          </el-table-column>
        </el-table>
         <Audio ref="audio" class="audio"></Audio>
    </div>
</template>
<script>
 import Audio from '../components/Audio.vue'
export default {
    data(){
        return{
            input:'',
            myMuiscData:[]
        }
    },
    methods:{
        seacrch(){
             let _this=this;
      this.$axios({
        url:"/MymusicServlet",
        method:"get",
        params:{item:this.input,userName:sessionStorage.getItem('username')}
      }).then(function (resp){
        _this.myMuiscData=resp.data
      });
        },
        play(row){
             this.$refs.audio.getSrc(this.$axios.defaults.baseURL+'/mp3/'+row.mp3File)
        },
        pause(){
            this.$refs.audio.pause();
        },
        like(row){
            let _this=this
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
            _this.reMymusic()
            }).catch(function(err){
            alert(err.message)
            })
        },
        reMymusic(){
            let _this=this;
      this.$axios({
        url:"/MymusicServlet",
        method:"get",
        params:{item:this.input,userName:sessionStorage.getItem('username')}
      }).then(function (resp){
        _this.myMuiscData=resp.data
      });
        }
    },
    mounted(){
    let _this=this;
      this.$axios({
        url:"/MymusicServlet",
        method:"get",
        params:{item:this.input,userName:sessionStorage.getItem('username')}
      }).then(function (resp){
        _this.myMuiscData=resp.data
      });
    },
    components:{
      Audio
    }
}
</script>
<style scoped>

.el-input{
    width: 250px;
     box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}
.el-button{
     box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
     position: relative;
     left: 20px;
}
#mymusic{
    border: #c4c7cf 1px solid;
    width: 1000px;
    position: relative;
    margin-left: -500px;
    left: 50%;
    height: auto;  
    min-height: 900px;
    border-top: none;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.search{
    position: relative;
    left:350px;
    top: 20px;
}
.head{
    position: relative;
    left: 10px;
    color: #242424;
}
.el-icon-video-play{
    font-size: 30px;
    margin-right:10px ;
    cursor: pointer;
}
.el-icon-video-play:hover{
    color: #e60026;
  }
.el-icon-video-pause{
    margin-right:10px ;
    font-size: 30px;
    cursor: pointer;
}
.el-icon-video-pause:hover{
    color: #e60026;
  }
.el-icon-star-on{
    
    font-size: 35px;
    cursor: pointer;
}
.el-icon-star-on:hover{
    color: #e60026;
  }
.audio{
    position: relative;
    top: 56.5px;
}
</style>