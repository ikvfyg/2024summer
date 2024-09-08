package cn.edu.seu.demo.service.AdminService;

import java.time.LocalDate;
import java.util.Map;

public interface AdminDataService {
    Map<String, Integer> GetNewsCount();

    Integer GetAllNewsCount();

    Integer GetUserCount();

    Integer GetChatCount();

    Map<LocalDate, Integer> GetNewsCountWeekly();

    Map<LocalDate, Integer> GetChatCountWeekly();
}
