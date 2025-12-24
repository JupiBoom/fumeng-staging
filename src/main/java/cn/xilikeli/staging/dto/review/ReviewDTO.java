package cn.xilikeli.staging.dto.review;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 商品评价DTO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@ApiModel(value = "商品评价DTO", description = "商品评价DTO")
public class ReviewDTO {

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空")
    @ApiModelProperty(value = "商品ID", required = true)
    private Long productId;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    /**
     * 评分(1-5星)
     */
    @NotNull(message = "评分不能为空")
    @Min(value = 1, message = "评分不能小于1星")
    @Max(value = 5, message = "评分不能大于5星")
    @ApiModelProperty(value = "评分(1-5星)", required = true)
    private Integer score;

    /**
     * 评价内容
     */
    @NotBlank(message = "评价内容不能为空")
    @ApiModelProperty(value = "评价内容", required = true)
    private String content;

}