package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.ReturnOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 退货订单 Repository 接口
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Repository
public interface ReturnOrderRepository extends JpaRepository<ReturnOrderDO, Long>, JpaSpecificationExecutor<ReturnOrderDO> {

    /**
     * 根据用户 ID 查询退货订单列表
     *
     * @param userId 用户 ID
     * @return 退货订单列表
     */
    List<ReturnOrderDO> findByUserId(Integer userId);

    /**
     * 根据订单 ID 查询退货订单
     *
     * @param orderId 订单 ID
     * @return 退货订单
     */
    ReturnOrderDO findByOrderId(Long orderId);
}
