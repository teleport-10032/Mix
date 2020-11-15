<template>
    <div>
        <el-card style="width: 500px;position: absolute;
                top:50%;left:50%;transform: translate(-50%,-50%)">
            <div slot="header" class="clearfix">
                <span>山东建筑大学第八届ICPC校赛报名</span>
                <el-button style="float: right; padding: 3px 0" type="text"
                           @click="resetForm">清空表单</el-button>
            </div>
            <el-form ref="formRef" :model="form"  :rules="formRules" label-width="100px">
                <el-form-item label="班级" prop="theClass">
                    <el-input v-model="form.theClass"
                              placeholder="输入班级，如网络171" ></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="form.name" placeholder="输入姓名"></el-input>
                </el-form-item>
                <el-form-item label="学号" prop="studentId">
                    <el-input v-model="form.studentId" placeholder="输入学号"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="form.sex">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                        <el-radio label="不确定"></el-radio>
                    </el-radio-group>
                    &nbsp;
                    <el-tooltip placement="right-start"  class="item" effect="dark" content="该选项用于区别女队">
                        <i class="el-icon-question"></i>
                    </el-tooltip>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="form.email"  placeholder="输入邮箱">
                        <el-button slot="append" type="primary" @click="send">发送验证码</el-button>
                    </el-input>
                </el-form-item>
                <el-form-item label="验证码" prop="verCode">
                    <el-input v-model="form.verCode" placeholder="输入验证码" @click="send"></el-input>
                </el-form-item>
                <el-form-item label="参赛类型" prop="joinType">
                    <el-radio-group v-model="form.joinType" style="text-align: left">
                        <el-radio label="正常"></el-radio>
                        <el-radio label="友情参赛"></el-radio>
                    </el-radio-group>
                    &nbsp;
                    <el-tooltip placement="right-start"  class="item" effect="dark" content="友情参赛选项供非大一以及校外选手使用。无需缴费，但同时也无法获得奖品和校队名额">
                        <i class="el-icon-question"></i>
                    </el-tooltip>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">立即报名！</el-button>
                    <el-button @click="viewList">查看报名名单</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
    import qs from 'qs'
    export default {
        name: "register",
        data() {
            var isEmail = (rule, value, callback) => {
                    const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
                    if (!value) {
                        return callback(new Error('邮箱不能为空'))
                    }
                    setTimeout(() => {
                        if (mailReg.test(value)) {
                            callback()
                        } else {
                            callback(new Error('请输入正确的邮箱格式'))
                        }
                    }, 100)
                }
            return{
                message:"",
                form: {
                    theClass:"",
                    studentId:"",
                    name:"",
                    sex:"男",
                    email:"",
                    verCode:"",
                    joinType:""
                },
                formRules: {
                    theClass: [
                        { required: true, message: '请输入班级', trigger: 'blur' },
                        { min: 4, max: 10, message: '长度在 4 到 10 个字符', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
                    ],
                    studentId: [
                        { required: true, message: '请输入学号', trigger: 'blur' },
                        { min: 5, max: 15, message: '长度在 5 到 15 个字符', trigger: 'blur' }
                    ],
                    email:[
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { validator: isEmail, trigger: 'blur'}
                    ],
                    verCode:[
                        { required: true, message: '请输入验证码', trigger: 'blur' },
                        { min: 6, message: '长度为 6 个字符', trigger: 'blur' }
                    ]
                },
            }
        },
        methods: {
            onSubmit() {
                let result =  this.$axios({
                    method: 'post',
                    url: '/submit',
                    headers: { 'content-type': 'application/x-www-form-urlencoded'},
                    data: qs.stringify({
                        theClass:this.form.theClass,
                        studentId:this.form.studentId,
                        name:this.form.name,
                        sex:this.form.sex,
                        email:this.form.email,
                        verCode:this.form.verCode,
                        joinType:this.form.joinType
                    })
                });
                result.then(res=>{
                    if(res.data.error === "1")
                        this.$message.error('验证码错误')
                    else if(res.data.error === "2")
                        this.$message.warning('邮箱已被注册')
                    else
                    {
                        this.$message.success("恭喜！报名成功")
                        this.$router.push('/list')
                    }
                })
            },
            resetForm()
            {
                this.$refs.formRef.resetFields()
            },
            send()
            {
                if(this.email_blur())
                {
                    this.$message.success('已提交发送请求')
                    let result =  this.$axios({
                        method: 'post',
                        url: '/send',
                        headers: { 'content-type': 'application/x-www-form-urlencoded'},
                        data: qs.stringify({
                            email: this.form.email
                        })
                    });
                    result.then(res=>{
                        console.log(res.data)
                        if(res.data.error === "1")
                            this.$message.warning('发送次数已用完。请联系QQ1021822981重置')
                        else if(res.data.error === "0")
                            this.$message.success('发送成功,您还剩'+res.data.times+'次发送机会')
                        else
                            this.$message.error("发送失败。请联系QQ1021822981")
                    })
                }
                else
                    this.$message.warning("请正确填写邮箱")
            },

            viewList()
            {
                this.$router.push('/list')
            },
            email_blur() {
                if(this.form.email==undefined || this.form.email=="")
                    return false;
                let myreg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9)+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/;
                return myreg.test(this.form.email);
            }

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
