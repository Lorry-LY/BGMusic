package com.music.bigdata.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.music.bigdata.common.Message;
import com.music.bigdata.common.impl.ErrorMessage;
import com.music.bigdata.common.impl.SuccessMessage;
import com.music.bigdata.entity.Music;
import com.music.bigdata.mapper.BusinessMapper;
import com.music.bigdata.service.MusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicServiceImpl implements MusicService {

    @Resource
    private BusinessMapper businessMapper;

    @Override
    public Message getMusicList(int kind, int index, int pageNumber) {
        try {
            List<Music> results = businessMapper.getMusicList(kind, index, pageNumber);
            return new SuccessMessage<>("查询成功", results);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }

    }

    @Override
    public Message getOverall(int index, int pageNumber) {
        try {
            List<Music> results = businessMapper.getOverall(index, pageNumber);
            return new SuccessMessage<>("查询成功", results);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }

    }

    @Override
    public Message getStyleList(int kind, int index, int pageNumber) {
        try {
            List<Music> results = businessMapper.getStyleList(kind, index, pageNumber);
            return new SuccessMessage<>("查询成功", results);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }
    }

    @Override
    public Message getEmotionList(int kind, int index, int pageNumber) {
        try {
            List<Music> results = businessMapper.getEmotionList(kind, index, pageNumber);
            return new SuccessMessage<>("查询成功", results);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }
    }

    @Override
    public Message getLanguageList(int kind, int index, int pageNumber) {
        try {
            List<Music> results = businessMapper.getLanguageList(kind, index, pageNumber);
            return new SuccessMessage<>("查询成功", results);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }
    }

    @Override
    public Message getMusicListCount(int kind, int pageNumber) {
        try {
            List<JSONObject> results = businessMapper.getMusicListCount(kind);
            int total = Integer.parseInt(results.get(0).get("count").toString());
            return new SuccessMessage<>("查询成功", total);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }
    }

    @Override
    public Message getStyleListPercent() {
        try {
            List<JSONObject> results = businessMapper.getStyleListPercent();
            System.out.println(results);
            return new SuccessMessage<>("查询成功", results);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }
    }

    @Override
    public Message getSearchCount(String text) {
        try {
            List<JSONObject> results = businessMapper.getSearchCount(text);
            int total = Integer.parseInt(results.get(0).get("count").toString());
            return new SuccessMessage<>("查询成功", total);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }
    }

    @Override
    public Message getSearchResult(String text, int startIndex, int pageNumber) {
        try {
            List<Music> results = businessMapper.getSearchResult(text, startIndex, pageNumber);
            return new SuccessMessage<>("查询成功", results);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }
    }

    @Override
    public Message getRecommendMusic(String userID) {
        try {
            List<String> rec_songs = new ArrayList<>();
            List<JSONObject> results = businessMapper.findRecommend(userID);
            if (results.isEmpty()) {
                List<JSONObject> temp = businessMapper.findHotSong();
                List<Integer> indexs = new ArrayList<>();
                for (int i = 0; i < 50; i++) {
                    int n;
                    do {
                        n = (int) (Math.random() * temp.size());
                    } while (indexs.contains(n));
                    indexs.add(n);
                    rec_songs.add(temp.get(n).get("_id").toString());
                }
            } else {
                rec_songs = Arrays.stream(results.get(0).get("rec_songs").toString().split(";")).collect(Collectors.toList());
            }
            System.out.println(rec_songs);
            List<Music> musics = businessMapper.findRecommendSong(rec_songs);
            return new SuccessMessage<>("查询成功", musics);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }
    }

    @Override
    public Message getMusicImage(String id) {
        try {
            List<JSONObject> results = businessMapper.getMusicImage(id);
            if (results.isEmpty()) return new ErrorMessage("数据库错误");
//            Binary data = (Binary) results.get(0).get("img");
//            data.getData()
//            // 保存图片
//            OutputStream out = new FileOutputStream("file/temp/img/1.jpg");
//            out.flush();
//            out.close();
//            // 返回图片的相对路径 = 图片分类路径+图片名+图片后缀
//            return new SuccessMessage<>("返回成功", "file/temp/img/1.jpg");
            return new SuccessMessage<>("获取成功", results.get(0).get("img"));
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("错误");
        }
    }
}
