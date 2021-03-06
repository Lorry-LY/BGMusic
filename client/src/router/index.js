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
            meta: [{path: ['????????????']},
            ],
        }, {
            path: '/hotList',
            component: hotList,
            meta: [{path: ['????????????', '?????????']},
            ],
        }, {
            path: '/newList',
            component: newList,
            meta: [{path: ['????????????', '?????????']},
            ],
        }, {
            path: '/soarList',
            component: soarList,
            meta: [{path: ['????????????', '?????????']},
            ],
        }, {
            path: '/originList',
            component: originList,
            meta: [{path: ['????????????', '?????????']},
            ],
        }, {
            path: '/styleList',
            component: styleList,
            meta: [{path: ['??????', '??????']},
                {kind: 1}
            ],
        }, {
            path: '/styleList',
            component: styleList,
            meta: [{path: ['??????', '??????']},
                {kind: 2}
            ],
        }, {
            path: '/musicStylePercent',
            component: musicStylePercent,
            meta: [{path: ['??????', '??????????????????']},
            ],
        }, {
            path: '/musicStyleCount',
            component: musicStyleCount,
            meta: [{path: ['??????', '????????????']},
            ],
        }, {
            path: '/bestSong',
            component: bestSong,
            meta: [{path: ['??????', '??????']},
            ],
        }, {
            path: '/bestSinger',
            component: bestSinger,
            meta: [{path: ['??????', '??????']},
            ],
        }, {
            path: '/bestCollection',
            component: bestCollection,
            meta: [{path: ['??????', '??????']},
            ],
        }, {
            path: '/circulation',
            component: circulation,
            meta: [{path: ['??????', '?????????']},
            ],
        }, {
            path: '/dayStream',
            component: dayStream,
            meta: [{path: ['??????', '????????????']},
            ],
        }, {
            path: '/hourStream',
            component: hourStream,
            meta: [{path: ['??????', '????????????']},
            ],
        }, {
            path: '/explain',
            component: explain,
            meta: ['??????', '??????'],
        }]
    }
]

export default new Router({
    routes,
    strict: process.env.NODE_ENV !== 'production',
})
