package com.goplatform.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.pojo.constant.KataUrl;
import com.goplatform.server.pojo.domain.KataCount;
import com.goplatform.server.service.KataService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class KataServiceImpl implements KataService {

    @Resource
    RestTemplate restTemplate;

    @Resource
    KataUrl kataUrl;

    public KataCount endCount(int[][] board) {
        KataCount ret;
        try {
            JSONObject param = new JSONObject();
            param.put("board", board);
            ret = restTemplate.postForObject(kataUrl.baseUrl + kataUrl.endCountPath, param, KataCount.class);
            return ret;
        } catch (Exception e) {
            throw new GoServerException(ExceptionEnum.KATA_GET_FAILED);
        }
    }
}
