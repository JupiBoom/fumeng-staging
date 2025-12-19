package cn.xilikeli.staging.dto.returnorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 退款处理 DTO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "退款处理 DTO", description = "退款处理 DTO")
public class RefundDTO {

    /**
     * 退款金额
     */
    @NotNull(message = "退款金额不能为空")
    @ApiModelProperty(value = "退款金额", required = true)
    private Integer refundAmount;

    /**
     * 退款流水号
     */
    @NotNull(message = "退款流水号不能为空")
    @ApiModelProperty(value = "退款流水号", required = true)
    private String refundNo;

    /**
     * 退款备注
     */
    @ApiModelProperty(value = "退款备注")
    private String refundRemark;

}
