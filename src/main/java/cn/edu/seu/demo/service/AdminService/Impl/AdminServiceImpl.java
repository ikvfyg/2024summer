package cn.edu.seu.demo.service.AdminService.Impl;

import cn.edu.seu.demo.mapper.AdminMapper.AdminMapper;
import cn.edu.seu.demo.pojo.History;
import cn.edu.seu.demo.pojo.HistoryText;
import cn.edu.seu.demo.pojo.PageBean;
import cn.edu.seu.demo.pojo.User;
import cn.edu.seu.demo.service.AdminService.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PageBean GetUserList(Integer page, Integer pagesize) {
        //1. 设置分页参数
        PageHelper.startPage(page, pagesize);
        //2. 执行查询
        List<User> userList = adminMapper.GetUserList();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            user.setPassword("不可见");
        }
        Page<User> p = (Page<User>) userList;
        //3. 封装pageBean 对象
        PageBean pageBean = new PageBean(p.getResult(), p.getTotal());
        return pageBean;
    }


    @Override
    public PageBean GetUserHistory(Integer userId, Integer page, Integer pagesize) {
        //1. 设置分页参数
        PageHelper.startPage(page, pagesize);
        //2. 执行查询
        List<History> histories = adminMapper.GetUserHistory(userId);
        Page<History> p = (Page<History>) histories;
        //3. 封装pageBean 对象
        PageBean pageBean = new PageBean(p.getResult(), p.getTotal());
        return pageBean;
    }

    @Override
    public PageBean GetUserHistoryItem(Integer historyId, Integer page, Integer pagesize) {
        //1. 设置分页参数
        PageHelper.startPage(page, pagesize);
        //2. 执行查询
        List<HistoryText> historyTexts = adminMapper.GetUserHistoryItem(historyId);
        Page<HistoryText> p = (Page<HistoryText>) historyTexts;
        //3. 封装pageBean 对象
        PageBean pageBean = new PageBean(p.getResult(), p.getTotal());
        return pageBean;
    }
}
