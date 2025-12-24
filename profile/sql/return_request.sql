-- 创建退货申请表
CREATE TABLE `return_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '退货申请ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `item_id` int(11) NOT NULL COMMENT '商品ID',
  `item_name` varchar(255) NOT NULL COMMENT '商品名称',
  `item_quantity` int(11) NOT NULL COMMENT '商品数量',
  `item_price` decimal(10,2) NOT NULL COMMENT '商品单价',
  `return_amount` decimal(10,2) NOT NULL COMMENT '退货金额',
  `reason` varchar(255) NOT NULL COMMENT '退货原因',
  `remark` varchar(500) DEFAULT NULL COMMENT '退货说明',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '申请状态: 0-待审核, 1-审核通过, 2-审核拒绝, 3-已取消, 4-已退货, 5-已完成',
  `auditor_id` int(11) DEFAULT NULL COMMENT '审核人ID',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_opinion` varchar(500) DEFAULT NULL COMMENT '审核意见',
  `tracking_number` varchar(50) DEFAULT NULL COMMENT '物流单号',
  `logistics_company` varchar(50) DEFAULT NULL COMMENT '物流公司',
  `logistics_status` int(11) DEFAULT '0' COMMENT '物流状态: 0-待发货, 1-已发货, 2-运输中, 3-已签收, 4-已拒收',
  `confirm_time` datetime DEFAULT NULL COMMENT '收货确认时间',
  `refund_status` int(11) DEFAULT '0' COMMENT '退款状态: 0-待退款, 1-退款中, 2-退款成功, 3-退款失败',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `refund_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `refund_method` int(11) DEFAULT NULL COMMENT '退款方式: 0-原路返回, 1-余额',
  `refund_remark` varchar(500) DEFAULT NULL COMMENT '退款备注',
  `cancel_reason` varchar(255) DEFAULT NULL COMMENT '取消原因',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `evidence_images` varchar(500) DEFAULT NULL COMMENT '图片凭证',
  `return_address` varchar(255) NOT NULL COMMENT '收货地址',
  `receiver` varchar(50) NOT NULL COMMENT '收货人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_item_id` (`item_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货申请表';

-- 创建索引
CREATE INDEX idx_user_id ON return_request(user_id);
CREATE INDEX idx_order_id ON return_request(order_id);
CREATE INDEX idx_item_id ON return_request(item_id);
CREATE INDEX idx_status ON return_request(status);
CREATE INDEX idx_create_time ON return_request(create_time);
