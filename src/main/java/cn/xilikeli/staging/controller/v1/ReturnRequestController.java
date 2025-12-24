package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.vo.response.CreatedResponseVO;
import cn.xilikeli.staging.vo.response.DeletedResponseVO;
import cn.xilikeli.staging.vo.response.PagingDozerVO;
import cn.xilikeli.staging.vo.response.UpdatedResponseVO;
import cn.xilikeli.staging.dto.returnrequest.*;
import cn.xilikeli.staging.model.ReturnRequestDO;
import cn.xilikeli.staging.service.ReturnRequestService;
import cn.xilikeli.staging.vo.returnrequest.ReturnRequestVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 退货申请控制器
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@RestController
@RequestMapping("/v1/return-request")
@Validated
public class ReturnRequestController {

    private final ReturnRequestService returnRequestService;

    @Autowired
    public ReturnRequestController(ReturnRequestService returnRequestService) {
        this.returnRequestService = returnRequestService;
    }

    /**
     * 创建退货申请
     *
     * @param dto 创建退货申请 DTO
     * @return 创建成功的退货申请
     */
    @PostMapping
    public CreatedResponseVO createReturnRequest(@RequestBody @Valid CreateReturnRequestDTO dto) {
        ReturnRequestDO returnRequest = new ReturnRequestDO();
        BeanUtils.copyProperties(dto, returnRequest);
        ReturnRequestDO savedReturnRequest = returnRequestService.createReturnRequest(returnRequest);
        return CreatedResponseVO.builder().id(savedReturnRequest.getId()).build();
    }

    /**
     * 根据ID查询退货申请
     *
     * @param id 退货申请ID
     * @return 退货申请信息
     */
    @GetMapping("/{id}")
    public ReturnRequestVO getReturnRequestById(@PathVariable Integer id) {
        ReturnRequestDO returnRequest = returnRequestService.getReturnRequestById(id);
        ReturnRequestVO vo = new ReturnRequestVO();
        BeanUtils.copyProperties(returnRequest, vo);
        return vo;
    }

    /**
     * 根据用户ID查询退货申请列表
     *
     * @param userId 用户ID
     * @return 退货申请列表
     */
    @GetMapping("/user/{userId}")
    public List<ReturnRequestVO> getReturnRequestsByUserId(@PathVariable Integer userId) {
        List<ReturnRequestDO> returnRequests = returnRequestService.getReturnRequestsByUserId(userId);
        return returnRequests.stream()
                .map(returnRequest -> {
                    ReturnRequestVO vo = new ReturnRequestVO();
                    BeanUtils.copyProperties(returnRequest, vo);
                    return vo;
                })
                .collect(Collectors.toList());
    }

    /**
     * 根据订单ID查询退货申请
     *
     * @param orderId 订单ID
     * @return 退货申请列表
     */
    @GetMapping("/order/{orderId}")
    public List<ReturnRequestVO> getReturnRequestsByOrderId(@PathVariable Integer orderId) {
        List<ReturnRequestDO> returnRequests = returnRequestService.getReturnRequestsByOrderId(orderId);
        return returnRequests.stream()
                .map(returnRequest -> {
                    ReturnRequestVO vo = new ReturnRequestVO();
                    BeanUtils.copyProperties(returnRequest, vo);
                    return vo;
                })
                .collect(Collectors.toList());
    }

    /**
     * 根据状态查询退货申请
     *
     * @param status 状态
     * @return 退货申请列表
     */
    @GetMapping("/status/{status}")
    public List<ReturnRequestVO> getReturnRequestsByStatus(@PathVariable Integer status) {
        List<ReturnRequestDO> returnRequests = returnRequestService.getReturnRequestsByStatus(status);
        return returnRequests.stream()
                .map(returnRequest -> {
                    ReturnRequestVO vo = new ReturnRequestVO();
                    BeanUtils.copyProperties(returnRequest, vo);
                    return vo;
                })
                .collect(Collectors.toList());
    }

    /**
     * 分页查询退货申请
     *
     * @param pageable 分页参数
     * @return 分页结果
     */
    @GetMapping
    public PagingDozerVO<ReturnRequestVO> getReturnRequestsPage(@PageableDefault Pageable pageable) {
        Page<ReturnRequestDO> page = returnRequestService.getReturnRequestsPage(null, pageable);
        List<ReturnRequestVO> vos = page.getContent().stream()
                .map(returnRequest -> {
                    ReturnRequestVO vo = new ReturnRequestVO();
                    BeanUtils.copyProperties(returnRequest, vo);
                    return vo;
                })
                .collect(Collectors.toList());
        return new PagingDozerVO<>(vos, page.getTotalElements(), page.getNumber(), page.getSize());
    }

    /**
     * 取消退货申请
     *
     * @param id 退货申请ID
     * @param dto 取消退货申请 DTO
     * @return 取消成功的响应
     */
    @PutMapping("/{id}/cancel")
    public UpdatedResponseVO cancelReturnRequest(@PathVariable Integer id, @RequestBody @Valid CancelReturnRequestDTO dto) {
        returnRequestService.cancelReturnRequest(id, dto.getCancelReason());
        return new UpdatedResponseVO();
    }

    /**
     * 审核退货申请
     *
     * @param id 退货申请ID
     * @param dto 审核退货申请 DTO
     * @return 审核成功的响应
     */
    @PutMapping("/{id}/audit")
    public UpdatedResponseVO auditReturnRequest(@PathVariable Integer id, @RequestBody @Valid AuditReturnRequestDTO dto) {
        returnRequestService.auditReturnRequest(id, dto.getAuditorId(), dto.getStatus(), dto.getAuditOpinion());
        return new UpdatedResponseVO();
    }

    /**
     * 录入物流信息
     *
     * @param id 退货申请ID
     * @param dto 录入物流信息 DTO
     * @return 录入成功的响应
     */
    @PutMapping("/{id}/logistics")
    public UpdatedResponseVO updateLogisticsInfo(@PathVariable Integer id, @RequestBody @Valid UpdateLogisticsInfoDTO dto) {
        returnRequestService.updateLogisticsInfo(id, dto.getTrackingNumber(), dto.getLogisticsCompany());
        return new UpdatedResponseVO();
    }

    /**
     * 更新物流状态
     *
     * @param id 退货申请ID
     * @param logisticsStatus 物流状态
     * @return 更新成功的响应
     */
    @PutMapping("/{id}/logistics-status")
    public UpdatedResponseVO updateLogisticsStatus(@PathVariable Integer id, @RequestParam Integer logisticsStatus) {
        returnRequestService.updateLogisticsStatus(id, logisticsStatus);
        return new UpdatedResponseVO();
    }

    /**
     * 确认收货
     *
     * @param id 退货申请ID
     * @return 确认成功的响应
     */
    @PutMapping("/{id}/confirm-receipt")
    public UpdatedResponseVO confirmReceipt(@PathVariable Integer id) {
        returnRequestService.confirmReceipt(id);
        return new UpdatedResponseVO();
    }

    /**
     * 处理退款
     *
     * @param id 退货申请ID
     * @param dto 处理退款 DTO
     * @return 处理成功的响应
     */
    @PutMapping("/{id}/refund")
    public UpdatedResponseVO processRefund(@PathVariable Integer id, @RequestBody @Valid ProcessRefundDTO dto) {
        returnRequestService.processRefund(id, dto.getRefundAmount(), dto.getRefundMethod(), dto.getRefundRemark());
        return new UpdatedResponseVO();
    }

    /**
     * 更新退款状态
     *
     * @param id 退货申请ID
     * @param refundStatus 退款状态
     * @return 更新成功的响应
     */
    @PutMapping("/{id}/refund-status")
    public UpdatedResponseVO updateRefundStatus(@PathVariable Integer id, @RequestParam Integer refundStatus) {
        returnRequestService.updateRefundStatus(id, refundStatus);
        return new UpdatedResponseVO();
    }

    /**
     * 统计退货原因
     *
     * @return 退货原因统计结果
     */
    @GetMapping("/statistics/reasons")
    public Map<String, Long> countReturnReasons() {
        return returnRequestService.countReturnReasons();
    }

    /**
     * 统计退货状态
     *
     * @return 退货状态统计结果
     */
    @GetMapping("/statistics/statuses")
    public Map<Integer, Long> countReturnStatuses() {
        return returnRequestService.countReturnStatuses();
    }

}