package model;

// Represent a stop watch having time needed to be spent and time spent on a task
public class Clock {

    private int time = 0;           // Total time added to stopwatch in seconds
    protected int timeSpent = 0;    // time that has been spent in seconds



    // REQUIRES: seconds > 0
    // MODIFIES: this
    // EFFECTS: adds seconds to time
    public void addTime(int seconds) {
        time += seconds;
    }


    // REQUIRES: time - timeSpent > 0
    // MODIFIES: this
    // EFFECTS: spends one second
    public void tickSecond() {
        timeSpent++;
    }

    // EFFECTS: returns time left to be spent
    public int getTimeLeft() {
        return time - timeSpent;
    }

    // EFFECTS: returns total time added to stopwatch in minutes and seconds
    public String getTimeInMinSec() {
        return convertTimeToMinSec(time);
    }

    // EFFECTS: returns time currently left to be spent in minutes and seconds
    public String getTimeLeftInMinSec() {
        String timeLeftInMinSec = convertTimeToMinSec(getTimeLeft());
        return timeLeftInMinSec;
    }

    // EFFECTS: resets the time spent to 0
    protected void resetTimeLeft() {
        timeSpent = 0;
    }

    //REQUIRES: timeInSecond > 0
    //MODIFIES: this
    // EFFECTS: converts time in seconds into minutes and seconds;
    //          return only seconds if timeInSecond < 60
    private String convertTimeToMinSec(int timeInSecond) {
        String timeInMinSec;
        int minutes = timeInSecond / 60;
        int seconds = timeInSecond % 60;

        if (timeInSecond >= 60) {
            timeInMinSec = minutes + " m " + seconds + " s";
        } else {
            timeInMinSec = seconds + " s";
        }
        return timeInMinSec;
    }


    public int getTime() {
        return time;
    }

}

