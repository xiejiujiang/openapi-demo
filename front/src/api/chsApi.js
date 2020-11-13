import axios from 'axios'
const Axios = axios.create({
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
})
export default {
  getchsBankList: ({ page = 0, size = 1 }) => {
    return new Promise((resolve) => {
      axios
        .get('/api/merchant/findMerchantById/' + page + '/' + size)
        .then((r) => resolve(r.data.value))
    })
  },
  getchsBankInfo: (id) => {
    return new Promise((resolve) => {
      axios
        .get('/api/merchant/findMerchantById/' + id)
        .then((r) => resolve(r.data.value))
    })
  },
  getZhangList: () => {
    return new Promise((resolve) => {
      axios.get('/api/merchant/getBookList')
        .then((r) => resolve(r.data.value))
    })
  },
  // 公钥
  publicKey: (obj) => {
    return new Promise(
      (resolve, reject) => {
        const config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
        const formData = new FormData()
        formData.append('file', obj.file)
        Axios.post('/api/merchant/uploadPublicKey', formData, config)
          .then((r) => {
            if (r.data.result) {
              resolve(r.data)
            } else {
              reject(r.data.error.hint)
            }
          })
      }
    )
  },
  // 私钥
  privateKey: (obj, pwd, merchantId) => {
    return new Promise(
      (resolve, reject) => {
        const config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
        const formData = new FormData()
        formData.append('file', obj.file)
        formData.append('pwd', pwd)
        formData.append('merchantId', merchantId)
        Axios.post('/api/merchant/uploadPrivateKey', formData, config)
          .then((r) => {
            if (r.data.result) {
              resolve(r.data)
            } else {
              reject(r.data.error.hint)
            }
          })
      }
    )
  },
  saveMerchant: (val) => {
    const promise = axios.post('/api/merchant/saveMerchant', val)
    return promise
  }
}
