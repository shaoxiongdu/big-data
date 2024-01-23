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

package cn.shaoxiongdu.dao.entity;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.apifan.common.random.RandomSource;
import com.apifan.common.random.constant.Province;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: dushaoxiong@lixiang.com
 * @date: 2024/1/23 : 15:47
 * @describe:
 */
@Data
@TableName("system_user")
@Builder
public class User {

    @TableId
    private Long id;
    
    private String idCardNumber;
    
    private Integer gender;
    
    private Integer age;
    
    private String name;
    
    private Date birthday;
    
    /**
     * 籍贯
     */
    private String origin;
    
    private int zipCode;
    
    /**
     * 联系地址
     */
    private String address;
    
    private String email;
    
    /**
     * 学历
     */
    private String degree;
    
    /**
     * 毕业学校
     */
    private String graduationSchool;
    
    public static User random(){
        
        Integer zipCode = null;
        try {
            zipCode = Integer.parseInt(RandomSource.areaSource().randomZipCode());
        }catch(Exception e){
         zipCode = 0;
        }
        
        ThreadLocalRandom random = RandomUtil.getRandom();
        String idCardNumber = RandomSource.personInfoSource()
                .randomMaleIdCard(Province.values()[RandomUtil.getRandom().nextInt(Province.values().length)],
                        random.nextInt(1, 99));
        
        String provinceName = IdcardUtil.getProvinceByIdCard(idCardNumber);
        if (StrUtil.isEmpty(provinceName)) {
            provinceName = "北京";
        }
        Province province = getProvinceByName(provinceName);
        
        if (province.toString().contains("MO")
        || province.toString().contains("HK")
        || province.toString().contains("TW")
        ){
            province = Province.BJ;
        }
        
        return new UserBuilder()
                .idCardNumber(idCardNumber)
                .gender(IdcardUtil.getGenderByIdCard(idCardNumber))
                .name(RandomSource.personInfoSource().randomChineseName())
                .birthday(IdcardUtil.getBirthDate(idCardNumber).toSqlDate())
                .age(IdcardUtil.getAgeByIdCard(idCardNumber))
                .origin(RandomSource.areaSource().randomAddress(province))
                .zipCode(zipCode)
                .address(RandomSource.areaSource().randomAddress(province))
                .email(RandomSource.internetSource().randomEmail(16))
                .degree(RandomSource.educationSource().randomDegree())
                .graduationSchool(RandomSource.educationSource().randomCollege())
                .build();
    }
    
    private static Province getProvinceByName(String provinceName){
        for (Province province : Province.values()) {
            if (province.getName().contains(provinceName)){
                return province;
            }
        }
        return Province.BJ;
    }
    
    public static void main(String[] args) {
        String id = "330206195512234977";
        System.out.println(IdcardUtil.getProvinceByIdCard(id));
        System.out.println(getProvinceByName(IdcardUtil.getProvinceByIdCard(id)));
    }
    
    
    
}
