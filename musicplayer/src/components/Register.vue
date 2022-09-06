<template>
    <div id="register">
  <!-- 注册 -->
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
            <el-input placeholder="请输入用户名" v-model="ruleForm.username" ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
            <el-input placeholder="请输入密码" type="password" v-model="ruleForm.pass" autocomplete="off" ></el-input>
        </el-form-item>   
        <el-form-item label="确认密码" prop="checkPass">
            <el-input placeholder="请再次输入密码" type="password" v-model="ruleForm.checkPass" autocomplete="off" ></el-input>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="register('ruleForm')">注册</el-button>
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
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      // 组件数据
      return {  
        ruleForm: {
            username: '',//注册-用户名
            pass: '',//注册-密码
            checkPass: '',//注册-检查密码
        },
        //实现输入框判空
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          username: [
            { validator: checkName, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
        },
       //控制对话框显示
        dialogFormVisible : this.$store.state.dialogLogineVisible
      };
    },
    methods:{
      //将注册信息提交到Servlet
      register(formName){
        let _this=this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios({
              url:'/registerServlet',
              methods:'post',
              params:{username:this.ruleForm.username,password:this.ruleForm.pass}
            }).then(function(resp){
              alert(resp.data)
               _this.$refs[formName].resetFields();
              //  _this.$emit('change')
              _this.$emit('change');
              _this.$emit('value',true);
               
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