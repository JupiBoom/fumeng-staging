package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.ReturnLogisticsDO;

/**
 * <p>
 * 退货物流 Repository 接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
public interface ReturnLogisticsRepository extends BaseRepository<ReturnLogisticsDO, Long> {

    /**
     * 根据退货申请 ID 获取物流信息
     *
     * @param returnApplicationId 退货申请 ID
     * @return 物流信息
     */
    ReturnLogisticsDO findByReturnApplicationId(Long returnApplicationId);

}