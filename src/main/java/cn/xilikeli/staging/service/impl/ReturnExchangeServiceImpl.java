package cn.xilikeli.staging.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xilikeli.staging.common.exception.http.FailedException;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.common.util.Assert;
import cn.xilikeli.staging.dto.return_exchange.ReturnApplicationDTO;
import cn.xilikeli.staging.dto.return_exchange.ReturnLogisticsDTO;
import cn.xilikeli.staging.dto.return_exchange.RefundRecordDTO;
import cn.xilikeli.staging.model.ReturnApplicationDO;
import cn.xilikeli.staging.model.ReturnLogisticsDO;
import cn.xilikeli.staging.model.RefundRecordDO;
import cn.xilikeli.staging.repository.ReturnApplicationRepository;
import cn.xilikeli.staging.repository.ReturnLogisticsRepository;
import cn.xilikeli.staging.repository.RefundRecordRepository;
import cn.xilikeli.staging.service.ReturnExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 退换货服务实现类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Slf4j
@Service
public class ReturnExchangeServiceImpl implements ReturnExchangeService {

    private ReturnApplicationRepository returnApplicationRepository;
    private ReturnLogisticsRepository returnLogisticsRepository;
    private RefundRecordRepository refundRecordRepository;

    @Autowired
    public void setReturnApplicationRepository(ReturnApplicationRepository returnApplicationRepository) {
        this.returnApplicationRepository = returnApplicationRepository;
    }

    @Autowired
    public void setReturnLogisticsRepository(ReturnLogisticsRepository returnLogisticsRepository) {
        this.returnLogisticsRepository = returnLogisticsRepository;
    }

    @Autowired
    public void setRefundRecordRepository(RefundRecordRepository refundRecordRepository) {
        this.refundRecordRepository = refundRecordRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnApplicationDO submitReturnApplication(ReturnApplicationDTO returnApplicationDTO) {
        ReturnApplicationDO returnApplicationDO = new ReturnApplicationDO();
        BeanUtils.copyProperties(returnApplicationDTO, returnApplicationDO);
        returnApplicationDO.setStatus(0); // 0-待审核
        returnApplicationDO.setApplyTime(new Date());
        return this.returnApplicationRepository.save(returnApplicationDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelReturnApplication(Long applicationId) {
        Optional<ReturnApplicationDO> applicationOptional = this.returnApplicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            ReturnApplicationDO returnApplicationDO = applicationOptional.get();
            returnApplicationDO.setStatus(3); // 3-已取消
            this.returnApplicationRepository.save(returnApplicationDO);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean auditReturnApplication(Long applicationId, Integer status, String auditUser, String auditRemark) {
        Optional<ReturnApplicationDO> applicationOptional = this.returnApplicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            ReturnApplicationDO returnApplicationDO = applicationOptional.get();
            returnApplicationDO.setStatus(status);
            returnApplicationDO.setAuditTime(new Date());
            returnApplicationDO.setAuditUser(auditUser);
            returnApplicationDO.setAuditRemark(auditRemark);
            this.returnApplicationRepository.save(returnApplicationDO);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnLogisticsDO addReturnLogistics(ReturnLogisticsDTO returnLogisticsDTO) {
        ReturnLogisticsDO returnLogisticsDO = new ReturnLogisticsDO();
        BeanUtils.copyProperties(returnLogisticsDTO, returnLogisticsDO);
        returnLogisticsDO.setLogisticsStatus(0); // 0-待发货
        return this.returnLogisticsRepository.save(returnLogisticsDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmReceiveGoods(Long applicationId) {
        Optional<ReturnApplicationDO> applicationOptional = this.returnApplicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            ReturnApplicationDO returnApplicationDO = applicationOptional.get();
            returnApplicationDO.setStatus(4); // 4-已完成
            this.returnApplicationRepository.save(returnApplicationDO);

            // 更新物流状态为已签收
            ReturnLogisticsDO returnLogisticsDO = this.returnLogisticsRepository.findByReturnApplicationId(applicationId);
            if (returnLogisticsDO != null) {
                returnLogisticsDO.setLogisticsStatus(3); // 3-已签收
                returnLogisticsDO.setReceiveTime(new Date());
                this.returnLogisticsRepository.save(returnLogisticsDO);
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RefundRecordDO processRefund(RefundRecordDTO refundRecordDTO) {
        RefundRecordDO refundRecordDO = new RefundRecordDO();
        BeanUtils.copyProperties(refundRecordDTO, refundRecordDO);
        refundRecordDO.setRefundStatus(2); // 2-退款成功
        refundRecordDO.setRefundTime(new Date());
        return this.refundRecordRepository.save(refundRecordDO);
    }

    @Override
    public List<ReturnApplicationDO> getReturnApplicationsByAccountId(Long accountId) {
        return this.returnApplicationRepository.findByAccountId(accountId);
    }

    @Override
    public List<ReturnApplicationDO> getReturnApplicationsByStatus(Integer status) {
        return this.returnApplicationRepository.findByStatus(status);
    }

    @Override
    public Map<String, Long> getReturnReasonStatistics() {
        List<Object[]> results = this.returnApplicationRepository.countByReasonGroupByReason();
        Map<String, Long> statistics = new HashMap<>();
        for (Object[] result : results) {
            String reason = (String) result[0];
            Long count = (Long) result[1];
            statistics.put(reason, count);
        }
        return statistics;
    }

}