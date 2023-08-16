//package com.binbinxiu.whh.jiudao.controller;
//
//import com.binbinxiu.whh.jiudao.config.ResponseVO;
//import com.binbinxiu.whh.jiudao.entity.User;
//import com.binbinxiu.whh.jiudao.entity.UserClassic;
//import com.binbinxiu.whh.jiudao.entity.vo.LikeReqVo;
//import com.binbinxiu.whh.jiudao.service.IUserClassicService;
//import com.binbinxiu.whh.jiudao.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author binbin
// * @since 2023-07-25
// */
//@Controller
//@RequestMapping("/like")
//public class UserClassicController {
//    @Autowired
//    private IUserService userService;
//    @Autowired
//    private IUserClassicService userClassicService;
//
//
//    @PostMapping("")
//    @ResponseBody
//    public ResponseVO<User> like(@RequestBody LikeReqVo likeReqVo){
//        UserClassic one = userClassicService.lambdaQuery().eq(UserClassic::getClassicId, likeReqVo.getArtId())
//                .eq(UserClassic::getUserId, 1)
//                .eq(UserClassic::getType,likeReqVo.getType())
//                .one();
//        if(one == null){
//            UserClassic userClassic = new UserClassic();
//            userClassic.setClassicId(likeReqVo.getArtId());
//            userClassic.setUserId(1L);
//            userClassic.setIsFav(1);
//            userClassic.setType(likeReqVo.getType());
//            userClassicService.save(userClassic);
//        }else {
//            one.setIsFav(1);
//            userClassicService.updateById(one);
//        }
//
//        return ResponseVO.successInstance();
//    }
//
//    @PostMapping("/cancel")
//    @ResponseBody
//    public ResponseVO<User> cancel(@RequestBody LikeReqVo likeReqVo){
//        UserClassic one = userClassicService.lambdaQuery().eq(UserClassic::getClassicId, likeReqVo.getArtId())
//                .eq(UserClassic::getUserId, 1)
//                .eq(UserClassic::getType,likeReqVo.getType())
//                .one();
//        if(one == null){
//            UserClassic userClassic = new UserClassic();
//            userClassic.setClassicId(likeReqVo.getArtId());
//            userClassic.setUserId(1L);
//            userClassic.setIsFav(0);
//            userClassic.setType(likeReqVo.getType());
//            userClassicService.save(userClassic);
//        }else {
//            one.setIsFav(0);
//            userClassicService.updateById(one);
//        }
//
//        return ResponseVO.successInstance();
//    }
//
//}
