<template>
    <div style="width: 95%;align-self: center;margin: 0 auto;">
        <el-button type="primary" @click="back()" size="small" round>返回</el-button>

        <!--                Accept-->
        <div  v-if="state.state==='Accept'">
            <div style="margin: 0 auto;width:95%;align-self: center">
                <el-card class="box-card" style="background: #F0F9EB;margin-top: 10px">
                    <div  style="padding-left: 10px;">
                        <div>
                            <i class="el-icon-success" style="color: #67C23A;"></i>
                            答案正确
                        </div>
                        <div style="padding-top: 10px;font-size:14px">
                            时间:{{state.timeCost}}ms &nbsp;
                            内存:{{state.memoryCost}}MB &nbsp;
                            作者:{{state.authorId}} &nbsp;
                            语言:{{state.language}} &nbsp;
                        </div>
                    </div>
                </el-card>
                <br>
            </div>
            <!--       代码-->
            <div style="margin: 0 auto;width:95%;align-self: center">
                <el-card>
                    <span style="color: #67C23A;">Code</span>
                    <p>
                        {{state.code}}
                    </p>
                </el-card>
            </div>
        </div>
        <!--    Wrong Answer-->
        <div  v-else-if="state.state==='Wrong Answer'">
            <div style="margin: 0 auto;width:95%;align-self: center">
                <el-card class="box-card" style="background: #FEF0F0;margin-top: 10px">
                    <div  style="padding-left: 10px;">
                        <div>
                            <i class="el-icon-error" style="color: #F56C6C;"></i>
                            答案错误
                        </div>
                        <div style="padding-top: 10px;font-size:14px">
                            时间:{{state.timeCost}}ms &nbsp;
                            内存:{{state.memoryCost}}MB &nbsp;
                            作者:{{state.authorId}} &nbsp;
                            语言:{{state.language}} &nbsp;
                        </div>
                    </div>
                </el-card>
                <br>
            </div>
            <!--       代码-->
            <div style="margin: 0 auto;width:95%;align-self: center">
                <el-card>
                    <span style="color: #F56C6C;">Code</span>
                    <p>
                        {{state.code}}
                    </p>
                </el-card>
            </div>
        </div>
        <!--    Pending-->
        <!--    Wrong Answer-->
        <div  v-else-if="state.state==='Pending'">
            <div style="margin: 0 auto;width:95%;align-self: center">
                <el-card class="box-card" style="background: #FDF6EC;margin-top: 10px">
                    <div  style="padding-left: 10px;">
                        <div>
                            <i class="el-icon-loading" style="color: #E6A23D;"></i>
                            {{state.state}}
                        </div>
                        <div style="padding-top: 10px;font-size:14px">
                            时间:{{state.timeCost}}ms &nbsp;
                            内存:{{state.memoryCost}}MB &nbsp;
                            作者:{{state.authorName}} &nbsp;
                            语言:{{state.language}} &nbsp;
                        </div>
                    </div>
                </el-card>
                <br>
            </div>
            <!--       代码-->
            <div style="margin: 0 auto;width:95%;align-self: center">
                <el-card>
                    <span style="color: #E6A23D;">Code</span>
                    <p>
                        {{state.code}}
                    </p>
                </el-card>
            </div>
        </div>
        <!--    else-->
        <div  v-else>
            <div style="margin: 0 auto;width:95%;align-self: center">
                <el-card class="box-card" style="background: #FDF6EC;margin-top: 10px">
                    <div  style="padding-left: 10px;">
                        <div>
                            <i class="el-icon-error" style="color: #E6A23D;"></i>
                            {{state.state}}
                        </div>
                        <div style="padding-top: 10px;font-size:14px">
                            时间:{{state.timeCost}}ms &nbsp;
                            内存:{{state.memoryCost}}MB &nbsp;
                            作者:{{state.authorId}} &nbsp;
                            语言:{{state.language}} &nbsp;
                        </div>
                    </div>
                </el-card>
                <br>
            </div>
            <!--       代码-->
            <div style="margin: 0 auto;width:95%;align-self: center">
                <el-card>
                    <span style="color: #E6A23D;">Code</span>
                    <p>
                        {{state.code}}
                    </p>
                </el-card>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: "state",
        data()
        {
            return {
                state: []
            }
        }
        ,
        created() {
            this.getDetail();
        }
        ,
        methods: {
            async getDetail() {
                const {data: res} = await this.$http.get('getStateDetail', {params: {id: this.$route.query.id}})
                // console.log(res);
                this.state = res.data;
            }
            ,
            back() {
                this.$router.push({path:'/stateList',query:{page:this.$route.query.page}})
            }
        }
    }
</script>

<style scoped>

</style>
