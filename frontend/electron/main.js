const {app, BrowserWindow} = require('electron');//引入electron
// const {readFile} = require('./static/js/readFs')
const path = require('path')

// 顺便这里放一个主进程的监听

let win;
let windowConfig = {
    width: 1087,
    height: 817,
    frame: false,
    titleBarStyle: 'hidden',//hiddenInset
    titleBarOverlay: true,
    vibrancy: 'medium-light',  // 'light', 'medium-light', 'fullscreen-ui'etc
    visualEffectState: "active", // 这个参数不加的话，鼠标离开应用程序其背景就会变成白色
    preload: path.join(__dirname, 'preload.js'),
    nodeIntegration: true,
    contextIsolation: false
};//窗口配置程序运行窗口的大小

function createWindow() {
    win = new BrowserWindow(windowConfig);//创建一个窗口
    win.loadURL(`file://${__dirname}/index.html`);//在窗口内要展示的内容index.html 就是打包生成的index.html
    // win.loadURL(`http://localhost:8880`);//在窗口内要展示的内容index.html 就是打包生成的index.html
    // win.webContents.openDevTools();  //开启调试工具
    win.on('close', () => {
        //回收BrowserWindow对象
        win = null;
    });
    win.on('resize', () => {
        win.reload();
    })
    // let con = readFile('./data/data.json')
    // con.then(res=>{
    //     console.log('123123123')
    //     console.log(res)
    // })
}

app.on('ready', createWindow);
app.on('window-all-closed', () => {
    app.quit();
});
app.on('activate', () => {
    if (win == null) {
        createWindow();
    }
});

// ipcMain.on('readMsg', async (event, arg) => {
//     console.log("readMsg")
//     if (arg === '获取人物信息') {
//         let con = await readFile('./data/data.json')
//         event.sender.send('returnMsgList', con)
//     }
// })
