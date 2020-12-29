<template>
    <div>
        <!-- 卡片视图区域 -->
        <el-card>
            <el-row type="flex">
                <el-col :span="2">
                    <el-button type="primary" @click="back()" size="small" round>返回</el-button>
                </el-col>
                <el-col :span="18"></el-col>
                <el-col :span="4" align="right">
                    <el-tag>编辑题目</el-tag>
                </el-col>
            </el-row>
            <el-divider></el-divider>

            <!--            表单-->
            <el-form ref="form" :model="form" label-width="80px" :rules="formRules" @submit.native.prevent>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="标题" prop="title">
                            <el-input v-model="form.title"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="16">
                    </el-col>
                </el-row>
                <el-form-item label="题目描述" prop="des">
                    <el-input type="textarea" :rows="5" v-model="form.des"></el-input>
                </el-form-item>
                <el-form-item label="输入描述" prop="input">
                    <el-input type="textarea" :rows="5" v-model="form.input"></el-input>
                </el-form-item>
                <el-form-item label="输出描述" prop="output">
                    <el-input type="textarea" :rows="5" v-model="form.output"></el-input>
                </el-form-item>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="样例输入" prop="inputExample">
                            <el-input type="textarea" :rows="5" v-model="form.inputExample"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="样例输出" prop="outputExample">
                            <el-input type="textarea" :rows="5" v-model="form.outputExample"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-form-item label="Hint">
                    <el-input type="textarea" :rows="5" v-model="form.hint"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="editProblem">保存更改</el-button>
                </el-form-item>
            </el-form>

        </el-card>
    </div>
</template>

<script>
    import qs from 'qs'
    export default {
        name: "problemEdit",
        data()
        {
            return{
                form: {
                    id:1,
                    title:"",
                    des:"",
                    input:"",
                    output:"",
                    inputExample:"",
                    outputExample:"",
                    hint:""
                },
                formRules: {
                    title: [
                        { required: true, message: '请输入题目标题', trigger: 'blur' }
                        // { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    des: [
                        { required: true, message: '请输入题目描述', trigger: 'blur' },
                    ],
                    input:[
                        { required: true, message: '请输入输入描述', trigger: 'blur' },
                    ],
                    output:[
                        { required: true, message: '请输入输出描述', trigger: 'blur' },
                    ],
                    inputExample:[
                        { required: true, message: '请输入样例输入', trigger: 'blur' },
                    ],
                    outputExample:[
                        { required: true, message: '请输入样例输出', trigger: 'blur' },
                    ]
                },
            }
        },
        created() {
            this.getDetail()
        }
        ,methods: {
            back()
            {
                this.$router.push({path:'/problemAdmin',query:{page:this.$route.query.page}})
            },
            async getDetail()
            {
                const {data:res} =  await this.$http.get('getProblemDetailAdmin',
                    {params:{id:this.$route.query.id,token:window.localStorage.getItem("token")}})
                // console.log(res);
                this.form = res.data;
            },
            // 提交
            editProblem() {
                //预验证
                this.$refs.form.validate(async valid => {
                    //未通过则return
                    if (!valid) return
                    //通过
                    let result =  this.$axios({
                        method: 'put',
                        url: '/editProblem',
                        headers: { 'content-type': 'application/x-www-form-urlencoded'},
                        data: qs.stringify({
                            token: window.localStorage.getItem("token"),
                            title: this.form.title,
                            des: this.form.des,
                            input:this.form.input,
                            output:this.form.output,
                            inputExample:this.form.inputExample,
                            outputExample:this.form.outputExample,
                            hint:this.form.hint,
                            id:this.form.id
                        })
                    });
                    result.then(res=>{
                        var error = res.data.error;
                        if(error === '0')
                        {
                            this.$message.success('更新题目信息成功')
                            this.$router.push('/problemAdmin')
                        }
                        else
                        {
                            this.$message.success('更新题目信息失败')
                        }


                    })
                })
            },
        }

    }
</script>

<style scoped>

</style>
