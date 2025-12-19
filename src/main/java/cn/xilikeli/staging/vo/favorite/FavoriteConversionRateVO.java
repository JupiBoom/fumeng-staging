package cn.xilikeli.staging.vo.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 收藏转化率统计 VO
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
@ApiModel(value = "收藏转化率统计 VO", description = "收藏转化率统计 VO")
public class FavoriteConversionRateVO {

    /**
     * 商品 ID
     */
    @ApiModelProperty(value = "商品 ID")
    private Long itemId;

    /**
     * 收藏次数
     */
    @ApiModelProperty(value = "收藏次数")
    private Long favoriteCount;

    /**
     * 购买次数
     */
    @ApiModelProperty(value = "购买次数")
    private Long purchaseCount;

    /**
     * 转化率
     */
    @ApiModelProperty(value = "转化率")
    private Double conversionRate;

}