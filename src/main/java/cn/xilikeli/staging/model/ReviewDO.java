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
 * 商品评价实体
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025/12/24
 * @since JDK1.8
 */
@Data
@Entity
@Table(name = "review")
@Builder
@Accessors(chain = true)
@DynamicInsert()
@DynamicUpdate()
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Where(clause = "delete_time is null")
@ApiModel(value = "商品评价实体", description = "商品评价实体")
public class ReviewDO extends BaseDO {

    private static final long serialVersionUID = 8853465350295250712L;

    /**
     * 评价 ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "评价 ID")
    private Long id;

    /**
     * 商品 ID
     */
    @ApiModelProperty(value = "商品 ID")
    private Long productId;

    /**
     * 用户 ID
     */
    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    /**
     * 评价评分 (1-5星)
     */
    @ApiModelProperty(value = "评价评分 (1-5星)")
    private Integer score;

    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容")
    private String content;

    /**
     * 评价时间
     */
    @ApiModelProperty(value = "评价时间")
    private Date reviewTime;

}