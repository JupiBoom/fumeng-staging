package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.ReturnRequestDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 退货申请 Repository 接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Repository
public interface ReturnRequestRepository extends JpaRepository<ReturnRequestDO, Integer>, JpaSpecificationExecutor<ReturnRequestDO> {

    /**
     * 根据用户ID查询退货申请列表
     *
     * @param userId 用户ID
     * @return 退货申请列表
     */
    List<ReturnRequestDO> findByUserId(Integer userId);

    /**
     * 根据订单ID查询退货申请
     *
     * @param orderId 订单ID
     * @return 退货申请列表
     */
    List<ReturnRequestDO> findByOrderId(Integer orderId);

    /**
     * 根据商品ID查询退货申请
     *
     * @param itemId 商品ID
     * @return 退货申请列表
     */
    List<ReturnRequestDO> findByItemId(Integer itemId);

    /**
     * 根据状态查询退货申请
     *
     * @param status 状态
     * @return 退货申请列表
     */
    List<ReturnRequestDO> findByStatus(Integer status);

    /**
     * 统计不同退货原因的数量
     *
     * @return 退货原因统计结果
     */
    @Query(value = "SELECT reason, COUNT(*) as count FROM return_request WHERE reason IS NOT NULL GROUP BY reason", nativeQuery = true)
    List<Object[]> countByReason();

    /**
     * 统计不同状态的退货申请数量
     *
     * @return 状态统计结果
     */
    @Query(value = "SELECT status, COUNT(*) as count FROM return_request GROUP BY status", nativeQuery = true)
    List<Object[]> countByStatus();

}