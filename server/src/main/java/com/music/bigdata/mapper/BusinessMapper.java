package com.music.bigdata.mapper;

import com.alibaba.fastjson.JSONObject;
import com.music.bigdata.Enum.MusicEnum;
import com.music.bigdata.entity.Music;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Repository
public class BusinessMapper {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private MusicEnum musicEnum;

    public List<Music> getMusicList(int kind, int index, int pageNumber) {
        String listName = musicEnum.getList(kind);
        listName = new String(listName.getBytes(), StandardCharsets.UTF_8);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup(listName + "_list", "_id", "_id", "List")
                , Aggregation.unwind("List", false)
                , Aggregation.sort(Sort.Direction.DESC, "comment")
                , Aggregation.skip(index)
                , Aggregation.limit(pageNumber)
        );
        return mongoTemplate.aggregate(aggregation, "song", Music.class).getMappedResults();
    }


    public List<Music> getOverall(int index, int pageNumber) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sort(Sort.Direction.DESC, "comment"),
                Aggregation.skip(index),
                Aggregation.limit(pageNumber)
        );
        return mongoTemplate.aggregate(aggregation, "song", Music.class).getMappedResults();
    }

    public List<Music> getStyleList(int kind, int index, int pageNumber) {
        String listName = musicEnum.getStyle(kind);
        listName = new String(listName.getBytes(), StandardCharsets.UTF_8);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("style", "_id", "_id", "List")
                , Aggregation.match(Criteria.where("List.category").is(listName))
                , Aggregation.unwind("List", false)
                , Aggregation.sort(Sort.Direction.DESC, "comment")
                , Aggregation.skip(index)
                , Aggregation.limit(pageNumber)
        );
        return mongoTemplate.aggregate(aggregation, "song", Music.class).getMappedResults();
    }

    public List<Music> getEmotionList(int kind, int index, int pageNumber) {
        String listName = musicEnum.getEmotion(kind);
        listName = new String(listName.getBytes(), StandardCharsets.UTF_8);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("emotion", "_id", "_id", "List")
                , Aggregation.match(Criteria.where("List.category").is(listName))
                , Aggregation.unwind("List", false)
                , Aggregation.sort(Sort.Direction.DESC, "comment")
                , Aggregation.skip(index)
                , Aggregation.limit(pageNumber)
        );
        return mongoTemplate.aggregate(aggregation, "song", Music.class).getMappedResults();
    }


    public List<Music> getLanguageList(int kind, int index, int pageNumber) {
        String listName = musicEnum.getLanguage(kind);
        listName = new String(listName.getBytes(), StandardCharsets.UTF_8);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("language", "_id", "_id", "List")
                , Aggregation.match(Criteria.where("List.category").is(listName))
                , Aggregation.unwind("List", false)
                , Aggregation.sort(Sort.Direction.DESC, "comment")
                , Aggregation.skip(index)
                , Aggregation.limit(pageNumber)
        );
        return mongoTemplate.aggregate(aggregation, "song", Music.class).getMappedResults();
    }


    public List<JSONObject> getMusicListCount(int kind) {
        String listName = musicEnum.getList(kind);
        listName = new String(listName.getBytes(), StandardCharsets.UTF_8);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.count().as("count"),
                Aggregation.project("count")
        );
        return mongoTemplate.aggregate(aggregation, listName + "_list", JSONObject.class).getMappedResults();
    }

    public List<JSONObject> getStyleListPercent() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("category", "counts")
        );
        return mongoTemplate.aggregate(aggregation, "percent_of_song", JSONObject.class).getMappedResults();
    }

    public List<JSONObject> getSearchCount(String text) {
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("song_name").regex(".*" + text + ".*")
                , Criteria.where("singer").regex(".*" + text + ".*")
                , Criteria.where("singer").regex(".*" + text + ".*"));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("song_name", "singer", "album"),
                Aggregation.match(criteria),
                Aggregation.count().as("count"),
                Aggregation.project("count")
        );
        return mongoTemplate.aggregate(aggregation, "song", JSONObject.class).getMappedResults();
    }

    public List<Music> getSearchResult(String text, int startIndex, int pageNumber) {
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("song_name").regex(".*" + text + ".*")
                , Criteria.where("singer").regex(".*" + text + ".*")
                , Criteria.where("singer").regex(".*" + text + ".*"));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria)
                , Aggregation.sort(Sort.Direction.DESC, "comment")
                , Aggregation.skip(startIndex)
                , Aggregation.limit(pageNumber)
        );
        return mongoTemplate.aggregate(aggregation, "song", Music.class).getMappedResults();
    }

    public List<JSONObject> findRecommend(String userID) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("user_id").is(userID))
        );
        return mongoTemplate.aggregate(aggregation, "recommendation_list", JSONObject.class).getMappedResults();
    }

    public List<JSONObject> findHotSong() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("_id")
        );
        return mongoTemplate.aggregate(aggregation, "hot_list", JSONObject.class).getMappedResults();
    }

    public List<Music> findRecommendSong(List<String> rec_songs) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("_id").in(rec_songs))
        );
        return mongoTemplate.aggregate(aggregation, "song", Music.class).getMappedResults();
    }

    public List<JSONObject> getMusicImage(String id) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("_id", "img"),
                Aggregation.match(Criteria.where("_id").is(id))
        );
        return mongoTemplate.aggregate(aggregation, "song", JSONObject.class).getMappedResults();
    }
}
