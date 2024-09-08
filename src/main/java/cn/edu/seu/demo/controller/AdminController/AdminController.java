package cn.edu.seu.demo.controller.AdminController;


import cn.edu.seu.demo.pojo.*;
import cn.edu.seu.demo.service.AdminService.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 获取用户列表
     * @return
     */
    @PostMapping("/get-user")
    public Result GetUserList(@RequestBody HashMap map){
        Integer page= (Integer) map.get("page");
        Integer pagesize= (Integer) map.get("pagesize");

        PageBean userList=adminService.GetUserList(page,pagesize);
        return Result.success(userList);
    }



    /**
     * 获取某用户历史记录
     */
    @PostMapping("/user-history")
    public Result GetUserHistory(@RequestBody HashMap map){
        Integer page= (Integer) map.get("page");
        Integer pagesize= (Integer) map.get("pagesize");
        Integer userId= (Integer) map.get("user_id");
        PageBean histories=adminService.GetUserHistory(userId,page,pagesize);
        return Result.success(histories);
    }

    /**
     * 获取历史记录条目
     */
    @PostMapping("/history-text")
    public Result GetUserHistoryItem(@RequestBody HashMap map){
        Integer page= (Integer) map.get("page");
        Integer pagesize= (Integer) map.get("pagesize");
        Integer historyId= (Integer) map.get("history_id");
        PageBean texts=adminService.GetUserHistoryItem(historyId,page,pagesize);
        return Result.success(texts);
    }

}
