<template>
  <div class="container">

        <div class="row">
          <div class="col-md-12 my-3">
            <h2 style="text-align: center">视频聊天</h2>
<!--            <input v-model="roomId">-->
          </div>
        </div>

    <el-form :model="roomId" label-width="120px" style="width: 80%">
      <el-form-item label="房间号：">
        <el-input v-model="roomId" />
      </el-form-item>

    </el-form>

    <div class="col-md-12 my-3" style="text-align: center; margin-bottom: 30px">
      <el-button type="primary" @click="onJoin">进入视频</el-button>

      <el-button type="danger" @click="onLeave">退出视频</el-button>
    </div>
<!--    <div class="row">-->
<!--      <div class="col-md-12 my-3">-->
<!--        <h2 style="text-align: center">视频聊天</h2>-->
<!--        <input v-model="roomId">-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="row" style="text-align: center">-->
<!--      <div class="col-md-12 my-3">-->
<!--        <el-button type="primary" @click="onJoin">进入视频</el-button>-->

<!--        <el-button type="danger" @click="onLeave">退出视频</el-button>-->
<!--      </div>-->
<!--    </div>-->
    <div class="row">
      <div class="col-md-12">
        <div class="">
          <vue-webrtc ref="webrtc"
                      width="100%"
                      :roomId="roomId"
                      :enableLogs="true"
                      v-on:joined-room="logEvent"
                      v-on:left-room="logEvent"
                      v-on:opened-room="logEvent"
                      v-on:share-started="logEvent"
                      v-on:share-stopped="logEvent"
                      @error="onError"/>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import {VueWebRTC} from 'vue-webrtc';
import 'setimmediate';

export default {
  name: 'WebRTC',
  components: {
    'vue-webrtc': VueWebRTC
  },
  data() {
    return {
      img: null,
      roomId: "public-room",
    };
  },
  mounted() {

  },
  computed: {},
  watch: {},
  methods: {
    onJoin() {
      this.$refs.webrtc.join();
    },
    onLeave() {
      this.$refs.webrtc.leave();
    },
    onError(error, stream) {
      console.log('On Error Event', error, stream);
    },
    logEvent(event) {
      console.log('Event : ', event);
    },
  }
};
</script>

<style>
.btn {
  margin-right: 8px;
}
</style>