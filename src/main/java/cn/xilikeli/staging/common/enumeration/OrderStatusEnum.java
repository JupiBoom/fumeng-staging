package cn.xilikeli.staging.common.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 订单状态枚举类
 * </p>
 *
 * @author xilikeli
 * @version 1.0
 * @date 2025-12-24
 * @since JDK1.8
 */
public enum OrderStatusEnum {

    /**
     * 未定义
     */
    UNDEFINED(-1, "未定义"),

    /**
     * 待支付
     */
    PENDING_PAYMENT(0, "待支付"),

    /**
     * 已支付
     */
    PAID(1, "已支付"),

    /**
     * 已发货
     */
    SHIPPED(2, "已发货"),

    /**
     * 已完成
     */
    COMPLETED(3, "已完成"),

    /**
     * 已取消
     */
    CANCELED(4, "已取消");

    private final Integer index;

    private final String description;

    OrderStatusEnum(Integer index, String description) {
        this.index = index;
        this.description = description;
    }

    public Integer getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据索引获取枚举
     *
     * @param index 索引
     * @return 枚举
     */
    public static OrderStatusEnum getByIndex(Integer index) {
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (statusEnum.getIndex().equals(index)) {
                return statusEnum;
            }
        }
        return UNDEFINED;
    }

    /**
     * 获取所有有效的枚举（排除未定义）
     *
     * @return 有效枚举列表
     */
    public static List<OrderStatusEnum> getValidEnums() {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(statusEnum -> !statusEnum.equals(UNDEFINED))
                .collect(Collectors.toList());
    }
}