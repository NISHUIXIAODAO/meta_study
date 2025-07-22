-- 创建用户与agent聊天记录表
CREATE TABLE IF NOT EXISTS `user_agent_chat_memory` (
  `memory_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`memory_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与agent聊天记录表';