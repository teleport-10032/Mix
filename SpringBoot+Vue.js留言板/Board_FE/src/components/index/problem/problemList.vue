<template>
    <div>
      <!--    卡片-->
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>问题列表</span>
        </div>
        <div class="text item" style="width: 100%;">
          <el-table :data="problemList" style="width: 100%"  v-loading="loading"
                    :cell-style="{'text-align':'center'}"
                    :header-cell-style="{'text-align':'center'}">

            <el-table-column label="#" min-width="5%">
              <template slot-scope="scope">
                <div @click="jumpDetail(scope.row.id)">
                  <el-tag type="warning" effect="dark" size="mini" v-if="scope.row.isAttempt === 1 && scope.row.isAccept !== 1">Attempt</el-tag>
                  <el-tag type="success" effect="dark" size="mini" v-else-if="scope.row.isAccept === 1">Accept</el-tag>
                </div>
              </template>

            </el-table-column>

            <el-table-column label="id" min-width="5%">
              <template slot-scope="scope">
                <div @click="jumpDetail(scope.row.id)">
                  <el-link class="buttonText" :underline="false"
                           style="font-size: 14px;font-weight: normal" @click="jumpDetail(scope.row.id)">
                    {{scope.row.id}}
                  </el-link>
                </div>
              </template>
            </el-table-column>


            <el-table-column label="标题" min-width="30%">
              <template slot-scope="scope">
                <div @click="jumpDetail(scope.row.id)">
                  <el-link class="buttonText" :underline="false"
                           style="font-size: 14px;font-weight: normal" @click="jumpDetail(scope.row.id)">
                    {{scope.row.title}}
                  </el-link>
                </div>
              </template>
            </el-table-column>

            <el-table-column prop="acSubmit" label="AC" min-width="5%"></el-table-column>
            <el-table-column prop="totalSubmit" label="提交" min-width="5%"></el-table-column>
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

      </el-card>
      <!--    卡片-->

    </div>
</template>

<script>
    export default {
        name: "problemList"
        ,
        data() {
            return {
                queryInfo:
                    {
                        page:1,
                        pre:10,
                        token: ""
                    }
                ,
                problemList: [],
                total:0,
                loading: true
            }
        }
        , created()
        {
            if(this.$route.query.page != null)
              this.queryInfo.page = this.$route.query.page - 0;
            if(window.localStorage.getItem("token") != null)
            {
              this.queryInfo.token = window.localStorage.getItem("token")
            }
            else
              this.queryInfo.token = " "
            this.getProblemList()
        },
        activated()
        {
        },
        methods: {
           async getProblemList()
          {
                this.loading = true;
                const {data:res} =  await this.$http.get('getProblemList',{params:this.queryInfo});
                this.loading = false;
                // console.log(res);
                if (res.status !== 200) {
                    return this.$message.error('获取题目列表失败！')
                }
                this.problemList = res.data;
                this.total = res.total;

            },
            //监听pagesize改变
            handleSizeChange(newsize)
            {
                // console.log(newsize);
                this.queryInfo.pre = newsize;
                this.getProblemList()
            },
            //监听页码的改变
            handleCurrentChange(newPage)
            {
                // console.log(newPage);
                this.queryInfo.page = newPage;
                this.getProblemList()
            },
            jumpDetail(id)
            {
                this.$router.push({path:'/problem',query:{id:id,page:this.queryInfo.page}})
            }


        }
    }
</script>

<style less scoped>


</style>
