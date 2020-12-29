<template>
    <div style="width: 95%;align-self: center;margin: 0 auto;">
        <el-card class="box-card" style="width: 100%;margin-top: 10px">
            <h3>{{contestObject.title}}</h3>
            <el-tabs v-model="activeName" value="second" @tab-click="handleClick">
                <el-tab-pane label="公告" name="first">
                    <el-card class="box-card" style="width: 100%;margin-top: 10px">
                        {{contestObject.des}}
                    </el-card>
                </el-tab-pane>
                <el-tab-pane label="题目" name="second">
                    题目列表为空
                </el-tab-pane>
                <el-tab-pane label="榜单" name="third">
                    榜单暂时为空
                </el-tab-pane>
            </el-tabs>
        </el-card>


    </div>
</template>

<script>
    export default {
        name: "contest"
        ,
        data()
        {
            return {
                activeName: 'first',
                contestObject: []
            }
        }
        ,
        created() {

            this.getDetail();
        }
        ,
        methods: {
            async getDetail() {
                const {data: res} = await this.$http.get('getContestDetail', {params: {id: this.$route.query.id}})
                this.contestObject = res.data;
                if(res.error !== "0")
                {
                    this.$message.warning('越权访问')
                    return this.$router.push('/');
                }
            },
            handleClick(tab, event) {
                //console.log(tab, event);
            }
        }
        }
</script>

<style scoped>

</style>
