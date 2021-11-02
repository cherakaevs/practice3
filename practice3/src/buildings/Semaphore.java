package buildings;

public class Semaphore {
    private int permits;

    public Semaphore(int p){
        permits = p;
    }

    public synchronized void acquire() throws InterruptedException{
        if(permits > 0){
            Thread.sleep(1000);
            permits--;
        }
        else {
            this.wait();
            permits--;
        }
    }

    public synchronized void release() throws InterruptedException{
        permits++;

        if(permits > 0){
            this.notify();
        }
    }
}
