package com.binbinxiu.whh.jiudao.service.impl;

import com.binbinxiu.whh.jiudao.entity.User;
import com.binbinxiu.whh.jiudao.mapper.UserMapper;
import com.binbinxiu.whh.jiudao.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
