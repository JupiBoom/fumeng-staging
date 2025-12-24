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
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 订单实体
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "order")
@Builder
@Accessors(chain = true)
@DynamicInsert()
@DynamicUpdate()
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "订单实体", description = "订单实体")
public class OrderDO extends BaseDO {

    private static final long serialVersionUID = 8853465350295250712L;

    /**
     * 订单 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "订单 ID")
    private Long id;

    /**
     * 商品 ID
     */
    @ApiModelProperty(value = "商品 ID")
    private Long productId;

    /**
     * 用户 ID
     */
    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    /**
     * 订单状态: 0-待支付, 1-已支付, 2-已完成, 3-已取消
     */
    @ApiModelProperty(value = "订单状态: 0-待支付, 1-已支付, 2-已完成, 3-已取消")
    private Integer status;

    /**
     * 订单完成时间
     */
    @ApiModelProperty(value = "订单完成时间")
    private Date finishTime;

}