package cn.xilikeli.staging.common.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 评价状态枚举类
 * </p>
 *
 * @author xilikeli
 * @version 1.0
 * @date 2025-12-24
 * @since JDK1.8
 */
public enum ReviewStatusEnum {

    /**
     * 未定义
     */
    UNDEFINED(-1, "未定义"),

    /**
     * 待审核
     */
    PENDING_REVIEW(0, "待审核"),

    /**
     * 已通过
     */
    APPROVED(1, "已通过"),

    /**
     * 已拒绝
     */
    REJECTED(2, "已拒绝");

    private final Integer index;

    private final String description;

    ReviewStatusEnum(Integer index, String description) {
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
    public static ReviewStatusEnum getByIndex(Integer index) {
        for (ReviewStatusEnum statusEnum : ReviewStatusEnum.values()) {
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
    public static List<ReviewStatusEnum> getValidEnums() {
        return Arrays.stream(ReviewStatusEnum.values())
                .filter(statusEnum -> !statusEnum.equals(UNDEFINED))
                .collect(Collectors.toList());
    }
}