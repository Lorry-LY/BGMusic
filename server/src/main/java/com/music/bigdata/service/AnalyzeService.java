package com.music.bigdata.service;


import com.music.bigdata.common.Message;

public interface AnalyzeService {


    Message getSumAnalyze(int kind);

    //统计每个歌手的歌曲量
    Message getSongSumAnalyze();

    //统计最受欢迎歌手、专辑
    Message getMostPopularAnalyze(int kind);

    //统计最受欢迎歌曲
    Message getBestSongAnalyze();

    Message getDayStream();

    Message getHourStream();
}
