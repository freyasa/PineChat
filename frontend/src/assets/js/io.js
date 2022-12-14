import io from 'socket.io-client'

const socket = io('http://101.42.153.156:8979', {
    query: {},
    transports: ['websocket', 'polling'],
    timeout: 5000,
})

export default socket