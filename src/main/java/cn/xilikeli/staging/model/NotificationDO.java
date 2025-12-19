package cn.xilikeli.staging.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * <p>
 * 用户通知表
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "notification")
@Builder
@Accessors(chain = true)
@DynamicInsert()
@DynamicUpdate()
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "用户通知实体", description = "用户通知实体")
public class NotificationDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 通知 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "通知 ID")
    private Integer id;

    /**
     * 用户 ID
     */
    @ApiModelProperty(value = "用户 ID")
    private Integer accountId;

    /**
     * 商品 ID
     */
    @ApiModelProperty(value = "商品 ID")
    private Long productId;

    /**
     * 商品类型
     */
    @ApiModelProperty(value = "商品类型")
    private String productType;

    /**
     * 通知类型: PRICE_DOWN(降价通知), STOCK_RECOVER(库存恢复通知), PROMOTION(促销活动提醒)
     */
    @ApiModelProperty(value = "通知类型")
    private String type;

    /**
     * 通知内容
     */
    @ApiModelProperty(value = "通知内容")
    private String content;

    /**
     * 是否已读: 0-未读, 1-已读
     */
    @ApiModelProperty(value = "是否已读")
    private Integer isRead;

}