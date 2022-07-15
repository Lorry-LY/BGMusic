<template>
    <div>
        <head-top></head-top>
        <simple-bar :barData="barData" :info='{name:"最受欢迎的歌曲（Top10）"}'></simple-bar>
    </div>
</template>

<script>
import headTop from '@/components/headTop'
import simpleBar from '@/components/simpleBar'
import {getBestSong} from '@/api/getData'

export default {
    data() {
        return {
            barData: [{name: "1", value: 1}],
        }
    },
    components: {
        headTop,
        simpleBar,
    },
    mounted() {
        this.initData();
    },
    methods: {
        async initData() {
            try {
                const res = await getBestSong();
                if (res.success === true) {
                    let temp = []
                    for (let i in res.data) {
                        temp.push({name: res.data[i].song_name, value: res.data[i].comment})
                    }
                    this.barData = temp;
                } else {
                    throw new Error(res)
                }
            } catch (err) {
                console.log('获取用户分布信息失败', err);
            }
        },
    }
}
</script>

<style lang="less">
@import '../../../style/mixin';

</style>
