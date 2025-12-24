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
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 退款记录实体
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "refund_record")
@Builder
@Accessors(chain = true)
@DynamicInsert()
@DynamicUpdate()
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "退款记录实体", description = "退款记录实体")
public class RefundRecordDO extends BaseDO {

    private static final long serialVersionUID = 8853465350295250712L;

    /**
     * 退款记录 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "退款记录 ID")
    private Long id;

    /**
     * 退货申请 ID
     */
    @ApiModelProperty(value = "退货申请 ID")
    private Long returnApplicationId;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    /**
     * 退款状态: 0-待退款, 1-退款中, 2-退款成功, 3-退款失败
     */
    @ApiModelProperty(value = "退款状态: 0-待退款, 1-退款中, 2-退款成功, 3-退款失败")
    private Integer refundStatus;

    /**
     * 退款时间
     */
    @ApiModelProperty(value = "退款时间")
    private Date refundTime;

    /**
     * 退款渠道
     */
    @ApiModelProperty(value = "退款渠道")
    private String refundChannel;

    /**
     * 退款单号
     */
    @ApiModelProperty(value = "退款单号")
    private String refundNo;

    /**
     * 退款备注
     */
    @ApiModelProperty(value = "退款备注")
    private String remark;

}