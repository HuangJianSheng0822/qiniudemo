package com.qn.qiniudemoapi.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * mybatis plus分页
 */
@Component
public class MybatisPlusHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //strictUpdateFill失效：MetaObjectHandler提供的默认方法的策略均为: 如果属性有值则不覆盖, 如果填充值为null则不填充。也就是说，MP的自动填充功能的前提是填充字段要求为null。
        //时间类型一样
        this.setFieldValByName("created", new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updated", new Date(),metaObject);
    }
}
