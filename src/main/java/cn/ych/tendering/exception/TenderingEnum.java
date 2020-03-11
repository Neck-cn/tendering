package cn.ych.tendering.exception;

public enum TenderingEnum {
    REQUEST_INVALID(400, "请求无效"),
    TOKEN_INVALID(401, "token无效"),
    PERMISSION_REFUSED(403, "权限不足"),
    USER_IS_EXISTED(422, "用户已存在"),
    ;
    private final int code;
    private final String errMsg;

    TenderingEnum(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
