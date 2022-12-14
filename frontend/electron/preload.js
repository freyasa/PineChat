const { contextBridge, ipcRenderer } = require('electron')

window.ipcRenderer = require('electron').ipcRenderer;


contextBridge.exposeInMainWorld('versions', {
    node: () => process.versions.node,
    chrome: () => process.versions.chrome,
    electron: () => process.versions.electron,
    ping: () => ipcRenderer.invoke('ping'),
    setTitle: (title) => ipcRenderer.send('set-title', title)
})

contextBridge.exposeInMainWorld('chatAPI', {
    desktop: true,
})

contextBridge.exposeInMainWorld('ElectronAPI', {
    setTitle: (title) => ipcRenderer.send('setTitle', title),   //渲染进程调用主进程方法
    openFile: () => ipcRenderer.invoke('dialog:openFile'),      //渲染进程调用主进程方法，并返回值
    handleCounter: (callback) => ipcRenderer.on('update-counter', callback), //主进程调用渲染进程方法
    handlePort: (mainRenderPort) => ipcRenderer.on('port', mainRenderPort),
})
