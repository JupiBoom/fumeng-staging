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
 * 退货申请实体
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "return_application")
@Builder
@Accessors(chain = true)
@DynamicInsert()
@DynamicUpdate()
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "退货申请实体", description = "退货申请实体")
public class ReturnApplicationDO extends BaseDO {

    private static final long serialVersionUID = 8853465350295250712L;

    /**
     * 退货申请 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "退货申请 ID")
    private Long id;

    /**
     * 订单 ID
     */
    @ApiModelProperty(value = "订单 ID")
    private Long orderId;

    /**
     * 用户 ID
     */
    @ApiModelProperty(value = "用户 ID")
    private Long accountId;

    /**
     * 商品 ID
     */
    @ApiModelProperty(value = "商品 ID")
    private Long productId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    private String productImage;

    /**
     * 退货数量
     */
    @ApiModelProperty(value = "退货数量")
    private Integer quantity;

    /**
     * 退货原因
     */
    @ApiModelProperty(value = "退货原因")
    private String reason;

    /**
     * 退货描述
     */
    @ApiModelProperty(value = "退货描述")
    private String description;

    /**
     * 申请状态: 0-待审核, 1-审核通过, 2-审核拒绝, 3-已取消, 4-已完成
     */
    @ApiModelProperty(value = "申请状态: 0-待审核, 1-审核通过, 2-审核拒绝, 3-已取消, 4-已完成")
    private Integer status;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    private Date auditTime;

    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人")
    private String auditUser;

    /**
     * 审核备注
     */
    @ApiModelProperty(value = "审核备注")
    private String auditRemark;

}