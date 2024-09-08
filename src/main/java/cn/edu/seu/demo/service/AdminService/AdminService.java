package cn.edu.seu.demo.service.AdminService;

import cn.edu.seu.demo.pojo.History;
import cn.edu.seu.demo.pojo.HistoryText;
import cn.edu.seu.demo.pojo.PageBean;
import cn.edu.seu.demo.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface AdminService {
    PageBean GetUserList(Integer page,Integer pagesize);



    PageBean GetUserHistory(Integer userId,Integer page,Integer pagesize);

    PageBean GetUserHistoryItem(Integer historyId,Integer page,Integer pagesize);
}
