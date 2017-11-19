package top.xudj.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xudj.demo.component.AsyncTask;
import top.xudj.demo.component.Task;

import java.util.concurrent.Future;

/**
 * Created by xudj on 17/11/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class TaskTest {

    @Autowired
    private Task task;

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void test() throws Exception {
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
    }


    /**
     * 如果异步没有返回值，调用函数无法判断，可能导致异步方法执行不完，
     * 调用函数若是主函数执行完后可能导致程序错乱
     * @throws Exception
     */
    @Test
    public void asyncTest() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
    }

    @Test
    public void asyncTestResult() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = asyncTask.doTaskOne();
        Future<String> task2 = asyncTask.doTaskTwo();
        Future<String> task3 = asyncTask.doTaskThree();

        while (true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务调用完
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        log.debug("任务全部完成，总耗时 {} 毫秒", end - start);
    }

}
