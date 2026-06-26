package com.library.controller;

import com.library.dto.*;
import com.library.service.LibraryService;
import com.library.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private LibraryService s ;

    @PostMapping("/add")
    public Result add(@RequestBody BookAddDTO d) {
        String m = s.addBook(d.getTitle(), d.getAuthor(), d.getPublisher(), d.getIsbn(), d.getStockQuantity());
        return m.startsWith("已成功入库") ? Result.ok(m) : Result.error(m);
    }

    @PostMapping("/out")
    public Result out(@RequestBody BookOutDTO d) {
        String m = s.removeBook(d.getIsbn());
        return m.startsWith("图书已出库") ? Result.ok(m) : Result.error(m);
    }

    @PostMapping("/search")
    public Result search(@RequestBody SearchDTO d) {
        return Result.ok("查询结果:", s.searchBooks(d.getKeyword()));
    }

    @GetMapping("/list")
    public Result list() {
        return Result.ok("当前库存图书：", s.listBooks());
    }
}
