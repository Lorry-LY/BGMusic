package com.music.bigdata.Enum;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource(value = "/config/analyze.properties", encoding = "UTF-8")
public class AnalyzeEnum {

    @Value("${analyze.list}")
    private List<String> lists = new ArrayList<>();

    @Value("${analyze.song_num}")
    private List<String> most_song_singer_list = new ArrayList<>();

    @Value("${analyze.best_song}")
    private List<String> best_song_list = new ArrayList<>();

    public String getLists(int index) {
        if (index < 0 || index >= lists.size()) try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists.get(index);
    }

    public String getCollection() {
        return most_song_singer_list.get(0);
    }

    public String getSong() {
        return best_song_list.get(0);
    }

}
