package com.example.cloudtry.utils;

import com.example.cloudtry.common.constant.result.ResultInfo;
import com.example.cloudtry.model.exceptions.BusinessException;
import com.example.cloudtry.model.exceptions.DuplicateSubmissionException;
import com.example.cloudtry.model.exceptions.NoAuthException;
import com.example.cloudtry.model.exceptions.ParamErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTools {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionTools.class);

    /**
     * 打印业务异常
     *
     * @param errorMsg 异常信息
     */
    public static void businessLogger(String errorMsg) {
        logger.error(errorMsg);
        throw new BusinessException(errorMsg);
    }

    /**
     * 打印业务异常
     */
    public static void businessLogger() {
        logger.error(ResultInfo.UNKNOWN_ERROR.getMessage());
        throw new BusinessException(ResultInfo.UNKNOWN_ERROR.getMessage());
    }

    /**
     * 打印参数异常
     */
    public static void paramLogger() {
        throw new ParamErrorException();
    }

    /**
     * 打印无数据异常
     */
    public static void noDataLogger() {
        throw new BusinessException(ResultInfo.NOT_FOUND.getMessage());
    }

    /**
     * 防刷异常
     */
    public static void duplicateMissionExp() {
        throw new DuplicateSubmissionException();
    }

    /**
     * 无权限
     */
    public static void noAuthExp(String exp) {
        logger.error(exp);
        throw new NoAuthException(exp);
    }
}
