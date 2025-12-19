package cn.xilikeli.staging.service;

import cn.xilikeli.staging.dto.returnorder.AuditReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.CreateReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.LogisticsInfoDTO;
import cn.xilikeli.staging.dto.returnorder.RefundDTO;
import cn.xilikeli.staging.model.ReturnOrderDO;
import cn.xilikeli.staging.vo.returnorder.ReturnOrderVO;
import cn.xilikeli.staging.vo.returnorder.ReturnReasonStatsVO;

import java.util.List;

/**
 * <p>
 * 退货订单服务接口
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
public interface ReturnOrderService {

    /**
     * 用户提交退货申请
     *
     * @param userId 用户 ID
     * @param createReturnOrderDTO 创建退货订单 DTO
     * @return 退货订单 VO
     */
    ReturnOrderVO submitReturnOrder(Long userId, CreateReturnOrderDTO createReturnOrderDTO);

    /**
     * 用户取消退货申请
     *
     * @param userId 用户 ID
     * @param returnOrderId 退货订单 ID
     * @return 退货订单 VO
     */
    ReturnOrderVO cancelReturnOrder(Long userId, Long returnOrderId);

    /**
     * 商家审核退货申请
     *
     * @param returnOrderId 退货订单 ID
     * @param auditReturnOrderDTO 审核退货订单 DTO
     * @return 退货订单 VO
     */
    ReturnOrderVO auditReturnOrder(Long returnOrderId, AuditReturnOrderDTO auditReturnOrderDTO);

    /**
     * 用户录入物流信息
     *
     * @param userId 用户 ID
     * @param returnOrderId 退货订单 ID
     * @param logisticsInfoDTO 物流信息 DTO
     * @return 退货订单 VO
     */
    ReturnOrderVO updateLogisticsInfo(Long userId, Long returnOrderId, LogisticsInfoDTO logisticsInfoDTO);

    /**
     * 商家确认收货
     *
     * @param returnOrderId 退货订单 ID
     * @return 退货订单 VO
     */
    ReturnOrderVO confirmReceipt(Long returnOrderId);

    /**
     * 商家处理退款
     *
     * @param returnOrderId 退货订单 ID
     * @param refundDTO 退款处理 DTO
     * @return 退货订单 VO
     */
    ReturnOrderVO processRefund(Long returnOrderId, RefundDTO refundDTO);

    /**
     * 根据退货订单 ID 查询退货订单
     *
     * @param returnOrderId 退货订单 ID
     * @return 退货订单 DO
     */
    ReturnOrderDO getReturnOrderById(Long returnOrderId);

    /**
     * 根据用户 ID 查询退货订单列表
     *
     * @param userId 用户 ID
     * @return 退货订单 VO 列表
     */
    List<ReturnOrderVO> getReturnOrderListByUserId(Long userId);

    /**
     * 查询所有退货订单列表
     *
     * @return 退货订单 VO 列表
     */
    List<ReturnOrderVO> getAllReturnOrderList();

    /**
     * 获取退货原因统计分析
     *
     * @return 退货原因统计列表
     */
    List<ReturnReasonStatsVO> getReturnReasonStats();

    /**
     * 将 ReturnOrderDO 转换为 ReturnOrderVO
     *
     * @param returnOrderDO 退货订单 DO
     * @return 退货订单 VO
     */
    ReturnOrderVO convertToReturnOrderVO(ReturnOrderDO returnOrderDO);

}
