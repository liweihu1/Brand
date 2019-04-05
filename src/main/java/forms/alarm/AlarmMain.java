package forms.alarm;

import forms.alarm.controller.AlarmController;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmMain {
    public static void main(String[] argv) throws Exception {
        Timer t = new Timer();
        AlarmController c = new AlarmController();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                c.sendMessage();
            }
        }, 0, 10000);
    }
}
