<template>
    <div>{{isLogin}}</div>
</template>

<script>
    import global_ from './Global'
    export default {
        name: 'test',
        data () {
            return {
                isLogin:global_.isLogin
            }
        }
    }
</script>
