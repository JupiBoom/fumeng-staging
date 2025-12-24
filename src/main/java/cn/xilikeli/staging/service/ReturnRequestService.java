package cn.xilikeli.staging.service;

import cn.xilikeli.staging.model.ReturnRequestDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退货申请服务接口
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
public interface ReturnRequestService {

    /**
     * 创建退货申请
     *
     * @param returnRequest 退货申请信息
     * @return 创建后的退货申请
     */
    ReturnRequestDO createReturnRequest(ReturnRequestDO returnRequest);

    /**
     * 根据ID查询退货申请
     *
     * @param id 退货申请ID
     * @return 退货申请信息
     */
    ReturnRequestDO getReturnRequestById(Integer id);

    /**
     * 根据用户ID查询退货申请列表
     *
     * @param userId 用户ID
     * @return 退货申请列表
     */
    List<ReturnRequestDO> getReturnRequestsByUserId(Integer userId);

    /**
     * 根据订单ID查询退货申请
     *
     * @param orderId 订单ID
     * @return 退货申请列表
     */
    List<ReturnRequestDO> getReturnRequestsByOrderId(Integer orderId);

    /**
     * 根据状态查询退货申请
     *
     * @param status 状态
     * @return 退货申请列表
     */
    List<ReturnRequestDO> getReturnRequestsByStatus(Integer status);

    /**
     * 分页查询退货申请
     *
     * @param specification 查询条件
     * @param pageable 分页参数
     * @return 分页结果
     */
    Page<ReturnRequestDO> getReturnRequestsPage(Specification<ReturnRequestDO> specification, Pageable pageable);

    /**
     * 取消退货申请
     *
     * @param id 退货申请ID
     * @param cancelReason 取消原因
     * @return 取消后的退货申请
     */
    ReturnRequestDO cancelReturnRequest(Integer id, String cancelReason);

    /**
     * 审核退货申请
     *
     * @param id 退货申请ID
     * @param auditorId 审核人ID
     * @param status 审核状态
     * @param auditOpinion 审核意见
     * @return 审核后的退货申请
     */
    ReturnRequestDO auditReturnRequest(Integer id, Integer auditorId, Integer status, String auditOpinion);

    /**
     * 录入物流信息
     *
     * @param id 退货申请ID
     * @param trackingNumber 物流单号
     * @param logisticsCompany 物流公司
     * @return 更新后的退货申请
     */
    ReturnRequestDO updateLogisticsInfo(Integer id, String trackingNumber, String logisticsCompany);

    /**
     * 更新物流状态
     *
     * @param id 退货申请ID
     * @param logisticsStatus 物流状态
     * @return 更新后的退货申请
     */
    ReturnRequestDO updateLogisticsStatus(Integer id, Integer logisticsStatus);

    /**
     * 确认收货
     *
     * @param id 退货申请ID
     * @return 更新后的退货申请
     */
    ReturnRequestDO confirmReceipt(Integer id);

    /**
     * 处理退款
     *
     * @param id 退货申请ID
     * @param refundAmount 退款金额
     * @param refundMethod 退款方式
     * @param refundRemark 退款备注
     * @return 更新后的退货申请
     */
    ReturnRequestDO processRefund(Integer id, java.math.BigDecimal refundAmount, Integer refundMethod, String refundRemark);

    /**
     * 更新退款状态
     *
     * @param id 退货申请ID
     * @param refundStatus 退款状态
     * @return 更新后的退货申请
     */
    ReturnRequestDO updateRefundStatus(Integer id, Integer refundStatus);

    /**
     * 统计退货原因
     *
     * @return 退货原因统计结果
     */
    Map<String, Long> countReturnReasons();

    /**
     * 统计退货状态
     *
     * @return 退货状态统计结果
     */
    Map<Integer, Long> countReturnStatuses();

}