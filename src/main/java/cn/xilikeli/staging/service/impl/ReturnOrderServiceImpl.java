package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.dto.returnorder.AuditReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.CreateReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.LogisticsInfoDTO;
import cn.xilikeli.staging.model.ReturnOrderDO;
import cn.xilikeli.staging.repository.ReturnOrderRepository;
import cn.xilikeli.staging.service.ReturnOrderService;
import cn.xilikeli.staging.vo.returnorder.ReturnOrderVO;
import cn.xilikeli.staging.vo.returnorder.ReturnReasonStatisticsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 退货订单服务实现类
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Slf4j
@Service
public class ReturnOrderServiceImpl implements ReturnOrderService {

    @Resource
    private ReturnOrderRepository returnOrderRepository;

    @Override
    public ReturnOrderVO submitReturnOrder(Integer userId, CreateReturnOrderDTO createReturnOrderDTO) {
        ReturnOrderDO returnOrderDO = new ReturnOrderDO();
        BeanUtils.copyProperties(createReturnOrderDTO, returnOrderDO);
        returnOrderDO.setUserId(userId);
        returnOrderDO.setStatus(0); // 待审核
        returnOrderDO.setRefundStatus(0); // 退款待处理

        ReturnOrderDO savedReturnOrder = returnOrderRepository.save(returnOrderDO);
        return convertToReturnOrderVO(savedReturnOrder);
    }

    @Override
    public void cancelReturnOrder(Integer userId, Long returnOrderId) {
        ReturnOrderDO returnOrderDO = returnOrderRepository.findById(returnOrderId)
                .orElseThrow(() -> new RuntimeException("退货订单不存在"));

        if (!returnOrderDO.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作此退货订单");
        }

        if (returnOrderDO.getStatus() != 0) { // 只有待审核状态可以取消
            throw new RuntimeException("该退货订单无法取消");
        }

        returnOrderDO.setStatus(6); // 已取消
        returnOrderRepository.save(returnOrderDO);
    }

    @Override
    public void auditReturnOrder(Long returnOrderId, AuditReturnOrderDTO auditReturnOrderDTO) {
        ReturnOrderDO returnOrderDO = returnOrderRepository.findById(returnOrderId)
                .orElseThrow(() -> new RuntimeException("退货订单不存在"));

        if (returnOrderDO.getStatus() != 0) { // 只有待审核状态可以审核
            throw new RuntimeException("该退货订单已审核");
        }

        if (auditReturnOrderDTO.getAuditResult() == 1) { // 审核通过
            returnOrderDO.setStatus(1);
        } else if (auditReturnOrderDTO.getAuditResult() == 2) { // 审核拒绝
            returnOrderDO.setStatus(2);
        } else {
            throw new RuntimeException("无效的审核结果");
        }

        returnOrderDO.setAuditRemark(auditReturnOrderDTO.getAuditRemark());
        returnOrderRepository.save(returnOrderDO);

        // TODO: 发送审核结果通知给用户
    }

    @Override
    public void updateLogisticsInfo(Integer userId, Long returnOrderId, LogisticsInfoDTO logisticsInfoDTO) {
        ReturnOrderDO returnOrderDO = returnOrderRepository.findById(returnOrderId)
                .orElseThrow(() -> new RuntimeException("退货订单不存在"));

        if (!returnOrderDO.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作此退货订单");
        }

        if (returnOrderDO.getStatus() != 1) { // 只有审核通过状态可以录入物流信息
            throw new RuntimeException("该退货订单无法录入物流信息");
        }

        returnOrderDO.setLogisticsNo(logisticsInfoDTO.getLogisticsNo());
        returnOrderDO.setLogisticsCompany(logisticsInfoDTO.getLogisticsCompany());
        returnOrderDO.setStatus(3); // 已发货
        returnOrderRepository.save(returnOrderDO);
    }

    @Override
    public void confirmReceipt(Long returnOrderId) {
        ReturnOrderDO returnOrderDO = returnOrderRepository.findById(returnOrderId)
                .orElseThrow(() -> new RuntimeException("退货订单不存在"));

        if (returnOrderDO.getStatus() != 3) { // 只有已发货状态可以确认收货
            throw new RuntimeException("该退货订单无法确认收货");
        }

        returnOrderDO.setStatus(4); // 已收货
        returnOrderRepository.save(returnOrderDO);
    }

    @Override
    public void processRefund(Long returnOrderId) {
        ReturnOrderDO returnOrderDO = returnOrderRepository.findById(returnOrderId)
                .orElseThrow(() -> new RuntimeException("退货订单不存在"));

        if (returnOrderDO.getStatus() != 4) { // 只有已收货状态可以处理退款
            throw new RuntimeException("该退货订单无法处理退款");
        }

        returnOrderDO.setStatus(5); // 已退款
        returnOrderDO.setRefundStatus(1); // 退款成功
        // TODO: 生成退款流水号
        // returnOrderDO.setRefundNo(generateRefundNo());
        returnOrderRepository.save(returnOrderDO);

        // TODO: 发送退款成功通知给用户
    }

    @Override
    public ReturnOrderVO getReturnOrderById(Long returnOrderId) {
        ReturnOrderDO returnOrderDO = returnOrderRepository.findById(returnOrderId)
                .orElseThrow(() -> new RuntimeException("退货订单不存在"));
        return convertToReturnOrderVO(returnOrderDO);
    }

    @Override
    public List<ReturnOrderVO> getReturnOrderListByUserId(Integer userId) {
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
    public List<ReturnReasonStatisticsVO> getReturnReasonStatistics() {
        List<ReturnOrderDO> returnOrderDOList = returnOrderRepository.findAll();

        // 按退货原因分组统计
        Map<String, Long> reasonCountMap = returnOrderDOList.stream()
                .collect(Collectors.groupingBy(ReturnOrderDO::getReason, Collectors.counting()));

        int total = returnOrderDOList.size();
        List<ReturnReasonStatisticsVO> statisticsVOList = new ArrayList<>();

        reasonCountMap.forEach((reason, count) -> {
            ReturnReasonStatisticsVO vo = new ReturnReasonStatisticsVO();
            vo.setReason(reason);
            vo.setCount(count.intValue());
            vo.setProportion(total > 0 ? (count.doubleValue() / total) * 100 : 0);
            statisticsVOList.add(vo);
        });

        return statisticsVOList;
    }

    @Override
    public ReturnOrderVO convertToReturnOrderVO(ReturnOrderDO returnOrderDO) {
        ReturnOrderVO returnOrderVO = new ReturnOrderVO();
        BeanUtils.copyProperties(returnOrderDO, returnOrderVO);
        return returnOrderVO;
    }
}
