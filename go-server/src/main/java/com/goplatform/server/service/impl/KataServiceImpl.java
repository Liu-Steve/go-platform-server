package com.goplatform.server.service.impl;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.pojo.constant.Constants;
import com.goplatform.server.pojo.domain.ChessDrop;
import com.goplatform.server.pojo.domain.KataCount;
import com.goplatform.server.pojo.domain.Result;
import com.goplatform.server.service.KataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class KataServiceImpl implements KataService {
    public static final String endCountPath = "/kata/count/";

    public static final String startPath = "/kata/start/";

    public static final String playPath = "/kata/play/";

    public static final String genPath = "/kata/gen/";

    public static final String destroyPath = "/kata/destroy/";

    private static final Logger logger = LoggerFactory.getLogger(KataServiceImpl.class);

    @Resource
    private RestTemplate restTemplate;

    @Value("${katago.url}")
    public String baseUrl;

    @Override
    @SuppressWarnings("unchecked")
    public KataCount endCount(Long roomId) {

        logger.debug("AI begin to count result int room {}", roomId);
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
        logger.info("AI result count black: {}, white: {} in room {}", res.getBlack(), res.getWhite(), roomId);
        return res;
    }

    @Override
    public void start(Long roomId) {
        logger.debug("AI begin to init int room {}", roomId);
        restTemplate.put(baseUrl + startPath + roomId, null);
        logger.info("AI init success in room {}", roomId);
    }

    @Override
    public void play(Long roomId, ChessDrop chessDrop, String color) {
        logger.debug("user {} begin to input row: {}, col: {} in room {} to AI",
                roomId, chessDrop.getDropPosition().get(0), chessDrop.getDropPosition().get(1), roomId);
        restTemplate.postForObject(baseUrl + playPath + roomId + "/" + color, chessDrop, Result.class);
        logger.info("user {} input row: {}, col: {} in room {} to AI success",
                roomId, chessDrop.getDropPosition().get(0), chessDrop.getDropPosition().get(1), roomId);
    }

    @Override
    public ChessDrop gen(Long roomId, String color) {
        logger.debug("AI begin to generate position in room {}", roomId);
        ChessDrop forObject = restTemplate.getForObject(baseUrl + genPath + roomId + "/" + color, ChessDrop.class);
        if (forObject == null) {
            throw new GoServerException(ExceptionEnum.KATA_GET_FAILED);
        }
        forObject.setDropPosition(new ArrayList<Integer>(){{ add((int) (Math.random() * 19)); add((int) (Math.random() * 19)); }});
        logger.info("AI generate position row: {}, col: {} in room {} success",
                forObject.getDropPosition().get(0), forObject.getDropPosition().get(1), roomId);
        return forObject;
    }


    @Override
    public void destroy(Long roomId) {
        logger.debug("AI begin to destroy in room {}", roomId);
        restTemplate.delete(baseUrl + destroyPath + roomId);
        logger.info("AI destroy success in room {}", roomId);
    }
}
