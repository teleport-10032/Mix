<template>
  <div style="width: 95%;align-self: center;margin: 0 auto;">
    <el-button type="primary" @click="back()" size="small" round>返回</el-button>
    <!--    卡片-->
    <el-card class="box-card" style="width: 100%;margin-top: 10px">
      <!--      公告详情-->
      <div slot="header" class="clearfix">
        <span style="text-align: left !important;margin-left: 10px;font-size: 20px;">公告详情</span>
      </div>
      <div style="padding-left: 25px">
        <h3>
          {{ann.title}}
        </h3>
      </div>
      <br>
      <div style="padding-left: 25px">
        <p>
          {{ann.content}}
        </p>
      </div>
      <div style="text-align: right;margin-right: 10px;margin-top: 20px">
        <span>
          <el-tag effect="plain">{{ann.authorName}}</el-tag>
          <el-tag effect="plain">{{ann.date}}</el-tag>
        </span>
      </div>
    </el-card>
    <!--    卡片-->
  </div>
</template>

<script>
    export default {
        name: "announcement"
        ,
        data()
        {
            return {
                ann: []
            }
        }
        ,
        created() {
            this.getDetail();
        }
        ,
        methods: {
            async getDetail()
            {
                const {data:res} =  await this.$http.get('getAnnDetail',{params:{id:this.$route.query.id}})
                if(res.error !== "0")
                {
                  this.$message.warning('越权访问')
                  return this.$router.push('/');
                }
                this.ann = res.data;
            }
            ,
            back()
            {
              this.$router.push({path:'/announcementList',query:{page:this.$route.query.page}})
            }

        }
    }
</script>

<style scoped>

</style>
