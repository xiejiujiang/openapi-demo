const path = require('path')
module.exports = {
  // 选项...
  devServer: {
    disableHostCheck: true,
    proxy: {
      '/api': {
        target: 'https://inte-csbank-pay.chanapp.chanjet.com'
        //changeOrigin: true,
      }
    }
  },
  // 补充 webpack 配置 (会 merge 到主配置中)
  configureWebpack: {
    mode: 'development',
    // 开发生产共同配置
    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src')
      }
    }
  },
}
