package com.library.controller;
import com.library.dto.ReaderAddDTO;
import com.library.service.LibraryService;
import com.library.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {
    @Autowired private LibraryService s;
    @PostMapping("/add") public Result add(@RequestBody ReaderAddDTO d) {
        String m = s.addReader(d.getName(), d.getCardNumber());
        return m.startsWith("已成功添加读者") ? Result.ok(m) : Result.error(m);
    }
    @GetMapping("/list") public Result list() { return Result.ok("读者列表：", s.listReaders()); }
}
