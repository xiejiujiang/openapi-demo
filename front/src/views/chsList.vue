<template>
  <div class="main" v-title data-title="openapi-demo" v-show="listShow">
    <chstop></chstop>
    <div class="content-box">
        <div class="content-white">
            <div class="chs-title">
                <span>商户信息</span>
                <a class="add-btn" @click="goFrom()">新增</a>
                <p>(账套新增支付通道）</p>
            </div>
            <!-- 列表信息 -->
            <div class="chs-table">
                <div class="chs-tab-border">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                            <th width="20%">账套名称</th>
                            <th width="25%">商户名称</th>
                            <th width="20%">商户ID</th>
                            <th width="20%">收款银行</th>
                            <th width="15%">操作</th>
                        </tr>
                        <tr v-for="item in chsList" :key="item.id">
                            <td>{{item.bookName}}</td>
                            <td>{{item.name}}</td>
                            <td>{{item.merchanId}}</td>
                            <td>{{item.bankName}}</td>
                            <td>
                                <a class="btn-info" @click="goChaInfo(item.id)">查看详情</a>
                            </td>
                        </tr>
                        <!-- <tr>
                            <td>账套名称001</td>
                            <td>畅捷通信息技术股份有限公司</td>
                            <td>6000 0000 0000 0000 000</td>
                            <td>长沙银行</td>
                            <td>
                                <a class="btn-info" @click="goChaInfo()">查看详情</a>
                            </td>
                        </tr> -->
                    </table>
                </div>
            </div>
            <!-- 分页 :hide-on-single-page='true'-->
            <div id="page-style">
                <el-pagination
                background
                prev-text=" 上一页 "
                next-text=" 下一页 "
                @current-change="handleCurrentChange"
                @size-change="handleSizeChange"
                :current-page="page.index"
                :page-size="page.size"
                layout="prev, pager, next"
                :total="page.total">
                </el-pagination>
            </div>
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
      page: {
        index: 0,
        size: 10,
        total: 0
      },
      chsList: [],
      listShow: false,
      query: {}
    }
  },
  created () {
    this.getchsList()
    this.uesrName = this.$route.query.name
  },
  methods: {
    handleCurrentChange (val) {
      console.log('页码：', val)
      this.page.index = val
      this.getchsList()
    },
    handleSizeChange (val) {},
    // 获取列表信息
    getchsList () {
      try {
        chsApi.getchsBankList({ page: this.page.index, size: this.page.size }).then((r) => {
          this.page.total = parseInt(r.totalCount)
          console.log('r---> ', r)
          this.chsList = r.resultList
          if( this.chsList.length == '0' ){
            this.goFrom ()
          }else {
            this.listShow = true
          }
        })
      } catch (e) {
        this.$message.error('系统出现错误,请稍后再试...')
      } finally {
        console.log('获取列表')
      }
    },
    // 获取url name值
    getName () {
      let name = this.$route.query.name;
      if(name){
        this.query.name = name;
      }
    },
    // 跳转详情页
    goChaInfo (id) {

    //    let name = this.$route.query.name;
    //    let query = {};
    //    if(name){
    //         query.name = name;
    //    }
      this.getName ()
      this.$router.push({
        path: '/chsinfo/' + id,
        query: this.query
      })
    },
    // 跳转表单页
    goFrom () {
      this.getName ()
      this.$router.push({
        path: '/chsform',
        query: this.query
      })
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
            span{
                display: block;
                font-size: 18px;
                line-height: 65px;
                font-weight: bold;
                color: #000;
            }
            a.add-btn{
                display: block;
                width: 96px;
                height: 28px;
                line-height: 28px;
                border-radius: 4px;
                color: #fff;
                background: #C30212;
                text-align: center;
                margin: 0 20px;
                font-weight: 500;
            }
            p{
                color: #666666;
            }
        }
        .chs-table{
            width: 100%;
            height: auto;
            padding: 20px;
            font-size: 12px;
            color: #666;
            text-align: center;
            .chs-tab-border{
                width: 100%;
                border:1px solid #ddd;
                border-bottom:none;
                th{
                    height: 40px;
                    line-height: 40px;
                    border-bottom:1px solid #ddd;
                    background-color: #f8f8f8;
                }
                td{
                    height: 40px;
                    line-height: 40px;
                    border-bottom:1px solid #ddd;
                    a.btn-info{
                        color: #C30212;
                        width: 65px;
                        padding-left: 15px;
                        background: url(../assets/images/btnicon.png) no-repeat left center;
                        background-size: 13px auto;
                    }
                }
            }
        }
        #page-style{
            text-align: center;
            padding-bottom: 20px;
        }
    }
}
</style>
<style>
.el-pagination.is-background .el-pager li:not(.disabled).active{background-color: #C30212 !important;}
#page-style .btn-next, #page-style .btn-prev{ padding: 0 10px;}
</style>
