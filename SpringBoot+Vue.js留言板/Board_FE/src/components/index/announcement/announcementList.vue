<template>
  <div style="width: 95%;align-self: center;margin: 0 auto;">
<!--    卡片-->
    <el-card class="box-card" style="width: 100%;">
<!--      公告部分-->
      <div slot="header" class="clearfix">
        <span style="text-align: left !important;">公告栏</span>
      </div>
      <div class="text item" style="width: 100%;">
        <el-table :show-header="false" :data="annList" style="width: 100%" v-loading="loading"
                  :cell-style="{'text-align':'center'}"
                  :header-cell-style="{'text-align':'center'}">
          <el-table-column prop="date" label="时间" min-width="10%"></el-table-column>
          <el-table-column label="标题" min-width="60%" >
            <template slot-scope="scope">
                <div @click="jumpDetail(scope.row.id)">
                    <el-link class="buttonText" :underline="false"
                             style="font-size: 14px;font-weight: normal" @click="jumpDetail(scope.row.id)">
                        {{scope.row.title}}
                    </el-link>
                </div>
            </template>
          </el-table-column>
          <el-table-column label="创建者" min-width="10%">
              <template slot-scope="scope">
                  <el-tag effect="plain">{{scope.row.authorName}}</el-tag>
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
        name: "announcementList"
        ,
        data() {
            return {
                queryInfo:
                    {
                        page:1,
                        pre:5
                    }
                    ,
                annList: [],
                total:0,
                loading: true
            }
        }
        ,
        created()
        {
            if(this.$route.query.page != null)
                this.queryInfo.page = this.$route.query.page - 0;
            this.getAnnList()
        },
        methods: {
            async getAnnList()
            {
                this.loading = true;
                const {data:res} =  await this.$http.get('getAnnList',{params:this.queryInfo});
                this.loading = false;
                // console.log(res);
                if (res.error !== "0") {
                    return this.$message.error('获取公告列表失败')
                }
                this.annList = res.data;
                this.total = res.total;

            },
            //监听pagesize改变
            handleSizeChange(newsize)
            {
                // console.log(newsize);
                this.queryInfo.pre = newsize;
                this.getAnnList()
            },
            //监听页码的改变
            handleCurrentChange(newPage)
            {
                // console.log(newPage);
                this.queryInfo.page = newPage;
                this.getAnnList()
            },
            jumpDetail(id)
            {
                this.$router.push({path:'/announcement',query:{id:id,page:this.queryInfo.page}})
            }


        }
    }
</script>

<style scoped>
.el-card
{
  margin-top: 30px;
}
</style>
