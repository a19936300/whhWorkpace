-- 创建用户表
CREATE TABLE t_user (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255),
      password VARCHAR(255),
      nickname VARCHAR(255)
);

-- 插入测试数据
INSERT INTO t_user (name, password, nickname) VALUES ('John Doe', 'password123', 'JD');
INSERT INTO t_user (name, password, nickname) VALUES ('Jane Smith', 'pass456', 'Jane');
