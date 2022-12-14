<template>

  <el-container style="height: 100%; width: 100%;">
    <el-aside width="260px"
              style="background-color: #f7f7f7; opacity: 1; color: black">
      <!--      border-right: 1px solid #e0e0e0;-->
      <el-container style="height: 100%">
        <el-header style="height: 60px; text-align: center; background-color: #f7f7f7; border-right: 1px solid #e0e0e0">
          <el-input
              v-model="searchInput"
              class="w-50 m-2"
              placeholder="Search"
              style="margin-top: 18px;"
              :prefix-icon="Search"
              size="small"
          >
            <template #prefix>
              <el-icon class="el-input__icon">
                <Search/>
              </el-icon>
            </template>
          </el-input>

        </el-header>
        <!--        聊天对象列表-->
        <el-main style="height: 100%; padding: 0; -webkit-app-region: no-drag; border-right: 1px solid #e0e0e0"
                 @dragstart.prevent>
          <div style="height: 100%; -webkit-app-region: no-drag;">
            <el-scrollbar
                style="overflow-x: hidden; overflow-y: hidden; white-space: nowrap; -webkit-user-select:none;">
              <div v-for="(item, index) in this.recentChat" :key="item" :ref="setSessionList"
                   style="height: 66px; border: 1px;"
                   @click="onSelect(item, index)">
                <div style="display: inline-block; height: 64px">
                  <el-avatar shape="square" :src="item.avatar" style="margin: 12px"/>
                </div>
                <div style="display: inline-block; weight:194px; height:64px; position: relative; top: -3px">
                  <span style="color: black; font-size: 14px">{{ item.nickname }}</span>
                  <br style="font-size: 1px"/>
                  <span style="color:#929292; font-size: 12px">{{ item.lastMsg }}</span>
                </div>
              </div>
            </el-scrollbar>

          </div>
        </el-main>
      </el-container>
    </el-aside>
    <el-main style="background-color: #f3f3f3; padding: 0;">
      <!--      这里是聊天界面-->
      <div style="position: relative; text-align: center; opacity: 30%;; height: 100%"
           v-if="this.selected.thisUser==null">
        <svg style="position: absolute; top: 50%; left: 50%; height: 30%; width: 50%; margin: -15% 0 0 -25%;"
             t="1666681932784" class="icon" viewBox="0 0 1047 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="3052" width="200" height="200">
          <path
              d="M779.272982 467.087719a143.719298 143.719298 0 0 0-143.719298-143.719298h-251.508772a143.719298 143.719298 0 0 0-143.719298 143.719298z m-574.877193 35.929825v-35.929825a179.649123 179.649123 0 0 1 179.649123-179.649123h251.508772a179.649123 179.649123 0 0 1 179.649123 179.649123v35.929825z m574.877193 305.403509h-538.947368v35.929824a143.719298 143.719298 0 0 0 143.719298 143.719298h251.508772a143.719298 143.719298 0 0 0 143.719298-143.719298z m35.929825 35.929824a179.649123 179.649123 0 0 1-179.649123 179.649123h-251.508772a179.649123 179.649123 0 0 1-179.649123-179.649123v-71.859649h610.807018z"
              fill="#5A5A68" p-id="3053"></path>
          <path
              d="M432.011228 80.392982a222.046316 222.046316 0 0 0-13.294035 33.324913c-28.115088-10.958596-55.421754-13.743158-68.356491-4.670877-26.228772 17.964912-28.025263 103.208421 5.569123 151.174736a141.563509 141.563509 0 0 0 25.061052 26.947369H449.167719a85.06386 85.06386 0 0 0 138.868772 0h68.625965a140.665263 140.665263 0 0 0 20.030877-22.815439c33.594386-47.966316 31.797895-132.760702 5.569123-151.174737-12.126316-8.443509-36.648421-6.647018-62.877193 2.604913a224.022456 224.022456 0 0 0-13.20421-33.145264c36.917895-12.934737 74.195088-14.282105 96.471579 1.347369 44.912281 31.348772 47.06807 138.599298 3.233684 201.207017a177.403509 177.403509 0 0 1-31.169123 33.953685l-5.030175 4.221754H368.325614l-4.850526-3.772632a178.122105 178.122105 0 0 1-37.187369-38.80421c-43.834386-62.877193-41.588772-169.858246 3.233685-201.207018 23.713684-16.707368 63.685614-14.102456 102.489824 0.808421z"
              fill="#5A5A68" p-id="3054"></path>
          <path
              d="M518.781754 35.929825c-6.736842 0-25.420351 14.731228-41.139649 37.277193A161.055439 161.055439 0 0 0 446.922105 161.684211c0 70.332632 31.348772 125.754386 71.859649 125.754385s71.859649-57.487719 71.85965-125.754385a162.582456 162.582456 0 0 0-30.809825-87.489123C544.022456 51.2 525.608421 35.929825 518.781754 35.929825z m0-35.929825c45.451228 0 107.789474 90.632982 107.789474 161.684211 0 85.423158-43.025965 161.684211-107.789474 161.68421s-107.789474-74.195088-107.789473-161.68421c0-73.027368 61.799298-161.684211 107.789473-161.684211zM958.113684 574.877193h80.392983a9.431579 9.431579 0 0 1 9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579 9.431579h-80.392983a9.431579 9.431579 0 0 1-9.431579-9.431579 9.431579 9.431579 0 0 1 9.431579-9.431579zM958.113684 631.646316h80.392983a9.431579 9.431579 0 0 1 9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579 9.431579h-80.392983a9.431579 9.431579 0 0 1-9.431579-9.431579 9.431579 9.431579 0 0 1 9.431579-9.431579zM958.113684 688.325614h80.392983a9.431579 9.431579 0 0 1 9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579 9.431579h-80.392983a9.431579 9.431579 0 0 1-9.431579-9.431579 9.431579 9.431579 0 0 1 9.431579-9.431579z"
              fill="#5A5A68" p-id="3055"></path>
          <path
              d="M958.113684 574.877193a9.431579 9.431579 0 0 1 9.431579 9.431579v113.448421a9.431579 9.431579 0 0 1-9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579-9.431579V584.308772a9.431579 9.431579 0 0 1 9.431579-9.431579z"
              fill="#5A5A68" p-id="3056"></path>
          <path
              d="M307.514386 574.877193h80.392982a9.431579 9.431579 0 0 1 9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579 9.431579h-80.392982a9.431579 9.431579 0 0 1-9.431579-9.431579A9.431579 9.431579 0 0 1 307.514386 574.877193z"
              fill="#5A5A68" p-id="3057"></path>
          <path
              d="M307.514386 631.646316h80.392982a9.431579 9.431579 0 0 1 9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579 9.431579h-80.392982a9.431579 9.431579 0 0 1-9.431579-9.431579 9.431579 9.431579 0 0 1 9.431579-9.431579z"
              fill="#5A5A68" p-id="3058"></path>
          <path
              d="M307.514386 688.325614h80.392982a9.431579 9.431579 0 0 1 9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579 9.431579h-80.392982a9.431579 9.431579 0 0 1-9.431579-9.431579 9.431579 9.431579 0 0 1 9.431579-9.431579z"
              fill="#5A5A68" p-id="3059"></path>
          <path
              d="M307.514386 574.877193a9.431579 9.431579 0 0 1 9.431579 9.431579v113.448421a9.431579 9.431579 0 0 1-9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579-9.431579V584.308772A9.431579 9.431579 0 0 1 307.514386 574.877193z"
              fill="#5A5A68" p-id="3060"></path>
          <path
              d="M135.949474 574.877193a9.431579 9.431579 0 0 1 9.431579 9.431579v113.448421a9.431579 9.431579 0 0 1-9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579-9.431579V584.308772A9.431579 9.431579 0 0 1 135.949474 574.877193zM829.484912 688.325614h85.06386a9.431579 9.431579 0 0 1 9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579 9.431579h-85.06386a9.431579 9.431579 0 0 1-9.431579-9.431579 9.431579 9.431579 0 0 1 9.431579-9.431579z"
              fill="#5A5A68" p-id="3061"></path>
          <path
              d="M829.484912 574.877193a9.431579 9.431579 0 0 1 9.431579 9.431579v113.448421a9.431579 9.431579 0 0 1-9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579-9.431579V584.308772A9.431579 9.431579 0 0 1 829.484912 574.877193zM178.885614 574.877193A9.431579 9.431579 0 0 1 188.676491 584.308772v113.448421a9.431579 9.431579 0 0 1-9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579-9.431579V584.308772A9.431579 9.431579 0 0 1 178.885614 574.877193zM263.949474 574.877193a9.431579 9.431579 0 0 1 9.431579 9.431579v113.448421a9.431579 9.431579 0 0 1-9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579-9.431579V584.308772A9.431579 9.431579 0 0 1 263.949474 574.877193z"
              fill="#5A5A68" p-id="3062"></path>
          <path
              d="M171.340351 589.967719a9.431579 9.431579 0 1 1 15.090526-11.317894l85.06386 113.448421a9.431579 9.431579 0 1 1-15.090526 11.317894z"
              fill="#5A5A68" p-id="3063"></path>
          <path
              d="M722.414035 593.830175h-19.402105a9.431579 9.431579 0 1 1 0-18.952982h54.882807a37.81614 37.81614 0 0 1 0 75.452632h-54.882807a9.431579 9.431579 0 1 1 0-18.952983h52.18807a18.952982 18.952982 0 1 0 0-37.81614z"
              fill="#5A5A68" p-id="3064"></path>
          <path
              d="M703.101754 574.877193a9.431579 9.431579 0 0 1 9.431579 9.431579v113.448421a9.431579 9.431579 0 0 1-9.431579 9.431579 9.431579 9.431579 0 0 1-9.431579-9.431579V584.308772A9.431579 9.431579 0 0 1 703.101754 574.877193z"
              fill="#5A5A68" p-id="3065"></path>
          <path
              d="M593.695439 593.830175h-19.402106a9.431579 9.431579 0 1 1 0-18.952982H628.816842a37.81614 37.81614 0 0 1 0 75.452632h-54.523509a9.431579 9.431579 0 1 1 0-18.952983h52.188071a18.952982 18.952982 0 0 0 0-37.81614z"
              fill="#5A5A68" p-id="3066"></path>
          <path
              d="M574.472982 574.877193A9.431579 9.431579 0 0 1 583.904561 584.308772v113.448421a9.431579 9.431579 0 0 1-9.431579 9.431579 9.431579 9.431579 0 0 1-9.431578-9.431579V584.308772A9.431579 9.431579 0 0 1 574.472982 574.877193z"
              fill="#5A5A68" p-id="3067"></path>
          <path
              d="M28.878596 593.830175H9.476491a9.431579 9.431579 0 1 1 0-18.952982h54.882807a37.81614 37.81614 0 0 1 0 75.452632H9.476491a9.431579 9.431579 0 1 1 0-18.952983h52.18807a18.952982 18.952982 0 0 0 0-37.81614z"
              fill="#5A5A68" p-id="3068"></path>
          <path
              d="M9.65614 574.877193a9.431579 9.431579 0 0 1 9.431579 9.431579v113.448421a9.431579 9.431579 0 0 1-9.431579 9.431579A9.431579 9.431579 0 0 1 0.044912 697.757193V584.308772A9.431579 9.431579 0 0 1 9.65614 574.877193z"
              fill="#5A5A68" p-id="3069"></path>
          <path
              d="M467.581754 580.895439a9.431579 9.431579 0 0 1 17.964913 6.916491L440.185263 701.260351a9.431579 9.431579 0 1 1-17.964912-6.916491z"
              fill="#5A5A68" p-id="3070"></path>
          <path
              d="M467.761404 588.081404a9.431579 9.431579 0 1 1 17.33614-7.545264l49.672982 113.448421a9.431579 9.431579 0 1 1-17.33614 7.545264z"
              fill="#5A5A68" p-id="3071"></path>
          <path
              d="M447.281404 667.037193a9.431579 9.431579 0 1 1 0-18.952982h61.26035a9.431579 9.431579 0 1 1 0 18.952982z"
              fill="#5A5A68" p-id="3072"></path>
          <path
              d="M214.366316 860.429474l16.078596-32.157193 112.550176 56.230175a53.894737 53.894737 0 0 0 47.06807 0.538948l89.824561-42.12772a89.824561 89.824561 0 0 1 78.416842 0.898246L637.799298 883.514386a53.894737 53.894737 0 0 0 49.493334-0.628772l101.950877-54.433684 16.887017 31.70807-101.950877 54.433684a89.824561 89.824561 0 0 1-82.458947 1.077895l-79.315088-39.702456a53.894737 53.894737 0 0 0-47.06807-0.538948l-89.824562 42.12772a89.824561 89.824561 0 0 1-78.416842-0.898246zM232.331228 393.341754l16.078597-32.157193 94.585263 47.24772a53.894737 53.894737 0 0 0 47.06807 0.538947l89.824561-42.127719a89.824561 89.824561 0 0 1 78.416842 0.898245l79.135439 39.612632a53.894737 53.894737 0 0 0 49.672982-0.718597l83.896141-44.91228 17.066666 31.618245-83.89614 44.912281a89.824561 89.824561 0 0 1-82.728421 1.257544l-79.135439-39.612632a53.894737 53.894737 0 0 0-47.06807-0.538947l-89.824561 42.127719a89.824561 89.824561 0 0 1-78.416842-0.898245z"
              fill="#5A5A68" p-id="3073"></path>
        </svg>
      </div>
      <el-container style="height: 100%" v-else>
        <el-header style="height: 60px; border-bottom: 1px solid #e0e0e0">
          <div style="padding: 20px; -webkit-user-select:none;">{{ this.selected.thisUser.nickname }}</div>
        </el-header>
        <el-main style="padding: 0; -webkit-app-region: no-drag;">
          <div style="margin: 0; padding: 0" id="chatRecord">
            <el-scrollbar ref="scrollbar" style="height: 595px">
              <div style="padding: 0; margin: 0;" ref="chatDiv">
                <!--load history-->
                <div v-for="(item) in this.detailChat" :key="item" :ref="setChatList"
                     style="margin-left: 20px; margin-right: 20px">
                  <div v-for="(var1, key1) in item" :key="var1">
                    <div class="msg msg-right" v-if="key1 === 'sender' && thisUser.username === var1.username">
                      <!--                <div class="img-wrapper">-->
                      <!--                  <img class="img" :src="item.avatar"/>-->
                      <el-avatar shape="square" :src="item.sender.avatar"/>
                      <!--                </div>-->
                      <div class="message-wrapper message-wrapper-right">
                        <div class="message">{{ item.content }}</div>
                      </div>
                    </div>
                    <div class="msg msg-left"
                         v-else-if="key1 === 'sender' && this.selected.thisUser.username === var1.username">
                      <!--                  <div class="img-wrapper">-->
                      <el-avatar shape="square" :src="item.sender.avatar"/>
                      <!--                  </div>-->
                      <div class="message-wrapper message-wrapper-left">
                        <div class="message">{{ item.content }}</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </el-scrollbar>
          </div>
        </el-main>
        <el-footer
            style="height: 162px; bottom: 0; border-top: 1px solid #e0e0e0; padding: 5px 18px 18px; -webkit-app-region: no-drag;">
          <el-popover placement="top" :width="400" trigger="click">
            <template #reference>
              <el-button style="background-color: transparent; border: none">
                <el-icon size="20px">
                  <VideoCamera/>
                </el-icon>
              </el-button>
            </template>
            <div style="width: 374px; height: 500px">
              <WebRTC/>
            </div>
          </el-popover>

          <div style="height: 80%">

            <label>
                <textarea style="background-color: transparent; border: transparent; outline:none; wrap:hard;
                    height: 100%; width: 100%; resize:none" name="msgArea" v-model="msgInput"
                          v-on:keyup.enter="msgSubmit"></textarea>
            </label>
          </div>
        </el-footer>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
// import isElectron from 'is-electron';
import WebRTC from "@/components/RTC";
import 'setimmediate';
import axios from "_axios@1.1.3@axios"; // HACK: https://github.com/winstonjs/winston/issues/1354
import AES from "../assets/js/AES"

export default {
  name: "ChatPart",
  components: {
    WebRTC,
  },
  mounted() {
    let queryData = {
      token: localStorage.getItem("token"),
      user: {
        username: localStorage.getItem("username")
      }
    }
    axios
        .post("http://freya.icu/pinechat/api/getSingleUser", queryData)
        .then((data) => {
          this.thisUser = JSON.parse(data.data).data
          console.log(this.thisUser)
        })
        .catch((err) => {
          console.log(err);
        });

    axios
        .post("http://freya.icu/pinechat/api/getRecentUser", queryData)
        .then((data) => {
          let allLogs = JSON.parse(data.data).data
          allLogs = allLogs.filter(item => item.username !== localStorage.getItem("username"))
          for (let log in allLogs) {
            console.log(log)
            let tempUser = {
              avatar: allLogs[parseInt(log)].avatar,
              username: allLogs[parseInt(log)].username,
              nickname: allLogs[parseInt(log)].nickname,
              lastMsg: allLogs[parseInt(log)].username,
            }
            this.recentChat.push(tempUser)
          }
        })
        .catch((err) => {
          console.log(err);
        });

    this.$socket.on('msgR', () => {
      console.log('getData')
      this.getUserMsg(this.selected.thisUser)
    });
  },
  props: {
    'localUser': Object,
  },

  setup() {

  },
  data() {
    return {
      msgInput: '',
      searchInput: '',
      test1: 'test',
      selected: {
        lastUser: null,
        thisUser: null,
      },
      refresh: true,
      sessionList: [],
      chatSessionList: [],
      singleChatSessionList: [],

      recentChat: [
        // {
        //   icon: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
        //   username: '66user',
        //   nickname: '66',
        //   lastMsg: '😭没有没有没有没有没有没有没',
        // },
        // {
        //   icon: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
        //   username: '77user',detailChat
        //   nickname: '77',
        //   lastMsg: '没asdasfdsfsagff没有没有没有',
        // },
      ],
      detailChat: [
        {
          avatar: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
          username: 'mainUser',
          nickname: 'MAINUSER',
          msg: '什么时间吃饭😭',
        },
        {
          avatar: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
          username: 'xzqc',
          nickname: '66',
          msg: '不吃饭',
        },
      ],
      thisUser: {
        username: localStorage.getItem("username")
      }
    }
  },

  methods: {
    setSessionList(el) {
      this.sessionList.push(el)
    },
    setSingleChatSessionList(el) {
      this.singleChatSessionList.push(el)
    },
    // eslint-disable-next-line no-unused-vars
    onSelect(item, index) {
      console.log('onSelect')
      console.log(this)
      this.selected.lastUser = this.selected.thisUser;
      this.selected.thisUser = this.recentChat.find(o => o.username === item.username);
      this.getUserMsg(this.selected.thisUser)
      if (this.selected.lastUser !== this.selected.thisUser) {
        // this.sessionList[this.recentChat.indexOf(this.selected.thisUser)].style['backgroundColor'] = '#dedede';
        if (this.selected.lastUser !== null) {
          // this.sessionList[this.recentChat.indexOf(this.selected.lastUser)].style['backgroundColor'] = '#f7f7f7';
        }
      }
    },
    msgSubmit() {
      console.log("msg")
      let sendMsg = {
        token: localStorage.getItem('token'),
        chatLog: {
          sender: {
            username: localStorage.getItem('username')
          },
          receiver: {
            username: this.selected.thisUser.username
          },
          content: AES.encrypt(this.msgInput.replace('\n', ''))
        }
      }
      // this.$socket.emit('msg', JSON.stringify(sendMsg))

      axios
          .post("http://freya.icu/pinechat/api/sendUserMsg", sendMsg)
          .then((data) => {
            console.log(data.data)
            // this.detailChat = JSON.parse(data.data).data
          })
          .catch((err) => {
            console.log(err);
          });

      let tempUser = {
        sender: this.thisUser,
        receiver: this.selected.thisUser,
        content: this.msgInput.replace('\n', '')
      }
      this.detailChat.push(tempUser)

      this.msgInput = ''
      this.scrollToBottoms()
    },
    setSelectedThisUser(var1) {
      let newRecentChatItem = {
        avatar: var1.avatar,
        username: var1.username,
        nickname: var1.nickname,
        lastMsg: var1.username,
      }

      let flag = 1;
      for (let usr in this.recentChat) {
        if (this.recentChat[parseInt(usr)].username !== var1.username) {
          flag *= 1
        } else {
          flag = 0
        }
      }

      if (flag === 1) {
        this.recentChat.push(newRecentChatItem)
      }

      this.onSelect(var1)
    },
    setChatList(el) {
      this.chatSessionList.push(el)
    },
    chatUpdated() {
    },
    getUserMsg(user) {
      let userInfo = {
        token: localStorage.getItem("token"),
        user: {
          username: localStorage.getItem("username")
        }
      }
      if (user != null)
        userInfo.user.username = user.username
      console.log(userInfo)
      axios
          .post("http://freya.icu/pinechat/api/getUserMsg", userInfo)
          .then((data) => {
            console.log(data)
            this.detailChat = [];
            let tempData = JSON.parse(data.data).data;
            for (let chat in JSON.parse(data.data).data) {
              // console.log(tempData[parseInt(chat)])
              tempData[parseInt(chat)].content = AES.decrypt(tempData[parseInt(chat)].content)
              this.detailChat.push(tempData[parseInt(chat)])
            }
            // this.detailChat = JSON.parse(data.data).data
          })
          .catch((err) => {
            console.log(err);
          });
      this.scrollToBottoms()
    },
    scrollToBottoms() {
      console.log(this.$refs.chatDiv.offsetHeight)
      this.$refs.scrollbar.setScrollTop(this.$refs.chatDiv.offsetHeight + 80)
    }
  }
}
</script>

<style scoped>

.message {
  margin: 8px;
  word-wrap: break-word;
}

.time {
  text-align: center;
}

.msg {
  display: flex;
  flex-direction: row;
}

.message-wrapper {
  max-width: 80%;

  margin: 0 10px 0 10px;
}

.message {
  margin: 8px;
  word-wrap: break-word;
}

.message-wrapper-left {
  background-color: #d9e9db;
  border-radius: 0 12px 12px 12px;
}

.message-wrapper-right {
  background-color: #eadcdc;
  border-radius: 12px 0 12px 12px;
}

.img {
  flex: auto;
  height: 36px;
  width: 36px;
  border-radius: 8px;
}

.msg-right {
  flex-direction: row-reverse;
}

.msg-left {
  flex-direction: row;
}
</style>