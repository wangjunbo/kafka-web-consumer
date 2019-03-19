package hello;

import hello.model.Greeting;
import hello.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class GreetingController {


    public final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public GreetingController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        Receiver.greetingController = this;
    }


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }


//    @SendTo("/topic/greetings")
//    public Greeting sendto(String message) throws Exception {
//        System.out.println("hello 1");
//        return new Greeting("Hello 1, " + HtmlUtils.htmlEscape(message) + "!");
//    }


//    /**
//     * 定时推送消息
//     */
//    @Scheduled(fixedRate = 1000)
//    public void callback() {
//        // 发现消息
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(new Date()));
//        messagingTemplate.convertAndSend("/topic/greetings", "定时推送消息时间: " + df.format(new Date()));
//    }



}
