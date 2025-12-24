-- ----------------------------
-- 退货申请表
-- ----------------------------
DROP TABLE IF EXISTS `return_application`;
CREATE TABLE `return_application`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '退货申请 ID, 自增主键',
    `order_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '订单 ID',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL COMMENT '用户 ID',
    `product_id`  BIGINT(20) UNSIGNED NOT NULL COMMENT '商品 ID',
    `product_name` VARCHAR(100) NOT NULL COMMENT '商品名称',
    `product_image` VARCHAR(255) NOT NULL COMMENT '商品图片',
    `quantity`    INT(10) UNSIGNED NOT NULL COMMENT '退货数量',
    `reason`      VARCHAR(200) NOT NULL COMMENT '退货原因',
    `description` TEXT COMMENT '退货描述',
    `status`      TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '申请状态: 0-待审核, 1-审核通过, 2-审核拒绝, 3-已取消, 4-已完成',
    `apply_time`  DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '申请时间',
    `audit_time`  DATETIME(3) DEFAULT NULL COMMENT '审核时间',
    `audit_user`  VARCHAR(50) COMMENT '审核人',
    `audit_remark` TEXT COMMENT '审核备注',
    `create_time` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `update_time` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    `delete_time` DATETIME(3) DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_account_id` (`account_id`),
    KEY `idx_status` (`status`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '退货申请表';

-- ----------------------------
-- 退货物流表
-- ----------------------------
DROP TABLE IF EXISTS `return_logistics`;
CREATE TABLE `return_logistics`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物流 ID, 自增主键',
    `return_application_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '退货申请 ID',
    `logistics_company` VARCHAR(50) NOT NULL COMMENT '物流公司',
    `logistics_no`      VARCHAR(50) NOT NULL COMMENT '物流单号',
    `logistics_status`  TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '物流状态: 0-待发货, 1-已发货, 2-运输中, 3-已签收',
    `sender_name`       VARCHAR(50) COMMENT '发货人姓名',
    `sender_mobile`     VARCHAR(30) COMMENT '发货人手机号',
    `receiver_name`     VARCHAR(50) COMMENT '收货人姓名',
    `receiver_mobile`   VARCHAR(30) COMMENT '收货人手机号',
    `receiver_address`  VARCHAR(200) COMMENT '收货人地址',
    `shipping_time`     DATETIME(3) DEFAULT NULL COMMENT '发货时间',
    `receive_time`      DATETIME(3) DEFAULT NULL COMMENT '收货时间',
    `create_time`       DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `update_time`       DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    `delete_time`       DATETIME(3) DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    KEY `idx_return_application_id` (`return_application_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '退货物流表';

-- ----------------------------
-- 退款记录表
-- ----------------------------
DROP TABLE IF EXISTS `refund_record`;
CREATE TABLE `refund_record`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '退款记录 ID, 自增主键',
    `return_application_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '退货申请 ID',
    `refund_amount`     DECIMAL(10,2) NOT NULL COMMENT '退款金额',
    `refund_status`     TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '退款状态: 0-待退款, 1-退款中, 2-退款成功, 3-退款失败',
    `refund_time`       DATETIME(3) DEFAULT NULL COMMENT '退款时间',
    `refund_channel`    VARCHAR(50) COMMENT '退款渠道',
    `refund_no`         VARCHAR(100) COMMENT '退款单号',
    `remark`            TEXT COMMENT '退款备注',
    `create_time`       DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `update_time`       DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    `delete_time`       DATETIME(3) DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    KEY `idx_return_application_id` (`return_application_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT = '退款记录表';