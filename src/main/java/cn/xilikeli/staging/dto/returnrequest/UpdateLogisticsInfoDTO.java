package cn.xilikeli.staging.dto.returnrequest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 * 录入物流信息 DTO
 * </p>
 *
 * @author xilikeli
 * @since 2025-12-24
 */
@Data
public class UpdateLogisticsInfoDTO {

    /**
     * 物流单号
     */
    @NotNull(message = "物流单号不能为空")
    @Size(max = 50, message = "物流单号不能超过50个字符")
    private String trackingNumber;

    /**
     * 物流公司
     */
    @NotNull(message = "物流公司不能为空")
    @Size(max = 50, message = "物流公司不能超过50个字符")
    private String logisticsCompany;

}