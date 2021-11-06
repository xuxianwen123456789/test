package com.trkj.xuxianwen.common;

public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }
    public Result(T data){this.data=data;}
    public static com.trkj.xuxianwen.common.Result success(){
        com.trkj.xuxianwen.common.Result result =new com.trkj.xuxianwen.common.Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }
    public static<T> com.trkj.xuxianwen.common.Result<T> success(T data){
        com.trkj.xuxianwen.common.Result<T> result =new com.trkj.xuxianwen.common.Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }
    public static com.trkj.xuxianwen.common.Result error(String code, String msg){
        com.trkj.xuxianwen.common.Result result =new com.trkj.xuxianwen.common.Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
