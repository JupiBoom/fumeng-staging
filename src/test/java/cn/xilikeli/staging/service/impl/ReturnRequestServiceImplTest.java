package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.model.ReturnRequestDO;
import cn.xilikeli.staging.repository.ReturnRequestRepository;
import cn.xilikeli.staging.service.ReturnRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * <p>
 * 退货申请服务实现类测试
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@ExtendWith(MockitoExtension.class)
class ReturnRequestServiceImplTest {

    @Mock
    private ReturnRequestRepository returnRequestRepository;

    @InjectMocks
    private ReturnRequestServiceImpl returnRequestService;

    private ReturnRequestDO returnRequest;

    @BeforeEach
    void setUp() {
        returnRequest = new ReturnRequestDO();
        returnRequest.setId(1L);
        returnRequest.setUserId(123);
        returnRequest.setOrderId(456);
        returnRequest.setItemId(789);
        returnRequest.setItemName("测试商品");
        returnRequest.setItemQuantity(1);
        returnRequest.setItemPrice(new BigDecimal("100.00"));
        returnRequest.setReturnAmount(new BigDecimal("100.00"));
        returnRequest.setReason("商品质量问题");
        returnRequest.setReturnAddress("测试地址");
        returnRequest.setReceiver("测试收货人");
        returnRequest.setPhone("13800138000");
    }

    @Test
    void createReturnRequest() {
        when(returnRequestRepository.save(any(ReturnRequestDO.class))).thenReturn(returnRequest);

        ReturnRequestDO createdReturnRequest = returnRequestService.createReturnRequest(returnRequest);

        assertNotNull(createdReturnRequest);
        assertEquals(1, createdReturnRequest.getId());
        assertEquals(0, createdReturnRequest.getStatus());
    }

    @Test
    void getReturnRequestById() {
        when(returnRequestRepository.findById(1)).thenReturn(Optional.of(returnRequest));

        ReturnRequestDO foundReturnRequest = returnRequestService.getReturnRequestById(1);

        assertNotNull(foundReturnRequest);
        assertEquals(1, foundReturnRequest.getId());
    }

    @Test
    void cancelReturnRequest() {
        when(returnRequestRepository.findById(1)).thenReturn(Optional.of(returnRequest));
        when(returnRequestRepository.save(any(ReturnRequestDO.class))).thenReturn(returnRequest);

        ReturnRequestDO cancelledReturnRequest = returnRequestService.cancelReturnRequest(1, "测试取消原因");

        assertNotNull(cancelledReturnRequest);
        assertEquals(3, cancelledReturnRequest.getStatus());
        assertEquals("测试取消原因", cancelledReturnRequest.getCancelReason());
    }

    @Test
    void auditReturnRequest() {
        when(returnRequestRepository.findById(1)).thenReturn(Optional.of(returnRequest));
        when(returnRequestRepository.save(any(ReturnRequestDO.class))).thenReturn(returnRequest);

        ReturnRequestDO auditedReturnRequest = returnRequestService.auditReturnRequest(1, 999, 1, "审核通过");

        assertNotNull(auditedReturnRequest);
        assertEquals(1, auditedReturnRequest.getStatus());
        assertEquals(999, auditedReturnRequest.getAuditorId());
        assertEquals("审核通过", auditedReturnRequest.getAuditOpinion());
    }

    @Test
    void updateLogisticsInfo() {
        returnRequest.setStatus(1);
        when(returnRequestRepository.findById(1)).thenReturn(Optional.of(returnRequest));
        when(returnRequestRepository.save(any(ReturnRequestDO.class))).thenReturn(returnRequest);

        ReturnRequestDO updatedReturnRequest = returnRequestService.updateLogisticsInfo(1, "1234567890", "顺丰速运");

        assertNotNull(updatedReturnRequest);
        assertEquals("1234567890", updatedReturnRequest.getTrackingNumber());
        assertEquals("顺丰速运", updatedReturnRequest.getLogisticsCompany());
        assertEquals(1, updatedReturnRequest.getLogisticsStatus());
    }

    @Test
    void confirmReceipt() {
        returnRequest.setStatus(1);
        when(returnRequestRepository.findById(1)).thenReturn(Optional.of(returnRequest));
        when(returnRequestRepository.save(any(ReturnRequestDO.class))).thenReturn(returnRequest);

        ReturnRequestDO confirmedReturnRequest = returnRequestService.confirmReceipt(1);

        assertNotNull(confirmedReturnRequest);
        assertEquals(4, confirmedReturnRequest.getStatus());
        assertEquals(0, confirmedReturnRequest.getRefundStatus());
    }

    @Test
    void processRefund() {
        returnRequest.setStatus(4);
        when(returnRequestRepository.findById(1)).thenReturn(Optional.of(returnRequest));
        when(returnRequestRepository.save(any(ReturnRequestDO.class))).thenReturn(returnRequest);

        ReturnRequestDO refundedReturnRequest = returnRequestService.processRefund(1, new BigDecimal("100.00"), 0, "测试退款备注");

        assertNotNull(refundedReturnRequest);
        assertEquals(new BigDecimal("100.00"), refundedReturnRequest.getRefundAmount());
        assertEquals(0, refundedReturnRequest.getRefundMethod());
        assertEquals("测试退款备注", refundedReturnRequest.getRefundRemark());
        assertEquals(1, refundedReturnRequest.getRefundStatus());
    }

}