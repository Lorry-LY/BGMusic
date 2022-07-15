<template>
    <div>
        <head-top></head-top>
        <visitor-pie :info="{name:'歌曲风格占比'}" :pieData="pieData"></visitor-pie>
    </div>
</template>

<script>
import headTop from '@/components/headTop'
import visitorPie from '@/components/simplePie'
import {getStylePercent} from '@/api/getData'

export default {
    data() {
        return {
            pieData: {},
        }
    },
    components: {
        headTop,
        visitorPie,
    },
    mounted() {
        this.initData();
    },
    methods: {
        async initData() {
            try {
                const res = await getStylePercent();
                if (res.success === true) {
                    this.pieData = []
                    for (let i in res.data) {
                        let temp = {}
                        temp.name = res.data[i].category
                        temp.value = res.data[i].counts
                        this.pieData.push(temp)
                    }
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
