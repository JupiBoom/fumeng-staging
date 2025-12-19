package cn.xilikeli.staging.vo.returnorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 退货原因统计 VO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "退货原因统计 VO", description = "退货原因统计 VO")
public class ReturnReasonStatsVO {

    /**
     * 退货原因
     */
    @ApiModelProperty(value = "退货原因")
    private Integer reason;

    /**
     * 退货原因描述
     */
    @ApiModelProperty(value = "退货原因描述")
    private String reasonDescription;

    /**
     * 退货数量
     */
    @ApiModelProperty(value = "退货数量")
    private Integer count;

    /**
     * 占比
     */
    @ApiModelProperty(value = "占比")
    private Double percentage;

}
