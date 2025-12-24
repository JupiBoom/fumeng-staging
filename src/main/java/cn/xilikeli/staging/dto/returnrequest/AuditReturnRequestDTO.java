package cn.xilikeli.staging.dto.returnrequest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 * 审核退货申请 DTO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class AuditReturnRequestDTO {

    /**
     * 审核人ID
     */
    @NotNull(message = "审核人ID不能为空")
    private Integer auditorId;

    /**
     * 审核状态
     */
    @NotNull(message = "审核状态不能为空")
    private Integer status;

    /**
     * 审核意见
     */
    @Size(max = 500, message = "审核意见不能超过500个字符")
    private String auditOpinion;

}