package dev.marco.demo.backend.tv;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static java.lang.Thread.sleep;

//@Component
public class TestTvScheduler {

    private TV tv;

    public TestTvScheduler(TV tv) {
        this.tv = tv;
        System.out.println("Created TestTVScheduler");
    }

    @Scheduled(cron = "0 */2 * * * *")
    public void executeEveryEvenMinute() {
        tvon();
    }

    //@Scheduled(cron = "1-59/2 * * * * *")
    public void executeEveryOddMinute() {
        tvoff();
    }

    public void tvon() {
        System.out.println("turning tv on.  Time = " + Instant.now());
        tv.turnOn();
        pause(2);
        tv.turnOnScreen();
        pause(2);
        tv.setHdmi();
    }

    public void tvoff() {
        System.out.println("turning tv off.  Time = " + Instant.now());
        tv.setHdmi();
        pause(2);
        tv.turnOffScreen();
    }

    private void pause( int seconds){
        try {
            sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
