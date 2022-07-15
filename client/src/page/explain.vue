<template>
    <div class="fillcontain">
        <head-top></head-top>
        <p class="explain_text">项目名称：基于大数据的音乐推送</p>
        <p class="explain_text">项目经理：翟震</p>
        <p class="explain_text">项目架构师：罗宇</p>
        <p class="explain_text">数据分析师：何思雨、巫鲁峰</p>
        <p class="explain_text">开发工程师：朱耀</p>
        <p class="explain_text">前端开发工程师：朱旭辉</p>
        <p class="explain_text">后端开发工程师：罗宇</p>
    </div>
</template>

<script>
import headTop from '../components/headTop'

export default {
    components: {
        headTop,
    },
}
</script>

<style lang="less">
@import '../style/mixin';

.explain_text {
    margin-top: 20px;
    text-align: center;
    font-size: 20px;
    color: #333;
}
</style>
