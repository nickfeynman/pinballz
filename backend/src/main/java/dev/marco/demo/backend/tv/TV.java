package dev.marco.demo.backend.tv;

import dev.marco.demo.backend.process.ProcessExecutor;
import dev.marco.demo.backend.process.ProcessResult;
import org.springframework.stereotype.Component;

@Component
public class TV {

    ProcessResult turnOn() {
        ProcessExecutor processExecutor = new ProcessExecutor();
        return processExecutor.execute("lgtv --name MyTV --ssl on");
    }
    ProcessResult turnOnScreen() {
        ProcessExecutor processExecutor = new ProcessExecutor();
        return processExecutor.execute("lgtv --name MyTV --ssl screenOn");
    }

    ProcessResult turnOffScreen() {
        ProcessExecutor processExecutor = new ProcessExecutor();
        return processExecutor.execute("lgtv --name MyTV --ssl screenOff");
    }

    ProcessResult setHdmi() {
        ProcessExecutor processExecutor = new ProcessExecutor();
        return processExecutor.execute("lgtv --name MyTV --ssl setInput HDMI_1");
    }
}
