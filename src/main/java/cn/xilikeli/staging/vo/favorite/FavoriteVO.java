package cn.xilikeli.staging.vo.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 收藏信息 VO
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
@ApiModel(value = "收藏信息 VO", description = "收藏信息 VO")
public class FavoriteVO {

    /**
     * 收藏记录 ID
     */
    @ApiModelProperty(value = "收藏记录 ID")
    private Long id;

    /**
     * 用户 ID
     */
    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    /**
     * 商品 ID
     */
    @ApiModelProperty(value = "商品 ID")
    private Long itemId;

    /**
     * 商品类型
     */
    @ApiModelProperty(value = "商品类型")
    private String itemType;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String itemName;

    /**
     * 商品价格
     */
    @ApiModelProperty(value = "商品价格")
    private Long itemPrice;

    /**
     * 商品图片 URL
     */
    @ApiModelProperty(value = "商品图片 URL")
    private String itemImage;

    /**
     * 收藏时间
     */
    @ApiModelProperty(value = "收藏时间")
    private Date createTime;

}