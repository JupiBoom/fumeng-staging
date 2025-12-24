package cn.xilikeli.staging.common.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 评价排序类型枚举类
 * </p>
 *
 * @author xilikeli
 * @version 1.0
 * @date 2025-12-24
 * @since JDK1.8
 */
public enum ReviewSortTypeEnum {

    /**
     * 未定义
     */
    UNDEFINED(-1, "未定义"),

    /**
     * 按时间排序
     */
    BY_TIME(0, "按时间排序"),

    /**
     * 按评分排序
     */
    BY_RATING(1, "按评分排序");

    private final Integer index;

    private final String description;

    ReviewSortTypeEnum(Integer index, String description) {
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
    public static ReviewSortTypeEnum getByIndex(Integer index) {
        for (ReviewSortTypeEnum sortTypeEnum : ReviewSortTypeEnum.values()) {
            if (sortTypeEnum.getIndex().equals(index)) {
                return sortTypeEnum;
            }
        }
        return UNDEFINED;
    }

    /**
     * 获取所有有效的枚举（排除未定义）
     *
     * @return 有效枚举列表
     */
    public static List<ReviewSortTypeEnum> getValidEnums() {
        return Arrays.stream(ReviewSortTypeEnum.values())
                .filter(sortTypeEnum -> !sortTypeEnum.equals(UNDEFINED))
                .collect(Collectors.toList());
    }
}