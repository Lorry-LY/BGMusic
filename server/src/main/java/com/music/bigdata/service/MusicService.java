package com.music.bigdata.service;

import com.music.bigdata.common.Message;

public interface MusicService {

    Message getMusicList(int kind, int index, int pageNumber);

    Message getOverall(int index, int pageNumber);

    Message getStyleList(int kind, int index, int pageNumber);

    Message getEmotionList(int kind, int index, int pageNumber);

    Message getLanguageList(int kind, int index, int pageNumber);

    Message getMusicListCount(int kind, int pageNumber);

    Message getStyleListPercent();

    Message getSearchCount(String text);

    Message getSearchResult(String text, int startIndex, int pageNumber);

    Message getRecommendMusic(String userID);

    Message getMusicImage(String id);
}
