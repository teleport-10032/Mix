<template>
  <div>
    <el-card style="width: 500px;position: absolute;
                top:50%;left:50%;transform: translate(-50%,-50%)">
      <div slot="header" class="clearfix">
        <span>留言板提交页</span>
        <el-button style="float: right; padding: 3px 0" type="text"
                   @click="resetForm">清空表单</el-button>
      </div>
      <el-form ref="formRef" :model="form"  :rules="formRules" label-width="100px"  @submit.native.prevent>
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title"
                    placeholder="输入留言的标题" ></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input v-model="form.content" placeholder="输入留言的内容" type="textarea" cols="20" rows="20">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即提交！</el-button>
          <el-button @click="viewList">查看留言列表</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import qs from 'qs'
export default {
  name: "messageSubmit",
  data() {
    return{
      form: {
        title:"",
        content:""
      },
      formRules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入内容', trigger: 'blur' },
        ],
      },
    }
  },
  methods: {
    onSubmit() {
      //预验证
      this.$refs.formRef.validate(async valid => {
        //未通过则return
        if (!valid) return
        //通过
        let result =  this.$axios({
          method: 'post',
          url: '/boardSubmit',
          headers: { 'content-type': 'application/x-www-form-urlencoded'},
          data: qs.stringify({
            title: this.form.title,
            content: this.form.content
          })
        });
        result.then(res=>{
          console.log(res)
          this.$message.success("提交成功!")
          this.$router.push('/messageList')
        })
      })

    },
    resetForm()
    {
      this.$refs.formRef.resetFields()
    },
    viewList()
    {
      this.$router.push('/messageList')
    },
  }
}
</script>

<style scoped>
body
{
  margin: 0;
  padding: 0;
}
</style>