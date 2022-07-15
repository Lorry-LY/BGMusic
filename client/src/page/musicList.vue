<template>
    <div class="fillcontain">
        <head-top></head-top>
        <div class="table_container">
            <el-table
                :data="tableData"
                highlight-current-row
                stripe
                style="width: 100%">
                <el-table-column
                    type="index"
                    width="100">
                </el-table-column>
                <el-table-column
                    label="歌名"
                    property="name"
                    width="200">
                    <template v-slot="slotProps">
                        <a :href="slotProps.row.url" onclick="return false;"> {{ slotProps.row.name }} </a>
                    </template>
                    <!--                    <template slot-scope="scope">-->
                    <!--                        <a :href="scope.row.url" onclick="return false;">  {{scope.row.name}}  </a>-->
                    <!--                    </template>-->
                </el-table-column>
                <el-table-column
                    label="歌手"
                    property="singer"
                    width="200">
                </el-table-column>
                <el-table-column
                    label="专辑"
                    property="collection"
                    width="200">
                </el-table-column>
                <el-table-column
                    label="热度"
                    property="playCount"
                    width="200">
                </el-table-column>
                <el-table-column
                    align="center"
                    label="操作">
                    <template v-slot="slotProps">
                        <a :href="slotProps.row.url" onclick="return false;"><i class="el-icon-caret-right"></i></a>
                    </template>
                </el-table-column>
            </el-table>
            <div class="Pagination" style="text-align: left;margin-top: 10px;">
                <el-pagination
                    :current-page="currentPage"
                    :page-size="20"
                    :total="count"
                    layout="total, prev, pager, next"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import headTop from '../components/headTop'
import {getSearchCount, getSearchResult} from '@/api/getData'

export default {
    data() {
        return {
            tableData: [],
            currentRow: null,
            offset: 0,
            limit: 20,
            count: 0,
            currentPage: 1,
        }
    },
    components: {
        headTop,
    },
    created() {
        // this.initData();
    },
    mounted() {
        this.$bus.$on("search", (data) => {
            this.initData(data)
        })
    },
    beforeDestroy() {
        console.log("搜索")
        this.$bus.off("search")
    },
    methods: {
        async initData(text) {
            try {
                const countData = await getSearchCount({text: text});
                if (countData.success === true) {
                    this.count = countData.data;
                } else {
                    throw new Error('获取数据失败');
                }
                await this.getMusics(text);
            } catch (err) {
                console.log('获取数据失败', err);
            }
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.offset = (val - 1) * this.limit;
            this.getMusics(this.searchText)
        },
        async getMusics(text) {
            const Musics = await getSearchResult({text: text, startIndex: this.offset, pageNumber: this.limit});
            console.log("weqe", Musics)
            let temp = [];
            Musics.data.forEach(item => {
                const tableData = {};
                tableData.name = item.name;
                tableData.singer = item.singer;
                tableData.collection = item.collection;
                tableData.playCount = item.playCount;
                tableData.url = item.url;
                temp.push(tableData);
            })
            this.tableData = temp;
        },
        handleEdit(index) {

        }
    },
}
</script>

<style lang="less">
@import '../style/mixin';

.table_container {
    padding: 20px;
}

.el-pagination {
    text-align: center;
}
</style>
