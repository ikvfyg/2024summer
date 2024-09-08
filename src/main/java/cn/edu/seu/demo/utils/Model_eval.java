package cn.edu.seu.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Model_eval {
//    public static String model_eval(String text) {
//        String result = new String();
//        try {
//            // 替换以下路径为你的Conda环境中的Python解释器路径
//            String pythonPath = "E:/ProgramData/anaconda3/envs/demo3/python.exe";
//            String scriptPath = "E:/keyan/24summer/seu/demo2/test_ollama_python.py";
//            // 使用ProcessBuilder构建命令
//            ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, scriptPath, text);
//
//            // 启动进程
//            Process process = processBuilder.start();
//
//            // 读取输出结果
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                result += line;
//            }
//
//            // 等待进程结束
//            int exitCode = process.waitFor();
//            System.out.println("Exited with error code : " + exitCode);
//
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
