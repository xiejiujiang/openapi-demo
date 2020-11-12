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
                        <el-form-item label="绑定账套" prop="region">
                            <el-select v-model="ruleForm.region" placeholder="请选择活动区域" style="width:100%">
                                <el-option label="区域一" value="shanghai"></el-option>
                                <el-option label="区域二" value="beijing"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="商户ID" prop="name">
                            <el-input v-model="ruleForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="商户秘钥" prop="name">
                            <el-input v-model="ruleForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="商户名称" prop="name">
                            <el-input v-model="ruleForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="收款银行" prop="name">
                            <el-input v-model="ruleForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="收款银行账户" prop="name">
                            <el-input v-model="ruleForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="公钥" prop="name" id="gong">
                            <el-upload
                            class="upload-demo"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :before-remove="beforeRemove"
                            multiple
                            :limit="3"
                            :on-exceed="handleExceed"
                            :file-list="fileList">
                            <el-button plain icon="el-icon-upload2">点击上传公钥文件</el-button>
                            <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
                            </el-upload>
                        </el-form-item>
                        <el-form-item label="私钥" prop="name" id="si">
                            <el-upload
                            class="upload-demo"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :before-remove="beforeRemove"
                            multiple
                            :limit="3"
                            :on-exceed="handleExceed"
                            :file-list="fileList">
                            <el-button plain icon="el-icon-upload2">点击上传私钥文件</el-button>
                            <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
                            </el-upload>
                        </el-form-item>
                        <el-form-item style="text-align: center;" id="tijiao">
                            <el-button style="width:200px;height:46px" type="primary" @click="submitForm('ruleForm')">保存并提交</el-button>
                        </el-form-item>
                    </el-form>
                </div>
                <div style='height:20px'></div>
            </div>
        </div>
    </div>
</template>

<script>
import chstop from '@/components/chsTop.vue'
export default {
  components: {
    chstop: chstop
  },
  data () {
    return {
      ruleForm: {
        name: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    goback () {
      this.$router.go(-1)
    },
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePreview (file) {
      console.log(file)
    },
    handleExceed (files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove (file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
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
</style>
<style>
.el-form-item{ margin-bottom: 30px !important;}
.el-form-item__label{ color:#151515 !important;}
.el-input__inner{border:1px solid #ddd !important;}
#gong .el-button, #si .el-button{color: #c30212;border: 1px solid #C30212;}
#tijiao .el-button--primary{background-color: #C30212;border: 1px solid #C30212;}
#tijiao .el-button--primary.is-disabled, #tijiao .el-button--primary.is-disabled:active, #tijiao .el-button--primary.is-disabled:focus, #tijiao .el-button--primary.is-disabled:hover{
    background-color: #DEDEDE;border: 1px solid  #DEDEDE;
}
</style>
