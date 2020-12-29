<template>
    <el-card shadow="hover">
        <div style="width: 200px">
            <el-avatar :size="100" :src="src" ></el-avatar>
        </div>
        <br>
        &nbsp;&nbsp;
        <el-button  @click="dialogVisible2 = true" size="small" type="success">更改头像</el-button>
        <el-dialog title="上传头像" :visible.sync="dialogVisible2" width="335px">
            <el-form :model="form" ref="formRef">
                <el-form-item :label-width="formLabelWidth"
                              ref="uploadElement">
                    <el-upload ref="upload"
                               action="http://localhost:8888/upload"
                               accept="image/png,image/jpg,image/jpeg"
                               list-type="picture-card"
                               :limit=limitNum
                               :auto-upload="false"
                               :before-upload="handleBeforeUpload"
                               :on-remove="handleRemove"
                               :on-change="imgChange"
                               :class="{hide:hideUpload}">
                        <i class="el-icon-plus"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <el-button size="small"
                               type="primary"
                               @click="uploadFile">立即上传</el-button>
                    <el-button size="small"
                               @click="cancel">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

    </el-card>

</template>

<script>
    import global_ from '../../Global'
    import qs from 'qs'
    export default {
        name: "userAvatar",
        created()
        {
            let result =  this.$axios({
                method: 'post',
                url: '/getUserIdByToken',
                headers: { 'content-type': 'application/x-www-form-urlencoded'},
                data: qs.stringify({
                    token: window.localStorage.getItem("token")
                })
            });
            result.then(res=>{
                if(res.data != null)
                {
                    this.src = global_.url+"/getAvatar?id="+res.data.userId;
                }
            })
        },
        data()
        {
            return {
                username:"",
                hideUpload: false,
                dialogImageUrl: '',
                formLabelWidth: '80px',
                limitNum: 1,
                form: {},
                dialogVisible2: false,
                myHeaders:{'content-type': 'application/x-www-form-urlencoded'},
                src:""
            }
        },
        methods: {
            handleBeforeUpload(file) {
                let flag = 0
                if (!(file.type === 'image/png' || file.type === 'image/jpg' || file.type === 'image/jpeg')) {
                    flag = 1;
                    this.$notify.warning({
                        title: '警告',
                        message: '请上传格式为image/png, image/gif, image/jpg, image/jpeg的图片'
                    })
                    return false
                }
                let size = file.size / 1024 / 1024
                // console.log(size)
                if (size > 2) {
                    flag = 1;
                    this.$notify.warning({
                        title: '警告',
                        message: '图片大小必须小于2M'
                    })
                    return false
                }
                if(flag === 0){
                    let fd = new FormData();
                    fd.append("avatar", file);
                    fd.append("token",window.localStorage.getItem("token"))
                    let result =  this.$axios({
                        method: 'put',
                        url: '/uploadAvatar',
                        headers: { 'content-type': 'application/x-www-form-urlencoded'},
                        data: fd
                    });
                    result.then(res=>{
                        // console.log(res)
                        if(res.data.error === "0")
                        {
                            this.dialogVisible2 = false
                            this.$refs.formRef.resetFields()
                            this.$message.success("更新头像成功，刷新查看")
                        }
                        else
                            this.$message.error("更新头像失败")
                    })
                }
                return false
            },
            handleRemove(file, fileList) {
                this.hideUpload = fileList.length >= this.limitNum;
            },
            uploadFile() {
                this.$refs.upload.submit()
            },
            imgChange(files, fileList) {
                this.hideUpload = fileList.length >= this.limitNum;
                if (fileList) {
                    this.$refs.uploadElement.clearValidate();
                }
            },
            cancel() {
                this.$refs.formRef.resetFields()
                this.dialogVisible2 = false
            }
        }
    }
</script>

<style lang="scss">
    .hide .el-upload--picture-card {
        display: none;
    }
    .el-form-item{
        text-align: left;
    }
</style>
