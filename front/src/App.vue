<template>
  <div id="app">
    <!-- <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>-->
    <router-view/>
  </div>
</template>

<style lang="scss">
body, dl, dd, h1, h2, h3, h4, h5, h6, p, ol, ul, form { margin:0;padding:0;}
a { text-decoration: none; cursor: pointer;}
a:hover, a:active, a:visited, a:link{ text-decoration: none; cursor:pointer;}
*, *:before, *:after {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
#app {
  font-family: "Microsoft Yahei","Helvetica Neue",Helvetica,Arial,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
