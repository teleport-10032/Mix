<template>
  <div style="width: 95%;align-self: center;margin: 0 auto;">
    <el-button type="primary" @click="back()" size="small" round>返回</el-button>


    <el-card class="box-card" style="width: 100%;margin-top: 10px">
      <h3>{{problem.title}}</h3>

<!--      描述-->
      <br>
      <span style="color: #409EFF;">题目描述</span>
      <div style="padding-left: 25px">
        <p>
          {{problem.des}}
        </p>
      </div>

<!--      输入描述-->
      <br>
      <span style="color: #409EFF;">输入</span>
      <div style="padding-left: 25px">
        <p>
          {{problem.input}}
        </p>
      </div>

      <!--      输出描述-->
      <span style="color: #409EFF;">输出</span>
      <div style="padding-left: 25px">
        <p>
          {{problem.output}}
        </p>
      </div>

      <!--      样例-->
      <br>
      <div style="display: flex;flex-direction: row;">
        <div style="flex: 10">
          <span style="color: #409EFF;">输入样例</span>
          <div style="border-radius: 0;margin-top: 10px;padding-left:10px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);line-height: 20px">
            <p style="padding-top: 10px;padding-bottom: 10px">
              {{problem.inputExample}}
            </p>
          </div>
        </div>
        <div style="flex:2"></div>

        <div style="flex: 10">
          <span style="color: #409EFF;">输出样例</span>
          <div style="border-radius: 0;margin-top: 10px;padding-left:10px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);line-height: 20px">
            <p style="padding-top: 10px;padding-bottom: 10px">
              {{problem.outputExample}}
            </p>
          </div>
        </div>
        <div style="flex:2"></div>
      </div>

      <!--      hint-->
      <span style="color: #409EFF;">提示</span>
      <div style="border-radius: 0;margin-top: 10px;padding-left:10px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);line-height: 20px">
        <p style="padding-top: 10px;padding-bottom: 10px">
          {{problem.hint}}
        </p>
      </div>

    </el-card>

    <el-card class="box-card" style="width: 100%;margin-top: 10px">
<!--      代码区-->
      <span style="color: #409EFF;">代码提交</span>
      <el-form ref="form" style="padding-top: 15px">
        <el-form-item>
          <el-input type="textarea"  :rows="15" v-model="code"   placeholder="Input Your Code Here..." clearable>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit()">提交</el-button>
        </el-form-item>
      </el-form>
    </el-card>

  </div>
</template>

<script>
  import qs from 'qs'
    export default {
        name: "problem"
        ,
        data()
        {
            return {
                problem: [],
                code: '',
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
              const {data:res} =  await this.$http.get('getProblemDetail',{params:{id:this.$route.query.id}})
              if(res.error !== "0")
              {
                  this.$message.warning('越权访问')
                  return this.$router.push('/');
              }

              // console.log(res);
              this.problem = res.data;
          },
          submit() {
            var token = window.localStorage.getItem('token')
            if(token != null)
            {
              let result =  this.$axios({
                method: 'post',
                url: '/submitProblemCode',
                headers: { 'content-type': 'application/x-www-form-urlencoded'},
                data: qs.stringify({
                  code: this.code,
                  problemId:this.$route.query.id,
                  token: token,
                  language:'C++'
                })
              });
              result.then(res=>{
                if(res.data != null)
                {
                  if(res.data.error === '0'){
                    this.$message.success('提交成功');
                    this.$router.push('/stateList');
                  }
                  else if(res.data.error === '1'){
                    this.$message.warning('登陆身份已过期，请重新登录')
                    window.localStorage.clear()
                    this.$router.push('/');
                  }
                  else
                    this.$message.warning('越权访问')
                }
              })
            }
            else
            {
              this.$message.warning("请先登录")
            }
          },
          back()
          {
            this.$router.push({path:'/problemList',query:{page:this.$route.query.page}})
          }
        }
    }
</script>

<style>
  .CodeMirror {
    line-height: 16px !important;
    height: auto !important;
  }
  .CodeMirror-scroll {
    min-height: 300px;
    max-height: 1000px;
  }
</style>
