<template>
  <div class="error">
    <img src="../assets/img/cookies.jpg" />
    <p>Ooops 404</p>
  </div>
</template>

<script>
export default {
  name: "App404Error",
};
</script>

<style>
.error {
  margin: 50px auto;
  width: 400px;
  height: 500px;
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
