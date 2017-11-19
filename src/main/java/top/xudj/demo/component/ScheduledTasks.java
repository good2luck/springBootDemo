package top.xudj.demo.component;

import com.oracle.tools.packager.Log;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xudj on 17/11/19.
 */
@Component
@Slf4j
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * fixedRate = 5000：上一次开始执行时间点之后5秒再执行，如果方法执行时间超过5秒，则方法执行完(方法执行时间)后执行
     * fixedDelay = 5000 ：上一次执行完毕时间点之后5秒再执行
     * initialDelay=1000：第一次延迟1秒执行
     * cron：通过cron表达式定义规则（"*\/5 * * * * *"：每5秒执行一次,方法执行时间超过5秒时，则第二个5秒执行,依此类推）
     */
    @Scheduled(cron="*/5 * * * * *")
    public void reportCurrentTime() throws Exception {
        log.debug("进入方法时间：{}", dateFormat.format(new Date()));
        Thread.sleep(8000);
        log.debug("结束方法时间：{}", dateFormat.format(new Date()));
    }

}
