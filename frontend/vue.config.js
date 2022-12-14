// const {defineConfig} = require('@vue/cli-service')
// const NodePolyfillPlugin = require('node-polyfill-webpack-plugin')

module.exports = {
    publicPath: './',
    outputDir: 'dist',
    assetsDir: 'static',
    // transpileDependencies: true,
    devServer: {// 环境配置
        host: 'localhost',
        port: 8880,
        https: false,
    },
}
