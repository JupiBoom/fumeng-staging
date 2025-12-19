package cn.xilikeli.staging.dto.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * <p>
 * 添加收藏 DTO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "添加收藏 DTO", description = "添加收藏 DTO")
public class AddFavoriteDTO {

    /**
     * 商品 ID
     */
    @Positive(message = "{item.id.positive}")
    @ApiModelProperty(value = "商品 ID", required = true)
    private Long itemId;

    /**
     * 商品类型
     */
    @NotBlank(message = "{item.type.not-blank}")
    @Length(max = 50, message = "{item.type.length}")
    @ApiModelProperty(value = "商品类型", example = "book", required = true)
    private String itemType;

    /**
     * 商品名称
     */
    @NotBlank(message = "{item.name.not-blank}")
    @Length(max = 100, message = "{item.name.length}")
    @ApiModelProperty(value = "商品名称", required = true)
    private String itemName;

    /**
     * 商品价格
     */
    @Positive(message = "{item.price.positive}")
    @ApiModelProperty(value = "商品价格", required = true)
    private Long itemPrice;

    /**
     * 商品图片 URL
     */
    @Length(max = 255, message = "{item.image.length}")
    @ApiModelProperty(value = "商品图片 URL")
    private String itemImage;

}