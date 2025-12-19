package cn.xilikeli.staging.common.constant.business;

/**
 * <p>
 * 用户收藏业务消息码常量
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 * @since JDK1.8
 */
public interface FavoriteCodeConstant {

    /**
     * 添加收藏成功消息码
     */
    Integer ADD_FAVORITE_SUCCESS = 10030;

    /**
     * 取消收藏成功消息码
     */
    Integer REMOVE_FAVORITE_SUCCESS = 10031;

    /**
     * 批量取消收藏成功消息码
     */
    Integer BATCH_REMOVE_FAVORITE_SUCCESS = 10032;

    /**
     * 已收藏该商品消息码
     */
    Integer ALREADY_FAVORITE = 20040;

    /**
     * 未找到收藏记录消息码
     */
    Integer NOT_FOUND_FAVORITE = 20041;

}
