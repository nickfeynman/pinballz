package dev.marco.demo.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledFuture;

@Component
public class ScreenSaver {

    private TaskScheduler taskScheduler;

    private HdmiSwitch hdmiSwitch;

    private ScheduledFuture yourTaskState;
    private int minutesToAdd = 20;

    @Autowired
    public ScreenSaver(TaskScheduler taskScheduler, HdmiSwitch hdmiSwitch) {
        this.taskScheduler = taskScheduler;
        this.hdmiSwitch = hdmiSwitch;
        start();
    }

    public void start(){
        Instant currentTimeStampInUTC = Instant.now();
        Instant inTheFuture = currentTimeStampInUTC.plus(minutesToAdd, ChronoUnit.MINUTES);
        yourTaskState =
                taskScheduler.schedule(
                        new ChangeToScreenSaverInput(this.hdmiSwitch), inTheFuture);
        System.out.println("Scheduled screensaver to run in " + minutesToAdd + " minutes.");
    }
    public void stop(){
        if (this.yourTaskState != null) {
            yourTaskState.cancel(false);
        }
        System.out.println("Cancelled screen saver schedule");
    }
    class ChangeToScreenSaverInput implements Runnable {

        private HdmiSwitch hdmiSwitch;
        public ChangeToScreenSaverInput(HdmiSwitch hdmiSwitch) {
            this.hdmiSwitch = hdmiSwitch;
        }

        @Override
        public void run() {
            System.out.println("Changing input to 8");
            this.hdmiSwitch.setChannel(8);
        }
    }
}
