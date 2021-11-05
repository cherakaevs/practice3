package buildings;

public class Semaphore {
    private int permits;
    private int users;

    public Semaphore(int p){
        permits = p;
        users = 0;
    }

    public synchronized void acquire() throws InterruptedException{
        if(permits > 0){
            Thread.sleep(1000);
            permits--;
            users++;
        }
        else {
            this.wait();
            permits--;
            users++;
        }
    }

    public synchronized void release() throws InterruptedException{
        permits++;
        users--;

        if(permits > 0){
            this.notify();
        }
    }

    public boolean isEmpty(){
        if (users == 0)
            return true;
        else
            return false;
    }

}
