package seedu.cardli.testing;

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
    private boolean isRunning;

    /**
     * Constructor for the class Countdown. Creates a Countdown object
     * with the given startValue and timesUpMessage to be displayed
     * when the time elapsed reaches zero.
     * @param startValue Value of the timer to start from
     * @param timesUpMessage Message to be displayed when timer expires
     */
    public Countdown(int startValue, String timesUpMessage) {
        this.timer = new Timer();
        this.countdownTimerTask = new CountdownTimerTask(startValue, timesUpMessage);
        this.isRunning = false;
    }

    /**
     * Private nested class extending a TimerTask that counts down
     * and displays the timer on the standard output.
     */
    private class CountdownTimerTask extends TimerTask {

        private int timeRemaining;
        private int ticks;
        private final String timesUpMessage;
        private boolean willDisplay;

        CountdownTimerTask(int startValue, String timesUpMessage) {
            this.timeRemaining = startValue;
            this.ticks = 0;
            this.timesUpMessage = timesUpMessage;
            this.willDisplay = false;
        }

        private void countDown() {
            if (this.timeRemaining <= 0) {
                Countdown.this.stop();
                return;
            }

            this.ticks = (this.ticks < 10) ? (this.ticks + 1) : 0;
            // update timeRemaining when 1s has passed
            this.timeRemaining = (this.ticks == 10) ?
                    (this.timeRemaining - 1) : this.timeRemaining;

            if (this.ticks > 0) {
                // allow Countdown to start displaying after 0.1s since start()
                // this allows some synchronous tasks called after to complete first
                this.willDisplay = true;
            }
        }

        private void display() {
            if (!this.willDisplay) {
                // do not display
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
        int period = 100; // repeat at intervals of 0.1s
        timer.scheduleAtFixedRate(this.countdownTimerTask, delay, period);
    }

    /**
     * Stops the timer. The timer will no longer run or display.
     */
    public void stop() {
        this.isRunning = false;
        timer.cancel();
    }

    /**
     * Returns the status of the timer, namely whether it is
     * running or not.
     * @return true if the timer is running, false if not
     */
    public boolean isRunning() {
        return this.isRunning;
    }
}
