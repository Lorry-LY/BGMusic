<template>
    <div class="area">
        <div id="area" class="" style="width: 90%;height:450px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts/core';
import {DataZoomComponent, GridComponent, TitleComponent, ToolboxComponent, TooltipComponent} from 'echarts/components';
import {LineChart} from 'echarts/charts';
import {UniversalTransition} from 'echarts/features';
import {CanvasRenderer} from 'echarts/renderers';

echarts.use([
    TitleComponent,
    ToolboxComponent,
    TooltipComponent,
    GridComponent,
    DataZoomComponent,
    LineChart,
    CanvasRenderer,
    UniversalTransition
]);

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
    props: ['barData', 'info'],
    methods: {
        initData() {
            let base = +new Date(1988, 9, 3);
            let oneDay = 24 * 3600 * 1000;
            let data = [[base, Math.random() * 300]];
            for (let i = 1; i < 20000; i++) {
                let now = new Date((base += oneDay));
                data.push([+now, Math.round((Math.random() - 0.5) * 20 + data[i - 1][1])]);
            }
            const option = {
                tooltip: {
                    trigger: 'axis',
                    position: function (pt) {
                        return [pt[0], '10%'];
                    }
                },
                title: {
                    left: 'center',
                    text: 'Large Ara Chart'
                },
                toolbox: {
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none'
                        },
                        restore: {},
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'time',
                    boundaryGap: false
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '100%']
                },
                dataZoom: [
                    {
                        type: 'inside',
                        start: 0,
                        end: 20
                    },
                    {
                        start: 0,
                        end: 20
                    }
                ],
                series: [
                    {
                        name: 'Fake Data',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        areaStyle: {},
                        data: data
                    }
                ]
            };

            option && myChart.setOption(option);
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
