package com.library.controller;

import com.library.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> cred, HttpSession session) {
        String u = cred.get("username"), p = cred.get("password");
        if ("admin".equals(u) && "admin123".equals(p)) {
            session.setAttribute("user", u);
            return Result.ok("登录成功");
        }
        return Result.error("用户名或密码错误");
    }

    @GetMapping("/status")
    public Result status(HttpSession session) {
        Object u = session.getAttribute("user");
        return u != null ? Result.ok("已登录", u) : Result.error("未登录");
    }

    @PostMapping("/logout")
    public Result logout(HttpSession session) {
        session.invalidate();
        return Result.ok("已退出登录");
    }
}
