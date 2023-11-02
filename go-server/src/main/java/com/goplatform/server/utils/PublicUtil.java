package com.goplatform.server.utils;

import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;

/**
 * 公共方法类
 */
public class PublicUtil {
    public static long getUUID() {
        return System.currentTimeMillis();
    }

    public static void checkUserIdValid(Long userId, UserRepository userRepository) {
        if (userId == null || !userRepository.findById(userId).isPresent()) {
            throw new GoServerException(ExceptionEnum.USER_USERID_INVALID);
        }
    }
}
