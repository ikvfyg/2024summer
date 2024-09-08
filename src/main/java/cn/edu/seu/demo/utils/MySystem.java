package cn.edu.seu.demo.utils;

import cn.edu.seu.demo.mapper.MySystemMapper;
import cn.edu.seu.demo.pojo.RawNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class MySystem {
    @Autowired
    private MySystemMapper systemMapper;

    public void GenerateSummary(Integer size) throws Exception {
        List<RawNews> rawNewsList = systemMapper.GetNonSummarizedNews(size);
        for (int i = 0; i < rawNewsList.size(); i++) {
            RawNews news = rawNewsList.get(i);
            String question = news.getContent();
            String response = ChatClient.systemllama3(question);
            while (response.length() <= 10) {
                response = ChatClient.systemllama3(question);
            }
            news.setSummary(response);
            systemMapper.SetSummary(news);
        }
    }

    public void RunSpider() {
        try {
            //Python环境路径
            String pythonPath = "E:/ProgramData/anaconda3/envs/scrapy/python.exe";
            // Python 脚本路径
            String scriptPath = "E:/keyan/rolling-news-master";
            // 配置文件路径
            String configFilePath = "E:/codes/idea_workspace/demo/src/main/resources/config/config.json";

            // 创建 ProcessBuilder 对象
            ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, scriptPath, configFilePath);

            // 设置工作目录 (可选)
            processBuilder.directory(new File(System.getProperty("user.dir")));

            // 启动进程
            Process process = processBuilder.start();

            // 读取 Python 脚本的标准输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // 输出到 Java 控制台
            }

            // 等待脚本执行完成
            int exitCode = process.waitFor();
            System.out.println("Python script exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    public void execute(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
        System.out.println("欢迎访问 pan_junbiao的博客 " + df.format(new Date()));
    }

}
