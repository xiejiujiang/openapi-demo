module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'https://inte-market.chanjet.com'
      }
    }
  }
}
