package cn.xilikeli.staging.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 退货订单模型
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "return_order")
@ApiModel(value = "退货订单模型", description = "退货订单模型")
public class ReturnOrderDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 退货订单 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "退货订单 ID")
    private Long id;

    /**
     * 用户 ID
     */
    @Column(name = "user_id", nullable = false)
    @ApiModelProperty(value = "用户 ID")
    private Integer userId;

    /**
     * 订单 ID
     */
    @Column(name = "order_id", nullable = false)
    @ApiModelProperty(value = "订单 ID")
    private Long orderId;

    /**
     * 订单编号
     */
    @Column(name = "order_no", nullable = false)
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 退货商品 ID
     */
    @Column(name = "product_id", nullable = false)
    @ApiModelProperty(value = "退货商品 ID")
    private Long productId;

    /**
     * 退货商品名称
     */
    @Column(name = "product_name", nullable = false)
    @ApiModelProperty(value = "退货商品名称")
    private String productName;

    /**
     * 退货数量
     */
    @Column(name = "quantity", nullable = false)
    @ApiModelProperty(value = "退货数量")
    private Integer quantity;

    /**
     * 退货金额
     */
    @Column(name = "amount", nullable = false)
    @ApiModelProperty(value = "退货金额")
    private Integer amount;

    /**
     * 退货原因
     */
    @Column(name = "reason", nullable = false)
    @ApiModelProperty(value = "退货原因")
    private String reason;

    /**
     * 退货说明
     */
    @Column(name = "description")
    @ApiModelProperty(value = "退货说明")
    private String description;

    /**
     * 退货状态: 0-待审核, 1-审核通过, 2-审核拒绝, 3-已发货, 4-已收货, 5-已退款, 6-已取消
     */
    @Column(name = "status", nullable = false)
    @ApiModelProperty(value = "退货状态")
    private Integer status;

    /**
     * 商家审核备注
     */
    @Column(name = "audit_remark")
    @ApiModelProperty(value = "商家审核备注")
    private String auditRemark;

    /**
     * 物流单号
     */
    @Column(name = "logistics_no")
    @ApiModelProperty(value = "物流单号")
    private String logisticsNo;

    /**
     * 物流公司
     */
    @Column(name = "logistics_company")
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;

    /**
     * 物流状态
     */
    @Column(name = "logistics_status")
    @ApiModelProperty(value = "物流状态")
    private String logisticsStatus;

    /**
     * 退款流水号
     */
    @Column(name = "refund_no")
    @ApiModelProperty(value = "退款流水号")
    private String refundNo;

    /**
     * 退款状态
     */
    @Column(name = "refund_status")
    @ApiModelProperty(value = "退款状态")
    private Integer refundStatus;

    @Override
    public Long getId() {
        return this.id;
    }
}
