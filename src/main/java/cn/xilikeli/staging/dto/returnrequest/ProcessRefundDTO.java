package cn.xilikeli.staging.dto.returnrequest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * <p>
 * 处理退款 DTO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class ProcessRefundDTO {

    /**
     * 退款金额
     */
    @NotNull(message = "退款金额不能为空")
    private BigDecimal refundAmount;

    /**
     * 退款方式
     */
    @NotNull(message = "退款方式不能为空")
    private Integer refundMethod;

    /**
     * 退款备注
     */
    @Size(max = 500, message = "退款备注不能超过500个字符")
    private String refundRemark;

}