package cn.keking.normal.model.storage;

import cn.keking.normal.common.enums.BaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class MiniInfo {
    private long id;
    private BaseTypeEnum baseType;
}
