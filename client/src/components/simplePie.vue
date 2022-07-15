<template>
    <div class="pie">
        <div id="pie" class="" style="width: 90%;height:450px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts/core';
import {LegendComponent, TitleComponent, TooltipComponent} from 'echarts/components';
import {PieChart} from 'echarts/charts';
import {LabelLayout} from 'echarts/features';
import {CanvasRenderer} from 'echarts/renderers';

echarts.use([
    TitleComponent,
    TooltipComponent,
    LegendComponent,
    PieChart,
    CanvasRenderer,
    LabelLayout
]);

export default {
    data() {
        return {
            kindsList: [],
        }
    },
    mounted() {
        this.myChart = echarts.init(document.getElementById('pie'));
        // this.initData();
    },
    props: ['pieData', 'info'],
    methods: {
        initData() {
            const option = {
                title: {
                    text: this.info.name,
                    subtext: '',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    triggerOnFocus: true,
                    formatter: '{a} <br/>{b} : {c} ({d}%)',
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: this.kindsList
                },
                series: [
                    {
                        name: this.info.name,
                        type: 'pie',
                        radius: '60%',
                        center: ['50%', '60%'],
                        data: this.pieData,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            option && this.myChart.setOption(option);
        }
    },
    watch: {
        pieData: function () {
            this.kindsList = [];
            for (let i in this.pieData) {
                let kind = this.pieData[i].name;
                this.kindsList.push(kind)
            }
            this.initData()
        }
    }
}
</script>

<style lang="less">
@import '../style/mixin';

.pie {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}
</style>
