<template>
    <div>
        <head-top></head-top>
        <simple-area :areaData="areaData" :info='{name:"每小时的流量"}'></simple-area>
    </div>
</template>

<script>
import headTop from '@/components/headTop'
import simpleArea from '@/components/simpleArea'
import {getHourStream} from '@/api/getData'

export default {
    data() {
        return {
            areaData: [{name: "1", value: 1}],
        }
    },
    components: {
        headTop,
        simpleArea,
    },
    mounted() {
        this.initData();
    },
    methods: {
        async initData() {
            try {
                const res = await getHourStream();
                if (res.success === true) {
                    let temp = []
                    for (let i in res.data) {
                        temp.push({name: res.data[i].hour, value: res.data[i].counts})
                    }
                    this.areaData = temp;
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
