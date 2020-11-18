<template>
    <div class="main" v-title data-title="长沙银行公共支付">
        <chstop></chstop>
        <div class="content-box">
            <div class="content-white">
                <div class="error">没有登录状态呦，请前往畅捷通应用市场产品详情页进行开通授权~~</div>
                <a class="error-lin" @click="goMarket()">点击前往</a>
            </div>
        </div>
    </div>
</template>

<script>
import chstop from '@/components/chsTop.vue'
import chsApi from '@/api/chsApi.js'
import domainConfig from '@/config/DomainConfig.js'
export default {
  components: {
    chstop: chstop
  },
  data () {
    return {
    }
  },
  created () {
  },
  methods: {
    goback () {
      this.$router.go(-1)
    },
    goMarket() {
      let marketUrl = domainConfig.getDomain().market
      window.open(marketUrl + '/proDetails/10243')
    }
  }
}
</script>

<style scoped lang="scss">
.content-box{
    width: 100%;
    height: auto;
    padding: 20px;
    .content-white{
        width: 100%;
        height: auto;
        background-color: #fff;
        padding-top: 100px;
        padding-bottom: 100px;
        .error{
          width: 830px;
          margin: 0 auto 30px;
          font-size: 26px;
          font-weight: bold;
          color: #333333;
          padding-left: 40px;
          background: url(../assets/images/error.png) no-repeat left center;
          background-size: 32px;
        }
        a.error-lin{
          display: block;
          width: 200px;
          height: 46px;
          line-height: 46px;
          text-align: center;
          background-color: #C30212;
          border-radius: 4px;
          color: #fff;
          font-size: 18px;
          margin: 0 auto;
          font-weight: bold;
        }
    }
}
</style>
