package cn.xilikeli.staging.vo.review;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 商品评价统计VO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@ApiModel(value = "商品评价统计VO", description = "商品评价统计VO")
public class ReviewStatisticsVO {

    /**
     * 总评价数
     */
    @ApiModelProperty(value = "总评价数")
    private long totalReviews;

    /**
     * 平均评分
     */
    @ApiModelProperty(value = "平均评分")
    private double averageScore;

    /**
     * 好评率(%)
     */
    @ApiModelProperty(value = "好评率(%)")
    private double goodRate;

    /**
     * 中评率(%)
     */
    @ApiModelProperty(value = "中评率(%)")
    private double mediumRate;

    /**
     * 差评率(%)
     */
    @ApiModelProperty(value = "差评率(%)")
    private double badRate;

}