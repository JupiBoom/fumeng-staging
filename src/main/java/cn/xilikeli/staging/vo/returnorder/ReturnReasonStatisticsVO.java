package cn.xilikeli.staging.vo.returnorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 退货原因统计分析 VO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "退货原因统计分析 VO", description = "退货原因统计分析的响应对象")
public class ReturnReasonStatisticsVO {

    /**
     * 退货原因
     */
    @ApiModelProperty(value = "退货原因")
    private String reason;

    /**
     * 退货次数
     */
    @ApiModelProperty(value = "退货次数")
    private Integer count;

    /**
     * 占比
     */
    @ApiModelProperty(value = "占比")
    private Double proportion;
}
