<template> 
<div id="upload">
    <el-dialog title="歌曲上传" :visible.sync="dialogFormVisible"  @close="close" width="480px">
        <el-form :model="uploadForm" label-width="100px" status-icon :rules="rules" ref="uploadForm">
             <el-form-item label="歌曲名称" prop="musicname">
                <el-input placeholder="请输入歌曲名称" v-model="uploadForm.musicname" :style="A"></el-input>
            </el-form-item>
             <el-form-item label="专辑名称" prop="album">
                <el-input placeholder="请输入专辑名称" v-model="uploadForm.album" :style="A"></el-input>
            </el-form-item>
            <el-form-item label="歌手" prop="singer" >
            <el-input placeholder="请输入歌手名字"  v-model="uploadForm.singer" autocomplete="off" :style="A"></el-input>
            </el-form-item>
            <el-form-item label="歌曲类型" prop="type" >
               <el-select v-model="uploadForm.type" clearable placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
              </el-select>
            </el-form-item>
        </el-form>
        <div class="upload-mian">
        <el-upload :auto-upload="false"  class="upload-demo"  drag  ref="upload"
            action=""
            :on-change="change"
            :limit="1"
            :on-exceed="handelExceed"
            :http-request="upload"
            >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传mp3文件</div>
        </el-upload>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitUpload('uploadForm')">上 传</el-button>
           
        </div>
</el-dialog>
    </div>
</template>
<script>
export default {
    data() {
        var checkName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('歌曲名不能为空'));
        }else{
          callback();
        }
      };
      var checkName2 = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('歌手不能为空'));
        }else{
          callback();
        }
      };
      var checkName3 = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('专辑名不能为空'));
        }else{
          callback();
        }
      };
        return {
          options: [
          { value: '流行',label: '流行'},
          { value: '摇滚',label: '摇滚'},
          { value: '古典',label: '古典'},
          { value: '爵士',label: '爵士'},
          { value: '民谣',label: '民谣'},
          { value: '说唱',label: '说唱'},
          ],
        rules: { 
          musicname: [
            { validator: checkName, trigger: 'blur' }
          ],
          album: [
            { validator: checkName3, trigger: 'blur' }
          ],
          singer: [
            { validator: checkName2, trigger: 'blur' }
          ]
        },
            dialogFormVisible:this.$store.state.dialogUploadVisible,
            uploadForm: {
                musicname: '',
                singer:'',
                album:'',
                type:'流行'
            },
            A:"width:300px"
        }
    },
    methods:{
        close(){
        this.$store.commit('dialog2',false)
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      change(filetemp){  //上传文件的时候进行判断
     
              var Name = filetemp.name;
              var idx = Name.lastIndexOf("."); 
              if (idx != -1){
              var ext = Name.substr(idx+1);  
              if (ext!='mp3'){
              alert("仅允许传入mp3文件")
              this.$refs.upload.clearFiles()
                return
              }
                this.uploadForm.musicname=Name.substr(0,idx)
              }else{
                alert("文件有误")
              }
              var t = Name.indexOf('-');
              this.uploadForm.singer=Name.substr(0,t)
            },
      handelExceed(){
        alert("每次最多只能上传一个文件")
      },
      //提交
      submitUpload(formName) {
        let _this=this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(_this.uploadForm.type==null||_this.uploadForm.type==''){
              alert("请选择歌曲类型")
              return
            }
            
            _this.$refs.upload.submit();
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      
     upload(filetemp){
       
          if(filetemp.file.size==0){//文件大小为0
            alert("请上传文件")
            return
        } 
          let parms = new FormData()
          //传入文件
        parms.append('file',filetemp.file) 
        parms.append('musicname',this.uploadForm.musicname)
        parms.append('album',this.uploadForm.album)
        parms.append('singer',this.uploadForm.singer)
        parms.append("type",this.uploadForm.type)
        parms.append("username",sessionStorage.getItem('username')) //上传者
        var _this = this
        this.$axios.post('/UploadServlet',parms,{headers: {'Content-Type': 'multipart/form-data'}})
        .then(function(resp){
        alert(resp.data)
        _this.$store.commit('getmusicData','')
        _this.dialogFormVisible=false
        }).catch(function(err){
          alert(err.message)
        })
    },
  },
}
</script>
<style scope>
.dialog-footer{
    text-align: center;
}
.el-input{
  width: 50px;
}
.upload-demo{
  position: relative;
 left: 50px;
}
</style> 

