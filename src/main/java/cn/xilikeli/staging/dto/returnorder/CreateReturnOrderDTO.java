package cn.xilikeli.staging.dto.returnorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 创建退货订单 DTO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "创建退货订单 DTO", description = "创建退货订单时使用的 DTO")
public class CreateReturnOrderDTO {

    /**
     * 订单 ID
     */
    @NotNull(message = "订单 ID 不能为空")
    @ApiModelProperty(value = "订单 ID", required = true)
    private Long orderId;

    /**
     * 订单编号
     */
    @NotNull(message = "订单编号不能为空")
    @ApiModelProperty(value = "订单编号", required = true)
    private String orderNo;

    /**
     * 退货商品 ID
     */
    @NotNull(message = "退货商品 ID 不能为空")
    @ApiModelProperty(value = "退货商品 ID", required = true)
    private Long productId;

    /**
     * 退货商品名称
     */
    @NotNull(message = "退货商品名称不能为空")
    @ApiModelProperty(value = "退货商品名称", required = true)
    private String productName;

    /**
     * 退货数量
     */
    @NotNull(message = "退货数量不能为空")
    @Min(value = 1, message = "退货数量不能少于 1")
    @ApiModelProperty(value = "退货数量", required = true)
    private Integer quantity;

    /**
     * 退货金额
     */
    @NotNull(message = "退货金额不能为空")
    @Min(value = 1, message = "退货金额不能少于 1")
    @ApiModelProperty(value = "退货金额", required = true)
    private Integer amount;

    /**
     * 退货原因
     */
    @NotNull(message = "退货原因不能为空")
    @ApiModelProperty(value = "退货原因", required = true)
    private String reason;

    /**
     * 退货说明
     */
    @ApiModelProperty(value = "退货说明")
    private String description;
}
