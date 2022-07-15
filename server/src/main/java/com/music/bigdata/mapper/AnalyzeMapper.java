package com.music.bigdata.mapper;

import com.alibaba.fastjson.JSONObject;
import com.music.bigdata.Enum.AnalyzeEnum;
import com.music.bigdata.entity.AnalyzeCount;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Repository
public class AnalyzeMapper {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private AnalyzeEnum analyzeEnum;

    public List<AnalyzeCount> getSumAnalyze(int kind) {
        String listName = analyzeEnum.getLists(kind);
        listName = new String(listName.getBytes(), StandardCharsets.UTF_8);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sort(Sort.by("category"))
                , Aggregation.project("category", "counts")
                , Aggregation.limit(20)
        );
        return mongoTemplate.aggregate(aggregation, listName, AnalyzeCount.class).getMappedResults();
    }

    public List<AnalyzeCount> getSongSumAnalyze() {
        String listName = analyzeEnum.getCollection();
        listName = new String(listName.getBytes(), StandardCharsets.UTF_8);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sort(Sort.Direction.DESC, "counts")
                , Aggregation.project("category", "counts")
                , Aggregation.limit(10)
        );
        return mongoTemplate.aggregate(aggregation, listName, AnalyzeCount.class).getMappedResults();
    }

    public List<AnalyzeCount> getMostPopularAnalyze(int kind) {
        String listName = analyzeEnum.getLists(kind);
        listName = new String(listName.getBytes(), StandardCharsets.UTF_8);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sort(Sort.Direction.DESC, "counts")
                , Aggregation.project("category", "counts")
                , Aggregation.limit(10)
        );
        return mongoTemplate.aggregate(aggregation, listName, AnalyzeCount.class).getMappedResults();
    }

    public List<JSONObject> getBestSongAnalyze() {
        String listName = analyzeEnum.getSong();
        listName = new String(listName.getBytes(), StandardCharsets.UTF_8);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sort(Sort.Direction.DESC, "comment")
                , Aggregation.project("song_name", "comment")
                , Aggregation.limit(10)
        );
        return mongoTemplate.aggregate(aggregation, listName, JSONObject.class).getMappedResults();
    }

    public List<JSONObject> getDayStream() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("date", "counts")
        );
        return mongoTemplate.aggregate(aggregation, "num_of_day", JSONObject.class).getMappedResults();
    }

    public List<JSONObject> getHourStream() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("hour", "counts")
        );
        return mongoTemplate.aggregate(aggregation, "num_of_hour", JSONObject.class).getMappedResults();
    }
}
