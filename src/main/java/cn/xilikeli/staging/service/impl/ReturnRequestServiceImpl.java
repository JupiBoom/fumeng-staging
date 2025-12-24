package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.model.ReturnRequestDO;
import cn.xilikeli.staging.repository.ReturnRequestRepository;
import cn.xilikeli.staging.service.ReturnRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退货申请服务实现类
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Service
public class ReturnRequestServiceImpl implements ReturnRequestService {

    private final ReturnRequestRepository returnRequestRepository;

    @Autowired
    public ReturnRequestServiceImpl(ReturnRequestRepository returnRequestRepository) {
        this.returnRequestRepository = returnRequestRepository;
    }

    @Override
    @Transactional
    public ReturnRequestDO createReturnRequest(ReturnRequestDO returnRequest) {
        returnRequest.setStatus(0); // 0-待审核
        returnRequest.setCreateTime(new Date());
        returnRequest.setUpdateTime(new Date());
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    public ReturnRequestDO getReturnRequestById(Integer id) {
        return returnRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("退货申请不存在"));
    }

    @Override
    public List<ReturnRequestDO> getReturnRequestsByUserId(Integer userId) {
        return returnRequestRepository.findByUserId(userId);
    }

    @Override
    public List<ReturnRequestDO> getReturnRequestsByOrderId(Integer orderId) {
        return returnRequestRepository.findByOrderId(orderId);
    }

    @Override
    public List<ReturnRequestDO> getReturnRequestsByStatus(Integer status) {
        return returnRequestRepository.findByStatus(status);
    }

    @Override
    public Page<ReturnRequestDO> getReturnRequestsPage(Specification<ReturnRequestDO> specification, Pageable pageable) {
        return returnRequestRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public ReturnRequestDO cancelReturnRequest(Integer id, String cancelReason) {
        ReturnRequestDO returnRequest = getReturnRequestById(id);
        if (returnRequest.getStatus() != 0) { // 只有待审核的申请才能取消
            throw new IllegalArgumentException("该退货申请无法取消");
        }
        returnRequest.setStatus(3); // 3-已取消
        returnRequest.setCancelReason(cancelReason);
        returnRequest.setCancelTime(new Date());
        returnRequest.setUpdateTime(new Date());
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    @Transactional
    public ReturnRequestDO auditReturnRequest(Integer id, Integer auditorId, Integer status, String auditOpinion) {
        ReturnRequestDO returnRequest = getReturnRequestById(id);
        if (returnRequest.getStatus() != 0) { // 只有待审核的申请才能审核
            throw new IllegalArgumentException("该退货申请无法审核");
        }
        returnRequest.setStatus(status);
        returnRequest.setAuditorId(auditorId);
        returnRequest.setAuditTime(new Date());
        returnRequest.setAuditOpinion(auditOpinion);
        returnRequest.setUpdateTime(new Date());
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    @Transactional
    public ReturnRequestDO updateLogisticsInfo(Integer id, String trackingNumber, String logisticsCompany) {
        ReturnRequestDO returnRequest = getReturnRequestById(id);
        if (returnRequest.getStatus() != 1) { // 只有审核通过的申请才能录入物流信息
            throw new IllegalArgumentException("该退货申请无法录入物流信息");
        }
        returnRequest.setTrackingNumber(trackingNumber);
        returnRequest.setLogisticsCompany(logisticsCompany);
        returnRequest.setLogisticsStatus(1); // 1-已发货
        returnRequest.setUpdateTime(new Date());
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    @Transactional
    public ReturnRequestDO updateLogisticsStatus(Integer id, Integer logisticsStatus) {
        ReturnRequestDO returnRequest = getReturnRequestById(id);
        if (returnRequest.getStatus() != 1) { // 只有审核通过的申请才能更新物流状态
            throw new IllegalArgumentException("该退货申请无法更新物流状态");
        }
        returnRequest.setLogisticsStatus(logisticsStatus);
        returnRequest.setUpdateTime(new Date());
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    @Transactional
    public ReturnRequestDO confirmReceipt(Integer id) {
        ReturnRequestDO returnRequest = getReturnRequestById(id);
        if (returnRequest.getStatus() != 1) { // 只有审核通过的申请才能确认收货
            throw new IllegalArgumentException("该退货申请无法确认收货");
        }
        returnRequest.setStatus(4); // 4-已退货
        returnRequest.setConfirmTime(new Date());
        returnRequest.setRefundStatus(0); // 0-待退款
        returnRequest.setUpdateTime(new Date());
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    @Transactional
    public ReturnRequestDO processRefund(Integer id, java.math.BigDecimal refundAmount, Integer refundMethod, String refundRemark) {
        ReturnRequestDO returnRequest = getReturnRequestById(id);
        if (returnRequest.getStatus() != 4) { // 只有已退货的申请才能处理退款
            throw new IllegalArgumentException("该退货申请无法处理退款");
        }
        returnRequest.setRefundAmount(refundAmount);
        returnRequest.setRefundMethod(refundMethod);
        returnRequest.setRefundRemark(refundRemark);
        returnRequest.setRefundStatus(1); // 1-退款中
        returnRequest.setRefundTime(new Date());
        returnRequest.setUpdateTime(new Date());
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    @Transactional
    public ReturnRequestDO updateRefundStatus(Integer id, Integer refundStatus) {
        ReturnRequestDO returnRequest = getReturnRequestById(id);
        if (returnRequest.getStatus() != 4) { // 只有已退货的申请才能更新退款状态
            throw new IllegalArgumentException("该退货申请无法更新退款状态");
        }
        returnRequest.setRefundStatus(refundStatus);
        returnRequest.setUpdateTime(new Date());
        if (refundStatus == 2) { // 2-退款成功
            returnRequest.setStatus(5); // 5-已完成
        }
        return returnRequestRepository.save(returnRequest);
    }

    @Override
    public Map<String, Long> countReturnReasons() {
        List<Object[]> results = returnRequestRepository.countByReason();
        Map<String, Long> reasonCount = new HashMap<>();
        for (Object[] result : results) {
            String reason = (String) result[0];
            Long count = (Long) result[1];
            reasonCount.put(reason, count);
        }
        return reasonCount;
    }

    @Override
    public Map<Integer, Long> countReturnStatuses() {
        List<Object[]> results = returnRequestRepository.countByStatus();
        Map<Integer, Long> statusCount = new HashMap<>();
        for (Object[] result : results) {
            Integer status = (Integer) result[0];
            Long count = (Long) result[1];
            statusCount.put(status, count);
        }
        return statusCount;
    }

}