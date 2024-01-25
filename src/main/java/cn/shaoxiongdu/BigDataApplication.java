package cn.shaoxiongdu;

import cn.shaoxiongdu.dao.entity.SystemUser;
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
        
        UserMapper userMapper = context.getBean(UserMapper.class);
        
        System.out.println(userMapper.selectCount(null));
        
//                for (int i = 0; i < 3000; i++) {
//
//            new Thread(() -> {
//                while (true){
//                    try {
//                        userMapper.insert(SystemUser.random());
//                    }catch (Throwable ignored){
//
//                    }
//
//                }
//            }).start();

//        }
    }

}
