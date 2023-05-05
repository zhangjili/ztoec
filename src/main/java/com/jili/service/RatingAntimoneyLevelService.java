package com.jili.service;

import com.jili.entity.RatingAntimoneyLevel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 反洗钱评级等级划分表 服务类
 * </p>
 *
 * @author zjl
 * @since 2023-03-08
 */
public interface RatingAntimoneyLevelService extends IService<RatingAntimoneyLevel> {

    String insert(String MerNo);

}
