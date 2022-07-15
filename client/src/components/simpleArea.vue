<template>
    <div class="area">
        <div id="area" class="" style="width: 90%;height:450px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts/core';
import {GridComponent, TitleComponent, TooltipComponent} from 'echarts/components';
import {LineChart} from 'echarts/charts';
import {UniversalTransition} from 'echarts/features';
import {CanvasRenderer} from 'echarts/renderers';

echarts.use([GridComponent, TitleComponent, TooltipComponent, LineChart, CanvasRenderer, UniversalTransition]);

export default {
    data() {
        return {
            kindsList: [],
            kindsValue: [],
        }
    },
    mounted() {
        this.myChart = echarts.init(document.getElementById('area'));
        this.initData();
    },
    props: ['areaData', 'info'],
    methods: {
        initData() {
            const option = {
                title: {
                    text: this.info.name,
                    subtext: '',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    triggerOnFocus: true,
                    formatter: '{b}h流量 <br/> {c}',
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: this.kindsList
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: this.info.name,
                        data: this.kindsValue,
                        type: 'line',
                        areaStyle: {}
                    }
                ]
            };
            option && this.myChart.setOption(option);
        }
    },
    watch: {
        areaData: function () {
            this.kindsList = [];
            this.kindsValue = [];
            for (let i in this.areaData) {
                let kind = this.areaData[i].name;
                let value = this.areaData[i].value;
                this.kindsList.push(kind)
                this.kindsValue.push(value)
            }
            this.initData()
        }
    }
}
</script>

<style lang="less">
@import '../style/mixin';

.area {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}
</style>
