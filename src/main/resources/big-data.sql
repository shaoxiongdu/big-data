/*
 *   Copyright ShaoxiongDu <email@shaoxiongdu.cn>
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

CREATE TABLE `system_user`
(
    `id`                BIGINT AUTO_INCREMENT PRIMARY KEY comment 'id',
    `id_card_number`    varchar(255) comment '身份证号',
    `gender`            tinyint comment '性别 0-女 1-男',
    `name`              varchar(255) comment '姓名',
    `birthday`          datetime comment '生日',
    `age`               int comment '年龄',
    `origin`            varchar(255) comment '籍贯',
    `zip_code`          int comment '邮编',
    `address`           varchar(255) comment '联系地址',
    `email`             varchar(255) comment '邮箱',
    `degree`            varchar(255) comment '学历',
    `graduation_school` varchar(255) comment '毕业学校',

    INDEX idx_card_number (id_card_number),
    INDEX idx_birthday (birthday)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;