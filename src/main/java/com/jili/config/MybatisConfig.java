package com.jili.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 两个分页插件都配置,不会冲突
 *
 * @author miemie
 * @date 2020/5/29
 */
@Configuration
public class MybatisConfig {

    /**
     * pagehelper的分页插件
     */
//    @Bean
//    public PageInterceptor pageInterceptor() {
//        return new PageInterceptor();
//    }

    //插入和更新时填充
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler(){

            @Override
            public void insertFill(MetaObject metaObject) {
                this.setFieldValByName("careateTime", LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),metaObject);
                this.setFieldValByName("updateTime",LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("updateTime",LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()),metaObject);
            }
        };
    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     *
     * mp 与 pagehelper 存在依赖 jsqlparser 冲突，不建议混用
     */
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
//        return interceptor;
//    }

//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }


}
