<template>
  <div id="app">
    <the-header-view />
    <router-view
      v-on:go-index="goIndex"
      v-on:notice-view="noticeView"
      v-on:board-view="boardView"
      v-on:board-list="boardList"
      v-on:notice-list="noticeList"
      v-on:board-write="boardWrite"
    ></router-view>
    <the-footer-view />
  </div>
</template>

<script>
import TheHeaderView from "@/components/include/TheHeaderView";
import TheFooterView from "@/components/include/TheFooterView";

export default {
  methods: {},
  components: {
    TheHeaderView,
    TheFooterView,
  },
  mounted() {},
  methods: {
    noticeView() {
      this.$router.push({ name: "NoticeView" });
    },
    boardView() {
      this.$router.push({ name: "BoardView" });
    },
    boardList() {
      this.$router.push({ name: "BoardList" });
    },
    noticeList() {
      this.$router.push({ name: "NoticeList" });
    },
    boardWrite() {
      this.$router.push({ name: "BoardWrite" });
    },
    goIndex() {
      this.$router.push({ name: "Index" });
      location.reload();
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
