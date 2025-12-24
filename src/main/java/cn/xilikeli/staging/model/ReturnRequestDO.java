package cn.xilikeli.staging.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 退货申请表
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "return_request")
public class ReturnRequestDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 商品ID
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 商品名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 商品数量
     */
    @Column(name = "item_quantity")
    private Integer itemQuantity;

    /**
     * 商品单价
     */
    @Column(name = "item_price")
    private BigDecimal itemPrice;

    /**
     * 退货金额
     */
    @Column(name = "return_amount")
    private BigDecimal returnAmount;

    /**
     * 退货原因
     */
    @Column(name = "reason")
    private String reason;

    /**
     * 退货说明
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 申请状态: 0-待审核, 1-审核通过, 2-审核拒绝, 3-已取消, 4-已退货, 5-已完成
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 审核人ID
     */
    @Column(name = "auditor_id")
    private Integer auditorId;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 审核意见
     */
    @Column(name = "audit_opinion")
    private String auditOpinion;

    /**
     * 物流单号
     */
    @Column(name = "tracking_number")
    private String trackingNumber;

    /**
     * 物流公司
     */
    @Column(name = "logistics_company")
    private String logisticsCompany;

    /**
     * 物流状态: 0-待发货, 1-已发货, 2-运输中, 3-已签收, 4-已拒收
     */
    @Column(name = "logistics_status")
    private Integer logisticsStatus;

    /**
     * 收货确认时间
     */
    @Column(name = "confirm_time")
    private Date confirmTime;

    /**
     * 退款状态: 0-待退款, 1-退款中, 2-退款成功, 3-退款失败
     */
    @Column(name = "refund_status")
    private Integer refundStatus;

    /**
     * 退款时间
     */
    @Column(name = "refund_time")
    private Date refundTime;

    /**
     * 退款金额
     */
    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

    /**
     * 退款方式: 0-原路返回, 1-余额
     */
    @Column(name = "refund_method")
    private Integer refundMethod;

    /**
     * 退款备注
     */
    @Column(name = "refund_remark")
    private String refundRemark;

    /**
     * 取消原因
     */
    @Column(name = "cancel_reason")
    private String cancelReason;

    /**
     * 取消时间
     */
    @Column(name = "cancel_time")
    private Date cancelTime;

    /**
     * 图片凭证
     */
    @Column(name = "evidence_images")
    private String evidenceImages;

    /**
     * 收货地址
     */
    @Column(name = "return_address")
    private String returnAddress;

    /**
     * 收货人
     */
    @Column(name = "receiver")
    private String receiver;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    private String phone;

}