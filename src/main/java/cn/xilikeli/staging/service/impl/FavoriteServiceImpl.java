package cn.xilikeli.staging.service.impl;

import cn.xilikeli.staging.common.LocalUser;
import cn.xilikeli.staging.common.exception.http.DuplicatedException;
import cn.xilikeli.staging.common.exception.http.NotFoundException;
import cn.xilikeli.staging.model.FavoriteDO;
import cn.xilikeli.staging.model.BookDO;
import cn.xilikeli.staging.repository.FavoriteRepository;
import cn.xilikeli.staging.repository.BookRepository;
import cn.xilikeli.staging.service.FavoriteService;
import cn.xilikeli.staging.vo.book.BookSampleVO;
import cn.xilikeli.staging.vo.response.PagingDozerVO;
import cn.xilikeli.staging.common.util.JpaPageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户收藏业务操作实现类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final BookRepository bookRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, BookRepository bookRepository) {
        this.favoriteRepository = favoriteRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFavorite(Long productId, String productType) {
        Integer uid = LocalUser.getLocalUser().getId();

        // 检查是否已经收藏过该商品
        favoriteRepository.findByAccountIdAndProductId(uid, productId)
                .ifPresent(favorite -> {
                    throw new DuplicatedException(20040);
                });

        // 创建收藏记录
        FavoriteDO favorite = FavoriteDO.builder()
                .accountId(uid)
                .productId(productId)
                .productType(productType)
                .build();

        favoriteRepository.save(favorite);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeFavorite(Long productId) {
        Integer uid = LocalUser.getLocalUser().getId();

        // 删除收藏记录
        Integer deleteCount = favoriteRepository.deleteByAccountIdAndProductId(uid, productId);
        if (deleteCount == 0) {
            throw new NotFoundException(20041);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchRemoveFavorite(List<Long> productIds) {
        Integer uid = LocalUser.getLocalUser().getId();

        // 批量删除收藏记录
        Integer deleteCount = favoriteRepository.deleteByAccountIdAndProductIdIn(uid, productIds);
        if (deleteCount == 0) {
            throw new NotFoundException(20041);
        }
    }

    @Override
    public PagingDozerVO getFavoriteList(Integer start, Integer count) {
        Integer uid = LocalUser.getLocalUser().getId();

        // 按收藏时间倒序排序
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(start, count, sort);

        // 查询用户收藏列表
        Page<FavoriteDO> favoritePage = favoriteRepository.findAllByAccountId(uid, pageRequest);

        // 根据收藏记录中的商品 ID 查询对应的图书信息
        List<Long> bookIds = favoritePage.getContent().stream()
                .map(FavoriteDO::getProductId)
                .collect(Collectors.toList());

        List<BookDO> bookList = bookRepository.findAllById(bookIds);

        // 将 BookDO 转换为 BookSampleVO
        List<BookSampleVO> bookSampleVOList = bookList.stream()
                .map(book -> BookSampleVO.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .image(book.getImage())
                        .build())
                .collect(Collectors.toList());

        // 创建新的 Page 对象
        Page<BookSampleVO> bookPage = new PageImpl<>(bookSampleVOList, pageRequest, favoritePage.getTotalElements());

        // 转换为 VO 并返回
        return JpaPageUtil.convertToPagingDozerVO(bookPage, BookSampleVO.class);
    }

    @Override
    public boolean checkIsFavorite(Long productId) {
        Integer uid = LocalUser.getLocalUser().getId();

        return favoriteRepository.findByAccountIdAndProductId(uid, productId)
                .isPresent();
    }

}
