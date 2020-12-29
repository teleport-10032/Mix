<template>
    <el-card shadow="hover">
        <span>更新密码</span><br><br>
        <el-form :model="form" ref="formRef" label-width="0px" style="width: 400px"
                    :rules="formRules">
            <el-form-item  prop="oldpasswd">
                <el-input v-model="form.oldpasswd" autocomplete="off" size="small"
                          type="password"
                          placeholder="原密码"></el-input>
            </el-form-item>
            <el-form-item  prop="passwd">
                <el-input v-model="form.passwd" autocomplete="off" size="small"
                          type="password"
                          placeholder="新密码"></el-input>
            </el-form-item>
            <el-form-item  prop="repasswd">
                <el-input v-model="form.repasswd" autocomplete="off" size="small"
                          type="password"
                          placeholder="确认新密码"></el-input>
            </el-form-item>
        </el-form>
        <div>
            <el-button @click="resetForm" size="small">重置</el-button>
            <el-button type="primary" @click="update" size="small">更新密码</el-button>
        </div>
    </el-card>
</template>

<script>
    import global_ from '../../Global'
    import qs from 'qs'
    import md5 from 'js-md5';
    export default {
        name: "userAccount",
        data()
        {
            var isRepasswd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'))
                } else if (value !== this.form.passwd) {
                    callback(new Error('两次输入密码不一致'))
                } else {
                    callback()
                }
            }
            return{
                form:
                    {
                        oldpasswd:'',
                        passwd:'',
                        repasswd:''
                    },
                formRules: {
                    oldpasswd: [
                        { required: true, message: '请输入原密码', trigger: 'blur' },
                        { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
                    ],
                    passwd: [
                        { required: true, message: '请输入新密码', trigger: 'blur' },
                        { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
                    ],
                    repasswd: [
                        { required: true, message: '请确认密码', trigger: 'blur' },
                        { validator: isRepasswd, trigger: 'blur'}
                    ]
                },
            }
        },
        methods: {
            resetForm() {
                this.$refs.formRef.resetFields()
            },
            sleep(ms) {
                return new Promise(resolve =>
                    setTimeout(resolve, ms)
                )
            },
            update()
            {
                this.$refs.formRef.validate(async valid => {
                    //未通过则return
                    if (!valid) return
                    //通过
                    let result =  this.$axios({
                        method: 'put',
                        url: 'updatePasswordByPrePasswd',
                        headers: { 'content-type': 'application/x-www-form-urlencoded'},
                        data: qs.stringify({
                            token: window.localStorage.getItem("token"),
                            oldpasswd:md5(this.form.oldpasswd),
                            passwd:md5(this.form.passwd)
                        })
                    });
                    result.then(res=>{
                        if(res.data.error === "1")
                            this.$message.error("原密码错误")
                        else if(res.data.error === '0')
                        {
                            this.resetForm()
                            window.localStorage.clear()
                            this.$message.success("修改成功")
                            //sleep 0.5s
                            this.sleep(500).then(()=>{
                                global_.isLogin = false
                                this.$router.push({ path: '/'})
                                return this.$router.go(0)
                            })


                        }
                        else
                            this.$message.error("systemError")
                        this.resetForm()
                    })
                })
            }
        }
    }
</script>

<style scoped>

</style>
