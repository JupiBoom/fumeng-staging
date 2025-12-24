package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.RefundRecordDO;

/**
 * <p>
 * 退款记录 Repository 接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
public interface RefundRecordRepository extends BaseRepository<RefundRecordDO, Long> {

    /**
     * 根据退货申请 ID 获取退款记录
     *
     * @param returnApplicationId 退货申请 ID
     * @return 退款记录
     */
    RefundRecordDO findByReturnApplicationId(Long returnApplicationId);

}