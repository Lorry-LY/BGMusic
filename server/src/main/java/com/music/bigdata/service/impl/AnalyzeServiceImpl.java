package com.music.bigdata.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.music.bigdata.common.Message;
import com.music.bigdata.common.impl.ErrorMessage;
import com.music.bigdata.common.impl.SuccessMessage;
import com.music.bigdata.entity.AnalyzeCount;
import com.music.bigdata.mapper.AnalyzeMapper;
import com.music.bigdata.service.AnalyzeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnalyzeServiceImpl implements AnalyzeService {

    @Resource
    private AnalyzeMapper analyzeMapper;

    @Override
    public Message getSumAnalyze(int kind) {
        try {
            List<AnalyzeCount> results = analyzeMapper.getSumAnalyze(kind);
            return new SuccessMessage<>("分析成功", results);
        } catch (Exception e) {
            return new ErrorMessage("分析错误");
        }
    }

    @Override
    public Message getSongSumAnalyze() {
        try {
            List<AnalyzeCount> results = analyzeMapper.getSongSumAnalyze();
            return new SuccessMessage<>("分析成功", results);
        } catch (Exception e) {
            return new ErrorMessage("分析错误");
        }
    }

    @Override
    public Message getMostPopularAnalyze(int kind) {
        try {
            List<AnalyzeCount> results = analyzeMapper.getMostPopularAnalyze(kind);
            return new SuccessMessage<>("分析成功", results);
        } catch (Exception e) {
            return new ErrorMessage("分析错误");
        }
    }

    @Override
    public Message getBestSongAnalyze() {
        try {
            List<JSONObject> results = analyzeMapper.getBestSongAnalyze();
            return new SuccessMessage<>("分析成功", results);
        } catch (Exception e) {
            return new ErrorMessage("分析错误");
        }
    }

    @Override
    public Message getDayStream() {
        try {
            List<JSONObject> results = analyzeMapper.getDayStream();
            return new SuccessMessage<>("分析成功", results);
        } catch (Exception e) {
            return new ErrorMessage("分析错误");
        }
    }

    @Override
    public Message getHourStream() {
        try {
            List<JSONObject> results = analyzeMapper.getHourStream();
            return new SuccessMessage<>("分析成功", results);
        } catch (Exception e) {
            return new ErrorMessage("分析错误");
        }
    }
}
