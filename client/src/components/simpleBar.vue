<template>
    <div class="bar">
        <div id="bar" class="" style="width: 90%;height:450px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts/core';
import {DataZoomComponent, GridComponent, TitleComponent, TooltipComponent,} from 'echarts/components';
import {BarChart} from 'echarts/charts';
import {CanvasRenderer} from 'echarts/renderers';

echarts.use([
    TitleComponent,
    GridComponent,
    DataZoomComponent,
    TooltipComponent,
    BarChart,
    CanvasRenderer
]);

export default {
    data() {
        return {
            kindsList: [],
            kindsValue: [],
        }
    },
    mounted() {
        this.myChart = echarts.init(document.getElementById('bar'));
        this.initData();
    },
    props: ['barData', 'info'],
    methods: {
        initData() {
            // prettier-ignore
            let dataAxis = this.kindsList;
// prettier-ignore
            let data = this.kindsValue;
            let yMax = 500;
            let dataShadow = [];
            for (let i = 0; i < data.length; i++) {
                dataShadow.push(yMax);
            }
            const option = {
                title: {
                    text: this.info.name,
                    subtext: '',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    triggerOnFocus: true,
                    formatter: '{a} <br/>{b} : {c}',
                },
                xAxis: {
                    data: dataAxis,
                    axisLabel: {
                        inside: false,
                        color: '#000',
                        interval: 0,
                        rotate: 30,
                    },
                    axisTick: {
                        show: false
                    },
                    axisLine: {
                        show: false
                    },
                    z: 10
                },
                yAxis: {
                    axisLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        color: '#999'
                    }
                },
                dataZoom: [
                    {
                        type: 'inside'
                    }
                ],
                series: [
                    {
                        name: this.info.name,
                        type: 'bar',
                        showBackground: true,
                        itemStyle: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                {offset: 0, color: '#83bff6'},
                                {offset: 0.5, color: '#188df0'},
                                {offset: 1, color: '#188df0'}
                            ])
                        },
                        emphasis: {
                            itemStyle: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                    {offset: 0, color: '#2378f7'},
                                    {offset: 0.7, color: '#2378f7'},
                                    {offset: 1, color: '#83bff6'}
                                ])
                            }
                        },
                        data: data,
                        label: {
                            show: true,
                            position: 'top',
                            valueAnimation: true
                        }
                    }
                ]
            };

            this.myChart.setOption(option);
        }
    },
    watch: {
        barData: function () {
            this.kindsList = [];
            this.kindsValue = [];
            for (let i in this.barData) {
                let kind = this.barData[i].name;
                let value = this.barData[i].value;
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

.bar {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}
</style>
