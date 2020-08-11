public class RunTime{
    private long startTime;
    private long endTime;

    public RunTime(){
        startTime = System.nanoTime();
    }


    public void setEndTime() {
        this.endTime = System.nanoTime();
    }

    public long computeTime(){
        return endTime - startTime;
    }
}
