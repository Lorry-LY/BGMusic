import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)


const login = r => require.ensure([], () => r(require('@/page/login')), 'login');
const register = r => require.ensure([], () => r(require('@/page/register')), 'register');
const manage = r => require.ensure([], () => r(require('@/page/manage')), 'manage');
const index = r => require.ensure([], () => r(require('@/page/index')), 'index');
const explain = r => require.ensure([], () => r(require('@/page/explain')), 'explain');
const musicList = r => require.ensure([], () => r(require('@/page/musicList')), 'musicList');
const hotList = r => require.ensure([], () => r(require('@/page/hotList')), 'hotList');
const newList = r => require.ensure([], () => r(require('@/page/newList')), 'newList');
const soarList = r => require.ensure([], () => r(require('@/page/soarList')), 'soarList');
const originList = r => require.ensure([], () => r(require('@/page/originList')), 'originList');
const musicStylePercent = r => require.ensure([], () => r(require('@/page/echarts/Pie/stylePie')), 'musicStylePercent');
const musicStyleCount = r => require.ensure([], () => r(require('@/page/echarts/Bar/styleBar')), 'musicStyleCount');
const bestSong = r => require.ensure([], () => r(require('@/page/echarts/Bar/bestSongBar')), 'bestSong');
const bestSinger = r => require.ensure([], () => r(require('@/page/echarts/Bar/bestSingerBar')), 'bestSinger');
const bestCollection = r => require.ensure([], () => r(require('@/page/echarts/Bar/bestCollectionBar')), 'bestCollection');
const circulation = r => require.ensure([], () => r(require('@/page/echarts/Bar/circulationBar')), 'circulation');
const dayStream = r => require.ensure([], () => r(require('@/page/echarts/Area/dayStreamArea')), 'dayStream');
const hourStream = r => require.ensure([], () => r(require('@/page/echarts/Area/hourStreamArea')), 'hourStream');

const styleList = r => require.ensure([], () => r(require('@/page/styleList')), 'styleList');


const routes = [
    {
        path: '/',
        component: login
    },
    {
        path: '/register',
        component: register
    },
    {
        path: '/manage',
        component: manage,
        name: '',
        children: [{
            path: '',
            component: index,
            meta: [''],
        }, {
            path: '/musicList',
            component: musicList,
            meta: [{path: ['搜素结果']},
            ],
        }, {
            path: '/hotList',
            component: hotList,
            meta: [{path: ['数据管理', '热歌榜']},
            ],
        }, {
            path: '/newList',
            component: newList,
            meta: [{path: ['数据管理', '新歌榜']},
            ],
        }, {
            path: '/soarList',
            component: soarList,
            meta: [{path: ['数据管理', '飙升榜']},
            ],
        }, {
            path: '/originList',
            component: originList,
            meta: [{path: ['数据管理', '怀旧榜']},
            ],
        }, {
            path: '/styleList',
            component: styleList,
            meta: [{path: ['种类', '流行']},
                {kind: 1}
            ],
        }, {
            path: '/styleList',
            component: styleList,
            meta: [{path: ['种类', '古典']},
                {kind: 2}
            ],
        }, {
            path: '/musicStylePercent',
            component: musicStylePercent,
            meta: [{path: ['图表', '歌曲种类占比']},
            ],
        }, {
            path: '/musicStyleCount',
            component: musicStyleCount,
            meta: [{path: ['图表', '歌曲种类']},
            ],
        }, {
            path: '/bestSong',
            component: bestSong,
            meta: [{path: ['图表', '歌曲']},
            ],
        }, {
            path: '/bestSinger',
            component: bestSinger,
            meta: [{path: ['图表', '歌手']},
            ],
        }, {
            path: '/bestCollection',
            component: bestCollection,
            meta: [{path: ['图表', '专辑']},
            ],
        }, {
            path: '/circulation',
            component: circulation,
            meta: [{path: ['图表', '发行量']},
            ],
        }, {
            path: '/dayStream',
            component: dayStream,
            meta: [{path: ['图表', '每天流量']},
            ],
        }, {
            path: '/hourStream',
            component: hourStream,
            meta: [{path: ['图表', '小时流量']},
            ],
        }, {
            path: '/explain',
            component: explain,
            meta: ['说明', '说明'],
        }]
    }
]

export default new Router({
    routes,
    strict: process.env.NODE_ENV !== 'production',
})
