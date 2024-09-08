package cn.edu.seu.demo.service.UserService.impl;

import cn.edu.seu.demo.mapper.UserMapper.UserNewsRecommendMapper;
import cn.edu.seu.demo.pojo.*;
import cn.edu.seu.demo.service.UserService.UserNewsRecommendService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserNewsRecommendServiceImpl implements UserNewsRecommendService {

    @Autowired
    private UserNewsRecommendMapper userNewsRecommendMapper;

    /**
     * 获取所有分类
     *
     * @return
     */
    @Override
    public List<Classify> GetAllClassify() {
        return userNewsRecommendMapper.GetAllClassify();
    }

//    /**
//     * 设置偏好分类
//     *
//     * @param userId
//     * @param classifyList
//     */
//    @Transactional(rollbackFor = {Exception.class})
//    @Override
//    public void SetPreferenceClassify(Integer userId, List<Classify> classifyList) {
//        userNewsRecommendMapper.RemoveAllPreferenceClassify(userId);
//        userNewsRecommendMapper.SetPreferenceClassify(userId, classifyList);
//    }

    /**
     * 获取某分类下某页推送
     *
     * @param classifyId
     * @param pagesize
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<News> GetOneClassifyRecommend(Integer classifyId, Integer startId, Integer pagesize) {
        //1. 设置分页参数
//        PageHelper.startPage(page, pagesize);
        //2. 执行查询
        List<NoContentNews> prenewsList = userNewsRecommendMapper.GetOneClassifyRecommend(classifyId, startId, pagesize);
//        Page<NoContentNews> p = (Page<NoContentNews>) newsList;
//        //3. 封装pageBean 对象
//        PageBean pageBean = new PageBean(p.getResult(), p.getTotal());
        List<Classify> classifyList = userNewsRecommendMapper.GetAllClassify();
        List<Source> sourceList = userNewsRecommendMapper.GetAllSource();
        // 创建映射，将 classifyId 映射为 classifyName
        Map<Integer, String> classifyMap = classifyList.stream()
                .collect(Collectors.toMap(Classify::getId, Classify::getClassifyName));

// 创建映射，将 sourceId 映射为 sourceStr
        Map<Integer, String> sourceMap = sourceList.stream()
                .collect(Collectors.toMap(Source::getId, Source::getSource));

// 使用映射来构建新闻列表
        List<News> newsList = prenewsList.stream()
                .map(father -> new News(
                        father,
                        classifyMap.get(father.getClassifyId()),  // 使用 classifyMap 获取 classifyName
                        sourceMap.get(father.getSourceId())       // 使用 sourceMap 获取 sourceStr
                ))
                .collect(Collectors.toList());

        return newsList;
//        return pageBean;
    }

    /**
     * 获取所有新闻源
     *
     * @return
     */
//    @Override
//    public List<Source> GetAllSource() {
//        List<Source> sourceList = userNewsRecommendMapper.GetAllSource();
//        return sourceList;
//    }


    /**
     * 根据用户偏好获取推送
     *
     * @param userId
     * @param pagesize
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<News> GetUserClassifyRecommend(Integer userId, Integer startId, Integer pagesize) {
        //获取用户偏好
        List<Integer> blackIds = userNewsRecommendMapper.GetUserBlackList(userId);

        List<Classify> classifyList = userNewsRecommendMapper.GetAllClassify();

        // 1. 提取classifyList中的id
        List<Integer> idList = classifyList.stream()
                .map(Classify::getId) // 将Classify对象的id提取出来
                .collect(Collectors.toList());

        // 2. 从idList中移除所有在blackIds中的元素
        List<Integer> filteredIds = idList.stream()
                .filter(id -> !blackIds.contains(id)) // 去除blackIds中的元素
                .collect(Collectors.toList());

        //3. 执行查询

        List<NoContentNews> prenewsList = userNewsRecommendMapper.GetAllButBlackRecommend(filteredIds, startId, pagesize);
        List<Source> sourceList = userNewsRecommendMapper.GetAllSource();
        Map<Integer, String> classifyMap = classifyList.stream()
                .collect(Collectors.toMap(Classify::getId, Classify::getClassifyName));

// 创建映射，将 sourceId 映射为 sourceStr
        Map<Integer, String> sourceMap = sourceList.stream()
                .collect(Collectors.toMap(Source::getId, Source::getSource));

// 使用映射来构建新闻列表
        List<News> newsList = prenewsList.stream()
                .map(father -> new News(
                        father,
                        classifyMap.get(father.getClassifyId()),  // 使用 classifyMap 获取 classifyName
                        sourceMap.get(father.getSourceId())       // 使用 sourceMap 获取 sourceStr
                ))
                .collect(Collectors.toList());

        return newsList;
    }

    /**
     * 获取用户黑名单
     *
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public List<String> GetUserBlackList(Integer userId) {
        List<Integer> blackIds = userNewsRecommendMapper.GetUserBlackList(userId);
        List<Classify> dict = userNewsRecommendMapper.GetAllClassify();
        // 使用流式处理将 List<Integer> 转换为 List<String>
        Map<Integer, String> classifyMap = dict.stream()
                .collect(Collectors.toMap(Classify::getId, Classify::getClassifyName));
        List<String> blackList = blackIds.stream()
                .map(id -> classifyMap.getOrDefault(id, "Unknown"))
                .collect(Collectors.toList());
        return blackList;
    }

    /**
     * 设置黑名单
     *
     * @param userId
     * @param blackList
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void SetUserBlackList(Integer userId, List<String> blackList) {
        userNewsRecommendMapper.deleteUserBlackList(userId);
        if(blackList.size()==0){

        }
        else {
            List<Classify> dict = userNewsRecommendMapper.GetAllClassify();

// 使用流式处理将 List<Classify> 转换为 Map<String, Integer> 以便更快查找
            Map<String, Integer> classifyMap = dict.stream()
                    .collect(Collectors.toMap(Classify::getClassifyName, Classify::getId));

// 使用 classifyMap 来直接映射黑名单分类名为分类ID
            List<Integer> blackIds = blackList.stream()
                    .map(name -> classifyMap.getOrDefault(name, -1))  // 如果找不到对应ID，返回 -1
                    .filter(id -> id != -1)                           // 过滤掉没有找到的分类名
                    .collect(Collectors.toList());
            userNewsRecommendMapper.SetUserBlackList(userId, blackIds);
        }
    }
}
