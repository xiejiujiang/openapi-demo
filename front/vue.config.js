module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'https://inte-csbank-pay.chanapp.chanjet.com'
      }
    }
  }
}
