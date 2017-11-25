package top.xudj.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudj on 17/11/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JavaMailSenderTest {

    @Autowired
    private JavaMailSender javaMailSender;

    private static String from = "xxx@qq.com";
    private static String to = "yyy@qq.com";

    @Autowired
    Configuration freemarkerConfiguration;

    /**
     * 普通邮件
     */
    @Test
    public void test() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("主题：简单邮件");
        simpleMailMessage.setText("简单邮件内容");

        javaMailSender.send(simpleMailMessage);

    }

    /**
     * 发送附件
     */
    @Test
    public void testMiniMessage() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        // 构造函数true支持文件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("主题：附件");
        helper.setText("带有附件邮件内容");

        FileSystemResource file = new FileSystemResource(new File("/Users/xudj/测试.png"));

        helper.addAttachment(MimeUtility.encodeText(file.getFilename()), file);

        javaMailSender.send(mimeMessage);
    }


    /**
     * 邮件正文中嵌入静态资源
     */
    @Test
    public void testSendInlineMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\\\"cid:contentId\\\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("/Users/xudj/测试.png"));
        helper.addInline("contentId", file);

        javaMailSender.send(mimeMessage);
    }


    /**
     * 发送模板邮件
     */
    @Test
    public void testSendTemplateMail() throws Exception {
       MimeMessage mimeMessage = javaMailSender.createMimeMessage();

       MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
       helper.setFrom(from);
       helper.setTo(to);
       helper.setSubject("主题：模板邮件");

       Map<String, Object> model = new HashMap<>();
       model.put("username", "xdj");

       Template template = freemarkerConfiguration.getTemplate("template.ftl");
       String text = FreeMarkerTemplateUtils.processTemplateIntoString(template ,model);

       // true 表示启动HTML格式的邮件
       helper.setText(text, true);

       javaMailSender.send(mimeMessage);
    }

}
