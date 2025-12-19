package cn.xilikeli.staging.common.enumeration;

import lombok.Getter;

/**
 * <p>
 * 退货原因枚举
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Getter
public enum ReturnReasonEnum {

    /**
     * 商品质量问题
     */
    QUALITY_ISSUE(1, "商品质量问题"),

    /**
     * 商品描述不符
     */
    DESCRIPTION_MISMATCH(2, "商品描述不符"),

    /**
     * 商品破损
     */
    DAMAGED(3, "商品破损"),

    /**
     * 商品过期
     */
    EXPIRED(4, "商品过期"),

    /**
     * 拍错/多拍
     */
    WRONG_PURCHASE(5, "拍错/多拍"),

    /**
     * 7天无理由退货
     */
    NO_REASON_RETURN(6, "7天无理由退货"),

    /**
     * 其他原因
     */
    OTHER(7, "其他原因");

    /**
     * 原因码
     */
    private final Integer code;

    /**
     * 原因描述
     */
    private final String description;

    ReturnReasonEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据原因码获取枚举
     *
     * @param code 原因码
     * @return 对应的枚举
     */
    public static ReturnReasonEnum getByCode(Integer code) {
        for (ReturnReasonEnum reasonEnum : values()) {
            if (reasonEnum.code.equals(code)) {
                return reasonEnum;
            }
        }
        return null;
    }
}
