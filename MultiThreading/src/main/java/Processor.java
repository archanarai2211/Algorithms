import java.util.LinkedList;

public class Processor {

    private LinkedList<Integer> list= new LinkedList<>();
    private int CAPACITY = 10;
    private Object lock = new Object();


    /*
    Once you know that you need to
    call wait from synchronized context and on the shared object,
    next thing is to avoid mistake made by several Java developer
    by calling wait() method inside if block instead of while loop.
    Since you call wait inside a conditional block e.g. producer thread
    should call wait() if queue is full, first instinct goes towards using if block,
    but calling wait() inside if block can lead to subtle bugs because it's possible
    for thread to wake up spuriously even when waiting condition is not changed.
    If you don't check the condition again after waking up by using a loop,
    you will take the wrong action which may cause problem e.g. trying to insert
    item on a full queue or trying to consume from an empty queue.
    That's why you should always call wait and notify method from a
    loop and not from if block
    */
    public void produce() throws InterruptedException {
            int value = 0;
            while(true){
                synchronized (lock){
                    while (CAPACITY==list.size()){
                        System.out.println("test1");
                        lock.wait();
                    }
                    list.add(value++);
                    lock.notify();
                }
            }
    }

    public void consume() throws InterruptedException {

        while(true){
            synchronized (lock){
                while (0==list.size()){
                    System.out.println("test2");
                    lock.wait();
                }
                System.out.println("List size: "+list.size());
                int value = list.removeFirst();
                System.out.println("Removed: "+value);
                lock.notify();
            }
           // Thread.sleep(500);
        }
    }
}
