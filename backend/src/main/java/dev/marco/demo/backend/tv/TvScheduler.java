package dev.marco.demo.backend.tv;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
public class TvScheduler {

    private TV tv;

    public TvScheduler(TV tv) {
        this.tv = tv;
        System.out.println("Created TvScheduler");
    }

    /**
     * 0 0 12 * * SUN — At 12:00 PM (noon) every Sunday.
     * 0 0 0 * * MON — At 12:00 AM (midnight) every Monday.
     * 0 0 15 * * MON — At 3:00 PM every Monday.
     * 0 0 0 * * TUE — At 12:00 AM (midnight) every Tuesday.
     * 0 0 15 * * TUE — At 3:00 PM every Tuesday.
     * 0 0 0 * * WED — At 12:00 AM (midnight) every Wednesday.
     * 0 0 15 * * WED — At 3:00 PM every Wednesday.
     * 0 0 0 * * THU — At 12:00 AM (midnight) every Thursday.
     * 0 0 15 * * THU — At 3:00 PM every Thursday.
     * 0 0 0 * * FRI — At 12:00 AM (midnight) every Friday.
     * 0 0 12 * * FRI — At 12:00 PM (noon) every Friday.
     * 0 0 2 * * SAT — At 2:00 AM every Saturday.
     * 0 0 12 * * SAT — At 12:00 PM (noon) every Saturday.
     * 0 0 2 * * SUN — At 2:00 AM every Sunday.
     */
    @Scheduled(cron = "0 0 12 * * SUN")
    public void tvOnSunday() {
        tvOn();
    }

    @Scheduled(cron = "0 0 0 * * MON")
    public void tvOffSunday() {
        tvOff();
    }

    @Scheduled(cron = "0 0 15 * * MON")
    public void tvOnMonday() {
        tvOn();
    }

    @Scheduled(cron = "0 0 0 * * TUE")
    public void tvOffMonday() {
        tvOff();
    }

    @Scheduled(cron = "0 0 15 * * TUE")
    public void tvOnTuesday() {
        tvOn();
    }

    @Scheduled(cron = "0 0 0 * * WED")
    public void tvOffTuesday() {
        tvOff();
    }

    @Scheduled(cron = "0 0 15 * * WED")
    public void tvOnWednesday() {
        tvOn();
    }

    @Scheduled(cron = "0 0 0 * * THU")
    public void tvOffWednesday() {
        tvOff();
    }

    @Scheduled(cron = "0 0 15 * * THU")
    public void tvOnThursday() {
        tvOn();
    }

    @Scheduled(cron = "0 0 0 * * FRI")
    public void tvOffThursday() {
        tvOff();
    }

    @Scheduled(cron = "0 0 12 * * FRI")
    public void tvOnFriday() {
        tvOn();
    }

    @Scheduled(cron = "0 0 2 * * SAT")
    public void tvOffFriday() {
        tvOff();
    }

    @Scheduled(cron = "0 0 12 * * SAT")
    public void tvOnSaturday() {
        tvOn();
    }

    @Scheduled(cron = "0 0 2 * * SUN")
    public void tvOffSaturday() {
        tvOff();
    }

    public void tvOn() {
        tv.turnOn();
        pause(2);
        tv.turnOnScreen();
        pause(2);
        tv.setHdmi();
    }

    public void tvOff() {
        tv.setHdmi();
        pause(2);
        tv.turnOffScreen();
    }

    private void pause ( int seconds){
        try {
            sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

