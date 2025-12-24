package cn.xilikeli.staging.common.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 商品状态枚举类
 * </p>
 *
 * @author xilikeli
 * @version 1.0
 * @date 2025-12-24
 * @since JDK1.8
 */
public enum ProductStatusEnum {

    /**
     * 未定义
     */
    UNDEFINED(-1, "未定义"),

    /**
     * 下架
     */
    OFF_SHELF(0, "下架"),

    /**
     * 上架
     */
    ON_SHELF(1, "上架");

    private final Integer index;

    private final String description;

    ProductStatusEnum(Integer index, String description) {
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
    public static ProductStatusEnum getByIndex(Integer index) {
        for (ProductStatusEnum statusEnum : ProductStatusEnum.values()) {
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
    public static List<ProductStatusEnum> getValidEnums() {
        return Arrays.stream(ProductStatusEnum.values())
                .filter(statusEnum -> !statusEnum.equals(UNDEFINED))
                .collect(Collectors.toList());
    }
}