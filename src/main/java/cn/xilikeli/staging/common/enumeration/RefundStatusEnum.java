package cn.xilikeli.staging.common.enumeration;

import lombok.Getter;

/**
 * <p>
 * 退款状态枚举
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Getter
public enum RefundStatusEnum {

    /**
     * 退款待处理
     */
    PENDING(0, "退款待处理"),

    /**
     * 退款成功
     */
    SUCCESS(1, "退款成功"),

    /**
     * 退款失败
     */
    FAIL(2, "退款失败");

    private final Integer value;
    private final String description;

    RefundStatusEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * 根据值获取枚举
     *
     * @param value 枚举值
     * @return 退款状态枚举
     */
    public static RefundStatusEnum getEnumByValue(Integer value) {
        for (RefundStatusEnum enumItem : RefundStatusEnum.values()) {
            if (enumItem.getValue().equals(value)) {
                return enumItem;
            }
        }
        return null;
    }
}
