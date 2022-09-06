<template>
    <div id="dialog">
        <el-dialog title="" :visible.sync="dialogFormVisible" @close="close" width="25%" center >
            <el-switch  style="display: block"  v-model="value"  active-color="#13ce66"  inactive-color="#ff4949"  active-text="注册"
                inactive-text="登录" @click.native="change(value)">
            </el-switch>
            <Login v-if="login"></Login>
            <Register v-if="register" @change="change(false)" ></Register>
        </el-dialog>
    </div>
</template>
<script>
import Login from './Login.vue'
import Register from './Register.vue'
export default {
    data() {
        return {
            value: false,
            login:true,
            register:false,
            dialogFormVisible : this.$store.state.dialogLogineVisible
        }
    },
    methods:{
      //切换注册/登录
      change(value){
        if(value==true){
            this.value=true
          this.login=false
          this.register=true
        }
        if(value==false){
            this.value=false
         this.login=true
        this.register=false
        }
      },
      //关闭对话框
      close(){
        this.$store.commit('dialog',false)
      },
      
    },
    components:{
      Login,
      Register
    }
}
</script>
<style scoped>
.dialog-footer{
 text-align: center;
}
.el-input{
  width: 90%;
}

</style>