<template>
    <el-card class="box-card" style="width: 70%;align-self: center;margin:0 auto;margin-top: 50px;text-align: center" shadow="hover">
        <el-avatar :size="100" :src="src" style="position: absolute;transform: translate(-50%, -70%); border: 1px solid #eee;border-radius: 50%;"></el-avatar>
        <div style="height: 50px"></div>
        <span style="font-size: 18px;">{{username}}</span>
        <br>
        <span v-if="type==='admin'"  style="margin-top: 100px">
        <el-tag type="success" size="mini">管理员</el-tag>
        </span>
        <span v-else-if="type==='user'">
            <el-tag size="mini">普通用户</el-tag>
        </span>
        <span v-else>
            <el-tag size="mini" type="info">未定义用户</el-tag>
        </span>
        <br>
        <div style="height: 3px"></div>
        <span style="font-size: 12px">{{sign}}</span>
        <br><br>
        <span style="font-size: 14px">
            <el-tooltip class="item" effect="dark" :content="site" placement="top">
                <el-link type="primary" :href="site" target="_blank" :underline="false" icon="el-icon-paperclip">部落格</el-link>
            </el-tooltip>
        </span>
        &nbsp;
        <span style="font-size: 14px">
            <el-tooltip class="item" effect="dark" :content="github" placement="top">
                <el-link type="primary" :href="github" target="_blank" :underline="false" icon="el-icon-s-promotion">Github</el-link>
            </el-tooltip>
        </span>
        <br><br>
        <el-row style="font-size: 14px">
            <el-tag effect="plain" size="mini">解题数:</el-tag>
            <el-tag effect="plain" size="mini">{{solvedNumber}}</el-tag>
        </el-row>
    </el-card>
</template>

<script>
    import global_ from '../../Global'
    export default {
        name: "userCard",
        created()
        {
            this.getUserCardInfo()
        },
        data()
        {
            return{
                src:global_.url+"/getAvatar?id="+this.$route.params.id,
                id:this.$route.params.id,
                type:'',
                site:'',
                sign:'',
                github:'',
                username:'',
                solvedNumber:0
            }
        },
        methods: {
            async getUserCardInfo()
            {
                const {data:res} =  await this.$http.get('getUserCardInfo/'+this.id);
                if(res != null)
                {
                    this.username = res.username
                    this.type = res.type
                    this.site = res.site
                    this.sign = res.sign
                    this.github = res.github
                    this.solvedNumber = res.solvedNumber
                }
                else
                    this.$message.error("获取信息失败")
            }
        }
    }
</script>

<style scoped>
    span {
        font-family:"PingFang SC";
    }

</style>
