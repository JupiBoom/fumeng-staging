package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.dto.returnorder.AuditReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.CreateReturnOrderDTO;
import cn.xilikeli.staging.dto.returnorder.LogisticsInfoDTO;
import cn.xilikeli.staging.service.ReturnOrderService;
import cn.xilikeli.staging.vo.returnorder.ReturnOrderVO;
import cn.xilikeli.staging.vo.returnorder.ReturnReasonStatisticsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 退货订单控制器
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2024/05/20
 * @since JDK1.8
 */
@Slf4j
@RestController
@RequestMapping("/v1/return-order")
@Api(value = "退货订单相关接口", tags = "退货订单相关接口")
@Validated
public class ReturnOrderController {

    @Resource
    private ReturnOrderService returnOrderService;

    /**
     * 用户提交退货申请
     */
    @PostMapping("/submit")
    @ApiOperation(value = "提交退货申请", notes = "用户提交退货申请")
    public ResponseEntity<ReturnOrderVO> submitReturnOrder(
            @Validated @RequestBody CreateReturnOrderDTO createReturnOrderDTO) {
        // TODO: 从 LocalUser 或 token 中获取用户 ID
        Integer userId = 1; // 临时硬编码，实际应从认证信息中获取
        ReturnOrderVO returnOrderVO = returnOrderService.submitReturnOrder(userId, createReturnOrderDTO);
        return ResponseEntity.ok(returnOrderVO);
    }

    /**
     * 用户取消退货申请
     */
    @DeleteMapping("/cancel/{id}")
    @ApiOperation(value = "取消退货申请", notes = "用户取消退货申请")
    @ApiImplicitParam(name = "id", value = "退货订单 ID", required = true, dataType = "Long", paramType = "path")
    public ResponseEntity<Void> cancelReturnOrder(@PathVariable @NotNull(message = "退货订单 ID 不能为空") Long id) {
        // TODO: 从 LocalUser 或 token 中获取用户 ID
        Integer userId = 1; // 临时硬编码，实际应从认证信息中获取
        returnOrderService.cancelReturnOrder(userId, id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 商家审核退货申请
     */
    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核退货申请", notes = "商家审核退货申请")
    @ApiImplicitParam(name = "id", value = "退货订单 ID", required = true, dataType = "Long", paramType = "path")
    public ResponseEntity<Void> auditReturnOrder(
            @PathVariable @NotNull(message = "退货订单 ID 不能为空") Long id,
            @Validated @RequestBody AuditReturnOrderDTO auditReturnOrderDTO) {
        returnOrderService.auditReturnOrder(id, auditReturnOrderDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 用户录入退货物流信息
     */
    @PutMapping("/logistics/{id}")
    @ApiOperation(value = "录入物流信息", notes = "用户录入退货物流信息")
    @ApiImplicitParam(name = "id", value = "退货订单 ID", required = true, dataType = "Long", paramType = "path")
    public ResponseEntity<Void> updateLogisticsInfo(
            @PathVariable @NotNull(message = "退货订单 ID 不能为空") Long id,
            @Validated @RequestBody LogisticsInfoDTO logisticsInfoDTO) {
        // TODO: 从 LocalUser 或 token 中获取用户 ID
        Integer userId = 1; // 临时硬编码，实际应从认证信息中获取
        returnOrderService.updateLogisticsInfo(userId, id, logisticsInfoDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * 商家确认收货
     */
    @PutMapping("/confirm-receipt/{id}")
    @ApiOperation(value = "确认收货", notes = "商家确认收到退货商品")
    @ApiImplicitParam(name = "id", value = "退货订单 ID", required = true, dataType = "Long", paramType = "path")
    public ResponseEntity<Void> confirmReceipt(@PathVariable @NotNull(message = "退货订单 ID 不能为空") Long id) {
        returnOrderService.confirmReceipt(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 商家处理退款
     */
    @PutMapping("/process-refund/{id}")
    @ApiOperation(value = "处理退款", notes = "商家处理退货退款")
    @ApiImplicitParam(name = "id", value = "退货订单 ID", required = true, dataType = "Long", paramType = "path")
    public ResponseEntity<Void> processRefund(@PathVariable @NotNull(message = "退货订单 ID 不能为空") Long id) {
        returnOrderService.processRefund(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 根据退货订单 ID 查询退货订单详情
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询退货订单详情", notes = "根据退货订单 ID 查询退货订单详情")
    @ApiImplicitParam(name = "id", value = "退货订单 ID", required = true, dataType = "Long", paramType = "path")
    public ResponseEntity<ReturnOrderVO> getReturnOrderById(@PathVariable @NotNull(message = "退货订单 ID 不能为空") Long id) {
        ReturnOrderVO returnOrderVO = returnOrderService.getReturnOrderById(id);
        return ResponseEntity.ok(returnOrderVO);
    }

    /**
     * 查询当前用户的退货订单列表
     */
    @GetMapping("/user/list")
    @ApiOperation(value = "查询用户退货订单列表", notes = "查询当前用户的退货订单列表")
    public ResponseEntity<List<ReturnOrderVO>> getReturnOrderListByUserId() {
        // TODO: 从 LocalUser 或 token 中获取用户 ID
        Integer userId = 1; // 临时硬编码，实际应从认证信息中获取
        List<ReturnOrderVO> returnOrderVOList = returnOrderService.getReturnOrderListByUserId(userId);
        return ResponseEntity.ok(returnOrderVOList);
    }

    /**
     * 查询所有退货订单列表（商家端）
     */
    @GetMapping("/all/list")
    @ApiOperation(value = "查询所有退货订单列表", notes = "查询所有退货订单列表（商家端）")
    public ResponseEntity<List<ReturnOrderVO>> getAllReturnOrderList() {
        List<ReturnOrderVO> returnOrderVOList = returnOrderService.getAllReturnOrderList();
        return ResponseEntity.ok(returnOrderVOList);
    }

    /**
     * 退货原因统计分析
     */
    @GetMapping("/statistics/reason")
    @ApiOperation(value = "退货原因统计分析", notes = "统计分析退货原因")
    public ResponseEntity<List<ReturnReasonStatisticsVO>> getReturnReasonStatistics() {
        List<ReturnReasonStatisticsVO> statisticsVOList = returnOrderService.getReturnReasonStatistics();
        return ResponseEntity.ok(statisticsVOList);
    }
}
