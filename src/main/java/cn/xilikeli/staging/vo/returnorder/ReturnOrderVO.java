package cn.xilikeli.staging.vo.returnorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 退货订单 VO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "退货订单 VO", description = "退货订单的响应对象")
public class ReturnOrderVO {

    /**
     * 退货订单 ID
     */
    @ApiModelProperty(value = "退货订单 ID")
    private Long id;

    /**
     * 订单 ID
     */
    @ApiModelProperty(value = "订单 ID")
    private Long orderId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 退货商品 ID
     */
    @ApiModelProperty(value = "退货商品 ID")
    private Long productId;

    /**
     * 退货商品名称
     */
    @ApiModelProperty(value = "退货商品名称")
    private String productName;

    /**
     * 退货数量
     */
    @ApiModelProperty(value = "退货数量")
    private Integer quantity;

    /**
     * 退货金额
     */
    @ApiModelProperty(value = "退货金额")
    private Integer amount;

    /**
     * 退货原因
     */
    @ApiModelProperty(value = "退货原因")
    private String reason;

    /**
     * 退货说明
     */
    @ApiModelProperty(value = "退货说明")
    private String description;

    /**
     * 退货状态: 0-待审核, 1-审核通过, 2-审核拒绝, 3-已发货, 4-已收货, 5-已退款, 6-已取消
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
     * 物流状态
     */
    @ApiModelProperty(value = "物流状态")
    private String logisticsStatus;

    /**
     * 退款流水号
     */
    @ApiModelProperty(value = "退款流水号")
    private String refundNo;

    /**
     * 退款状态
     */
    @ApiModelProperty(value = "退款状态")
    private Integer refundStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
