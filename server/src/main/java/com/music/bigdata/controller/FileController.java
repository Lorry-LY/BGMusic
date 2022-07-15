package com.music.bigdata.controller;

import com.music.bigdata.common.Message;
import com.music.bigdata.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Api(tags = "动态文件管理")
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private MusicService musicService;

    @ApiOperation(value = "获取图片", notes = "音乐图片管理")
    @RequestMapping(value = "/musicImage", method = RequestMethod.GET)
    public Message musicImage(
            @ApiParam(name = "id", required = true, value = "歌曲编号")
            @RequestParam(value = "id") String id
    ) {
        return musicService.getMusicImage(id);
    }

    private byte[] getImage(String path) throws IOException {
        //读取图片变成字节数组
        FileInputStream fileInputStream = new FileInputStream(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = fileInputStream.read(b)) != -1) {
            bos.write(b, 0, len);
        }
//        //进行base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
//        String data = encoder.encode(fileByte);
        return bos.toByteArray();
    }
}