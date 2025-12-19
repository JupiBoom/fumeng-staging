package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.LocalUser;
import cn.xilikeli.staging.dto.returnorder.AuditReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.CreateReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.LogisticsInfoDTO;
import cn.xilikeli.staging.dto.returnorder.RefundDTO;
import cn.xilikeli.staging.service.ReturnOrderService;
import cn.xilikeli.staging.vo.returnorder.ReturnOrderVO;
import cn.xilikeli.staging.vo.returnorder.ReturnReasonStatsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 退货订单管理控制器
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Slf4j
@RestController
@RequestMapping("v1/return-order")
@Api(tags = "退货订单管理")
@Validated
public class ReturnOrderController {

    private final ReturnOrderService returnOrderService;

    public ReturnOrderController(ReturnOrderService returnOrderService) {
        this.returnOrderService = returnOrderService;
    }

    @PostMapping("/")
    @ApiOperation(value = "用户提交退货申请")
    public ReturnOrderVO submitReturnOrder(
            @Validated @RequestBody CreateReturnOrderDTO createReturnOrderDTO
    ) {
        Long userId = LocalUser.getLocalUser().getId();
        return returnOrderService.submitReturnOrder(userId, createReturnOrderDTO);
    }

    @PutMapping("/{id}/cancel")
    @ApiOperation(value = "用户取消退货申请")
    public ReturnOrderVO cancelReturnOrder(
            @ApiParam(value = "退货订单 ID", required = true) @PathVariable(name = "id") @NotNull(message = "退货订单 ID 不能为空") Long id
    ) {
        Long userId = LocalUser.getLocalUser().getId();
        return returnOrderService.cancelReturnOrder(userId, id);
    }

    @PutMapping("/{id}/audit")
    @ApiOperation(value = "商家审核退货申请")
    public ReturnOrderVO auditReturnOrder(
            @ApiParam(value = "退货订单 ID", required = true) @PathVariable(name = "id") @NotNull(message = "退货订单 ID 不能为空") Long id,
            @Validated @RequestBody AuditReturnOrderDTO auditReturnOrderDTO
    ) {
        return returnOrderService.auditReturnOrder(id, auditReturnOrderDTO);
    }

    @PutMapping("/{id}/logistics")
    @ApiOperation(value = "用户录入物流信息")
    public ReturnOrderVO updateLogisticsInfo(
            @ApiParam(value = "退货订单 ID", required = true) @PathVariable(name = "id") @NotNull(message = "退货订单 ID 不能为空") Long id,
            @Validated @RequestBody LogisticsInfoDTO logisticsInfoDTO
    ) {
        Long userId = LocalUser.getLocalUser().getId();
        return returnOrderService.updateLogisticsInfo(userId, id, logisticsInfoDTO);
    }

    @PutMapping("/{id}/confirm-receipt")
    @ApiOperation(value = "商家确认收货")
    public ReturnOrderVO confirmReceipt(
            @ApiParam(value = "退货订单 ID", required = true) @PathVariable(name = "id") @NotNull(message = "退货订单 ID 不能为空") Long id
    ) {
        return returnOrderService.confirmReceipt(id);
    }

    @PutMapping("/{id}/refund")
    @ApiOperation(value = "商家处理退款")
    public ReturnOrderVO processRefund(
            @ApiParam(value = "退货订单 ID", required = true) @PathVariable(name = "id") @NotNull(message = "退货订单 ID 不能为空") Long id,
            @Validated @RequestBody RefundDTO refundDTO
    ) {
        return returnOrderService.processRefund(id, refundDTO);
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取当前用户的退货订单列表")
    public List<ReturnOrderVO> getReturnOrderListByUserId() {
        Long userId = LocalUser.getLocalUser().getId();
        return returnOrderService.getReturnOrderListByUserId(userId);
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有退货订单列表")
    public List<ReturnOrderVO> getAllReturnOrderList() {
        return returnOrderService.getAllReturnOrderList();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据 ID 获取退货订单详情")
    public ReturnOrderVO getReturnOrderById(
            @ApiParam(value = "退货订单 ID", required = true) @PathVariable(name = "id") @NotNull(message = "退货订单 ID 不能为空") Long id
    ) {
        return returnOrderService.convertToReturnOrderVO(returnOrderService.getReturnOrderById(id));
    }

    @GetMapping("/stats/reason")
    @ApiOperation(value = "获取退货原因统计分析")
    public List<ReturnReasonStatsVO> getReturnReasonStats() {
        return returnOrderService.getReturnReasonStats();
    }

}
