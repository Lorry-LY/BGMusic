<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-carousel :interval="4000" height="350px" type="card">
            <el-carousel-item v-for="item in 6" :key="item">
                <img :src="'http://114.132.232.157:9999/file/img/index'+item+'.jpeg'" alt=""
                     style="width: 100%;height: 100%">
            </el-carousel-item>
        </el-carousel>
        <el-row class="contain">
            <template>
                <el-popconfirm
                    cancel-button-text='再看看'
                    confirm-button-text='刷新'
                    icon="el-icon-info"
                    icon-color="black"
                    title="这是一段内容确定删除吗？"
                    @confirm="fresh"
                >
                    <el-button slot="reference" class="fresh">猜你喜欢</el-button>
                </el-popconfirm>
            </template>
        </el-row>
        <el-row class="contain">
            <el-col v-for="fit in currentMusic" :key="fit.info.id" :span="6" class="item">
                <el-row class="img">
                    <a :href="fit.info.url">
                        <img :src="fit.img" alt="图片加载失败" class="img">
                    </a>
                </el-row>
                <br/>
                <span class="title"><a :href="fit.info.url">{{ fit.info.name }}</a></span>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import headTop from '../components/headTop'
import {getMusicImage, getRecommendMusic} from '@/api/getData'

export default {
    data() {
        return {
            recommendMusic: null,
            currentMusic: null,
        }
    },
    created() {
        this.recommend()
    },
    methods: {
        async recommend() {
            let user = JSON.parse(localStorage.getItem('user'))
            console.log(user)
            const res = await getRecommendMusic({userID: user.id});
            if (res.success === true) {
                this.recommendMusic = res.data;
                await this.fresh()
            }
        },
        async fresh() {
            let indexs = []
            this.currentMusic = []
            for (let i = 0; i < 12; i++) {
                let number = null
                do {
                    number = Math.floor(Math.random() * this.recommendMusic.length)
                } while (indexs.includes(number))
                indexs.push(number)
                let data = await this.getImage(this.recommendMusic[number].id)
                this.currentMusic.push({info: this.recommendMusic[number], img: data})
            }
            console.log(indexs)

        },
        async getImage(id) {
            const res = await getMusicImage({id: id});
            if (res.success === true) {
                // console.log(res.data.data)
                return 'data:image/jpg;base64,' + res.data.data
            } else {
                return "/file/img/default.jpg"
            }
        }
    },
    components: {
        headTop
    }
}
</script>

<style lang="less" scoped>
@import '../style/mixin';

.contain {
    margin: 30px 100px;
}

.img {
    display: inline-block;
    width: 130px;
    height: 130px;
    border-radius: 50%;
}

.title {
    display: inline-block;
    height: 60px;
}

.img:hover {
    animation: loading 10s linear infinite;
    cursor: pointer;
}

@keyframes loading {
    /*以百分比来规定改变发生的时间 也可以通过"from"和"to",等价于0% 和 100%*/
    0% {
        /*rotate(2D旋转) scale(放大或者缩小) translate(移动) skew(翻转)*/
        transform: rotate(0deg);
    }
    100% {
        transform: rotate(360deg);
    }

}

a, a:visited {
    color: #000000;
}

.item {
    margin: 20px 0;
    text-align: center;
}

.el-avatar {
    border-radius: 50%;
}

.fresh {
    border: none;
}

.el-carousel {
    margin: 30px 50px;
}

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}
</style>
