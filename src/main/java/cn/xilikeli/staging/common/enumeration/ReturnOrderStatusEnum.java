package cn.xilikeli.staging.common.enumeration;

import lombok.Getter;

/**
 * <p>
 * 退货订单状态枚举
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Getter
public enum ReturnOrderStatusEnum {

    /**
     * 用户已提交退货申请，待商家审核
     */
    PENDING_AUDIT(1, "待审核"),

    /**
     * 商家已通过退货申请，待用户发货
     */
    APPROVED(2, "审核通过，待发货"),

    /**
     * 用户已发货，待商家收货
     */
    SHIPPED(3, "已发货，待收货"),

    /**
     * 商家已收货，待退款
     */
    RECEIVED(4, "已收货，待退款"),

    /**
     * 退款已完成
     */
    REFUNDED(5, "退款完成"),

    /**
     * 退货申请已取消
     */
    CANCELED(6, "已取消"),

    /**
     * 商家已拒绝退货申请
     */
    REJECTED(7, "已拒绝");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String description;

    ReturnOrderStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据状态码获取枚举
     *
     * @param code 状态码
     * @return 对应的枚举
     */
    public static ReturnOrderStatusEnum getByCode(Integer code) {
        for (ReturnOrderStatusEnum statusEnum : values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
}
