package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.constant.business.AccountCodeConstant;
import cn.xilikeli.staging.common.interceptor.ScopeLevel;
import cn.xilikeli.staging.dto.return_exchange.ReturnApplicationDTO;
import cn.xilikeli.staging.dto.return_exchange.ReturnLogisticsDTO;
import cn.xilikeli.staging.dto.return_exchange.RefundRecordDTO;
import cn.xilikeli.staging.model.ReturnApplicationDO;
import cn.xilikeli.staging.model.ReturnLogisticsDO;
import cn.xilikeli.staging.model.RefundRecordDO;
import cn.xilikeli.staging.service.ReturnExchangeService;
import cn.xilikeli.staging.vo.response.CreatedResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退换货 API 接口控制器
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Validated
@RestController
@RequestMapping("/v1/return-exchange")
@Api(value = "退换货 API 接口", tags = {"退换货业务的相关接口"})
public class ReturnExchangeController {

    private ReturnExchangeService returnExchangeService;

    @Autowired
    public void setReturnExchangeService(ReturnExchangeService returnExchangeService) {
        this.returnExchangeService = returnExchangeService;
    }

    /**
     * 用户提交退货申请
     *
     * @param returnApplicationDTO 退货申请信息 DTO
     * @return 退货申请信息
     */
    @ScopeLevel
    @PostMapping("/application")
    @ApiOperation(value = "提交退货申请接口", notes = "用户提交退货申请", httpMethod = "POST")
    public CreatedResponseVO<ReturnApplicationDO> submitReturnApplication(
            @Validated
            @RequestBody ReturnApplicationDTO returnApplicationDTO) {
        ReturnApplicationDO returnApplicationDO = this.returnExchangeService.submitReturnApplication(returnApplicationDTO);
        return new CreatedResponseVO<>(AccountCodeConstant.REGISTER_SUCCESS, returnApplicationDO);
    }

    /**
     * 用户取消退货申请
     *
     * @param applicationId 退货申请 ID
     * @return 取消结果
     */
    @ScopeLevel
    @PutMapping("/application/{applicationId}/cancel")
    @ApiOperation(value = "取消退货申请接口", notes = "用户取消退货申请", httpMethod = "PUT")
    public CreatedResponseVO<Boolean> cancelReturnApplication(
            @PathVariable Long applicationId) {
        boolean result = this.returnExchangeService.cancelReturnApplication(applicationId);
        return new CreatedResponseVO<>(AccountCodeConstant.UPDATE_ACCOUNT_SUCCESS, result);
    }

    /**
     * 商家审核退货申请
     *
     * @param applicationId 退货申请 ID
     * @param status 审核状态: 1-通过, 2-拒绝
     * @param auditUser 审核人
     * @param auditRemark 审核备注
     * @return 审核结果
     */
    @ScopeLevel
    @PutMapping("/application/{applicationId}/audit")
    @ApiOperation(value = "审核退货申请接口", notes = "商家审核退货申请", httpMethod = "PUT")
    public CreatedResponseVO<Boolean> auditReturnApplication(
            @PathVariable Long applicationId,
            @RequestParam Integer status,
            @RequestParam String auditUser,
            @RequestParam(required = false) String auditRemark) {
        boolean result = this.returnExchangeService.auditReturnApplication(applicationId, status, auditUser, auditRemark);
        return new CreatedResponseVO<>(AccountCodeConstant.UPDATE_ACCOUNT_SUCCESS, result);
    }

    /**
     * 录入退货物流信息
     *
     * @param returnLogisticsDTO 退货物流信息 DTO
     * @return 退货物流信息
     */
    @ScopeLevel
    @PostMapping("/logistics")
    @ApiOperation(value = "录入退货物流信息接口", notes = "录入退货物流信息", httpMethod = "POST")
    public CreatedResponseVO<ReturnLogisticsDO> addReturnLogistics(
            @Validated
            @RequestBody ReturnLogisticsDTO returnLogisticsDTO) {
        ReturnLogisticsDO returnLogisticsDO = this.returnExchangeService.addReturnLogistics(returnLogisticsDTO);
        return new CreatedResponseVO<>(AccountCodeConstant.REGISTER_SUCCESS, returnLogisticsDO);
    }

    /**
     * 确认收货
     *
     * @param applicationId 退货申请 ID
     * @return 确认结果
     */
    @ScopeLevel
    @PutMapping("/application/{applicationId}/confirm-receive")
    @ApiOperation(value = "确认收货接口", notes = "商家确认收到退货商品", httpMethod = "PUT")
    public CreatedResponseVO<Boolean> confirmReceiveGoods(
            @PathVariable Long applicationId) {
        boolean result = this.returnExchangeService.confirmReceiveGoods(applicationId);
        return new CreatedResponseVO<>(AccountCodeConstant.UPDATE_ACCOUNT_SUCCESS, result);
    }

    /**
     * 处理退款
     *
     * @param refundRecordDTO 退款记录信息 DTO
     * @return 退款记录信息
     */
    @ScopeLevel
    @PostMapping("/refund")
    @ApiOperation(value = "处理退款接口", notes = "商家处理退款", httpMethod = "POST")
    public CreatedResponseVO<RefundRecordDO> processRefund(
            @Validated
            @RequestBody RefundRecordDTO refundRecordDTO) {
        RefundRecordDO refundRecordDO = this.returnExchangeService.processRefund(refundRecordDTO);
        return new CreatedResponseVO<>(AccountCodeConstant.REGISTER_SUCCESS, refundRecordDO);
    }

    /**
     * 根据用户 ID 获取退货申请列表
     *
     * @param accountId 用户 ID
     * @return 退货申请列表
     */
    @ScopeLevel
    @GetMapping("/application/account/{accountId}")
    @ApiOperation(value = "根据用户 ID 获取退货申请列表接口", notes = "根据用户 ID 获取退货申请列表", httpMethod = "GET")
    public List<ReturnApplicationDO> getReturnApplicationsByAccountId(
            @PathVariable Long accountId) {
        return this.returnExchangeService.getReturnApplicationsByAccountId(accountId);
    }

    /**
     * 根据状态获取退货申请列表
     *
     * @param status 申请状态
     * @return 退货申请列表
     */
    @ScopeLevel
    @GetMapping("/application/status/{status}")
    @ApiOperation(value = "根据状态获取退货申请列表接口", notes = "根据状态获取退货申请列表", httpMethod = "GET")
    public List<ReturnApplicationDO> getReturnApplicationsByStatus(
            @PathVariable Integer status) {
        return this.returnExchangeService.getReturnApplicationsByStatus(status);
    }

    /**
     * 获取退货原因统计分析
     *
     * @return 退货原因统计分析结果
     */
    @ScopeLevel
    @GetMapping("/statistics/reason")
    @ApiOperation(value = "获取退货原因统计分析接口", notes = "获取退货原因统计分析", httpMethod = "GET")
    public Map<String, Long> getReturnReasonStatistics() {
        return this.returnExchangeService.getReturnReasonStatistics();
    }

}