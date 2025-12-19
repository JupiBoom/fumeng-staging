package cn.xilikeli.staging.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 用户收藏商品实体
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "favorite")
@Builder
@Accessors(chain = true)
@DynamicInsert()
@DynamicUpdate()
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "用户收藏商品实体", description = "用户收藏商品实体")
public class FavoriteDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏记录 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
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
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    private String itemImage;

}