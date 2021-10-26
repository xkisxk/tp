package seedu.duke.flashcard;

import org.fusesource.jansi.Ansi;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Implements the Countdown class, which creates a timer which counts down
 * from the given time upon start and displays the remaining time. Once
 * the time runs out, displays the given message instead.
 */
public class Countdown {

    private Timer timer;
    private CountdownTimerTask countdownTimerTask;
    private int startValue;
    private String timesUpMessage;
    private boolean isRunning;

    /**
     * Constructor for the class Countdown. Creates a Countdown object
     * with the given startValue and timesUpMessage to be displayed
     * when the time elapsed reaches zero.
     * @param startValue
     * @param timesUpMessage
     */
    public Countdown(int startValue, String timesUpMessage) {
        this.timer = new Timer();
        this.startValue = startValue;
        this.timesUpMessage = timesUpMessage;
        this.countdownTimerTask = new CountdownTimerTask(startValue, timesUpMessage);
        this.isRunning = false;
    }

    private class CountdownTimerTask extends TimerTask {

        private int startValue;
        private int timeRemaining;
        private String timesUpMessage;

        CountdownTimerTask(int startValue, String timesUpMessage) {
            this.startValue = startValue;
            this.timeRemaining = startValue;
            this.timesUpMessage = timesUpMessage;
        }

        private void countDown() {
            if (this.timeRemaining >= 0) {
                this.timeRemaining--;
            }
        }

        private void display() {
            if (this.timeRemaining < 0) {
                Countdown.this.stop();
                return;
            }

            String displayed = (this.timeRemaining == 0) ? this.timesUpMessage
                    : "Time remaining: " + this.timeRemaining;

            Ansi ansi = Ansi.ansi()
                    .saveCursorPosition()
                    .cursorUpLine()
                    .cursorToColumn(1)
                    .eraseLine()
                    .append(displayed)
                    .restoreCursorPosition();

            System.out.print(ansi);
        }

        /**
         * Starts the TimerTask. Displays a countdown starting
         * from the initialised time.
         */
        @Override
        public void run() {
            display();
            countDown();
        }
    }

    /**
     * Starts the timer with the initialised countdown value.
     * Displays the countdown in the standard output.
     */
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

    public boolean isRunning() {
        return this.isRunning;
    }
}
