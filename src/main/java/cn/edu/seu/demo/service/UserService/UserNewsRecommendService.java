package cn.edu.seu.demo.service.UserService;

import cn.edu.seu.demo.pojo.*;

import java.util.List;

public interface UserNewsRecommendService {
    List<Classify> GetAllClassify();

//    void SetPreferenceClassify(Integer userId, List<Classify> classifyList);

    List<News> GetOneClassifyRecommend(Integer classifyId, Integer page, Integer pagesize);

//    List<Source> GetAllSource();

    List<News> GetUserClassifyRecommend(Integer userId, Integer startId, Integer pagesize);

    List<String> GetUserBlackList(Integer userId);

    void SetUserBlackList(Integer userId, List<String> blackList);
}
