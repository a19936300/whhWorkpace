package com.binbinxiu.whh.jiudao.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.binbinxiu.whh.jiudao.config.ResponseVO;
import com.binbinxiu.whh.jiudao.entity.Classic;
import com.binbinxiu.whh.jiudao.entity.UserClassic;
import com.binbinxiu.whh.jiudao.entity.vo.ClassicVo;
import com.binbinxiu.whh.jiudao.service.IClassicService;
import com.binbinxiu.whh.jiudao.service.IUserClassicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author binbin
 * @since 2023-07-25
 */
@RestController
@RequestMapping("/classic")
public class ClassicController {
    @Autowired
    private IClassicService classicService;

    @Autowired
    private IUserClassicService userClassicService;

    /**
     * 获取最新一期
     */
    @GetMapping("/latest")
    public Mono<ClassicVo> latest(){
        Classic one= classicService.lambdaQuery().orderByDesc(Classic::getClassicIndex).last("LIMIT 1").one();
        ClassicVo classicVo = new ClassicVo();
        BeanUtils.copyProperties(one,classicVo);
        classicVo.setId(one.getOldId());
        classicVo.setIndex(one.getClassicIndex());

        UserClassic userClassic = userClassicService.lambdaQuery()
                .eq(UserClassic::getClassicId, one.getOldId())
                .eq(UserClassic::getUserId, 1)
                .eq(UserClassic::getType,one.getType())
                .one();
        classicVo.setLikeStatus(userClassic == null?0:userClassic.getIsFav());

        return Mono.just(classicVo);
    }

    /**
     * 获取最新一期
     */
    @GetMapping("/{index}/next")
    public ClassicVo nextLatest(@PathVariable String index){
        Classic one = classicService.lambdaQuery().gt(Classic::getClassicIndex, index)
                .orderByDesc(Classic::getClassicIndex).last("LIMIT 1").one();
        ClassicVo classicVo = new ClassicVo();
        BeanUtils.copyProperties(one,classicVo);
        classicVo.setId(one.getOldId());
        classicVo.setIndex(one.getClassicIndex());
        return classicVo;
    }

    /**
     * 获取某一期详细信息
     */
    @GetMapping("/{type}/{id}")
    public ClassicVo typeId(@PathVariable String id, @PathVariable String type){
        Classic one = classicService.lambdaQuery().eq(Classic::getId,id).eq(Classic::getType,type).one();
        ClassicVo classicVo = new ClassicVo();
        BeanUtils.copyProperties(one,classicVo);
        classicVo.setId(one.getOldId());
        classicVo.setIndex(one.getClassicIndex());
        return classicVo;
    }


    /**
     * 获取当前一期的上一期
     */
    @GetMapping("/{index}/previous")
    public ClassicVo previousLatest(@PathVariable String index){
        Classic one = classicService.lambdaQuery().lt(Classic::getClassicIndex,index)
                .orderByDesc(Classic::getClassicIndex).last("LIMIT 1").one();
        ClassicVo classicVo = new ClassicVo();
        BeanUtils.copyProperties(one,classicVo);
        classicVo.setId(one.getOldId());
        classicVo.setIndex(one.getClassicIndex());
        return classicVo;
    }

    /**
     * 获取点赞信息
     */
    @GetMapping("/{type}/{id}/favor")
    public HashMap typeIdFavor(@PathVariable Long id, @PathVariable Integer type){
        HashMap<String, Integer> result = new HashMap<>();
        Classic one = classicService.lambdaQuery().eq(Classic::getOldId,id).eq(Classic::getType,type).one();

        UserClassic userClassic = userClassicService.lambdaQuery()
                .eq(UserClassic::getClassicId, one.getId())
                .eq(UserClassic::getUserId, 1)
                .eq(UserClassic::getType,type)
                .one();

        result.put("fav_nums",one.getFavNums());
        result.put("id",one.getOldId().intValue());
        result.put("like_status",userClassic==null ? 0:Integer.valueOf(userClassic.getIsFav()));
        return result;
    }
    /**
     * 获取我喜欢的期刊
     */
    @GetMapping("/favor")
    public ResponseVO favor(){

        return ResponseVO.successInstance();
    }

}
