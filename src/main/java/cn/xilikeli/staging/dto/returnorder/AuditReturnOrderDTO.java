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
 * @date 2024/05/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "审核退货订单 DTO", description = "商家审核退货订单时使用的 DTO")
public class AuditReturnOrderDTO {

    /**
     * 审核结果: 1-通过, 2-拒绝
     */
    @NotNull(message = "审核结果不能为空")
    @ApiModelProperty(value = "审核结果: 1-通过, 2-拒绝", required = true)
    private Integer auditResult;

    /**
     * 审核备注
     */
    @ApiModelProperty(value = "审核备注")
    private String auditRemark;
}
