package cn.xilikeli.staging.model;

import lombok.*;

import javax.persistence.*;

/**
 * <p>
 * 用户收藏表
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favorite")
public class FavoriteDO extends BaseDO {

    /**
     * 收藏记录 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户 ID
     */
    private Integer accountId;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 商品类型
     */
    private String productType;

}
