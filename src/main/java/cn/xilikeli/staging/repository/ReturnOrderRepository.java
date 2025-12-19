package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.ReturnOrderDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * <p>
 * 退货订单 Repository 接口
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
public interface ReturnOrderRepository extends BaseRepository<ReturnOrderDO, Long>, JpaSpecificationExecutor<ReturnOrderDO> {

    /**
     * 根据用户 ID 查询退货订单列表
     *
     * @param userId 用户 ID
     * @return 退货订单列表
     */
    List<ReturnOrderDO> findByUserId(Long userId);

    /**
     * 根据订单 ID 查询退货订单
     *
     * @param orderId 订单 ID
     * @return 退货订单
     */
    ReturnOrderDO findByOrderId(Long orderId);

}
