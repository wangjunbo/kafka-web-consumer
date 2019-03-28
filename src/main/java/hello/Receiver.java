package hello;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {

    public static GreetingController greetingController;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "test")
    public void receiveTest(String payload) throws Exception {
//        System.out.println(payload);
        greetingController.messagingTemplate.convertAndSend("/topic/test", payload);
        latch.countDown();
    }
    @KafkaListener(topics = "tracker")
    public void receiveTracker(String payload) throws Exception {
//        System.out.println(payload);
        greetingController.messagingTemplate.convertAndSend("/topic/tracker", payload);
        latch.countDown();
    }
    @KafkaListener(topics = "tracker-view")
    public void receiveTrackerView(String payload) throws Exception {
//        System.out.println(payload);
        greetingController.messagingTemplate.convertAndSend("/topic/tracker-view", payload);
        latch.countDown();
    }

    @KafkaListener(topics = "event")
    public void receiveTrackerEvent(String payload) throws Exception {
//        System.out.println(payload);
        greetingController.messagingTemplate.convertAndSend("/topic/event", payload);
        latch.countDown();
    }

    @KafkaListener(topics = "form")
    public void receiveForm(String payload) throws Exception {
//        System.out.println(payload);
        greetingController.messagingTemplate.convertAndSend("/topic/form", payload);
        latch.countDown();
    }
}
