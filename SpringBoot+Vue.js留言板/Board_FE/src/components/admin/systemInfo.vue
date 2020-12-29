<template>
    <div>
        <el-card>
            <el-tag  effect="plain" size="mini">SystemInfo</el-tag>
            <br><br>
            <el-tag effect="plain" size="mini">用户数量:</el-tag> <el-tag effect="plain" size="mini">{{userNum}}</el-tag>
            <br><br>
            <el-tag effect="plain" size="mini">题目数量:</el-tag> <el-tag effect="plain" size="mini">{{problemNum}}</el-tag>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "systemInfo",
        data()
        {
            return{
                userNum:-1,
                problemNum:-1,
            }
        },
        created() {
            this.getSystemInfo()
        },
        methods:{
            async getSystemInfo()
            {
                const {data:res} =  await this.$http.get('getSystemInfo',{params:{token:window.localStorage.getItem("token")}});
                this.userNum = res.userNum;
                this.problemNum = res.problemNum;
            }
        }
    }
</script>

<style scoped>

</style>
