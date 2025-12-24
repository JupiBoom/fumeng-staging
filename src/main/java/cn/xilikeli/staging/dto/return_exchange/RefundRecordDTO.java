package cn.xilikeli.staging.dto.return_exchange;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * <p>
 * 退款记录信息 DTO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "退款记录信息 DTO", description = "退款记录信息 DTO")
public class RefundRecordDTO {

    /**
     * 退货申请 ID
     */
    @NotNull(message = "退货申请 ID 不能为空")
    @ApiModelProperty(value = "退货申请 ID", required = true, example = "1")
    private Long returnApplicationId;

    /**
     * 退款金额
     */
    @NotNull(message = "退款金额 不能为空")
    @DecimalMin(value = "0.01", message = "退款金额 必须大于 0")
    @ApiModelProperty(value = "退款金额", required = true, example = "99.99")
    private BigDecimal refundAmount;

    /**
     * 退款渠道
     */
    @ApiModelProperty(value = "退款渠道", example = "支付宝")
    private String refundChannel;

    /**
     * 退款单号
     */
    @ApiModelProperty(value = "退款单号", example = "REF1234567890")
    private String refundNo;

    /**
     * 退款备注
     */
    @ApiModelProperty(value = "退款备注", example = "正常退款")
    private String remark;

}