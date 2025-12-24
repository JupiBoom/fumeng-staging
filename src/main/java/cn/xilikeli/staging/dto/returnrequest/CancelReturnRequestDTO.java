package cn.xilikeli.staging.dto.returnrequest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 * 取消退货申请 DTO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class CancelReturnRequestDTO {

    /**
     * 取消原因
     */
    @NotNull(message = "取消原因不能为空")
    @Size(max = 255, message = "取消原因不能超过255个字符")
    private String cancelReason;

}