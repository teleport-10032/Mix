<template>
  <div>
    <!--    卡片-->
    <el-card class="box-card" style="width: 800px;position: absolute;
                top:20px;left:50%;transform: translate(-50%,0%)">
      <div slot="header" class="clearfix">
        <span>留言列表</span>
        &nbsp;&nbsp;
        <el-button @click="jump" type="primary" plain size="mini">增加留言</el-button>
      </div>
      <br>
      <div class="text item" style="width: 100%;">
        <el-table :data="list" style="width: 100%"  v-loading="loading"
                  :cell-style="{'text-align':'center'}"
                  :header-cell-style="{'text-align':'center'}">
          <el-table-column label="id" prop="id" min-width="10%"></el-table-column>
          <el-table-column label="title" prop="title" min-width="15%"></el-table-column>
          <el-table-column label="content" min-width="10%" prop="content"></el-table-column>
        </el-table>
      </div>
      <br>
      <el-divider></el-divider>
    </el-card>
    <!--    卡片-->
  </div>
</template>

<script>
export default {
  name: "messageList",
  data() {
    return {
      list: [],
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
      const {data:res} =  await this.$http.get('/getBoardList');
      this.loading = false;
      console.log(res);
      this.list = res.data;
    },
    jump()
    {
      this.$router.push('/messageSubmit')
    }
  }
}
</script>

<style scoped>

</style>