<template>
  <div style="height: 100%">
    <!--    height: 817px; width:1087px e6e2e3-->
    <el-dialog
        v-model="dialogVisible"
        width="30%"
        :show-close="false"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
    >
      <el-tabs v-model="dialogPage" class="demo-tabs">
        <el-tab-pane label="登录" name="login" style="text-align: center;">
          <el-form
              :model="loginUser"
              label-width="0"
              style="display: inline-block; width: 80%"
          >
            <el-alert title="用户名或密码错误" type="error" v-if="this.info.staffLoginAlert"/>
            <br/>
            <el-form-item label="" prop="pass">
              <el-input v-model="loginUser.username" type="username" autocomplete="off" placeholder="用户名"/>
            </el-form-item>
            <el-form-item label="" prop="pass">
              <el-input v-model="loginUser.password" type="password" autocomplete="off" placeholder="密码"/>
            </el-form-item>
          </el-form>
          <div class="dialog-footer" style="text-align: center;">
            <el-button @click="loginVerification">登录</el-button>
          </div>
        </el-tab-pane>

        <el-tab-pane label="注册" name="register">
          <el-form
              :model="loginUser"
              label-width="80px"
          >
            <el-alert title="用户名或密码错误" type="error" v-if="this.info.staffLoginAlert"/>
            <br/>
            <el-form-item label="用户名" prop="pass">
              <el-input v-model="loginUser.username" type="text" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input v-model="loginUser.password" type="password" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="昵称" prop="pass">
              <el-input v-model="loginUser.nickname" type="text" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="地区" prop="pass">
              <el-input v-model="loginUser.region" type="text" autocomplete="off"/>
            </el-form-item>
            <el-form-item label="性别" prop="pass">
              <el-radio-group v-model="loginUser.sex">
                <el-radio label="M">男</el-radio>
                <el-radio label="F">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
          <div class="dialog-footer" style="text-align: center">
            <el-button @click="register">注册</el-button>
          </div>

        </el-tab-pane>
      </el-tabs>
    </el-dialog>


    <el-dialog
        v-model="modifyVisible"
        width="30%"
        :show-close="false"
        :close-on-click-modal="true"
        :close-on-press-escape="true"
    >
      <el-form
          :model="modifyUser"
          label-width="80px"
          style="display: inline-block; width: 80%"
      >
        <br/>
        <el-form-item label="用户名" prop="pass">
          <el-input v-model="modifyUser.username" type="text" autocomplete="off" placeholder="用户名" disabled/>
        </el-form-item>
        <el-form-item label="昵称" prop="pass">
          <el-input v-model="modifyUser.nickname" type="text" autocomplete="off" placeholder="昵称"/>
        </el-form-item>
        <el-form-item label="性别" prop="pass">
          <el-radio-group v-model="modifyUser.sex" class="ml-4">
            <el-radio label="M">男</el-radio>
            <el-radio label="F">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="头像" prop="pass">
          <el-upload
              class="dl-avatar-uploader-min square"
              :action="'http://freya.icu/pinechat/api/userUpload'"
              :show-file-list="false"
              :on-success="handleUpImage"
              :before-upload="beforeImageUpload"
              list-type="picture"
              accept="image/*"
          >
            <el-image v-if="modifyUser.avatar" :src="modifyUser.avatar" class="avatar"></el-image>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
          <div class="el-upload__tip">只能上传图片,且单张图片大小不能超过10MB</div>
        </el-form-item>
        <el-form-item label="地区" prop="pass">
          <el-input v-model="modifyUser.region" type="text" autocomplete="off" placeholder="地区"/>
        </el-form-item>
      </el-form>
      <div class="dialog-footer" style="text-align: center;">
        <el-button @click="handleModifyUser">修改信息</el-button>
      </div>

    </el-dialog>

    <el-container style="height: 100%; width: 100%">
      <el-aside width="68px" style="background-color: #e6e2e3; opacity: 80%; height: 100%" @dragstart.prevent>
        <!--        这里是最左边一栏-->

        <el-popover placement="right" :width="336" :height="352" trigger="click">
          <template #reference>
            <div style="margin-top: 70px; text-align: center">
              <el-avatar shape="square" :size="35" :src="this.loginUser.avatar"/>
            </div>
          </template>
          <div style="text-align: center">
            <div>
              <div style="display: inline-block; margin-top: 50px; margin-bottom: 50px">
                <div style="text-align: left">
                  <div style="display: inline-block"><span
                      style="color:#000; font-size: 25px; margin-right: 6px">{{ this.loginUser.nickname }}</span></div>
                  <div style="display: inline-block;">
                    <div v-if="loginUser.sex === 'M'">
                      <svg t="1670420923210" class="icon" viewBox="0 0 1024 1024" version="1.1"
                           xmlns="http://www.w3.org/2000/svg" p-id="5148" width="15" height="15">
                        <path
                            d="M631.119772 619.422812l0.344028-16.685369c79.298505-55.388544 117.829666-153.092558 117.829666-297.756425 0-139.503444-87.211154-222.758273-233.423148-222.758273l-7.912649 0c-146.03998 0-233.423148 83.254829-233.423148 222.758273 0 143.975811 38.875189 241.679825 118.86175 297.412397l0 16.857383c-133.138922 17.889467-266.621871 70.869814-266.621871 151.88846 0 113.357299 129.526625 170.810012 385.139593 170.810012s385.139593-57.452713 385.139593-170.810012C897.053586 690.292626 763.914665 637.31228 631.119772 619.422812z"
                            p-id="5149" fill="#74b5ec"></path>
                      </svg>
                    </div>
                    <div v-else>
                      <svg t="1670420923210" class="icon" viewBox="0 0 1024 1024" version="1.1"
                           xmlns="http://www.w3.org/2000/svg" p-id="5148" width="15" height="15">
                        <path
                            d="M631.119772 619.422812l0.344028-16.685369c79.298505-55.388544 117.829666-153.092558 117.829666-297.756425 0-139.503444-87.211154-222.758273-233.423148-222.758273l-7.912649 0c-146.03998 0-233.423148 83.254829-233.423148 222.758273 0 143.975811 38.875189 241.679825 118.86175 297.412397l0 16.857383c-133.138922 17.889467-266.621871 70.869814-266.621871 151.88846 0 113.357299 129.526625 170.810012 385.139593 170.810012s385.139593-57.452713 385.139593-170.810012C897.053586 690.292626 763.914665 637.31228 631.119772 619.422812z"
                            p-id="5149" fill="#d97d7c"></path>
                      </svg>
                    </div>

                  </div>
                </div>
                <div style="margin-right: 50px">PineChat号：{{ this.loginUser.username }}</div>
              </div>
              <el-avatar shape="square" :size="50" :src="this.loginUser.avatar"/>
            </div>
            <div style="margin-bottom: 40px">
              <div style="text-align: center; width: 52%">地区：{{ this.loginUser.region }}</div>
            </div>
          </div>
          <div style="text-align: center">
            <div style="margin-bottom: 20px; text-align: center; display: inline-block">
              <el-button plain @click="modifyUserInfo">修改信息</el-button>
            </div>
            <div style="margin-bottom: 20px; text-align: center; display: inline-block; margin-left: 20px">
              <el-button plain @click="logout">退出登录</el-button>
            </div>
          </div>
        </el-popover>

        <div style="margin-top: 28px; text-align: center" class="hovered" @click="this.showPage = 'chat'">
          <el-icon :size="24" v-if="this.showPage === 'chat'">
            <ChatLineRound color='#de705b'/>
          </el-icon>
          <el-icon :size="24" v-else>
            <ChatRound color='#787878'/>
          </el-icon>
        </div>

        <div style="margin-top: 28px; text-align: center" class="hovered" @click="this.showPage = 'friend'">
          <el-icon :size="24" v-if="this.showPage === 'friend'">
            <UserFilled color='#de705b'/>
          </el-icon>
          <el-icon :size="24" v-else>
            <UserFilled color="#787878"/>
          </el-icon>
        </div>

        <div style="position: center; margin-top: 800%; text-align: center" class="hovered" @click="btnDebug">
          <el-icon :size="24" v-if="this.showPage === 'setting'">
            <More color='#de705b'/>
          </el-icon>
          <el-icon :size="24" v-else>
            <More color="#787878"/>
          </el-icon>
        </div>
      </el-aside>
      <el-main style="background-color: #f3f3f3; padding: 0">
        <chat-part v-on:getLoginUser="getLoginUser" :local-user="this.loginUser" ref="chats"
                   v-show="this.showPage === 'chat'"/>
        <friend-part v-on:changePageEmit="changePages" v-show="this.showPage === 'friend'" ref="friends"/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import ChatPart from '@/components/Chat'
import FriendPart from "@/components/Friend";
import axios from 'axios'
import md5 from 'js-md5';
import {ElMessage} from 'element-plus'

export default {
  name: 'App',
  components: {
    ChatPart,
    FriendPart,
  },

  mounted() {
    this.$socket.on('msg', (data) => {
      console.log('msg')
      console.log(data)
    })

    this.$socket.on('sub', (data) => {
      console.log('sub')
      console.log(data)
    })

    this.receiveWeb();
    this.loginCheck();
  },

  data() {
    return {
      showPage: 'chat',
      squareUrl: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
      sizeList: ['small', '', 'large'],
      loginUser: {
        icon: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
        username: '',
        nickname: '',
        password: "",
        sex: "",
      },
      dialogVisible: true,
      modifyVisible: false,
      info: {
        staffLoginAlert: false,
      },
      dialogPage: 'login',
      modifyUser: {
        icon: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
        username: "",
        nickname: '',
        password: "",
        sex: "",
      }
    }
  },
  methods: {
    changePages(var1) {
      console.log(var1.showPage)
      this.showPage = var1.showPage;
      console.log(this.showPage)
      console.log(111111)
      console.log(this.$refs)
      this.$refs.chats.setSelectedThisUser(var1.user);
    },
    btnDebug() {
      // console.log(this.$refs.changeObjUser)
      // this.$refs.changeObjUser.setSelectedThisUser({
      //   icon: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
      //   username: '马莎user',
      //   nickname: '马莎'
      // });
      // this.send();
      // this.receiveWeb();
      this.addFriend();
    },
    getLoginUser() {
      console.log(this.loginUser)
      return this.loginUser;
    },
    send() {
      this.$socket.emit('sub', "hgf");
    },
    receiveWeb() {
      // const {proxy} = getCurrentInstance()
    },
    loginCheck() {
      let data = {
        username: localStorage.getItem("username")
      }
      axios
          .post("http://freya.icu/pinechat/api/isSignIn", data)
          .then((data) => {
            console.log(data.data);
            let result = JSON.parse(data.data);
            if (result.code === 200) {
              this.dialogVisible = false
              this.loginUser = result.data
              this.$socket.emit('verify', JSON.stringify(this.loginUser))
            }
          })
          .catch((err) => {
            console.log(err);
          });
    },
    loginVerification() {
      this.loginUser.password = md5(this.loginUser.password)
      axios
          .post("http://freya.icu/pinechat/api/signIn", this.loginUser)
          .then((data) => {
            let result = JSON.parse(data.data);
            console.log(data.data);
            if (result.code === 200) {
              this.dialogVisible = false
              this.loginUser = result.data
              localStorage.setItem("username", this.loginUser.username)
              localStorage.setItem("token", this.loginUser.password)
              this.$socket.emit('verify', JSON.stringify(this.loginUser))
            }
          })
          .catch((err) => {
            console.log(err);
          });
    },
    register() {
      this.loginUser.password = md5(this.loginUser.password)
      axios
          .post("http://freya.icu/pinechat/api/register", this.loginUser)
          .then((data) => {
            console.log(data.data);
          })
          .catch((err) => {
            console.log(err);
          });
    },
    logout() {
      this.loginUser.password = md5(this.loginUser.password)
      axios
          .post("http://freya.icu/pinechat/api/logout", this.loginUser)
          .then((data) => {
            console.log(data.data);
            localStorage.removeItem("username")
            localStorage.removeItem("token")

            this.dialogVisible = true
          })
          .catch((err) => {
            console.log(err);
          });
    },
    modifyUserInfo() {
      this.modifyVisible = true;
      this.modifyUser = this.loginUser
    },
    handleUpImage(res) {
      this.modifyUser.avatar = res;
    },
    beforeImageUpload(rawFile) {
      if (rawFile.size / 1024 / 1024 > 10) {
        ElMessage.error("单张图片大小不能超过10MB!");
        return false;
      }
      return true;
    },
    handleModifyUser() {
      this.modifyUser.password = this.loginUser.password
      axios
          .post("http://freya.icu/pinechat/api/modifySingleUser", this.modifyUser)
          .then((data) => {
            console.log(data.data);
            this.modifyVisible = false
          })
          .catch((err) => {
            console.log(err);
          });
    }
    // addFriend() {
    //   console.log("addFriend")
    //   let addJson = {
    //     fromUser: this.loginUser,
    //     toUser: "1",
    //   }
    //   this.$socket.emit('friend', JSON.stringify(addJson));
    // }
  }
}
</script>


<style>



button, a, input, textarea {
  -webkit-app-region: no-drag;
}

.noDrag {
  -webkit-app-region: no-drag;
}

.hovered:hover {
  opacity: 50%;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}


</style>

