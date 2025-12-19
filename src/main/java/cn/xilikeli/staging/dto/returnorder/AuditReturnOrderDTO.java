package cn.xilikeli.staging.dto.returnorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 审核退货订单 DTO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "审核退货订单 DTO", description = "审核退货订单 DTO")
public class AuditReturnOrderDTO {

    /**
     * 是否通过审核
     */
    @NotNull(message = "审核结果不能为空")
    @ApiModelProperty(value = "是否通过审核", required = true)
    private Boolean approved;

    /**
     * 审核备注
     */
    @ApiModelProperty(value = "审核备注")
    private String remark;

}
