package cn.xilikeli.staging.service;

import cn.xilikeli.staging.model.NotificationDO;
import cn.xilikeli.staging.vo.response.PagingDozerVO;

import java.util.List;

/**
 * <p>
 * 用户通知服务类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 * @since JDK1.8
 */
public interface NotificationService {

    /**
     * 发送降价通知
     *
     * @param bookId    图书 ID
     * @param oldPrice  旧价格
     * @param newPrice  新价格
     */
    void sendPriceDownNotification(Long bookId, Integer oldPrice, Integer newPrice);

    /**
     * 发送库存恢复通知
     *
     * @param bookId 图书 ID
     */
    void sendStockRecoverNotification(Long bookId);

    /**
     * 发送促销活动通知
     *
     * @param bookId   图书 ID
     * @param promotion 促销信息
     */
    void sendPromotionNotification(Long bookId, String promotion);

    /**
     * 获取用户通知列表
     *
     * @param accountId 用户 ID
     * @param isRead    是否已读: 0-未读, 1-已读, null-全部
     * @param page      当前页数
     * @param count     每页通知数
     * @return 返回获取的通知列表
     */
    PagingDozerVO getNotificationList(Integer accountId, Integer isRead, Integer page, Integer count);

    /**
     * 标记单个通知为已读
     *
     * @param accountId     用户 ID
     * @param notificationId 通知 ID
     */
    void markNotificationAsRead(Integer accountId, Integer notificationId);

    /**
     * 批量标记通知为已读
     *
     * @param accountId       用户 ID
     * @param notificationIds 通知 ID 列表
     */
    void batchMarkNotificationAsRead(Integer accountId, List<Integer> notificationIds);

    /**
     * 删除单个通知
     *
     * @param accountId     用户 ID
     * @param notificationId 通知 ID
     */
    void deleteNotification(Integer accountId, Integer notificationId);

    /**
     * 批量删除通知
     *
     * @param accountId       用户 ID
     * @param notificationIds 通知 ID 列表
     */
    void batchDeleteNotification(Integer accountId, List<Integer> notificationIds);

    /**
     * 获取未读通知数量
     *
     * @param accountId 用户 ID
     * @return 返回未读通知数量
     */
    Integer getUnreadNotificationCount(Integer accountId);

}