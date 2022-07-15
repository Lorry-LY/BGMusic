<template>
    <div class="manage_page fillcontain">
        <el-row style="height: 100%;">
            <el-col :span="4" style="min-height: 100%; background-color: #324057;">
                <el-row style='text-align: center;height: 70px;line-height: 70px;
                    color:#EFF2F7;font-size: x-large;'>
                    BGMusic
                </el-row>
                <el-row>
                    <el-input
                        v-model="searchText"
                        placeholder="请输入内容"
                        prefix-icon="el-icon-search">
                    </el-input>
                    <el-button type="primary" @click="search">搜索</el-button>
                </el-row>
                <el-menu :default-active="defaultActive" active-text-color="#ffd04b"
                         background-color="#324057"
                         router
                         style="min-height: 100%;"
                         text-color="#fff">
                    <el-menu-item index="manage"><i class="el-icon-menu"></i>首页</el-menu-item>
                    <el-menu-item index="hotList">热歌榜</el-menu-item>
                    <el-menu-item index="newList">新歌榜</el-menu-item>
                    <el-menu-item index="soarList">飙升榜</el-menu-item>
                    <el-menu-item index="originList">怀旧榜</el-menu-item>
                    <el-submenu index="4" theme="dark">
                        <template slot="title"><i class="el-icon-star-on"></i>图表</template>
                        <el-submenu index="4-1">
                            <template slot="title"><i class="el-icon-star-on"></i>数据分析</template>
                            <el-menu-item index="musicStylePercent">歌曲种类占比</el-menu-item>
                            <el-menu-item index="bestSong">歌曲排行</el-menu-item>
                            <el-menu-item index="bestSinger">歌手排行</el-menu-item>
                            <el-menu-item index="bestCollection">专辑排行</el-menu-item>
                            <el-menu-item index="circulation">发行量</el-menu-item>
                        </el-submenu>
                        <el-submenu index="4-2">
                            <template slot="title"><i class="el-icon-star-on"></i>业务分析</template>
                            <el-menu-item index="musicStyleCount">歌曲种类数量</el-menu-item>
                        </el-submenu>
                        <el-submenu index="4-3">
                            <template slot="title"><i class="el-icon-star-on"></i>系统分析</template>
                            <el-menu-item index="dayStream">每天流量</el-menu-item>
                            <el-menu-item index="hourStream">小时流量</el-menu-item>
                        </el-submenu>
                        <!-- <el-menu-item index="newMember">用户数据</el-menu-item> -->
                    </el-submenu>
                    <el-submenu index="7">
                        <template slot="title"><i class="el-icon-warning"></i>说明</template>
                        <el-menu-item index="explain">说明</el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-col>
            <el-col :span="20" style="height: 100%;overflow: auto;">
                <keep-alive>
                    <router-view></router-view>
                </keep-alive>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
    data() {
        return {
            styleList: [
                {id: 1, name: "styleList1", path: "styleList", value: 1, text: "流行"},
                {id: 2, name: "styleList2", path: "styleList", value: 2, text: "古典"},
            ],
            searchText: ''
        }
    },
    methods: {
        search() {
            this.$bus.$emit("search", this.searchText)
            this.$router.push({
                path: `musicList`,
            })
        }
    },
    computed: {
        defaultActive: function () {
            return this.$route.path.replace('/', '')
        }
    },
}
</script>


<style lang="less" scoped>
@import '../style/mixin';

.el-menu {
    background-color: #324057;
    color: #EFF2F7;
}

.el-input {
    margin: 30px 0 20px 10px;
    width: 65%;
}

.el-button {
    margin-left: 5px;
}
</style>
