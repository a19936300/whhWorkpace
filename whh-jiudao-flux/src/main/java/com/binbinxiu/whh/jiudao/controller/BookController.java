//package com.binbinxiu.whh.jiudao.controller;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.map.MapUtil;
//import com.binbinxiu.whh.jiudao.config.ResponseVO;
//import com.binbinxiu.whh.jiudao.entity.Book;
//import com.binbinxiu.whh.jiudao.entity.vo.BookVo;
//import com.binbinxiu.whh.jiudao.service.IBookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author binbin
// * @since 2023-08-04
// */
//@Controller
//@RequestMapping("/book")
//public class BookController {
//
//    @Autowired
//    private IBookService bookService;
//
//    @GetMapping("/hot_list")
//    @ResponseBody
//    public List<BookVo>  hotList(){
//        List<Book> list = bookService.list();
//
//        List<BookVo> resBookVos = list.stream().map(book -> {
//            BookVo bookVo = BeanUtil.copyProperties(book, BookVo.class);
//            bookVo.setId(book.getId());
//            return bookVo;
//        }).collect(Collectors.toList());
//        return resBookVos;
//    }
//
//
//    @GetMapping("/book_id/short_comment")
//    @ResponseBody
//    public List<BookVo>  shortComment(@PathVariable("book_id")String bookId){
//        List<Book> list = bookService.list();
//
//        List<BookVo> resBookVos = list.stream().map(book -> {
//            BookVo bookVo = BeanUtil.copyProperties(book, BookVo.class);
//            bookVo.setId(book.getId());
//            return bookVo;
//        }).collect(Collectors.toList());
//        return resBookVos;
//    }
//
//
//    @GetMapping("/favor/count")
//    @ResponseBody
//    public Map<String,Integer>  favorCount(){
//        List<Book> list = bookService.list();
//
//        List<BookVo> resBookVos = list.stream().map(book -> {
//            BookVo bookVo = BeanUtil.copyProperties(book, BookVo.class);
//            bookVo.setId(book.getId());
//            return bookVo;
//        }).collect(Collectors.toList());
//        return MapUtil.newHashMap();
//    }
//
//
//    @GetMapping("/{book_id}/favor")
//    @ResponseBody
//    public Map<String,Integer> bookFavor(@PathVariable("book_id")String bookId){
//        List<Book> list = bookService.list();
//
//        List<BookVo> resBookVos = list.stream().map(book -> {
//            BookVo bookVo = BeanUtil.copyProperties(book, BookVo.class);
//            bookVo.setId(book.getId());
//            return bookVo;
//        }).collect(Collectors.toList());
//        return MapUtil.newHashMap();
//    }
//
//
//    @GetMapping("/add/short_comment")
//    @ResponseBody
//    public ResponseVO addShortComment(){
//        List<Book> list = bookService.list();
//
//        List<BookVo> resBookVos = list.stream().map(book -> {
//            BookVo bookVo = BeanUtil.copyProperties(book, BookVo.class);
//            bookVo.setId(book.getId());
//            return bookVo;
//        }).collect(Collectors.toList());
//        return ResponseVO.successInstance();
//    }
//}
