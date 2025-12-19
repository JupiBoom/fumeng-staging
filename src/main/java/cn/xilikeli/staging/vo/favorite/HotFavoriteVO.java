package cn.xilikeli.staging.vo.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 热门收藏商品 VO
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
@ApiModel(value = "热门收藏商品 VO", description = "热门收藏商品 VO")
public class HotFavoriteVO {

    /**
     * 商品 ID
     */
    @ApiModelProperty(value = "商品 ID")
    private Long itemId;

    /**
     * 收藏次数
     */
    @ApiModelProperty(value = "收藏次数")
    private Long count;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String itemName;

    /**
     * 商品图片 URL
     */
    @ApiModelProperty(value = "商品图片 URL")
    private String itemImage;

}