package cn.xilikeli.staging.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 退货物流实体
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "return_logistics")
@Builder
@Accessors(chain = true)
@DynamicInsert()
@DynamicUpdate()
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "退货物流实体", description = "退货物流实体")
public class ReturnLogisticsDO extends BaseDO {

    private static final long serialVersionUID = 8853465350295250712L;

    /**
     * 物流 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "物流 ID")
    private Long id;

    /**
     * 退货申请 ID
     */
    @ApiModelProperty(value = "退货申请 ID")
    private Long returnApplicationId;

    /**
     * 物流公司
     */
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;

    /**
     * 物流单号
     */
    @ApiModelProperty(value = "物流单号")
    private String logisticsNo;

    /**
     * 物流状态: 0-待发货, 1-已发货, 2-运输中, 3-已签收
     */
    @ApiModelProperty(value = "物流状态: 0-待发货, 1-已发货, 2-运输中, 3-已签收")
    private Integer logisticsStatus;

    /**
     * 发货人姓名
     */
    @ApiModelProperty(value = "发货人姓名")
    private String senderName;

    /**
     * 发货人手机号
     */
    @ApiModelProperty(value = "发货人手机号")
    private String senderMobile;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    /**
     * 收货人手机号
     */
    @ApiModelProperty(value = "收货人手机号")
    private String receiverMobile;

    /**
     * 收货人地址
     */
    @ApiModelProperty(value = "收货人地址")
    private String receiverAddress;

    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间")
    private Date shippingTime;

    /**
     * 收货时间
     */
    @ApiModelProperty(value = "收货时间")
    private Date receiveTime;

}