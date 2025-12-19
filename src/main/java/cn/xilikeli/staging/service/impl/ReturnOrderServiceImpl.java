package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.common.enumeration.ReturnOrderStatusEnum;
import cn.xilikeli.staging.common.enumeration.ReturnReasonEnum;
import cn.xilikeli.staging.dto.returnorder.AuditReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.CreateReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.LogisticsInfoDTO;
import cn.xilikeli.staging.dto.returnorder.RefundDTO;
import cn.xilikeli.staging.model.ReturnOrderDO;
import cn.xilikeli.staging.repository.ReturnOrderRepository;
import cn.xilikeli.staging.service.ReturnOrderService;
import cn.xilikeli.staging.vo.returnorder.ReturnOrderVO;
import cn.xilikeli.staging.vo.returnorder.ReturnReasonStatsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 退货订单服务实现类
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Slf4j
@Service
public class ReturnOrderServiceImpl implements ReturnOrderService {

    private final ReturnOrderRepository returnOrderRepository;

    public ReturnOrderServiceImpl(ReturnOrderRepository returnOrderRepository) {
        this.returnOrderRepository = returnOrderRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnOrderVO submitReturnOrder(Long userId, CreateReturnOrderDTO createReturnOrderDTO) {
        ReturnOrderDO returnOrderDO = ReturnOrderDO.builder()
                .userId(userId)
                .orderId(createReturnOrderDTO.getOrderId())
                .productId(createReturnOrderDTO.getProductId())
                .productName(createReturnOrderDTO.getProductName())
                .quantity(createReturnOrderDTO.getQuantity())
                .reason(createReturnOrderDTO.getReason())
                .description(createReturnOrderDTO.getDescription())
                .images(createReturnOrderDTO.getImages())
                .status(ReturnOrderStatusEnum.PENDING_AUDIT.getCode())
                .build();

        returnOrderRepository.save(returnOrderDO);

        return convertToReturnOrderVO(returnOrderDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnOrderVO cancelReturnOrder(Long userId, Long returnOrderId) {
        ReturnOrderDO returnOrderDO = getReturnOrderById(returnOrderId);

        // 检查退货订单是否属于该用户
        if (!returnOrderDO.getUserId().equals(userId)) {
            throw new RuntimeException("该退货订单不属于当前用户");
        }

        // 检查退货订单是否可以取消
        if (!ReturnOrderStatusEnum.PENDING_AUDIT.getCode().equals(returnOrderDO.getStatus())) {
            throw new RuntimeException("该退货订单无法取消");
        }

        returnOrderDO.setStatus(ReturnOrderStatusEnum.CANCELED.getCode());
        returnOrderRepository.save(returnOrderDO);

        return convertToReturnOrderVO(returnOrderDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnOrderVO auditReturnOrder(Long returnOrderId, AuditReturnOrderDTO auditReturnOrderDTO) {
        ReturnOrderDO returnOrderDO = getReturnOrderById(returnOrderId);

        // 检查退货订单是否处于待审核状态
        if (!ReturnOrderStatusEnum.PENDING_AUDIT.getCode().equals(returnOrderDO.getStatus())) {
            throw new RuntimeException("该退货订单不处于待审核状态");
        }

        if (auditReturnOrderDTO.getApproved()) {
            returnOrderDO.setStatus(ReturnOrderStatusEnum.APPROVED.getCode());
        } else {
            returnOrderDO.setStatus(ReturnOrderStatusEnum.REJECTED.getCode());
        }
        returnOrderDO.setAuditRemark(auditReturnOrderDTO.getRemark());

        returnOrderRepository.save(returnOrderDO);

        // TODO: 发送审核结果通知给用户

        return convertToReturnOrderVO(returnOrderDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnOrderVO updateLogisticsInfo(Long userId, Long returnOrderId, LogisticsInfoDTO logisticsInfoDTO) {
        ReturnOrderDO returnOrderDO = getReturnOrderById(returnOrderId);

        // 检查退货订单是否属于该用户
        if (!returnOrderDO.getUserId().equals(userId)) {
            throw new RuntimeException("该退货订单不属于当前用户");
        }

        // 检查退货订单是否处于已审核通过状态
        if (!ReturnOrderStatusEnum.APPROVED.getCode().equals(returnOrderDO.getStatus())) {
            throw new RuntimeException("该退货订单不处于已审核通过状态");
        }

        returnOrderDO.setLogisticsNo(logisticsInfoDTO.getLogisticsNo());
        returnOrderDO.setLogisticsCompany(logisticsInfoDTO.getLogisticsCompany());
        returnOrderDO.setStatus(ReturnOrderStatusEnum.SHIPPED.getCode());

        returnOrderRepository.save(returnOrderDO);

        return convertToReturnOrderVO(returnOrderDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnOrderVO confirmReceipt(Long returnOrderId) {
        ReturnOrderDO returnOrderDO = getReturnOrderById(returnOrderId);

        // 检查退货订单是否处于已发货状态
        if (!ReturnOrderStatusEnum.SHIPPED.getCode().equals(returnOrderDO.getStatus())) {
            throw new RuntimeException("该退货订单不处于已发货状态");
        }

        returnOrderDO.setStatus(ReturnOrderStatusEnum.RECEIVED.getCode());
        returnOrderRepository.save(returnOrderDO);

        return convertToReturnOrderVO(returnOrderDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnOrderVO processRefund(Long returnOrderId, RefundDTO refundDTO) {
        ReturnOrderDO returnOrderDO = getReturnOrderById(returnOrderId);

        // 检查退货订单是否处于已收货状态
        if (!ReturnOrderStatusEnum.RECEIVED.getCode().equals(returnOrderDO.getStatus())) {
            throw new RuntimeException("该退货订单不处于已收货状态");
        }

        returnOrderDO.setRefundAmount(refundDTO.getRefundAmount());
        returnOrderDO.setRefundNo(refundDTO.getRefundNo());
        returnOrderDO.setRefundRemark(refundDTO.getRefundRemark());
        returnOrderDO.setStatus(ReturnOrderStatusEnum.REFUNDED.getCode());

        returnOrderRepository.save(returnOrderDO);

        // TODO: 发送退款完成通知给用户

        return convertToReturnOrderVO(returnOrderDO);
    }

    @Override
    public ReturnOrderDO getReturnOrderById(Long returnOrderId) {
        return returnOrderRepository.findById(returnOrderId)
                .orElseThrow(() -> new RuntimeException("退货订单不存在"));
    }

    @Override
    public List<ReturnOrderVO> getReturnOrderListByUserId(Long userId) {
        List<ReturnOrderDO> returnOrderDOList = returnOrderRepository.findByUserId(userId);
        return returnOrderDOList.stream()
                .map(this::convertToReturnOrderVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReturnOrderVO> getAllReturnOrderList() {
        List<ReturnOrderDO> returnOrderDOList = returnOrderRepository.findAll();
        return returnOrderDOList.stream()
                .map(this::convertToReturnOrderVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReturnReasonStatsVO> getReturnReasonStats() {
        List<ReturnOrderDO> returnOrderDOList = returnOrderRepository.findAll();
        int totalCount = returnOrderDOList.size();

        // 统计各退货原因的数量
        List<ReturnReasonStatsVO> statsVOList = new ArrayList<>();
        for (ReturnReasonEnum reasonEnum : ReturnReasonEnum.values()) {
            long count = returnOrderDOList.stream()
                    .filter(order -> reasonEnum.getCode().equals(order.getReason()))
                    .count();

            ReturnReasonStatsVO statsVO = new ReturnReasonStatsVO();
            statsVO.setReason(reasonEnum.getCode());
            statsVO.setReasonDescription(reasonEnum.getDescription());
            statsVO.setCount((int) count);
            statsVO.setPercentage(totalCount > 0 ? (double) count / totalCount * 100 : 0.0);

            statsVOList.add(statsVO);
        }

        return statsVOList;
    }

    @Override
    public ReturnOrderVO convertToReturnOrderVO(ReturnOrderDO returnOrderDO) {
        ReturnOrderVO returnOrderVO = new ReturnOrderVO();
        BeanUtils.copyProperties(returnOrderDO, returnOrderVO);

        // 设置原因描述
        if (returnOrderDO.getReason() != null) {
            ReturnReasonEnum reasonEnum = ReturnReasonEnum.getByCode(returnOrderDO.getReason());
            if (reasonEnum != null) {
                returnOrderVO.setReasonDescription(reasonEnum.getDescription());
            }
        }

        // 设置状态描述
        if (returnOrderDO.getStatus() != null) {
            ReturnOrderStatusEnum statusEnum = ReturnOrderStatusEnum.getByCode(returnOrderDO.getStatus());
            if (statusEnum != null) {
                returnOrderVO.setStatusDescription(statusEnum.getDescription());
            }
        }

        return returnOrderVO;
    }

}
