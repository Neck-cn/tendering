package cn.ych.tendering.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
public class TenderingException extends RuntimeException {
    private int code;
    private String errMsg;

    public TenderingException(TenderingEnum eryaEnum) {
        this.code = eryaEnum.getCode();
        this.errMsg = eryaEnum.getErrMsg();
    }

    @Override
    public String getMessage() {
        return this.errMsg;
    }
}
