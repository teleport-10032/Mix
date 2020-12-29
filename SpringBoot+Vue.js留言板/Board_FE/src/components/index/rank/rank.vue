<template>
  <div>
    <!--    卡片-->
    <el-card class="box-card" style="width: 95%;align-self: center;margin: 0 auto;">
      <!--      柱形图-->
      <div class="text item">
        <div id="main" style="width: 60%;height: 400px;margin: 0 auto;align-self: center"></div>
      </div>
      <!--      柱形图-->

      <!--      表格-->
      <el-table :data="rank" style="width: 100%"
                :cell-style="{'text-align':'center'}"
                :header-cell-style="{'text-align':'center'}">
        <el-table-column prop="rank" label="Rank" min-width="5%"></el-table-column>
        <el-table-column label="User" min-width="5%">
          <template slot-scope="scope">
            <el-link  class="buttonText" :underline="false" @click="jumpToUserCard(scope.row.url)"
                     style="font-size: 14px;font-weight: normal">
              {{ scope.row.username }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column prop="sign" label="Sign" min-width="20%"></el-table-column>
        <el-table-column prop="solvedNum" label="AC" min-width="5%"></el-table-column>
      </el-table>
      <!--      表格-->
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
import echarts from 'echarts'
export default {
  name: "rank",
  data() {
    return {
      queryInfo:
          {
            page: 1,
            pre: 10
          }
      ,
      rank: [],
      total: 0,
      loading: true,
      charts: '',
      opinionData: ["3", "2", "4", "4", "5"],
      top10Username:[],
      top10SolvedNum:[],
    }
  },
  created() {
    let result =  this.$axios({
      method: 'get',
      url: '/getRankChartData',
    });
    result.then(res=>{
      if(res.data.error !== "0")
        this.$message.error("获取榜单数据失败")
      this.top10Username = res.data.usernameList
      this.top10SolvedNum = res.data.solvedNumList
      this.$nextTick(function () {
        this.drawLine('main')
      })
    });

    this.getRank()

  },
  methods: {
    async getRank() {
      this.loading = true;
      const {data: res} = await this.$http.get('getRank', {params: this.queryInfo});
      this.loading = false;
      if (res.error !== "0") {
        return this.$message.error('获取Rank失败')
      }
      this.rank = res.data;
      this.total = res.total;
    },
    // async getRankChartData()
    // {
    //   const {data:res} = await this.$http.get('getRankChartData');
    //   if (res.error !== "0") {
    //     return this.$message.error('获取Rank数据失败')
    //   }
    //   this.top10Username = res.usernameList;
    //   this.top10SolvedNum = res.solvedNumList;
    // },
    //监听pagesize改变
    handleSizeChange(newsize) {
      // console.log(newsize);
      this.queryInfo.pre = newsize;
      this.getRank()
    },
    //监听页码的改变
    handleCurrentChange(newPage) {
      this.test()
      this.queryInfo.page = newPage;
      this.getRank()
    },
    jumpToUserCard(url)
    {
      this.$router.push({path:url})
    },
    drawLine(id) {
      var myChart = echarts.init(document.getElementById(id));
      // 指定图表的配置项和数据
      var option = {
        title: {
          text: 'ACM Rank',
          subtext: '',
          x: 'center',
          y: 'top',
          textAlign: 'left'
        },
        tooltip: {},
        legend: {
          data: ['AC']
        },
        xAxis: {
          data:this.top10Username
        },
        yAxis: {},
        series: [{
          name: 'Rating',
          type: 'bar',
          data:this.top10SolvedNum
        }]
      };

      // 使用刚指定的配置项和数据显示图表
      myChart.setOption(option);
      setTimeout(function () {
        window.onresize = function () {
          myChart.resize();
        }
      }, 200)
    }
  },
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
  text-align: center;
}

.el-card {
  margin-top: 30px;
}
</style>
