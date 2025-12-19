package cn.xilikeli.staging.dto.favorite;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * <p>
 * 批量取消收藏 DTO
 * </p>
 *
 * @author AI Assistant
 * @version 1.0
 * @date 2025/5/20
 * @since JDK1.8
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "批量取消收藏 DTO", description = "批量取消收藏 DTO")
public class BatchRemoveFavoriteDTO {

    /**
     * 收藏记录 ID 列表
     */
    @NotEmpty(message = "{favorite.ids.not-empty}")
    @ApiModelProperty(value = "收藏记录 ID 列表", required = true)
    private List<@Positive(message = "{favorite.id.positive}") Long> favoriteIds;

}