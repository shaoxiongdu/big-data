package cn.shaoxiongdu;

import cn.hutool.core.util.StrUtil;
import cn.shaoxiongdu.dao.entity.User;
import cn.shaoxiongdu.dao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
* @author author
*/
@SpringBootApplication
@MapperScan("cn.shaoxiongdu.dao.mapper")
@Slf4j
public class BigDataApplication {

    public static void main(String[] args) {
        runInsertUser(SpringApplication.run(BigDataApplication.class, args));
    }

    private static void runInsertUser(ConfigurableApplicationContext context){

        for (int i = 0; i < 2000; i++) {

            new Thread(() -> {
                int count = 1;
                while (true){
                    context.getBean(UserMapper.class).insert(User.random());
                    log.info(StrUtil.format("{}", count++));
                }
            }).start();

        }
    }

}
