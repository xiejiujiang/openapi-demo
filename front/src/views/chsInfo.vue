<template>
    <div class="main" v-title data-title="长沙银行公共支付">
        <chstop></chstop>
        <div class="content-box">
            <div class="content-white">
                <div class="chs-title">
                    <span>长沙银行支付商户信息</span>
                    <a class="back-list" @click="goback()">返回列表</a>
                </div>
                <!-- 详细信息 -->
                <ul class="info-list">
                    <li>
                        <span class="info-left-sty">绑定账套：</span>
                        <span>{{chsInfo.bookName}}</span>
                    </li>
                    <li>
                        <span class="info-left-sty">商户ID：</span>
                        <span>{{chsInfo.merchanId}}</span>
                    </li>
                    <li>
                        <span class="info-left-sty">商户名称：</span>
                        <span>{{chsInfo.name}}</span>
                    </li>
                    <li>
                        <span class="info-left-sty">收款银行：</span>
                        <span>{{chsInfo.bankName}}</span>
                    </li>
                    <li>
                        <span class="info-left-sty">收款银行账户：</span>
                        <span>{{chsInfo.accountName}}</span>
                    </li>
                    <li>
                        <span class="info-left-sty">公钥：</span>
                        <span>{{chsInfo.publicKeyFileName}}</span>
                    </li>
                    <li>
                        <span class="info-left-sty">私钥：</span>
                        <span>{{chsInfo.privateKeyFileName}}</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
import chstop from '@/components/chsTop.vue'
import chsApi from '@/api/chsApi.js'
export default {
  components: {
    chstop: chstop
  },
  data () {
    return {
      id: '',
      chsInfo: {}
    }
  },
  created () {
    this.getchsInfo()
  },
  methods: {
    goback () {
      this.$router.go(-1)
    },
    getchsInfo () {
      try {
        var id = this.$route.params.id
        chsApi.getchsBankInfo(id).then((r) => {
          console.log('订单详情---> ', r)
          this.chsInfo = r
        })
      } catch (e) {
        this.$message.error('系统出现错误,请稍后再试...')
      } finally {
        console.log('获取详情')
      }
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
        .chs-title{
            width: 100%;
            height: 65px;
            padding: 0 20px;
            border-bottom: 1px solid #DDDDDD;
            display: flex;
            align-items:center;
            justify-content:space-between;
            span{
                display: block;
                font-size: 18px;
                line-height: 65px;
                font-weight: bold;
                color: #000;
            }
            a.back-list{
                width: 96px;
                height: 28px;
                line-height: 28px;
                color: #c30212;
                border: 1px solid #C30212;
                border-radius: 4px;
                text-align: center;
                font-size: 14px;
            }
        }
        .info-list{
            width: 600px;
            margin: 0 auto;
            list-style: none;
            font-size: 14px;
            padding: 40px 0 20px;
            li{
                margin-bottom: 20px;
                text-align: center;
                overflow: hidden;
                span{
                    display: block;
                    float: left;
                    height: 30px;
                    line-height: 30px;
                }
                .info-left-sty{
                    width: 260px;
                    text-align: right;
                }
            }
        }
    }
}

</style>
