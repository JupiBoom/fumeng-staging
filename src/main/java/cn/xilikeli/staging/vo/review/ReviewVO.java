package cn.xilikeli.staging.vo.review;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 商品评价VO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@ApiModel(value = "商品评价VO", description = "商品评价VO")
public class ReviewVO {

    /**
     * 评价ID
     */
    @ApiModelProperty(value = "评价ID")
    private Long id;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long productId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 评分(1-5星)
     */
    @ApiModelProperty(value = "评分(1-5星)")
    private Integer score;

    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容")
    private String content;

    /**
     * 评价时间
     */
    @ApiModelProperty(value = "评价时间")
    private Date reviewTime;

}