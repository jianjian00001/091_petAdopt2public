package com.qingge.springboot.service.impl;

import com.qingge.springboot.entity.Feed;
import com.qingge.springboot.mapper.FeedMapper;
import com.qingge.springboot.service.IFeedService;
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
public class FeedServiceImpl extends ServiceImpl<FeedMapper, Feed> implements IFeedService {

}
