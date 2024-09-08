package cn.edu.seu.demo.utils;
import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hashing {
    @NotNull
    public static String sha256(@NotNull String input) {
        try {
            // 获取 SHA-256 消息摘要实例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 计算输入的 SHA-256 哈希值，得到字节数组
            byte[] hashBytes = digest.digest(input.getBytes());

            // 将字节数组转为16进制表示的字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                // 将每个字节转换为2位的16进制数
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // 示例：将字符串 "password123" 加密为 SHA-256 哈希
        String password = "123456";
        String hashedPassword = sha256(password);
        System.out.println("SHA-256 Hash of password: " + hashedPassword);
    }
}
