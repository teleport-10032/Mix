<template>
    <div>

        <!-- 卡片视图区域 -->
        <el-card>
            <!-- 搜索与添加区域 -->
            <el-row :gutter="20">
                <el-col :span="8">
                    <el-input placeholder="请输入内容" v-model="queryInfo.key" clearable @clear="getContestList"
                              @keyup.enter.native="getContestList">
                        <el-button slot="append" icon="el-icon-search" @click="getContestList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true">创建比赛</el-button>
                </el-col>
            </el-row>
            <br>

            <!-- 用户列表区域 stripe 斑马-->
            <el-table :data="contestlist" border stripe v-loading="loading"
                      :header-cell-style="{'text-align':'center'}"
                      :cell-style="{'text-align':'center'}">
                <!--                索引列-->
                <el-table-column label="ID" prop="id"  min-width="10%"></el-table-column>
                <el-table-column label="标题" prop="title"  min-width="10%"></el-table-column>
                <el-table-column label="开始时间" prop="startTime"  min-width="15%"></el-table-column>
                <el-table-column label="结束时间" prop="endTime"   min-width="15%"></el-table-column>

                <el-table-column label="可见性"  width="70px">
                    <template slot-scope="scope">
                        <el-switch v-model="scope.row.visible" @change="contestVisibleChanged(scope.row.id)">
                        </el-switch>
                    </template>
                </el-table-column>

                <el-table-column label="作者" prop="authorName"  width="70px"></el-table-column>

                <el-table-column label="操作" width="125px">
                    <template slot-scope="scope">
                        <!-- 编辑按钮 -->
                        <!--                        enterable=false表示鼠标进入tooltip区域自动隐藏-->
                        <el-tooltip effect="dark" content="编辑" placement="top" :enterable="false">
                            <el-button type="primary" icon="el-icon-edit" size="mini" @click="editContest(scope.row.id)"></el-button>
                        </el-tooltip>
                        <!-- 删除按钮 -->
                        <el-tooltip effect="dark" content="删除" placement="top" :enterable="false">
                            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeContestById(scope.row.id)"></el-button>
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
        <!-- 添加对话框 -->
        <el-dialog title="创建比赛" :visible.sync="addDialogVisible" width="550px" @close="addDialogClosed">
            <!-- 内容主体区域 -->
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="addForm.title" @keyup.enter.native="createContest"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="des">
                    <el-input v-model="addForm.des" @keyup.enter.native="createContest"></el-input>
                </el-form-item>
                <el-form-item label="题目列表" prop="problemList">
                    <el-input v-model="addForm.problemList" @keyup.enter.native="createContest"></el-input>
                </el-form-item>
                <el-form-item label="起止时间" prop="date">
                    <div class="block">
                        <el-date-picker value-format="yyyy-MM-dd HH:mm:ss"
                                v-model="addForm.date"
                                type="datetimerange"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                        </el-date-picker>
                    </div>
                </el-form-item>

            </el-form>
            <!-- 底部区域 -->
            <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="createContest">确 定</el-button>
      </span>
        </el-dialog>

        <!-- 修改对话框 -->
        <el-dialog title="修改比赛" :visible.sync="editDialogVisible" width="550px" @close="editDialogClosed">
            <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="80px">
                <el-form-item label="id" prop="id">
                    <el-input v-model="editForm.id" disabled></el-input>
                </el-form-item>
                <el-form-item label="标题" prop="title">
                    <el-input v-model="editForm.title"
                              @keyup.enter.native="editContestSubmit"></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="des">
                    <el-input v-model="editForm.des"
                              @keyup.enter.native="editContestSubmit"></el-input>
                </el-form-item>
                <el-form-item label="题目列表" prop="problemList">
                    <el-input v-model="editForm.problemList"
                              @keyup.enter.native="editContestSubmit"></el-input>
                </el-form-item>
                <el-form-item label="起止时间" prop="date">
                    <div class="block">
                        <el-date-picker value-format="yyyy-MM-dd HH:mm:ss"
                                        v-model="editDate"
                                        type="datetimerange"
                                        range-separator="至"
                                        :start-placeholder="editForm.startTime"
                                        :end-placeholder="editForm.endTime">
                        </el-date-picker>
                    </div>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editContestSubmit">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    import qs from 'qs'
    import md5 from 'js-md5';
    export default {
        name:"contestAdmin",
        data() {
            return {
                value1: '',
                loading:true,
                // 获取用户列表的参数对象
                queryInfo: {
                    // 当前的页数
                    key:"",
                    page: 1,
                    // 当前每页显示多少条数据
                    pre: 5,
                    token:""
                },
                contestlist:[],
                total: 0,
                // 控制添加对话框的显示与隐藏
                addDialogVisible: false,
                // 添加比赛的表单数据
                addForm: {
                    title:"",
                    des:"",
                    problemList:"",
                    date:""
                },
                // 添加表单的验证规则对象
                addFormRules: {
                    title: [
                        { required: true, message: '请输入标题', trigger: 'blur' }
                    ],
                    des: [
                        { required: true, message: '请输入描述', trigger: 'blur' }
                    ],
                    problemList: [
                        { required: true, message: '请输入比赛包含的题目，使用";"间隔', trigger: 'blur' }
                    ],
                    date: [
                        { required: true, message: '请选择比赛起止时间', trigger: 'blur' }
                    ]
                },
                // 控制修改对话框的显示与隐藏
                editDialogVisible: false,
                // 查询到的比赛信息对象
                editForm: {
                    id:0,
                    title:"",
                    des:"",
                    problemList:"",
                    startTime:"",
                    endTime:"",
                    visible:1,
                    authorName:"",
                    date:[]
                },
                editDate:[],
                // 修改表单的验证规则对象
                editFormRules: {
                    title: [
                        { required: true, message: '请输入标题', trigger: 'blur' }
                    ],
                    des: [
                        { required: true, message: '请输入描述', trigger: 'blur' }
                    ],
                    problemList: [
                        { required: true, message: '请输入比赛包含的题目，使用";"间隔', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            this.getContestList()
        },
        methods: {
            dateFilter(input) {
                var d = new Date(input);
                var year = d.getFullYear();
                var month = d.getMonth() < 9 ? "0" + (d.getMonth() + 1) : "" + (d.getMonth() + 1);
                var day = d.getDate() < 10 ? "0" + d.getDate() : "" + d.getDate();
                var hour = d.getHours() < 10 ? "0" + d.getHours() : "" + d.getHours();
                var minutes = d.getMinutes() < 10 ? "0" + d.getMinutes() : "" + d.getMinutes();
                var seconds = d.getSeconds() < 10 ? "0" + d.getSeconds() : "" + d.getSeconds();
                return (year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds);
            },
            async getContestList() {
                if(window.localStorage.getItem("token") != null)
                {
                    this.loading = true;
                    this.queryInfo.token = window.localStorage.getItem("token")
                    const { data: res } = await this.$http.get('/getContestListAdmin', {
                        params: this.queryInfo
                    })
                    this.loading = false;
                    if (res.error !== 0) {
                        return this.$message.error('获取比赛列表失败！')
                    }
                    this.contestlist = res.data
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
                this.getContestList()
            },
            // 监听 页码值 改变的事件
            handleCurrentChange(newPage) {
                // console.log(newPage)
                this.queryInfo.page = newPage
                this.getContestList()
            },
            // 监听 switch 开关状态的改变
            async contestVisibleChanged(id) {

                let result =  this.$axios({
                    method: 'put',
                    url: '/changeContestVisible',
                    headers: { 'content-type': 'application/x-www-form-urlencoded'},
                    data: qs.stringify({
                        id:id,
                        token: window.localStorage.getItem("token")
                    })
                });
                result.then(res=>{
                    var error = res.data.error;
                    if(error === '0')
                    {
                        this.$message.success('更改可见性成功')
                    }
                    else
                        this.$message.warning('越权操作')
                    this.getContestList()
                })
            },
            // 监听添加用户对话框的关闭事件 重置表单
            addDialogClosed() {
                this.$refs.addFormRef.resetFields()
            },
            // 创建比赛
            createContest() {
                // 预验证
                this.$refs.addFormRef.validate(async valid => {
                    //未通过则return
                    if (!valid) return
                    //通过
                    let result =  this.$axios({
                        method: 'post',
                        url: '/createContest',
                        headers: { 'content-type': 'application/x-www-form-urlencoded'},
                        data: qs.stringify({
                            token:window.localStorage.getItem("token"),
                            title:this.addForm.title,
                            des:this.addForm.des,
                            problemList:this.addForm.problemList,
                            startTime:this.addForm.date[0],
                            endTime:this.addForm.date[1]
                        })
                    });
                    result.then(res=>{
                        var error = res.data.error;
                        if(error === '0')
                        {
                            this.$message.success('创建比赛成功')
                            this.$refs.addFormRef.resetFields()
                            this.addDialogVisible = false
                        }
                        else
                            this.$message.warning('创建比赛失败')
                        this.getContestList()

                    })
                })
            },
            // 展示编辑对话框
            async editContest(id) {
                this.editForm.id = id
                this.editDialogVisible = true
                // 在展示对话框之前，获取所有角色的列表
                const { data: res } = await this.$http.get('/getContestDetailByIdAdmin'
                    ,{params:{id:id,token:window.localStorage.getItem("token")}})
                this.editForm= res.data;
                this.editDate.push(this.dateFilter(this.editForm.startTime))
                this.editDate.push(this.dateFilter(this.editForm.endTime))
            },
            // 监听修改对话框的关闭事件
            editDialogClosed() {
                this.editDialogVisible = false
                this.editDate = []
            },
            // 修改并提交
            editContestSubmit() {
                this.$refs.editFormRef.validate(async valid => {
                    if (!valid) return
                    let result =  this.$axios({
                        method: 'put',
                        url: '/editContestById',
                        headers: { 'content-type': 'application/x-www-form-urlencoded'},
                        data: qs.stringify({
                            token:window.localStorage.getItem("token"),
                            title:this.editForm.title,
                            des:this.editForm.des,
                            problemList:this.editForm.problemList,
                            startTime:this.editDate[0],
                            endTime:this.editDate[1],
                            id:this.editForm.id
                        })
                    });
                    result.then(res=>{
                        var error = res.data.error;
                        if(error === '0')
                        {
                            this.editDialogVisible = false
                            this.$message.success('更新比赛信息成功')
                        }
                        else
                            this.$message.warning('更新比赛信息失败')
                        this.getContestList()

                    })
                })
            },
            // 根据Id删除对应的比赛
            async removeContestById(id) {
                const confirmResult = await this.$confirm(
                    '此操作将永久删除该比赛, 是否继续?',
                    '提示',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }
                ).catch(err => err)
                // console.log(confirmResult)
                if (confirmResult !== 'confirm') {
                    return this.$message.info('已取消删除')
                }

                const { data: res } = await this.$http.delete('deleteContestById',
                    {params:{
                            id:id,
                            token:window.localStorage.getItem("token")
                        }})
                if (res.error !== "0") {
                    return this.$message.error('删除用户失败！')
                }
                this.$message.success('删除比赛成功！')
                this.getContestList()
            },

        }
    }
</script>

<style lang="less" scoped>
</style>
