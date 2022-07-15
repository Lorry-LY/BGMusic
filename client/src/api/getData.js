import fetch from '@/config/fetch'

export const getAdminInfo = () => fetch('/admin/info');

export const getSearchCount = data => fetch('/musicList/searchCount', data)
export const getSearchResult = data => fetch('/musicList/searchResult', data)

export const getRecommendMusic = data => fetch('/recommendMusic', data)
export const getMusicImage = data => fetch('/file/musicImage', data)

/**
 * 登陆
 */

export const login = data => fetch('/client/login', data, 'POST');
export const register = data => fetch('/client/register', data, 'POST');
/**
 * 退出
 */

export const signout = () => fetch('/client/logout');

/**
 * 获取用户列表
 */

export const getUserList = data => fetch('/musicList', data);

/**
 * 获取用户数量
 */

export const getUserCount = data => fetch('/musicList/count', data);

/**
 * 获取订单列表
 */

export const getMusicList = data => fetch('/musicList', data);

export const getMusicCount = data => fetch('/musicList/count', data);

export const getStylePercent = () => fetch('/styleList/percent');

export const getBestSong = () => fetch('/analyze/bestSong')

export const getBestList = data => fetch('/analyze/best', data)

export const getCirculation = () => fetch('/analyze/songmax')

export const getDayStream = () => fetch('/analyze/dayStream')

export const getHourStream = () => fetch('/analyze/hourStream')
