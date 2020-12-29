<template>
    <div>

        <!-- 卡片视图区域 -->
        <el-card>
            <!-- 搜索与添加区域 -->
            <el-row :gutter="20">
                <el-col :span="8">
                    <el-input placeholder="请输入内容" v-model="queryInfo.key" clearable @clear="getProblemList"
                              @keyup.enter.native="getProblemList">
                        <el-button slot="append" icon="el-icon-search" @click="getProblemList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="jumpToCreate">添加题目</el-button>
                </el-col>
            </el-row>
            <br>

            <!-- 列表区域 stripe 斑马-->
            <el-table :data="problemlist" border stripe v-loading="loading"
                      :header-cell-style="{'text-align':'center'}"
                      :cell-style="{'text-align':'center'}">
                <!--                索引列-->
                <el-table-column label="ID" prop="id"  min-width="10%"></el-table-column>
                <el-table-column label="标题" prop="title"  min-width="10%"></el-table-column>
                <el-table-column label="创建时间" prop="createTime"   min-width="20%"></el-table-column>
                <el-table-column label="作者" prop="authorName"  min-width="10%"></el-table-column>

                <el-table-column label="可见性"  width="70px">
                    <template slot-scope="scope">
                        <el-switch v-model="scope.row.visible" @change="problemVisibleChanged(scope.row.id)">
                        </el-switch>
                    </template>
                </el-table-column>

                <el-table-column label="操作" width="125px">
                    <template slot-scope="scope">
                        <!-- 编辑按钮 -->
                        <!--                        enterable=false表示鼠标进入tooltip区域自动隐藏-->
                        <el-tooltip effect="dark" content="编辑" placement="top" :enterable="false">
<!--                            @click="editProblem(scope.row.id)"-->
                            <el-button type="primary" icon="el-icon-edit" size="mini" @click="jumpToEdit(scope.row.id)"></el-button>
                        </el-tooltip>
                        <!-- 删除按钮 -->
                        <el-tooltip effect="dark" content="删除" placement="top" :enterable="false">
                            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeProblemById(scope.row.id)"></el-button>
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
    </div>
</template>

<script>
    import qs from 'qs'
    export default {
        name:"problemAdmin",
        data() {
            return {
                loading: true,
                // 获取用户列表的参数对象
                queryInfo: {
                    // 当前的页数
                    key:"",
                    page: 1,
                    // 当前每页显示多少条数据
                    pre: 5,
                    token: ""
                },
                problemlist:[],
                total: 0,
            }
        },
        created() {
            if(this.$route.query.page != null)
                this.queryInfo.page = this.$route.query.page - 0;
            this.getProblemList()
        },
        methods: {
            async getProblemList() {
                if(window.localStorage.getItem("token") != null)
                {
                    this.loading = true;
                    this.queryInfo.token = window.localStorage.getItem("token")
                    const { data: res } = await this.$http.get('getProblemListAdmin', {
                        params: this.queryInfo
                    })
                    this.loading = false
                    // console.log(res)
                    if (res.status !== 1) {
                        return this.$message.error('获取题目列表失败！')
                    }
                    this.problemlist = res.data
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
                this.getProblemList()
            },
            // 监听 页码值 改变的事件
            handleCurrentChange(newPage) {
                // console.log(newPage)
                this.queryInfo.page = newPage
                this.getProblemList()
            },
            // 监听 switch 开关状态的改变
            async problemVisibleChanged(id) {

                let result =  this.$axios({
                    method: 'put',
                    url: '/changeProblemVisible',
                    headers: { 'content-type': 'application/x-www-form-urlencoded'},
                    data: qs.stringify({
                        id: id,
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
                    this.getProblemList()
                })
            },
            // 根据Id删除对应的用户信息
            async removeProblemById(id) {
                // 弹框询问用户是否删除数据
                const confirmResult = await this.$confirm(
                    '此操作将永久删除该题目, 是否继续?',
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

                const { data: res } = await this.$http.delete('deleteProblemById',
                    {params:{
                            id:id,
                            token:window.localStorage.getItem("token")
                        }})
                if (res.error !== "0") {
                    return this.$message.error('删除题目失败！')
                }
                this.$message.success('删除题目成功！')
                this.getProblemList()
            },
            jumpToCreate()
            {
                this.$router.push({path:'/problemCreate'})
            },
            jumpToEdit(id)
            {
                this.$router.push({path:'/problemEdit',query:{id:id,page:this.queryInfo.page}})
            }

        }
    }
</script>

<style lang="less" scoped>
</style>
