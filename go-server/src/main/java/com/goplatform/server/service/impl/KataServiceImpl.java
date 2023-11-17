package com.goplatform.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.pojo.constant.Constants;
import com.goplatform.server.pojo.domain.ChessDrop;
import com.goplatform.server.pojo.domain.KataCount;
import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.service.KataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

@Service
public class KataServiceImpl implements KataService {
    public static final String endCountPath = "/kata/count/";

    public static final String startPath = "/kata/start/";

    public static final String playPath = "/kata/play/";

    public static final String genPath = "/kata/gen/";

    public static final String destroyPath = "/kata/destroy/";

    @Resource
    private RestTemplate restTemplate;

    @Value("${katago.url}")
    public String baseUrl;

    @Override
    @SuppressWarnings("unchecked")
    public KataCount endCount(Long roomId) {
        Result forObject = restTemplate.getForObject(baseUrl + endCountPath + roomId, Result.class);
        if (forObject == null) {
            throw new GoServerException(ExceptionEnum.KATA_GET_FAILED);
        }
        LinkedHashMap<String, Integer> result;
        try {
            result = (LinkedHashMap<String, Integer>) forObject.getResult();
        } catch (Exception e) {
            throw new GoServerException(ExceptionEnum.KATA_GET_FAILED);
        }

        KataCount res = new KataCount();
        res.setBlack(result.get(Constants.BLACK));
        res.setWhite(result.get(Constants.WHITE));
        return res;
    }

    @Override
    public void start(Long roomId) {
        restTemplate.put(baseUrl + startPath + roomId, null);
    }

    @Override
    public void play(Long roomId, ChessDrop chessDrop, String color) {
        restTemplate.postForObject(baseUrl + playPath + roomId + "/" + color, chessDrop, Result.class);
    }

    @Override
    public ChessDrop gen(Long roomId, String color) {
        return restTemplate.getForObject(baseUrl + genPath + roomId + "/" + color, ChessDrop.class);
    }


    @Override
    public void destroy(Long roomId) {
        restTemplate.delete(baseUrl + destroyPath + roomId);
    }
}
