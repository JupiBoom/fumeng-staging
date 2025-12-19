package cn.xilikeli.staging.controller.v1;

import cn.xilikeli.staging.common.interceptor.ScopeLevel;
import cn.xilikeli.staging.service.NotificationService;
import cn.xilikeli.staging.vo.response.PagingDozerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户通知管理接口控制器
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 * @since JDK1.8
 */
@RestController
@RequestMapping("v1/notification")
@Api(tags = "用户通知管理接口")
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("list")
    @ScopeLevel()
    @ApiOperation(value = "获取用户通知列表", notes = "获取用户通知列表")
    public PagingDozerVO getNotificationList(
            @ApiParam(value = "是否已读: 0-未读, 1-已读, null-全部", required = false) @RequestParam(value = "is_read", required = false) Integer isRead,
            @ApiParam(value = "当前页数", required = true) @RequestParam(value = "page", defaultValue = "0") Integer page,
            @ApiParam(value = "每页通知数", required = true) @RequestParam(value = "count", defaultValue = "10") Integer count) {
        // 获取当前登录用户 ID（假设通过 LocalUser 工具类获取）
        Integer accountId = cn.xilikeli.staging.common.LocalUser.getAccountId();
        return this.notificationService.getNotificationList(accountId, isRead, page, count);
    }

    @PutMapping("{id}/read")
    @ScopeLevel()
    @ApiOperation(value = "标记单个通知为已读", notes = "标记单个通知为已读")
    public void markNotificationAsRead(
            @ApiParam(value = "通知 ID", required = true) @PathVariable("id") Integer notificationId) {
        Integer accountId = cn.xilikeli.staging.common.LocalUser.getAccountId();
        this.notificationService.markNotificationAsRead(accountId, notificationId);
    }

    @PutMapping("batch/read")
    @ScopeLevel()
    @ApiOperation(value = "批量标记通知为已读", notes = "批量标记通知为已读")
    public void batchMarkNotificationAsRead(
            @ApiParam(value = "通知 ID 列表", required = true) @RequestBody List<Integer> notificationIds) {
        Integer accountId = cn.xilikeli.staging.common.LocalUser.getAccountId();
        this.notificationService.batchMarkNotificationAsRead(accountId, notificationIds);
    }

    @DeleteMapping("{id}")
    @ScopeLevel()
    @ApiOperation(value = "删除单个通知", notes = "删除单个通知")
    public void deleteNotification(
            @ApiParam(value = "通知 ID", required = true) @PathVariable("id") Integer notificationId) {
        Integer accountId = cn.xilikeli.staging.common.LocalUser.getAccountId();
        this.notificationService.deleteNotification(accountId, notificationId);
    }

    @DeleteMapping("batch")
    @ScopeLevel()
    @ApiOperation(value = "批量删除通知", notes = "批量删除通知")
    public void batchDeleteNotification(
            @ApiParam(value = "通知 ID 列表", required = true) @RequestBody List<Integer> notificationIds) {
        Integer accountId = cn.xilikeli.staging.common.LocalUser.getAccountId();
        this.notificationService.batchDeleteNotification(accountId, notificationIds);
    }

    @GetMapping("unread/count")
    @ScopeLevel()
    @ApiOperation(value = "获取未读通知数量", notes = "获取未读通知数量")
    public Integer getUnreadNotificationCount() {
        Integer accountId = cn.xilikeli.staging.common.LocalUser.getAccountId();
        return this.notificationService.getUnreadNotificationCount(accountId);
    }

}
