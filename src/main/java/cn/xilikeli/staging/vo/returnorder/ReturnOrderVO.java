package cn.xilikeli.staging.vo.returnorder;

import cn.xilikeli.staging.common.enumeration.ReturnOrderStatusEnum;
import cn.xilikeli.staging.common.enumeration.ReturnReasonEnum;
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
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@ApiModel(value = "退货订单 VO", description = "退货订单 VO")
public class ReturnOrderVO {

    /**
     * 退货订单 ID
     */
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
     * 退货原因描述
     */
    @ApiModelProperty(value = "退货原因描述")
    private String reasonDescription;

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
     * 退货状态描述
     */
    @ApiModelProperty(value = "退货状态描述")
    private String statusDescription;

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
