package cn.keking.normal.service.validator;

public interface UserOperateValidator {
    boolean isKeyValid(String key);
    boolean hasEditPermission(String key);
}
