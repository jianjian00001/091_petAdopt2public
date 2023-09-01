package com.qingge.springboot.service.impl;

import com.qingge.springboot.service.ILostService;
import com.qingge.springboot.entity.Lost;
import com.qingge.springboot.mapper.LostMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-04
 */
@Service
public class LostServiceImpl extends ServiceImpl<LostMapper, Lost> implements ILostService {

}
