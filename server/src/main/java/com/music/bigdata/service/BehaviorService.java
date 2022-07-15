package com.music.bigdata.service;


import com.music.bigdata.entity.Music;

public interface BehaviorService {

    void recordSong(String userID, Music music);
}
