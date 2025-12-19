package cn.xilikeli.staging.service;

import cn.xilikeli.staging.dto.returnorder.AuditReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.CreateReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.LogisticsInfoDTO;
import cn.xilikeli.staging.model.ReturnOrderDO;
import cn.xilikeli.staging.vo.returnorder.ReturnOrderVO;
import cn.xilikeli.staging.vo.returnorder.ReturnReasonStatisticsVO;

import java.util.List;

/**
 * <p>
 * 退货订单服务接口
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
public interface ReturnOrderService {

    /**
     * 提交退货申请
     *
     * @param userId               用户 ID
     * @param createReturnOrderDTO 创建退货订单 DTO
     * @return 退货订单 VO
     */
    ReturnOrderVO submitReturnOrder(Integer userId, CreateReturnOrderDTO createReturnOrderDTO);

    /**
     * 取消退货申请
     *
     * @param userId       用户 ID
     * @param returnOrderId 退货订单 ID
     */
    void cancelReturnOrder(Integer userId, Long returnOrderId);

    /**
     * 商家审核退货申请
     *
     * @param returnOrderId       退货订单 ID
     * @param auditReturnOrderDTO 审核退货订单 DTO
     */
    void auditReturnOrder(Long returnOrderId, AuditReturnOrderDTO auditReturnOrderDTO);

    /**
     * 录入退货物流信息
     *
     * @param userId               用户 ID
     * @param returnOrderId        退货订单 ID
     * @param logisticsInfoDTO     物流信息 DTO
     */
    void updateLogisticsInfo(Integer userId, Long returnOrderId, LogisticsInfoDTO logisticsInfoDTO);

    /**
     * 商家确认收货
     *
     * @param returnOrderId 退货订单 ID
     */
    void confirmReceipt(Long returnOrderId);

    /**
     * 处理退款
     *
     * @param returnOrderId 退货订单 ID
     */
    void processRefund(Long returnOrderId);

    /**
     * 根据退货订单 ID 查询退货订单
     *
     * @param returnOrderId 退货订单 ID
     * @return 退货订单 VO
     */
    ReturnOrderVO getReturnOrderById(Long returnOrderId);

    /**
     * 根据用户 ID 查询退货订单列表
     *
     * @param userId 用户 ID
     * @return 退货订单 VO 列表
     */
    List<ReturnOrderVO> getReturnOrderListByUserId(Integer userId);

    /**
     * 获取所有退货订单列表
     *
     * @return 退货订单 VO 列表
     */
    List<ReturnOrderVO> getAllReturnOrderList();

    /**
     * 退货原因统计分析
     *
     * @return 退货原因统计分析 VO 列表
     */
    List<ReturnReasonStatisticsVO> getReturnReasonStatistics();

    /**
     * 将 ReturnOrderDO 转换为 ReturnOrderVO
     *
     * @param returnOrderDO 退货订单 DO
     * @return 退货订单 VO
     */
    ReturnOrderVO convertToReturnOrderVO(ReturnOrderDO returnOrderDO);
}
