package cn.xilikeli.staging.service;

import cn.xilikeli.staging.dto.return_exchange.ReturnApplicationDTO;
import cn.xilikeli.staging.dto.return_exchange.ReturnLogisticsDTO;
import cn.xilikeli.staging.dto.return_exchange.RefundRecordDTO;
import cn.xilikeli.staging.model.ReturnApplicationDO;
import cn.xilikeli.staging.model.ReturnLogisticsDO;
import cn.xilikeli.staging.model.RefundRecordDO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退换货服务类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
public interface ReturnExchangeService {

    /**
     * 用户提交退货申请
     *
     * @param returnApplicationDTO 退货申请信息 DTO
     * @return 退货申请信息
     */
    ReturnApplicationDO submitReturnApplication(ReturnApplicationDTO returnApplicationDTO);

    /**
     * 用户取消退货申请
     *
     * @param applicationId 退货申请 ID
     * @return 取消结果
     */
    boolean cancelReturnApplication(Long applicationId);

    /**
     * 商家审核退货申请
     *
     * @param applicationId 退货申请 ID
     * @param status 审核状态: 1-通过, 2-拒绝
     * @param auditUser 审核人
     * @param auditRemark 审核备注
     * @return 审核结果
     */
    boolean auditReturnApplication(Long applicationId, Integer status, String auditUser, String auditRemark);

    /**
     * 录入退货物流信息
     *
     * @param returnLogisticsDTO 退货物流信息 DTO
     * @return 退货物流信息
     */
    ReturnLogisticsDO addReturnLogistics(ReturnLogisticsDTO returnLogisticsDTO);

    /**
     * 确认收货
     *
     * @param applicationId 退货申请 ID
     * @return 确认结果
     */
    boolean confirmReceiveGoods(Long applicationId);

    /**
     * 处理退款
     *
     * @param refundRecordDTO 退款记录信息 DTO
     * @return 退款记录信息
     */
    RefundRecordDO processRefund(RefundRecordDTO refundRecordDTO);

    /**
     * 根据用户 ID 获取退货申请列表
     *
     * @param accountId 用户 ID
     * @return 退货申请列表
     */
    List<ReturnApplicationDO> getReturnApplicationsByAccountId(Long accountId);

    /**
     * 根据状态获取退货申请列表
     *
     * @param status 申请状态
     * @return 退货申请列表
     */
    List<ReturnApplicationDO> getReturnApplicationsByStatus(Integer status);

    /**
     * 获取退货原因统计分析
     *
     * @return 退货原因统计分析结果
     */
    Map<String, Long> getReturnReasonStatistics();

}