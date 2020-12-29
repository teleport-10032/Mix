<template>
    <div>

        <!-- 卡片视图区域 -->
        <el-card>
            <!-- 搜索与添加区域 -->
            <el-row :gutter="20">
                <el-col :span="8">
                    <el-input placeholder="请输入内容" v-model="queryInfo.key" clearable @clear="getUserList"
                              @keyup.enter.native="getUserList">
                        <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>
                </el-col>
            </el-row>
            <br>

            <!-- 用户列表区域 stripe 斑马-->
            <el-table :data="userlist" border stripe v-loading="loading"
                      :header-cell-style="{'text-align':'center'}"
                      :cell-style="{'text-align':'center'}">
                <!--                索引列-->
                <el-table-column label="ID" prop="id"  min-width="10%"></el-table-column>
                <el-table-column label="姓名" prop="username"  min-width="10%"></el-table-column>
                <el-table-column label="邮箱" prop="email"   min-width="20%"></el-table-column>


                <el-table-column label="角色" prop="type" width="70px">
                    <template slot-scope= "scope">
                        <div v-if="scope.row.type==='admin'">
                            <el-tag type="success" effect="light" size="mini">
                                管理员
                            </el-tag>
                        </div>
                        <div v-else>
                            <el-tag effect="light" size="mini">
                                用户
                            </el-tag>
                        </div>
                    </template>
                </el-table-column>

                <el-table-column label="可用性"  width="70px">
                    <template slot-scope="scope">
                        <el-switch v-model="scope.row.available" @change="userAvailableChanged(scope.row.id)">
                        </el-switch>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="125px">
                    <template slot-scope="scope">
                        <!-- 编辑按钮 -->
                        <!--                        enterable=false表示鼠标进入tooltip区域自动隐藏-->
                        <el-tooltip effect="dark" content="编辑" placement="top" :enterable="false">
                            <el-button type="primary" icon="el-icon-edit" size="mini" @click="editUser(scope.row.id)"></el-button>
                        </el-tooltip>
                        <!-- 删除按钮 -->
                        <el-tooltip effect="dark" content="删除" placement="top" :enterable="false">
                        <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeUserById(scope.row.id)"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>

            <br>
            <!-- 分页区域 -->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                           :current-page="queryInfo.page" :page-sizes="[5, 10]"
                           :page-size="queryInfo.pre"
                           layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </el-card>
        <!-- 添加用户的对话框 -->
        <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
            <!-- 内容主体区域 -->
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="addForm.email" @keyup.enter.native="addUser"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="addForm.username" @keyup.enter.native="addUser"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="passwd">
                    <el-input v-model="addForm.passwd" @keyup.enter.native="addUser"></el-input>
                </el-form-item>
            </el-form>
            <!-- 底部区域 -->
            <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
        </el-dialog>

        <!-- 修改用户的对话框 -->
        <el-dialog title="修改用户" :visible.sync="editDialogVisible" width="500px" @close="editDialogClosed">
            <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
                <el-form-item label="id" prop="id">
                    <el-input v-model="editForm.id" disabled></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="editForm.email"
                              @keyup.enter.native="editUserSubmit"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="editForm.username"
                              @keyup.enter.native="editUserSubmit"></el-input>
                </el-form-item>
                <el-form-item label="类型">
                    <el-select v-model="editForm.type" placeholder="账户类型">
                        <el-option label="user" value="user"></el-option>
                        <el-option label="admin" value="admin"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="密码" prop="passwd">
                    <el-input v-model="editForm.passwd"
                              @keyup.enter.native="editUserSubmit"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUserSubmit">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    import qs from 'qs'
    import md5 from 'js-md5';
    export default {
        name:"userAdmin",
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
            };

            return {
                loading:true,
                // 获取用户列表的参数对象
                queryInfo: {
                    // 当前的页数
                    key:"",
                    page: 1,
                    // 当前每页显示多少条数据
                    pre: 5,
                    token: ""
                },
                userlist:[],
                total: 0,
                // 控制添加用户对话框的显示与隐藏
                addDialogVisible: false,
                // 添加用户的表单数据
                addForm: {
                    email: '',
                    username: '',
                    passwd: '',
                },
                // 添加表单的验证规则对象
                addFormRules: {
                    email: [
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { validator: isEmail, trigger: 'blur'}
                    ],
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    passwd: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
                    ]
                },
                // 控制修改用户对话框的显示与隐藏
                editDialogVisible: false,
                // 下面是编辑用户
                // 查询到的用户信息对象
                editForm: {
                    id:0,
                    username:"",
                    email:"",
                    type:"",
                    passwd:""
                },
                // 修改表单的验证规则对象
                editFormRules: {
                    email: [
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { validator: isEmail, trigger: 'blur'},
                    ],
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    passwd: [
                        { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
                    ]
                },
                // 控制分配角色对话框的显示与隐藏
                editUserDialogVisible: false
            }
        },
        created() {
            this.getUserList()
        },
        methods: {
            async getUserList() {
                if(window.localStorage.getItem("token") != null)
                {
                    this.loading = true;
                    this.queryInfo.token = window.localStorage.getItem("token")
                    const { data: res } = await this.$http.get('getUserList', {
                        params: this.queryInfo
                    })
                    this.loading = false;
                    // console.log(res)
                    if (res.error !== "0") {
                        return this.$message.error('获取用户列表失败！')
                    }
                    this.userlist = res.data
                    this.total = res.num

                }
                else
                {
                    this.$message.warning("请先登录")
                    this.$router.push('/')
                }
            },
            // 监听 pagesize 改变的事件
            handleSizeChange(newSize) {
                // console.log(newSize)
                this.queryInfo.pre = newSize
                this.getUserList()
            },
            // 监听 页码值 改变的事件
            handleCurrentChange(newPage) {
                // console.log(newPage)
                this.queryInfo.page = newPage
                this.getUserList()
            },
            // 监听 switch 开关状态的改变
            async userAvailableChanged(id) {

                let result =  this.$axios({
                    method: 'put',
                    url: '/changeUserAvailable',
                    headers: { 'content-type': 'application/x-www-form-urlencoded'},
                    data: qs.stringify({
                        id:id,
                        token: window.localStorage.getItem("token")
                    })
                });
                result.then(res=>{
                    var error = res.data.error;
                    if(error === "0")
                    {
                        this.$message.success('更改可用性成功')
                    }
                    else if(error === "2")
                        this.$message.warning('越权操作')
                    else
                        this.$message.error("更改可用性失败")
                    this.getUserList()
                })
            },
            // 监听添加用户对话框的关闭事件 重置表单
            addDialogClosed() {
                this.$refs.addFormRef.resetFields()
            },
            // 点击按钮，添加新用户
            addUser() {
                //预验证
                this.$refs.addFormRef.validate(async valid => {
                    //未通过则return
                    if (!valid) return
                    //通过
                    let result =  this.$axios({
                        method: 'post',
                        url: '/register',
                        headers: { 'content-type': 'application/x-www-form-urlencoded'},
                        data: qs.stringify({
                            email: this.addForm.email,
                            username: this.addForm.username,
                            passwd: md5(this.addForm.passwd)
                        })
                    });
                    result.then(res=>{
                        var error = res.data.error;
                        if(error === '0')
                        {
                            this.$message.success('添加成功')
                            this.$refs.addFormRef.resetFields()
                            this.addDialogVisible = false
                        }
                        else if(error === '1')
                            this.$message.warning('邮箱已被注册')
                        else if(error === '2')
                            this.$message.warning('用户名已被注册')
                        else if(error === '3')
                            this.$message.warning('数据格式不符合规范')
                        else
                            this.$message.warning('发生SQL错误，请联系管理员')
                        this.getUserList()

                    })
                })
            },
            // 展示编辑用户对话框
            async editUser(id) {
                this.editForm.id = id
                this.editDialogVisible = true
                // 在展示对话框之前，获取所有角色的列表
                const { data: res } = await this.$http.get('/getUserDetailById'
                    ,{params:{id:id,token:window.localStorage.getItem("token")}})
                // console.log(res)
                if (res.error !== "0") {
                    return this.$message.error('获取角色列表失败！')
                }
                this.editForm.username = res.userDetail.username
                this.editForm.email = res.userDetail.email
                this.editForm.type = res.userDetail.type
                this.editUserDialogVisible = true
            },
            // 监听修改用户对话框的关闭事件
            editDialogClosed() {
                this.editForm.passwd = ""
            },
            // 修改用户信息并提交
            editUserSubmit() {
                this.$refs.editFormRef.validate(async valid => {
                    if (!valid) return

                    // console.log(this.editForm)
                    //密码无变动
                    if(this.editForm.passwd === "")
                    {
                        let result =  this.$axios({
                            method: 'put',
                            url: 'updateUserWithoutPasswd',
                            headers: { 'content-type': 'application/x-www-form-urlencoded'},
                            data: qs.stringify({
                                token: window.localStorage.getItem("token"),
                                email: this.editForm.email,
                                username: this.editForm.username,
                                type : this.editForm.type,
                                id: this.editForm.id
                            })
                        });
                        result.then(res=>{
                            var error = res.data.error;
                            if(error === '0')
                            {
                                this.$message.success('修改成功')
                                this.editDialogVisible = false
                            }
                            else if(error === '1')
                                this.$message.warning('邮箱已被注册')
                            else if(error === '2')
                                this.$message.warning('用户名已被注册')
                            else if(error === '3')
                                this.$message.warning('数据格式不符合规范')
                            else if(error === "4")
                                this.$message.warning('发生SQL错误，请联系管理员')
                            else
                                this.$message.warning('越权操作')
                            this.getUserList()
                        })
                    }
                    else
                    {
                        let result =  this.$axios({
                            method: 'put',
                            url: 'updateUser',
                            headers: { 'content-type': 'application/x-www-form-urlencoded'},
                            data: qs.stringify({
                                token: window.localStorage.getItem("token"),
                                email: this.editForm.email,
                                username: this.editForm.username,
                                type : this.editForm.type,
                                id: this.editForm.id,
                                passwd: md5(this.editForm.passwd)
                            })
                        });
                        result.then(res=>{
                            var error = res.data.error;
                            if(error === '0')
                            {
                                this.$message.success('修改成功')
                                this.editDialogVisible = false
                            }
                            else if(error === '1')
                                this.$message.warning('邮箱已被注册')
                            else if(error === '2')
                                this.$message.warning('用户名已被注册')
                            else if(error === '3')
                                this.$message.warning('数据格式不符合规范')
                            else if(error === "4")
                                this.$message.warning('发生SQL错误，请联系管理员')
                            else
                                this.$message.warning('越权操作')
                            this.getUserList()
                        })
                    }
                })
            },
            // 根据Id删除对应的用户信息
            async removeUserById(id) {
                // 弹框询问用户是否删除数据
                const confirmResult = await this.$confirm(
                    '此操作将永久删除该用户, 是否继续?',
                    '提示',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }
                ).catch(err => err)

                // 如果用户确认删除，则返回值为字符串 confirm
                // 如果用户取消了删除，则返回值为字符串 cancel
                // console.log(confirmResult)
                if (confirmResult !== 'confirm') {
                    return this.$message.info('已取消删除')
                }

                const { data: res } = await this.$http.delete('deleteUser',
                    {params:{
                        id:id,
                        token:window.localStorage.getItem("token")
                        }})
                if (res.error !== "0") {
                    return this.$message.error('删除用户失败！')
                }
                this.$message.success('删除用户成功！')
                this.getUserList()
            }
        }
    }
</script>

<style lang="less" scoped>
</style>
