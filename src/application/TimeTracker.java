package application;


public class TimeTracker {

    private long startTime;
    private boolean isPaused;
    private long timeElapsedDuringPauses;

    private long pauseStartPoint;

    public TimeTracker(){
        this.timeElapsedDuringPauses = 0;

    }

    /**
     * Returns total game time elapsed (i.e., does not include paused time)
     * @return
     */
    public long getTimeElapsed(){

        long totalTimeElapsed = System.currentTimeMillis() - startTime;
        return totalTimeElapsed - timeElapsedDuringPauses;
    }

    /**
     * Starts timing when game starts
     */
    public void startTimer(){

        this.startTime = System.currentTimeMillis();
        this.isPaused = false;
    }


    /**
     * Resumes timer after a pause
     */
    public void resumeTimer(){

        if (isPaused){
            long pauseEndPoint = System.currentTimeMillis();
            long timeElapsedDuringPause = pauseEndPoint - pauseStartPoint;
            timeElapsedDuringPauses += timeElapsedDuringPause;
        }

        isPaused = false;
    }

    /**
     * Pauses timer at start of a pause
     */
    public void pauseTimer(){

        if (!isPaused){
            this.pauseStartPoint = System.currentTimeMillis();
        }

        isPaused = true;

    }
}
