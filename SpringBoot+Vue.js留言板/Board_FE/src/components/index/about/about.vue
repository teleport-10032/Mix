<template>
  <div style="width: 95%;align-self: center;margin: 0 auto;">
    <div style="width: 100%; height: 30px"></div>
    <!--    卡片-->
    <el-card class="box-card" style="width: 100%;margin: 0 auto;align-self: center">
      <div slot="header" class="clearfix">
        <span style="text-align: left !important;">Target</span>
      </div>
      <div class="text item" style="width: 100%;">
        <!--    折叠面板-->
        <el-collapse  style="width: 100%;align-self: center;margin-top:15px">
          <el-collapse-item title="控制（Secure）" name="1">
            <div>基金会控制异常以防止它们落入平民或敌对组织手中，透过广泛观察及监测并采取行动尽早拦截它们。
            </div>
          </el-collapse-item>
          <el-collapse-item title="收容（Contain）" name="2">
            <div>基金会收容异常以防止它们的影响或效应散播，通过迁移、掩饰、拆除它们或是通过抑制或阻止公众传播它们的知识的方式进行。
            </div>
          </el-collapse-item>
          <el-collapse-item title="保护（Protect）" name="3">
            <div>基金会保护人类免受异常的影响并保护异常本身直至它们被完全理解以及出现自它们的特性及行为上制定的新科学定理为止;</div>
            <div>如果异常被收容时被判定为过度危险，基金会或也会以将异常无效化或摧毁作为最后手段。</div>
          </el-collapse-item>
        </el-collapse>
        <!--    折叠面板-->
      </div>
      <br>

    </el-card>
    <!--    卡片-->
  </div>
</template>

<script>
    export default {
        name: "about"
    }
</script>

<style scoped>

</style>
