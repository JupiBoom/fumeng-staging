package cn.xilikeli.staging.dto.returnorder;

import cn.xilikeli.staging.common.enumeration.ReturnReasonEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 创建退货订单 DTO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "创建退货订单 DTO", description = "创建退货订单 DTO")
public class CreateReturnOrderDTO {

    /**
     * 原订单 ID
     */
    @NotNull(message = "原订单 ID 不能为空")
    @ApiModelProperty(value = "原订单 ID", required = true)
    private Long orderId;

    /**
     * 商品 ID
     */
    @NotNull(message = "商品 ID 不能为空")
    @ApiModelProperty(value = "商品 ID", required = true)
    private Long productId;

    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不能为空")
    @ApiModelProperty(value = "商品名称", required = true)
    private String productName;

    /**
     * 退货数量
     */
    @NotNull(message = "退货数量不能为空")
    @ApiModelProperty(value = "退货数量", required = true)
    private Integer quantity;

    /**
     * 退货原因
     * @see ReturnReasonEnum
     */
    @NotNull(message = "退货原因不能为空")
    @ApiModelProperty(value = "退货原因", required = true)
    private Integer reason;

    /**
     * 退货说明
     */
    @ApiModelProperty(value = "退货说明")
    private String description;

    /**
     * 退货图片
     */
    @ApiModelProperty(value = "退货图片")
    private String images;

}
