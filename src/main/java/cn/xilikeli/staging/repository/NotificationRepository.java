package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.NotificationDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户通知 Repository 接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 * @since JDK1.8
 */
@Repository
public interface NotificationRepository extends BaseRepository<NotificationDO, Integer> {

    /**
     * 根据用户 ID 和是否已读获取通知列表
     *
     * @param accountId 用户 ID
     * @param isRead    是否已读: 0-未读, 1-已读
     * @param pageable  分页参数
     * @return 返回获取的通知列表
     */
    Page<NotificationDO> findAllByAccountIdAndIsRead(Integer accountId, Integer isRead, Pageable pageable);

    /**
     * 根据用户 ID 获取通知列表
     *
     * @param accountId 用户 ID
     * @param pageable  分页参数
     * @return 返回获取的通知列表
     */
    Page<NotificationDO> findAllByAccountId(Integer accountId, Pageable pageable);

    /**
     * 根据用户 ID 和是否已读统计通知数量
     *
     * @param accountId 用户 ID
     * @param isRead    是否已读: 0-未读, 1-已读
     * @return 返回统计的通知数量
     */
    Integer countByAccountIdAndIsRead(Integer accountId, Integer isRead);

    /**
     * 批量标记通知为已读
     *
     * @param accountId       用户 ID
     * @param notificationIds 通知 ID 列表
     */
    void updateIsReadByIdIn(Integer accountId, List<Integer> notificationIds, Integer isRead);

}