package cn.xilikeli.staging.vo.returnrequest;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 退货申请 VO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class ReturnRequestVO {

    /**
     * 退货申请ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 商品ID
     */
    private Integer itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品数量
     */
    private Integer itemQuantity;

    /**
     * 商品单价
     */
    private BigDecimal itemPrice;

    /**
     * 退货金额
     */
    private BigDecimal returnAmount;

    /**
     * 退货原因
     */
    private String reason;

    /**
     * 退货说明
     */
    private String remark;

    /**
     * 申请状态: 0-待审核, 1-审核通过, 2-审核拒绝, 3-已取消, 4-已退货, 5-已完成
     */
    private Integer status;

    /**
     * 审核人ID
     */
    private Integer auditorId;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核意见
     */
    private String auditOpinion;

    /**
     * 物流单号
     */
    private String trackingNumber;

    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 物流状态: 0-待发货, 1-已发货, 2-运输中, 3-已签收, 4-已拒收
     */
    private Integer logisticsStatus;

    /**
     * 收货确认时间
     */
    private Date confirmTime;

    /**
     * 退款状态: 0-待退款, 1-退款中, 2-退款成功, 3-退款失败
     */
    private Integer refundStatus;

    /**
     * 退款时间
     */
    private Date refundTime;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款方式: 0-原路返回, 1-余额
     */
    private Integer refundMethod;

    /**
     * 退款备注
     */
    private String refundRemark;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 图片凭证
     */
    private String evidenceImages;

    /**
     * 收货地址
     */
    private String returnAddress;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}