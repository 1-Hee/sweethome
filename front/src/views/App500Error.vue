<template>
  <div class="error">
    <img src="https://yt3.ggpht.com/ytc/AMLnZu877Rcn7n5rm5ZtMuSE8ve0fHFsRc8snsMkcLDo=s900-c-k-c0x00ffffff-no-rj" />
    <p>SERVER IS BURNED!!!</p>
  </div>
</template>

<script>
export default {
  name: "App500Error",
};
</script>

<style>
.error {
  margin: 50px auto;
  width: 400px;
  height: 700px;
}
.error > img {
  width: 400px;
  height: 400px;
}
.error > p {
  font-size: 70px;
  font-weight: 900;
  text-align: center;
  color: red;
}
</style>
