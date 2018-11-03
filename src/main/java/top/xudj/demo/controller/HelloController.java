package top.xudj.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xudj on 17/11/1.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/hello/global/exception")
    public String testGlobalException() {
        // 全局异常处理测试
        int i = 1 / 0;
        return "exception";
    }

    public static void main(String[] args) {

    }

    private static void test() {
        System.out.println(LocalDateTime.now());
    }

    private static void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }

    private static void testArrayList() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if ("1".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.println(a);

        List<String> b = new ArrayList<String>();
        b.add("1");
        b.add("2");
        for (String temp : b) {
            if ("2".equals(temp)) {
                b.remove(temp);
            }
        }
        System.out.println(b);
    }


    private static void testSubList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        List subList = list.subList(1, 2);
        System.out.println(list);
        System.out.println(subList);
    }

    private static void testIntern() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1 == str1.intern());

        String str2 = new StringBuilder("de").append("mo").toString();
        System.out.println(str2 == str2.intern());

        String str22 = new StringBuilder("ja").append("va").toString();
        System.out.println(str22 == str22.intern());

        String str3 = new StringBuilder("word").toString();
        System.out.println(str3 == str3.intern());
    }

}
