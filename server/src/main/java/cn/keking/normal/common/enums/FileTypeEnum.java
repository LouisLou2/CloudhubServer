package cn.keking.normal.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public enum FileTypeEnum{

    IMAGE(1, "Picture"),
    AUDIO(2, "Audio"),
    VIDEO(3, "Video"),
    DOCUMENT(4, "Document"),
    OTHER(5, "Other");

    final int code;
    final String msg;
    @NonNull
    public static FileTypeEnum parseTypeByCode(int code){
        for(FileTypeEnum type:FileTypeEnum.values()){
            if(type.getCode()==code){
                return type;
            }
        }
        return null;
    }
}
