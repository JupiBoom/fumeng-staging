package cn.xilikeli.staging.vo.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <p>
 * 用户收藏偏好 VO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户收藏偏好 VO", description = "用户收藏偏好 VO")
public class FavoritePreferenceVO {

    /**
     * 用户 ID
     */
    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    /**
     * 收藏商品类型分布
     */
    @ApiModelProperty(value = "收藏商品类型分布")
    private Map<String, Long> typeDistribution;

    /**
     * 收藏商品价格区间分布
     */
    @ApiModelProperty(value = "收藏商品价格区间分布")
    private Map<String, Long> priceRangeDistribution;

    /**
     * 总收藏数量
     */
    @ApiModelProperty(value = "总收藏数量")
    private Long totalFavorites;

    /**
     * 平均收藏价格
     */
    @ApiModelProperty(value = "平均收藏价格")
    private Long averagePrice;

}