<template>
    <div>
        <head-top></head-top>
        <simple-bar :barData="barData" :info='{name:"歌曲风格"}'></simple-bar>
    </div>
</template>

<script>
import headTop from '@/components/headTop'
import simpleBar from '@/components/simpleBar'
import {getStylePercent} from '@/api/getData'

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
                const res = await getStylePercent();
                if (res.success === true) {
                    let temp = []
                    for (let i in res.data) {
                        temp.push({name: res.data[i].category, value: res.data[i].counts})
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
