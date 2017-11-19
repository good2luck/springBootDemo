package top.xudj.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by xudj on 17/11/19.
 */
@Component
@Slf4j
public class AsyncTask {

    public static Random random = new Random();

    /**
     * @Async标注本方法为异步方法
     * @throws InterruptedException
     */
    @Async
    public Future<String> doTaskOne() throws InterruptedException {
        log.debug("开始执行任务4");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.debug("完成任务4，耗时：" + (end - start));
        return new AsyncResult<>("任务4完成");
    }

    @Async
    public Future<String> doTaskTwo() throws InterruptedException {
        log.debug("开始执行任务5");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.debug("完成任务5，耗时：" + (end - start));
        return new AsyncResult<>("任务5完成");
    }

    @Async
    public Future<String> doTaskThree() throws InterruptedException {
        log.debug("开始执行任务6");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.debug("完成任务6，耗时：" + (end - start));
        return new AsyncResult<>("任务6完成");
    }

}
