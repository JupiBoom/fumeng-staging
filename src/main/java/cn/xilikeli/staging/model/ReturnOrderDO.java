package cn.xilikeli.staging.model;

import cn.xilikeli.staging.common.enumeration.ReturnOrderStatusEnum;
import cn.xilikeli.staging.common.enumeration.ReturnReasonEnum;
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
 * 退货订单实体
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "return_order")
@Builder
@Accessors(chain = true)
@DynamicInsert()
@DynamicUpdate()
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "退货订单实体", description = "退货订单实体")
public class ReturnOrderDO extends BaseDO {

    private static final long serialVersionUID = -287723241117592869L;

    /**
     * 退货订单 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "退货订单 ID")
    private Long id;

    /**
     * 用户 ID
     */
    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    /**
     * 原订单 ID
     */
    @ApiModelProperty(value = "原订单 ID")
    private Long orderId;

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
     * 退货数量
     */
    @ApiModelProperty(value = "退货数量")
    private Integer quantity;

    /**
     * 退货原因
     * @see ReturnReasonEnum
     */
    @ApiModelProperty(value = "退货原因")
    private Integer reason;

    /**
     * 退货说明
     */
    @ApiModelProperty(value = "退货说明")
    private String description;

    /**
     * 退货图片
     */
    @ApiModelProperty(value = "退货图片")
    private String images;

    /**
     * 退货状态
     * @see ReturnOrderStatusEnum
     */
    @ApiModelProperty(value = "退货状态")
    private Integer status;

    /**
     * 商家审核备注
     */
    @ApiModelProperty(value = "商家审核备注")
    private String auditRemark;

    /**
     * 物流单号
     */
    @ApiModelProperty(value = "物流单号")
    private String logisticsNo;

    /**
     * 物流公司
     */
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    private Integer refundAmount;

    /**
     * 退款流水号
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundNo;

    /**
     * 退款备注
     */
    @ApiModelProperty(value = "退款备注")
    private String refundRemark;

}
