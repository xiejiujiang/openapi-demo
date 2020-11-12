<template>
    <div class="chstop-box">
       <div class="logo-box"><img src="@/assets/images/chslogo.png" alt=""></div>
       <div class="user-name">1231231231321</div>
    </div>
</template>

<script>
// import uesImg from '@/assets/images/use.png'
export default {}
</script>

<style lang="scss" scoped>
.chstop-box{
    width: 100%;
    height: 50px;
    background: #C30212;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.15);
    display: flex;
    justify-content:space-between;
    .logo-box{
        width: 160px;
        height: 50px;
        padding-top: 10px;
        padding-left: 20px;
        img{
            display: block;
            height: 30px;
        }
    }
    .user-name{
        width: 180px;
        padding-left: 30px;
        line-height: 50px;
        color: #fff;
        font-size: 16px;
        background: url(../assets/images/use.png) no-repeat left center;
        background-size: 25px auto;
    }
}
</style>
