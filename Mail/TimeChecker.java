package dao.Mail;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeChecker {
    private final EmailSender emailSender;
    private final CountChecker countChecker;
    private final int intervalMinutes;

    public TimeChecker(int intervalMinutes) {
        this.emailSender = new EmailSender();
        this.countChecker = new CountChecker();
        this.intervalMinutes = intervalMinutes;
    }

    public void startChecking() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable checkTask = () -> {
            try {
                LocalDateTime currentTime = LocalDateTime.now();
                System.out.println("thời gian hiện tại " + currentTime);

                emailSender.sendEmailsForTime(currentTime);

                boolean hasMore = countChecker.checkAndUpdateCount();
                System.out.println("Has more schedules to send: " + hasMore);
                // Không dừng scheduler ngay để debug
                // if (!hasMore) {
                //     System.out.println("No more emails to send. Stopping scheduler.");
                //     scheduler.shutdown();
                // }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        //****
        scheduler.scheduleAtFixedRate(checkTask, 0, intervalMinutes, TimeUnit.MINUTES);
    }
}