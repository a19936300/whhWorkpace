package com.binbinxiu.whh.jiudao.controller;

import com.binbinxiu.whh.jiudao.config.ResponseVO;
import com.binbinxiu.whh.jiudao.entity.User;
import com.binbinxiu.whh.jiudao.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
@Controller
@RequestMapping("/userClassic")
public class UserClassicController {
    @Autowired
    private IUserService userService;
    @GetMapping("queryAll")
    @ResponseBody
    public ResponseVO<User> queryAll(){
        System.out.println("808--------------");
        List<User> list =
                userService.list();
        return ResponseVO.successInstance(list);
    }
}
