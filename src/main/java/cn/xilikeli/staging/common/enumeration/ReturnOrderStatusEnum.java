package cn.xilikeli.staging.common.enumeration;

import lombok.Getter;

/**
 * <p>
 * 退货订单状态枚举
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Getter
public enum ReturnOrderStatusEnum {

    /**
     * 待审核
     */
    PENDING_AUDIT(0, "待审核"),

    /**
     * 审核通过
     */
    AUDIT_PASSED(1, "审核通过"),

    /**
     * 审核拒绝
     */
    AUDIT_REJECTED(2, "审核拒绝"),

    /**
     * 已发货
     */
    SHIPPED(3, "已发货"),

    /**
     * 已收货
     */
    RECEIVED(4, "已收货"),

    /**
     * 已退款
     */
    REFUNDED(5, "已退款"),

    /**
     * 已取消
     */
    CANCELLED(6, "已取消");

    private final Integer value;
    private final String description;

    ReturnOrderStatusEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * 根据值获取枚举
     *
     * @param value 枚举值
     * @return 退货订单状态枚举
     */
    public static ReturnOrderStatusEnum getEnumByValue(Integer value) {
        for (ReturnOrderStatusEnum enumItem : ReturnOrderStatusEnum.values()) {
            if (enumItem.getValue().equals(value)) {
                return enumItem;
            }
        }
        return null;
    }
}
