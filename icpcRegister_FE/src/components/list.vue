<template>
    <div>
        <!--    卡片-->
        <el-card class="box-card" style="width: 800px;position: absolute;
                top:20px;left:50%;transform: translate(-50%,0%)">
            <div slot="header" class="clearfix">
                <span>SDJZU第八届ICPC校赛报名表</span>
                <el-input placeholder="输入姓名以查找" v-model="queryInfo.key" clearable @clear="getList"
                          @keyup.enter.native="getList" style="width: 300px;position: absolute;right:10px;top:10px">
                    <el-button slot="append" icon="el-icon-search" @click="getList"></el-button>
                </el-input>
            </div>
            <br>
            <div class="text item" style="width: 100%;">
                <el-table :data="list" style="width: 100%"  v-loading="loading"
                          :cell-style="{'text-align':'center'}"
                          :header-cell-style="{'text-align':'center'}">
                    <el-table-column label="班级" prop="theClass" min-width="10%"></el-table-column>
                    <el-table-column label="姓名" prop="name" min-width="15%"></el-table-column>

                    <el-table-column label="性别" min-width="10%" prop="sex"></el-table-column>


                    <el-table-column label="参赛类型" min-width="15%">
                        <template slot-scope="scope">
                            <el-tag v-if="(scope.row.joinType) === '友情参赛'" effect="plain">友情参赛</el-tag>
                            <span v-else>正常</span>
                        </template>
                    </el-table-column>


                    <el-table-column label="缴费情况" min-width="15%">
                        <template slot-scope="scope">
                            <el-tag v-if="(scope.row.isPay) === 1" type="success">已缴费</el-tag>
                            <el-tag v-else-if="(scope.row.isPay) === 0" type="warning">未缴费</el-tag>
                            <el-tag v-else-if="(scope.row.isPay) === 2" type="success">无需缴费</el-tag>
                        </template>
                    </el-table-column>

                </el-table>
            </div>
            <br>
            <!--      分页区域-->
            <div class="block" style="text-align: center">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="queryInfo.page"
                        :page-sizes="[10,20]"
                        :page-size="queryInfo.pre"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="total">
                </el-pagination>
            </div>
            <el-divider></el-divider>
            <span style="font-size: 13px">1.友情参赛选手无需缴费，但也不能获得奖品和校队名额。校内友情参赛选手可正常获得创新学分。</span><br>
            <span style="font-size: 13px">2.本次比赛按惯例设置最佳女队奖，由于是个人赛，此奖项颁发给成绩最好的女选手，可以与其它奖项叠加。</span><br>
            <span style="font-size: 13px">3.参与即得创新学分，一二三等奖分别为10%，20%，30%。</span>

        </el-card>
        <!--    卡片-->

    </div>
</template>

<script>
    export default {
        name: "list",
        data() {
            return {
                queryInfo: {
                        page:1,
                        pre:10,
                        key:""
                    }
                ,
                list: [],
                total:0,
                loading: true
            }
        }
        , created()
        {
            this.getList()
        },
        methods: {
            async getList()
            {
                this.loading = true;
                const {data:res} =  await this.$http.get('getList',{params:this.queryInfo});
                this.loading = false;
                console.log(res);
                if (res.error !== "0") {
                    return this.$message.error('获取题目列表失败！')
                }
                this.list = res.data;
                this.total = res.total;

            },
            //监听pagesize改变
            handleSizeChange(newsize)
            {
                this.queryInfo.pre = newsize;
                this.getList()
            },
            //监听页码的改变
            handleCurrentChange(newPage)
            {
                this.queryInfo.page = newPage;
                this.getList()
            }

        }
    }
</script>

<style scoped>

</style>
