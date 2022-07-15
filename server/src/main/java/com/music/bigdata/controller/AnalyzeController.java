package com.music.bigdata.controller;

import com.music.bigdata.common.Message;
import com.music.bigdata.service.AnalyzeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "数据分析控制器")
@RestController
@RequestMapping("/analyze")
public class AnalyzeController {

    @Resource
    private AnalyzeService analyzeService;

    @ApiOperation(value = "数量分析", notes = "分析各种数据数量")
    @RequestMapping(value = "/sum", method = RequestMethod.GET, params = {"kind"})
    public Message sumAnalyze(
            @ApiParam(name = "kind", required = true, value = "分析表的索引", example = "0")
            @RequestParam(value = "kind") int kind
    ) {
        return analyzeService.getSumAnalyze(kind);
    }

    @ApiOperation(value = "每个歌手的歌曲量", notes = "统计不同歌手的歌曲量")
    @RequestMapping(value = "/songmax", method = RequestMethod.GET)
    public Message songSumAnalyze(
    ) {
        return analyzeService.getSongSumAnalyze();
    }

    @ApiOperation(value = "天流量", notes = "每天的流量")
    @RequestMapping(value = "/dayStream", method = RequestMethod.GET)
    public Message dayStream(
    ) {
        return analyzeService.getDayStream();
    }

    @ApiOperation(value = "小时流量", notes = "一天不同时段的流量")
    @RequestMapping(value = "/hourStream", method = RequestMethod.GET)
    public Message hourStream(
    ) {
        return analyzeService.getHourStream();
    }

    @ApiOperation(value = "最受欢迎种类", notes = "最受欢迎歌手、专辑")
    @RequestMapping(value = "/best", method = RequestMethod.GET)
    public Message mostPopularAnalyze(
            @ApiParam(name = "kind", required = true, value = "分析种类的索引", example = "0")
            @RequestParam(value = "kind") int kind
    ) {
        return analyzeService.getMostPopularAnalyze(kind);
    }

    @ApiOperation(value = "最受欢迎种类", notes = "最受欢迎歌曲")
    @RequestMapping(value = "/bestSong", method = RequestMethod.GET)
    public Message bestSongAnalyze(
    ) {
        return analyzeService.getBestSongAnalyze();
    }

}
