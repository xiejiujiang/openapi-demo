<template>
    <div>
        <chstop></chstop>
        <div class="content-box">
            <div class="content-white">
                <div class="chs-title">
                    <span>长沙银行支付商户信息</span>
                    <a class="back-list" @click="goback()">返回列表</a>
                </div>
                <!-- 表单 -->
                <div class="form-style">
                    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                        <el-form-item label="绑定账套" prop="bookName">
                            <el-select v-model="ruleForm.bookName" placeholder="请选择账套" style="width:100%" @change="name">
                                <el-option
                                v-for="(item,index) in bookList"
                                :key="index"
                                :label="item.name"
                                :value="{bookId: item.id, bookName:item.name}" ></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="商户ID" prop="merchanId">
                            <el-input v-model="ruleForm.merchanId"></el-input>
                        </el-form-item>
                        <el-form-item label="商户秘钥" prop="shkey">
                            <el-input v-model="ruleForm.shkey"></el-input>
                        </el-form-item>
                        <el-form-item label="商户名称" prop="name">
                            <el-input v-model="ruleForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="收款银行名称" prop="bankName">
                            <el-input v-model="ruleForm.bankName"></el-input>
                        </el-form-item>
                        <el-form-item label="收款银行账户" prop="accountName">
                            <el-input v-model.number="ruleForm.accountName"></el-input>
                        </el-form-item>
                        <el-form-item label="公钥" prop="public" id="gong">
                            <el-upload
                            class="upload-demo"
                            multiple
                            :limit="1"
                            :action="action"
                            :on-remove="handleRemove"
                            :before-upload="onBeforeUpload"
                            :http-request="filePublic">
                            <el-button plain icon="el-icon-upload2">点击上传公钥文件</el-button>
                            <!--:file-list="fileList" <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
                            </el-upload>
                        </el-form-item>
                        <el-form-item label="私钥" prop="private" id="si">
                            <el-upload
                            class="upload-demo"
                            multiple
                            :limit="1"
                            :action="action"
                            :on-remove="handleRemove"
                            :before-upload="onBeforeUpload"
                            :http-request="filePrivate">
                            <el-button plain icon="el-icon-upload2">点击上传私钥文件</el-button>
                            <!--:file-list="fileList" <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
                            </el-upload>
                        </el-form-item>
                        <el-form-item style="text-align: center;" id="tijiao">
                            <el-button
                            style="width:200px;height:46px;"
                            type="primary"
                            @click="submitForm(ruleForm)">保存并提交</el-button>
                            <!--:disabled = " disabled "  <div class="check-style">
                                <el-checkbox v-model="shsChecked" @change="infoChenk()">信息填写后需履行<a href="">【长沙银行支付协议】</a></el-checkbox>
                            </div> -->
                        </el-form-item>
                    </el-form>
                </div>
                <div style='height:20px'></div>
            </div>
        </div>
        <!-- 绑定成功 -->
        <div class="pop-succes" v-show="popSuccse">
            <div class="pop-content-suc">
                <div class="suc-icon">支付通道已绑定</div>
                <a @click="goList()">点击跳转列表页</a>
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
		let publicValid = ( rule, value, callback ) => {
			if(this.$refs.public){
				callback(new Error("请上传公钥文件"));
        return	
			}
			callback()
		}
		let privateValid = ( rule, value, callback ) => {
			if(this.$refs.private){
				callback(new Error("请上传公钥文件"));
        return	
			}
			callback()
		}
    return {
      action: 'test',
      disabled: true,
      shsChecked: false,
      ruleForm: {
				bookId: '',
				bookName: '',
        merchanId: '',
        bankName: '',
        accountName: '',
        name: '',
        privateKeyId: '',
        publicKeyId: '',
        shkey: ''
      },
      bookList: [],
      messageForm: '',
      popSuccse: false,
      rules: {
        bookName: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        merchanId: [
          { required: true, message: '请输入商户ID', trigger: 'blur' }
        ],
        shkey: [
          { required: true, message: '请输入商户秘钥', trigger: 'change' }
        ],
        bankName: [
          { required: true, message: '请输入银行名称', trigger: 'blur' }
        ],
        accountName: [
          { required: true, message: '请输入银行账户', trigger: 'blur' },
          { type: 'number', message: '银行账户必须为数字' }
        ],
        name: [
          { required: true, message: '请输入商户名称', trigger: 'blur' }
        ],
        public: [
          { required: true, validator:publicValid }
        ],
        private: [
          { required: true, validator:privateValid }
        ]
			}
		}
  },
  created () {
    this.getBookList()
  },
  methods: {
    goback () {
      this.$router.go(-1)
    },
    // 获得账套列表下拉内容
    getBookList () {
      chsApi.getZhangList().then(r => {
        console.log('账套列表-------', r)
        this.bookList = r
      })
    },
    // 公钥上传
    filePublic (obj) {
      chsApi.publicKey(obj).then(r => {
				this.$emit('uploadSuccess', r)
				console.log('g--------',r.value.id);
				this.ruleForm.publicKeyId = r.value.id
      }).catch(error => {
        console.log('公钥---', error)
        this.$message.error('文件上传失败')
      })
    },
    // 私钥上传
    filePrivate (obj) {
      var pwd = this.ruleForm.shkey
      var merchantId = this.ruleForm.merchanId
      chsApi.privateKey(obj, pwd, merchantId).then(r => {
				this.$emit('uploadSuccess', r)
				console.log('s--------',r.value.id);
				this.ruleForm.privateKeyId = r.value.id
      }).catch(error => {
        console.log('私钥---', error)
        this.$message.error(error)
      })
    },
    // 校验文件
    onBeforeUpload (obj) {
      let isTxt = true
      if (obj.type === 'application/x-pkcs12' || obj.type === 'application/x-x509-ca-cert') {
        isTxt = true
      } else {
        isTxt = false
      }
      if (!isTxt) {
        this.$message.error('上传的文件必须是 pex 或 cer 格式')
      }
      return isTxt
    },
    // 移除
    handleRemove (obj) {
      console.log('删除-----', obj)
      this.$emit('txtRemove', obj)
    },
    infoChenk () {
      if (this.shsChecked) {
        this.disabled = false
      } else {
        this.disabled = true
      }
    },
    // 提交表单
    submitForm: function (val) {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {  
					delete val.shkey
          chsApi.saveMerchant(val).then(r => {
            console.log('success', r)
            if (r.data.result) {
							// this.$message('成功')
              this.popSuccse = true
            } else {
              this.messageForm = r.data.error.hint
              this.openTishi()
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
		},
		name (val) {
			this.ruleForm.bookName = val.bookName
			this.ruleForm.bookId = val.bookId
			if(val == ''){
				callback(new Error('请选择'))
			}
		},
    openTishi () {
      const h = this.$createElement
      //   this.$msgbox.confirm(this.messageForm, {
      //     confirmButtonText: '确定'
      //   })
      this.$msgbox({
        message: h('p', null, [
          h('span', null, this.messageForm)
        ]),
        confirmButtonText: '确定'
      })
    },
    goList () {
			 let name = this.$route.query.name;
       let query = {};
       if(name){
            query.name = name;
       }
      this.$router.push({
				path: '/chslist',
				query: query
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
        .form-style{
            width: 600px;
            margin: 30px auto 20px;
        }
    }
}
.pop-succes{
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.7);
    position: fixed;
    left: 0;
    top:0;
    z-index: 100;
    .pop-content-suc{
        width: 560px;
        height: 160px;
        background-color: #fff;
        box-shadow: 0 0 4px 0 rgba(0,0,0,0.30);
        border-radius: 4px;
        position: absolute;
        left: 50%;
        top:50%;
        margin-left: -280px;
        margin-top: -120px;
        z-index: 110;
        font-size: 26px;
        color: #333333;
        padding-top: 45px;
        .suc-icon{
            width: 220px;
            margin: 0 auto;
            padding-left: 36px;
            background: url(../assets/images/suc.png) no-repeat left center;
            background-size: 32px auto;
        }
        a{
            display: block;
            width: 120px;
            height: 26px;
            line-height: 26px;
            font-size: 14px;
            color: #C30212;
            text-align: center;
            margin: 16px auto 0;
            border:1px solid #C30212;
            border-radius: 3px;
        }
    }
}
</style>
<style>
.el-form-item{ margin-bottom: 30px !important;}
.el-form-item__label{ color:#151515 !important;}
.el-input__inner{border:1px solid #ddd !important;}
#gong .el-button, #si .el-button{color: #c30212;border: 1px solid #C30212;}
#color .el-button--primary{background-color: #C30212;border: 1px solid #C30212;}
#tijiao .el-button--primary.is-disabled, #tijiao .el-button--primary.is-disabled:active, #tijiao .el-button--primary.is-disabled:focus, #tijiao .el-button--primary.is-disabled:hover{
    background-color: #DEDEDE;border: 1px solid  #DEDEDE;
}
.check-style .el-checkbox__input.is-checked+.el-checkbox__label{color: #666!important;}
.check-style .el-checkbox__input.is-checked .el-checkbox__inner, .el-checkbox__input.is-indeterminate .el-checkbox__inner{
    background-color: #C30212 !important;
    border-color: #C30212 !important;
}
.check-style .el-checkbox__input.is-focus .el-checkbox__inner{border-color: #C30212 !important;}
.check-style{
    padding-top: 10px;
    color: #C30212;
}
.check-style a{
    color: #C30212;
}
#color .el-message-box{width: 560px;}
#color .el-message-box__content{padding: 50px !important;}
</style>
