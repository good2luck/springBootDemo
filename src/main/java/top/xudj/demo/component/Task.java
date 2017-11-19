package top.xudj.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by xudj on 17/11/19.
 */
@Component
@Slf4j
public class Task {

    public static Random random = new Random();

    public void doTaskOne() throws InterruptedException {
        log.debug("开始执行任务1");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.debug("完成任务1，耗时：" + (end - start));
    }


    public void doTaskTwo() throws InterruptedException {
        log.debug("开始执行任务2");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.debug("完成任务2，耗时：" + (end - start));
    }


    public void doTaskThree() throws InterruptedException {
        log.debug("开始执行任务3");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.debug("完成任务3，耗时：" + (end - start));
    }


}
