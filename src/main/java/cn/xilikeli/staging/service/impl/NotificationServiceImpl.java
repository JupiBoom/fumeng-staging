package cn.xilikeli.staging.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xilikeli.staging.model.NotificationDO;
import cn.xilikeli.staging.model.FavoriteDO;
import cn.xilikeli.staging.model.BookDO;
import cn.xilikeli.staging.repository.NotificationRepository;
import cn.xilikeli.staging.repository.FavoriteRepository;
import cn.xilikeli.staging.repository.BookRepository;
import cn.xilikeli.staging.service.NotificationService;
import cn.xilikeli.staging.vo.response.PagingDozerVO;
import cn.xilikeli.staging.common.util.JpaPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户通知服务类实现类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2025-05-19
 * @since JDK1.8
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private FavoriteRepository favoriteRepository;
    private BookRepository bookRepository;

    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Autowired
    public void setFavoriteRepository(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void sendPriceDownNotification(Long bookId, Integer oldPrice, Integer newPrice) {
        BookDO bookDO = this.bookRepository.findById(bookId).orElse(null);
        if (bookDO == null) {
            return;
        }

        // 获取所有收藏该商品的用户
        List<FavoriteDO> favoriteDOList = this.favoriteRepository.findByProductId(bookId);
        for (FavoriteDO favoriteDO : favoriteDOList) {
            NotificationDO notificationDO = NotificationDO.builder()
                    .accountId(favoriteDO.getAccountId())
                    .productId(bookId)
                    .productType(favoriteDO.getProductType())
                    .type("PRICE_DOWN")
                    .content(String.format("您收藏的商品\"%s\"降价了！原价%d元，现价%d元。", 
                            bookDO.getTitle(), oldPrice, newPrice))
                    .isRead(0)
                    .build();
            this.notificationRepository.save(notificationDO);
        }
    }

    @Override
    public void sendStockRecoverNotification(Long bookId) {
        BookDO bookDO = this.bookRepository.findById(bookId).orElse(null);
        if (bookDO == null) {
            return;
        }

        // 获取所有收藏该商品的用户
        List<FavoriteDO> favoriteDOList = this.favoriteRepository.findByProductId(bookId);
        for (FavoriteDO favoriteDO : favoriteDOList) {
            NotificationDO notificationDO = NotificationDO.builder()
                    .accountId(favoriteDO.getAccountId())
                    .productId(bookId)
                    .productType(favoriteDO.getProductType())
                    .type("STOCK_RECOVER")
                    .content(String.format("您收藏的商品\"%s\"库存已恢复，赶紧去购买吧！", bookDO.getTitle()))
                    .isRead(0)
                    .build();
            this.notificationRepository.save(notificationDO);
        }
    }

    @Override
    public void sendPromotionNotification(Long bookId, String promotion) {
        BookDO bookDO = this.bookRepository.findById(bookId).orElse(null);
        if (bookDO == null) {
            return;
        }

        // 获取所有收藏该商品的用户
        List<FavoriteDO> favoriteDOList = this.favoriteRepository.findByProductId(bookId);
        for (FavoriteDO favoriteDO : favoriteDOList) {
            NotificationDO notificationDO = NotificationDO.builder()
                    .accountId(favoriteDO.getAccountId())
                    .productId(bookId)
                    .productType(favoriteDO.getProductType())
                    .type("PROMOTION")
                    .content(String.format("您收藏的商品\"%s\"正在进行促销活动：%s", bookDO.getTitle(), promotion))
                    .isRead(0)
                    .build();
            this.notificationRepository.save(notificationDO);
        }
    }

    @Override
    public PagingDozerVO getNotificationList(Integer accountId, Integer isRead, Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<NotificationDO> notificationDOPage;

        if (isRead == null) {
            notificationDOPage = this.notificationRepository.findAllByAccountId(accountId, pageable);
        } else {
            notificationDOPage = this.notificationRepository.findAllByAccountIdAndIsRead(accountId, isRead, pageable);
        }

        return JpaPageUtil.convertToPagingDozerVO(notificationDOPage, NotificationDO.class);
    }

    @Override
    public void markNotificationAsRead(Integer accountId, Integer notificationId) {
        NotificationDO notificationDO = this.notificationRepository
                .findById(notificationId)
                .orElse(null);
        if (notificationDO != null && notificationDO.getAccountId().equals(accountId)) {
            notificationDO.setIsRead(1);
            this.notificationRepository.save(notificationDO);
        }
    }

    @Override
    public void batchMarkNotificationAsRead(Integer accountId, List<Integer> notificationIds) {
        this.notificationRepository.updateIsReadByIdIn(accountId, notificationIds, 1);
    }

    @Override
    public void deleteNotification(Integer accountId, Integer notificationId) {
        NotificationDO notificationDO = this.notificationRepository
                .findById(notificationId)
                .orElse(null);
        if (notificationDO != null && notificationDO.getAccountId().equals(accountId)) {
            this.notificationRepository.delete(notificationDO.getId());
        }
    }

    @Override
    public void batchDeleteNotification(Integer accountId, List<Integer> notificationIds) {
        List<NotificationDO> notificationDOList = this.notificationRepository.findAllById(notificationIds);
        List<Integer> idsToDelete = notificationDOList.stream()
                .filter(notificationDO -> notificationDO.getAccountId().equals(accountId))
                .map(NotificationDO::getId)
                .collect(Collectors.toList());
        this.notificationRepository.deleteByIdIn(idsToDelete);
    }

    @Override
    public Integer getUnreadNotificationCount(Integer accountId) {
        return this.notificationRepository.countByAccountIdAndIsRead(accountId, 0);
    }

}