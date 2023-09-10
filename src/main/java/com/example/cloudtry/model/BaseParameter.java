package com.example.cloudtry.model;

import cn.hutool.core.date.DateUtil;
import com.example.cloudtry.common.enums.YesOrNoEnum;

public class BaseParameter {
    /**
     * 创建时间
     */
    private Long createTime = DateUtil.current();

    /**
     * 修改时间
     */
    private Long updateTime = DateUtil.current();

    /**
     * 是否已删除
     */
    private Integer isDelete = YesOrNoEnum.NO.getCode();

    public Long getCreateTime() {
        return createTime;
    }

    public BaseParameter setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public BaseParameter setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public BaseParameter setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        return "BaseParameter["
                + " createTime=" + createTime + ","
                + " updateTime=" + updateTime + ","
                + " isDelete=" + isDelete
                + " ]";
    }
}
