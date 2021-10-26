package seedu.duke.flashcard;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

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

//        private Ansi ansi;
        private String SAVE_CURSOR_POSITION = "\u001b7";
        // private Ansi thing = ansi.cursorUpLine(1);
        private String CLEAR_PREVIOUS_LINE = "\u001b[1F\r\u001b[K\u001b[1B";
        private String RESTORE_CURSOR_POSITION = "\u001b8";

        int startValue;
        int timeRemaining;

        CountdownTimerTask(int startValue) {

//            this.ansi = Ansi.ansi();
            this.startValue = startValue;
            this.timeRemaining = startValue;
        }

        private void countDown() {
            if (this.timeRemaining >= 0) {
                this.timeRemaining--;
            }
        }

        private boolean isStart() {
            return this.startValue == this.timeRemaining;
        }

//        clearScreen(PrintStream out) {
//            out.print(ansi().eraseScreen(Ansi.Erase.ALL).toString());
//            out.print(ansi().cursor(1, 1).toString());
//            out.flush();

        private void display() {
            if (this.timeRemaining < 0) {
                System.out.println();
                this.cancel();
                return;
            }

            String displayed = (this.timeRemaining == 0) ? "TIME'S UP!" : String.valueOf(timeRemaining) ;
            String result = (isStart()) ? displayed : displayed + System.lineSeparator();

            Ansi ansi = Ansi.ansi()
                    .saveCursorPosition()
                    .cursorUpLine()
                    .cursorToColumn(1)
                    .eraseLine()
                    .append(displayed)
                    .restoreCursorPosition();
//            Ansi ansi = Ansi.ansi().eraseLine().append(displayed);

            System.out.println(ansi);

//            AnsiConsole.out().print(result);
        }

        /**
         * Starts the TimerTask. Displays a countdown.
         */
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
        AnsiConsole.systemInstall();
        System.out.println("Starting");
        countdown.start();
        System.out.println("Line after");
    }

}
