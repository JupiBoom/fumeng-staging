package cn.xilikeli.staging.dto.returnorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 物流信息 DTO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "物流信息 DTO", description = "物流信息录入时使用的 DTO")
public class LogisticsInfoDTO {

    /**
     * 物流单号
     */
    @NotNull(message = "物流单号不能为空")
    @ApiModelProperty(value = "物流单号", required = true)
    private String logisticsNo;

    /**
     * 物流公司
     */
    @NotNull(message = "物流公司不能为空")
    @ApiModelProperty(value = "物流公司", required = true)
    private String logisticsCompany;
}
