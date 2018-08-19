package jzhu.com.libbase.base;

import java.io.Serializable;

public class BaseResp<T> implements Serializable {

    private T data;
    private String code;
    private String msg;

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
