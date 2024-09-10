package cn.edu.seu.demo.utils;

public class SentenceChecker {

    // 判断句子是否以标点符号结尾
    public static boolean isSentenceComplete(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return false;  // 空字符串或null不是完整句子
        }

        // 常见的句尾标点符号
        char[] punctuationMarks = {
                '.', '!', '?',  // 英文常用句尾符号
                '。', '！', '？',  // 中文常用句尾符号
                '…', '：', '；',  // 省略号、冒号、分号
                '"', '”', '’', '\'',  // 引号
                ')', '）', ']', '}', '》'  // 括号
        };

        // 获取句子的最后一个非空白字符
        char lastChar = sentence.trim().charAt(sentence.trim().length() - 1);

        // 判断最后一个字符是否是标点符号
        for (char punctuation : punctuationMarks) {
            if (lastChar == punctuation) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // 测试用例
        String sentence1 = "This is a complete sentence.";
        String sentence2 = "Is this a question?";
        String sentence3 = "这是一个完整的句子。";
        String sentence4 = "This is not complete";
        String sentence5 = "这是不完整的句子";

        System.out.println(isSentenceComplete(sentence1)); // 输出: true
        System.out.println(isSentenceComplete(sentence2)); // 输出: true
        System.out.println(isSentenceComplete(sentence3)); // 输出: true
        System.out.println(isSentenceComplete(sentence4)); // 输出: false
        System.out.println(isSentenceComplete(sentence5)); // 输出: false
    }
}
