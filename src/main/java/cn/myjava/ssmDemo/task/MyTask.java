package cn.myjava.ssmDemo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/27  22:42
 */
@Component
public class MyTask {
    /**
     * 秒 分 时 日 月 星期 年
     */
    @Scheduled(cron = ("50/2 * * 26 * ?"))
    public void firstTask() {
        System.out.println(System.currentTimeMillis());
        System.out.println("==============MyTask,Hello===============");
        System.out.println(System.currentTimeMillis());
    }
}
