package cn.xilikeli.staging.dto.return_exchange;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 退货物流信息 DTO
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "退货物流信息 DTO", description = "退货物流信息 DTO")
public class ReturnLogisticsDTO {

    /**
     * 退货申请 ID
     */
    @NotNull(message = "退货申请 ID 不能为空")
    @ApiModelProperty(value = "退货申请 ID", required = true, example = "1")
    private Long returnApplicationId;

    /**
     * 物流公司
     */
    @NotBlank(message = "物流公司 不能为空")
    @ApiModelProperty(value = "物流公司", required = true, example = "顺丰快递")
    private String logisticsCompany;

    /**
     * 物流单号
     */
    @NotBlank(message = "物流单号 不能为空")
    @ApiModelProperty(value = "物流单号", required = true, example = "SF1234567890")
    private String logisticsNo;

    /**
     * 发货人姓名
     */
    @NotBlank(message = "发货人姓名 不能为空")
    @ApiModelProperty(value = "发货人姓名", required = true, example = "张三")
    private String senderName;

    /**
     * 发货人手机号
     */
    @NotBlank(message = "发货人手机号 不能为空")
    @ApiModelProperty(value = "发货人手机号", required = true, example = "13800138000")
    private String senderMobile;

    /**
     * 收货人姓名
     */
    @NotBlank(message = "收货人姓名 不能为空")
    @ApiModelProperty(value = "收货人姓名", required = true, example = "李四")
    private String receiverName;

    /**
     * 收货人手机号
     */
    @NotBlank(message = "收货人手机号 不能为空")
    @ApiModelProperty(value = "收货人手机号", required = true, example = "13900139000")
    private String receiverMobile;

    /**
     * 收货人地址
     */
    @NotBlank(message = "收货人地址 不能为空")
    @ApiModelProperty(value = "收货人地址", required = true, example = "北京市朝阳区某某街道")
    private String receiverAddress;

}