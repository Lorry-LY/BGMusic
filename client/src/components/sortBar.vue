<template>
    <div class="bar">
        <div id="bar" class="" style="width: 90%;height:450px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts/core';
import {DatasetComponent, GridComponent, TransformComponent} from 'echarts/components';
import {BarChart} from 'echarts/charts';
import {CanvasRenderer} from 'echarts/renderers';

echarts.use([
    DatasetComponent,
    GridComponent,
    TransformComponent,
    BarChart,
    CanvasRenderer
]);

export default {
    data() {
        return {
            kindsList: ["1"],
            kindsValue: [["1", 2]],
        }
    },
    mounted() {
        this.myChart = echarts.init(document.getElementById('bar'));
        // this.initData();
    },
    props: ['barData', 'info'],
    methods: {
        initData() {
            const echarts = require('echarts/lib/echarts');
            require('echarts/lib/component/dataset');
            require('echarts/lib/component/grid');
            require('echarts/lib/chart/bar');

            const option = {
                title: {
                    text: this.info.name,
                    subtext: '',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    triggerOnFocus: true,
                    formatter: '{a} <br/>{b} : {c} ({d}%)',
                },
                dataset: [
                    {
                        dimensions: ['name', 'value'],
                        source: this.kindsValue
                    },
                    {
                        transform: {
                            type: 'sort',
                            config: {dimension: 'value', order: 'desc'},
                        }
                    }
                ],
                xAxis: {
                    type: 'category',
                    axisLabel: {interval: 0, rotate: 30}
                },
                yAxis: {},
                series: {
                    type: 'bar',
                    encode: {x: 'name', y: 'value'},
                    datasetIndex: 1,
                    label: {
                        show: true,
                        position: 'top',
                        valueAnimation: true
                    }
                }
            };

            option && this.myChart.setOption(option);
        },
    },
    watch: {
        barData() {
            if (this.barData) {
                this.kindsList = [];
                this.kindsValue = [];
                this.barData.forEach(e => {
                    if (e.name) {
                        let kind = e.name;
                        let value = e.value;
                        this.kindsList.push(kind)
                        this.kindsValue.push([kind, value])
                    }
                });
                this.initData()
            }
        }
    },
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
