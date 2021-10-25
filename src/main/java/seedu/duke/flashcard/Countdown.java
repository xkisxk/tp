package seedu.duke.flashcard;

import java.util.Timer;
import java.util.TimerTask;

public class Countdown {

    private Timer timer; // could also use a swing timer
    private CountdownTimerTask countdownTimerTask;
    private int startValue;
    private boolean isRunning; // if no need to pause timer, this is not needed

    public Countdown(int startValue) {
        this.timer = new Timer();
        this.startValue = startValue;
        this.countdownTimerTask = new CountdownTimerTask(startValue);
        this.isRunning = false;
    }

    private class CountdownTimerTask extends TimerTask {
        private String SAVE_CURSOR_POSITION = "\u001b7";
        private String CLEAR_PREVIOUS_LINE = "\u001b[1A\r\u001b[K";
        private String RESTORE_CURSOR_POSITION = "\u001b8";

        int timeRemaining;

        CountdownTimerTask(int startValue) {
            this.timeRemaining = startValue;
        }

        private void countDown() {
            if (this.timeRemaining >= 0) {
                this.timeRemaining--;
            }
        }

        private void display() {
            if (this.timeRemaining < 0) {
                return;
            }
            String displayed = (this.timeRemaining == 0) ? "TIME'S UP!" : String.valueOf(timeRemaining) ;
            String result = SAVE_CURSOR_POSITION + CLEAR_PREVIOUS_LINE + displayed + RESTORE_CURSOR_POSITION;
            System.out.print(result);
        }

        @Override
        public void run() {
            display();
            countDown();
        }
    }

    public void start() {
        this.isRunning = true;
        int delay = 0;
        int period = 1000;
        timer.scheduleAtFixedRate(this.countdownTimerTask, delay, period);
    }

    public void stop() {
        this.isRunning = false;
        timer.cancel();
    }

    public static void main(String[] args) {
        Countdown countdown = new Countdown(10);
        countdown.start();
        System.out.println();
    }

}
