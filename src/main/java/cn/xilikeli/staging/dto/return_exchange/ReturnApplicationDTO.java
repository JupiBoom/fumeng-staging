package cn.xilikeli.staging.dto.return_exchange;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * <p>
 * 退货申请信息 DTO
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
@ApiModel(value = "退货申请信息 DTO", description = "退货申请信息 DTO")
public class ReturnApplicationDTO {

    /**
     * 订单 ID
     */
    @NotNull(message = "订单 ID 不能为空")
    @ApiModelProperty(value = "订单 ID", required = true, example = "1")
    private Long orderId;

    /**
     * 商品 ID
     */
    @NotNull(message = "商品 ID 不能为空")
    @ApiModelProperty(value = "商品 ID", required = true, example = "1")
    private Long productId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称 不能为空")
    @ApiModelProperty(value = "商品名称", required = true, example = "测试商品")
    private String productName;

    /**
     * 商品图片
     */
    @NotBlank(message = "商品图片 不能为空")
    @ApiModelProperty(value = "商品图片", required = true, example = "https://example.com/image.jpg")
    private String productImage;

    /**
     * 退货数量
     */
    @NotNull(message = "退货数量 不能为空")
    @Positive(message = "退货数量 必须大于 0")
    @ApiModelProperty(value = "退货数量", required = true, example = "1")
    private Integer quantity;

    /**
     * 退货原因
     */
    @NotBlank(message = "退货原因 不能为空")
    @ApiModelProperty(value = "退货原因", required = true, example = "商品质量问题")
    private String reason;

    /**
     * 退货描述
     */
    @ApiModelProperty(value = "退货描述", example = "商品存在明显瑕疵")
    private String description;

}