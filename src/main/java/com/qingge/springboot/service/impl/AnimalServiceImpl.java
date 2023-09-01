package com.qingge.springboot.service.impl;

import com.qingge.springboot.entity.Animal;
import com.qingge.springboot.mapper.AnimalMapper;
import com.qingge.springboot.service.IAnimalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-02
 */
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements IAnimalService {

}
