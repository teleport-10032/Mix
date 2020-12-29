<template>
    <el-card shadow="hover">
        <el-row>
            <el-col>
                <span>个人信息设置</span><br><br>
                <el-form :model="form" ref="formRef" label-width="80px" style="width: 400px" >
                    <!--                 :rules="registerFormRules"-->
                    <el-form-item  prop="id" label="ID">
                        <el-input v-model="form.id" autocomplete="off" size="small"
                                  @keyup.enter.native="update" disabled
                                  placeholder="ID"></el-input>
                    </el-form-item>
                    <el-form-item  prop="email" label="邮箱">
                        <el-input v-model="form.email" autocomplete="off" size="small"
                                  @keyup.enter.native="update" disabled
                                  placeholder="邮箱"></el-input>
                    </el-form-item>
                    <el-form-item  prop="username" label="用户名">
                        <el-input v-model="form.username" autocomplete="off" size="small"
                                  @keyup.enter.native="update"
                                  placeholder="用户名"></el-input>
                    </el-form-item>
                    <el-form-item  prop="type" label="类型">
                        <el-input v-model="form.type" autocomplete="off" size="small"
                                  @keyup.enter.native="update" disabled
                                  placeholder="类型"></el-input>
                    </el-form-item>
                    <el-form-item  prop="sign" label="个人签名">
                        <el-input v-model="form.sign" autocomplete="off" size="small"
                                  @keyup.enter.native="update"
                                  placeholder="个人签名"></el-input>
                    </el-form-item>
                    <el-form-item  prop="github" label="github">
                        <el-input v-model="form.github" autocomplete="off" size="small"
                                  @keyup.enter.native="update"
                                  placeholder="github"></el-input>
                    </el-form-item>
                    <el-form-item  prop="site" label="个人站点">
                        <el-input v-model="form.site" autocomplete="off" size="small"
                                  @keyup.enter.native="update"
                                  placeholder="个人站点"></el-input>
                    </el-form-item>
                </el-form>
                <div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <el-button @click="resetForm" size="small">重置</el-button>
                    <el-button type="primary" @click="update" size="small">更新个人信息</el-button>
                </div>
            </el-col>
        </el-row>
    </el-card>
</template>

<script>
    import qs from 'qs'
    export default {
        name: "userInfo",
        data()
        {
            return{
                form:{
                    id:'',
                    email:'',
                    username:'',
                    type:'',
                    sign:'',
                    site:'',
                    github:''
                }
            }
        },
        created()
        {
            this.getUserPersonInfo()
        },
        methods: {
            resetForm() {
                this.$refs.formRef.resetFields()
            },
            getUserPersonInfo()
            {
                let result =  this.$axios({
                    method: 'post',
                    url: 'getUserPersonInfo',
                    headers: { 'content-type': 'application/x-www-form-urlencoded'},
                    data: qs.stringify({
                        token: window.localStorage.getItem("token")
                    })
                });
                result.then(res=>{
                    if(res.data.error === "0")
                    {
                        this.form.id = res.data.id;
                        this.form.email = res.data.email;
                        this.form.username = res.data.username;
                        this.form.type = res.data.type;
                        this.form.github = res.data.github;
                        this.form.sign = res.data.sign;
                        this.form.site = res.data.site;
                    }
                    else
                        this.$message.error('获取信息失败')
                })

            },
            update()
            {
                let result =  this.$axios({
                    method: 'put',
                    url: 'updateUserPersonInfo',
                    headers: { 'content-type': 'application/x-www-form-urlencoded'},
                    data: qs.stringify({
                        token: window.localStorage.getItem("token"),
                        username:this.form.username,
                        sign: this.form.sign,
                        site: this.form.site,
                        github: this.form.github
                    })
                });
                //error 1 用户名已被注册 2 更新失败
                result.then(res=>{
                    // console.log(res)
                    if(res.data.error === "0")
                    {
                        this.getUserPersonInfo()
                        this.$message.success("修改成功,刷新页面查看")
                    }
                    else if(res.data.error === "1")
                        this.$message.warning('用户名已被注册')
                    else
                        this.$message.error('server error')
                })
            }
        }
    }
</script>

<style scoped>

</style>
