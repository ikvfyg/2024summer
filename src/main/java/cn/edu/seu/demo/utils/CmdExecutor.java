package cn.edu.seu.demo.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class CmdExecutor {
    public void main() {
        try {
            // 使用 ProcessBuilder 构建命令
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("cmd.exe", "/c", "ollama list");

            String modelList=new String();
            String modelfile=new String();
            // 启动进程
            Process process = builder.start();

            // 读取命令行输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                modelList += line+'\n';
            }

            // 等待进程完成
            int exitCode = process.waitFor();
            System.out.println("\nExited with code: " + exitCode);

            // 如果你还需要执行 `ollama show` 命令，重复上述操作
            builder.command("cmd.exe", "/c", "ollama show --modelfile news_sum");
            process = builder.start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                modelfile+=line+'\n';
            }

            exitCode = process.waitFor();
            System.out.println("\nExited with code: " + exitCode);

            String ollamaModels = System.getenv("OLLAMA_MODELS");

            // 检查是否成功获取到环境变量
            if (ollamaModels != null) {
                System.out.println("OLLAMA_MODELS: " + ollamaModels);
            } else {
                System.out.println("环境变量 OLLAMA_MODELS 未设置。");
            }

            System.out.println("model list:"+modelList);
            System.out.println("modelfile:"+modelfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

