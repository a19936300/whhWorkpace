package com.binbinxiu.whh.jiudao.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.binbinxiu.whh.jiudao.config.ResponseVO;
import com.binbinxiu.whh.jiudao.entity.Classic;
import com.binbinxiu.whh.jiudao.service.IClassicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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


    /**
     * 获取最新一期
     */
    @GetMapping("/latest")
    public ResponseVO latest(){
        LambdaQueryChainWrapper<Classic> eq = classicService.lambdaQuery().orderByDesc(Classic::getClassicIndex).last("LIMIT 1");
        Classic one = classicService.getOne(eq);
        return ResponseVO.successInstance(one);
    }

    /**
     * 获取最新一期
     */
    @GetMapping("/{index}/next")
    public ResponseVO nextLatest(@PathVariable String index){
        LambdaQueryChainWrapper<Classic> eq = classicService.lambdaQuery().gt(Classic::getClassicIndex,index)
                .orderByDesc(Classic::getClassicIndex).last("LIMIT 1");
        Classic one = classicService.getOne(eq);
        return ResponseVO.successInstance(one);
    }

    /**
     * 获取某一期详细信息
     */
    @GetMapping("/{type}/{id}")
    public ResponseVO typeId(@PathVariable String id, @PathVariable String type){
        LambdaQueryChainWrapper<Classic> eq = classicService.lambdaQuery().eq(Classic::getId,id).eq(Classic::getType,type);
        Classic one = classicService.getOne(eq);
        return ResponseVO.successInstance(one);
    }


    /**
     * 获取当前一期的上一期
     */
    @GetMapping("/{index}/previous")
    public ResponseVO previousLatest(@PathVariable String index){
        LambdaQueryChainWrapper<Classic> eq = classicService.lambdaQuery().lt(Classic::getClassicIndex,index)
                .orderByDesc(Classic::getClassicIndex).last("LIMIT 1");
        Classic one = classicService.getOne(eq);
        return ResponseVO.successInstance(one);
    }

    /**
     * 获取点赞信息
     */
    @GetMapping("/{type}/{id}/favor")
    public ResponseVO typeIdFavor(@PathVariable String id, @PathVariable String type){
        return ResponseVO.successInstance();
    }
    /**
     * 获取我喜欢的期刊
     */
    @GetMapping("/favor")
    public ResponseVO favor(){

        return ResponseVO.successInstance();
    }

}
