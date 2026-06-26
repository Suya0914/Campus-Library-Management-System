package com.library.controller;
import com.library.dto.*;
import com.library.service.LibraryService;
import com.library.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operate")
public class OperateController {
    @Autowired private LibraryService s;
    @PostMapping("/borrow") public Result borrow(@RequestBody BorrowDTO d) {
        String m = s.borrowBook(d.getCardNumber(), d.getIsbn());
        return m.startsWith("借阅成功") ? Result.ok(m) : Result.error(m);
    }
    @PostMapping("/return") public Result ret(@RequestBody ReturnDTO d) {
        String m = s.returnBook(d.getCardNumber(), d.getIsbn());
        return m.startsWith("归还成功") ? Result.ok(m) : Result.error(m);
    }
}
