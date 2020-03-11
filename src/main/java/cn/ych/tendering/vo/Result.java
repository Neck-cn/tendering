package cn.ych.tendering.vo;

import cn.ych.tendering.exception.TenderingEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code = 200;
    private Object data;

    public Result(Object data) {
        if (data instanceof TenderingEnum) {
            this.code = ((TenderingEnum) data).getCode();
            this.data = ((TenderingEnum) data).getErrMsg();
        } else {
            this.data = data;
        }
    }
}