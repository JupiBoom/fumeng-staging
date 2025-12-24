package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.ReturnApplicationDO;

import java.util.List;

/**
 * <p>
 * 退货申请 Repository 接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
public interface ReturnApplicationRepository extends BaseRepository<ReturnApplicationDO, Long> {

    /**
     * 根据用户 ID 获取退货申请列表
     *
     * @param accountId 用户 ID
     * @return 退货申请列表
     */
    List<ReturnApplicationDO> findByAccountId(Long accountId);

    /**
     * 根据状态获取退货申请列表
     *
     * @param status 申请状态
     * @return 退货申请列表
     */
    List<ReturnApplicationDO> findByStatus(Integer status);

    /**
     * 根据退货原因统计数量
     *
     * @return 退货原因统计结果
     */
    @org.springframework.data.jpa.repository.Query("SELECT reason, COUNT(id) FROM ReturnApplicationDO GROUP BY reason")
    List<Object[]> countByReasonGroupByReason();

}