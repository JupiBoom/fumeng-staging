package cn.xilikeli.staging.dto.returnrequest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * <p>
 * 创建退货申请 DTO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class CreateReturnRequestDTO {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空")
    private Integer orderId;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空")
    private Integer itemId;

    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不能为空")
    @Size(max = 255, message = "商品名称不能超过255个字符")
    private String itemName;

    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空")
    @Positive(message = "商品数量必须大于0")
    private Integer itemQuantity;

    /**
     * 商品单价
     */
    @NotNull(message = "商品单价不能为空")
    private BigDecimal itemPrice;

    /**
     * 退货金额
     */
    @NotNull(message = "退货金额不能为空")
    private BigDecimal returnAmount;

    /**
     * 退货原因
     */
    @NotNull(message = "退货原因不能为空")
    @Size(max = 255, message = "退货原因不能超过255个字符")
    private String reason;

    /**
     * 退货说明
     */
    @Size(max = 500, message = "退货说明不能超过500个字符")
    private String remark;

    /**
     * 图片凭证
     */
    private String evidenceImages;

    /**
     * 收货地址
     */
    @NotNull(message = "收货地址不能为空")
    @Size(max = 255, message = "收货地址不能超过255个字符")
    private String returnAddress;

    /**
     * 收货人
     */
    @NotNull(message = "收货人不能为空")
    @Size(max = 50, message = "收货人不能超过50个字符")
    private String receiver;

    /**
     * 联系电话
     */
    @NotNull(message = "联系电话不能为空")
    @Size(max = 20, message = "联系电话不能超过20个字符")
    private String phone;

}