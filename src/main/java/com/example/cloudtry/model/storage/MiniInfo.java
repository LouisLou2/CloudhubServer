package com.example.cloudtry.model.storage;

import com.example.cloudtry.common.enums.BaseTypeEnum;
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
