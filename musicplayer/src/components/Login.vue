<template>
<div id="login">
<!-- 登录 -->
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
     <el-form-item label="用户名" prop="username">
        <el-input  placeholder="请输入用户名" v-model="ruleForm.username" ></el-input>
      </el-form-item>
      <el-form-item label="密 码" prop="pass">
        <el-input placeholder="请输入密码" type="password" v-model="ruleForm.pass" autocomplete="off" ></el-input>
      </el-form-item>   
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </div>
</div>
</template>

<script>
  export default {
    data() {
      // 输入框判断
      var checkName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户名不能为空'));
        }else{
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          return callback(new Error('请输入密码'));
        }else{
          callback();
        }
      };
      
      // 组件数据
      return {  
        ruleForm: {
          username: '',//登录-用户名
          pass: '',//登录-密码
        },
        //实现输入框判空
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          username: [
            { validator: checkName, trigger: 'blur' }
          ],
        },
       //控制对话框显示
      };
    },
    methods:{
      //用户登录
      submitForm(formName) {
        let _this=this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios({
              url:'/loginServlet',
              methods:'get',
              params:{username:this.ruleForm.username,password:this.ruleForm.pass}
            }).then(function(resp){
              alert(resp.data)
              console.log(resp.data)
              if(resp.data=='登录成功!'){
                _this.$store.commit('islogin',_this.ruleForm.username)
                _this.$store.commit('dialog',false)        
                //location.reload();
              }
            }).catch(function(err){
              alert(err.message)
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      //清空对话框
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
    
  };
</script>
<style scoped>

.el-input{
  width: 90%;
}

</style>