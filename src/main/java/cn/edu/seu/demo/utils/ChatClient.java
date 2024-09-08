package cn.edu.seu.demo.utils;

import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.edu.seu.demo.exception.UserRequestInProgressException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChatClient {

    private static final String API_URL = "http://localhost:11434/api/chat";

    // 用于存储每个用户的锁
    private static final ConcurrentHashMap<Integer, Lock> userLocks = new ConcurrentHashMap<>();

    // 获取或创建一个特定用户的锁
    private static Lock getUserLock(Integer userId) {
        return userLocks.computeIfAbsent(userId, k -> new ReentrantLock());
    }

    public static void prerequest(Integer userId) throws Exception {
        // 获取该用户对应的锁
        Lock lock = getUserLock(userId);

        // 尝试获取锁，如果无法获取，抛出异常
        if (!lock.tryLock()) {
            throw new UserRequestInProgressException("当前还有其他请求正在进行");
        }
        try {
//            Thread.sleep(3000);
        } finally {
            lock.unlock();
        }
    }

    public static String systemllama3(String prompt) throws Exception {


        // Create the JSON payload
        JSONObject data = new JSONObject();
        data.put("model", "news_sum");
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);
//            data.put("messages", new JSONObject[]{message});
        JSONArray messages = new JSONArray();
        messages.put(message);
        data.put("messages", messages);
        data.put("stream", false);

        // Create a URL object
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method and headers
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Write the JSON payload to the request body
        try (OutputStream os = connection.getOutputStream()) {
            os.write(data.toString().getBytes("UTF-8"));
            os.flush();
        }

        // Read the response
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        // Parse and return the response content
        JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getJSONObject("message").getString("content");

    }

    public static String llama3(String prompt, Integer userId) throws Exception {
        // 获取该用户对应的锁
        Lock lock = getUserLock(userId);

        // 尝试获取锁，如果无法获取，抛出异常
        if (!lock.tryLock()) {
            throw new UserRequestInProgressException("当前还有其他请求正在进行");
        }
        try {
            // Create the JSON payload
            JSONObject data = new JSONObject();
            data.put("model", "news_sum");
            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", prompt);
//            data.put("messages", new JSONObject[]{message});
            JSONArray messages = new JSONArray();
            messages.put(message);
            data.put("messages", messages);

            data.put("stream", false);

            // Create a URL object
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method and headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write the JSON payload to the request body
            try (OutputStream os = connection.getOutputStream()) {
                os.write(data.toString().getBytes("UTF-8"));
                os.flush();
            }

            // Read the response
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }

            // Parse and return the response content
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getJSONObject("message").getString("content");
        } finally {
            // 确保最终释放锁
            lock.unlock();
        }
    }


}
