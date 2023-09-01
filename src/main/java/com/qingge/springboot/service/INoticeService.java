package com.qingge.springboot.service;

import com.qingge.springboot.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-04-04
 */
public interface INoticeService extends IService<Notice> {

    List<Notice> limit(int i);
}
