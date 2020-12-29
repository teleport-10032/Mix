<template>
  <div>

    <!--    卡片-->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>竞赛列表</span>
      </div>

      <div class="text item" style="width: 100%;">
        <el-table :data="contestList" style="width: 100%" v-loading="loading"
                  :cell-style="{'text-align':'center'}"
                  :header-cell-style="{'text-align':'center'}">
          <el-table-column label="标题" min-width="10%">
            <template slot-scope="scope">
              <div @click="jumpDetail(scope.row.id)">
                <el-link class="buttonText" :underline="false"
                         style="font-size: 14px;font-weight: normal" @click="jumpDetail(scope.row.id)">
                  {{scope.row.title}}
                </el-link>
              </div>
            </template>
          </el-table-column>

          <el-table-column prop="startTime" label="开始时间" min-width="10%"></el-table-column>
          <el-table-column prop="endTime" label="结束时间" min-width="10%"></el-table-column>


          <el-table-column label="状态" min-width="5%">
            <template slot-scope="scope">
              <div  v-if="scope.row.state==='已结束'">
<!--                    style="color: #F56C6C;border: 1px dashed #F56C6C;border-radius:0px">-->
                <el-tag type="danger">已结束</el-tag>
              </div>
              <div  v-if="scope.row.state==='筹备中'">
<!--                    style="color: #E6A23C;border: 1px dashed #E6A23C;border-radius:0px"-->
                <el-tag type="warning">筹备中</el-tag>
              </div>
              <div  v-if="scope.row.state==='进行中'">
                <el-tag type="success">进行中</el-tag>
<!--                    style="color: #67C23A;border: 1px dashed #67C23A;border-radius:0px">-->
              </div>
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
                :page-sizes="[5,10,20]"
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
        name: "contestList",
        data() {
            return {
                queryInfo:
                        {
                          page:1,
                          pre:5
                        }
                ,
                contestList: [],
                total:0,
                loading: true
            }
        }
        ,
      created()
      {
        this.getContestList();
      },
      methods: {
            async getContestList()
            {
              this.loading = true;
              const {data:res} =  await this.$http.get('getContestList',{params:this.queryInfo});
              this.loading = false;
              if (res.status !== 200) {
                return this.$message.error('获取题目列表失败！')
              }
              this.contestList = res.data;
              // console.log(this.contestList);
              this.total = res.total;
            },
            jumpDetail(id)
            {
              this.$router.push({path:'/contest',query:{id:id}})
            },
            //监听pagesize改变
            handleSizeChange(newsize)
            {
              // console.log(newsize);
              this.queryInfo.pre = newsize;
              this.getContestList()
            },
            //监听页码的改变
            handleCurrentChange(newPage)
            {
              // console.log(newPage);
              this.queryInfo.page = newPage;
              this.getContestList()
            },
      }
    }
</script>

<style scoped>

</style>
