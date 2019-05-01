import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * FIFO: Page replacement algorithm representing First-In-First-Out replacement.
 */
public class FIFO extends ReplacementAlgorithm {
    //Queue which holds the current order of page eviction
    private Queue<Integer> pages = new LinkedList<>();
    //Buffer which contains the actively loaded pages
    private ArrayList<Integer> buffer = new ArrayList<>();

    /**
     * Constructor method which generates a new FIFO algorithm
     * using the specified pageFrameCount.
     * @param pageFrameCount number of frames in the buffer.
     */
    public FIFO(int pageFrameCount) {
        super(pageFrameCount);
        this.buffer.ensureCapacity(pageFrameCount);
    }
    public void printBuffer(){
        for(int item : buffer){
            System.out.print(item+" ");
        }
        System.out.println();
    }

    @Override
    /**
     * Method which checks the status of a page in the buffer.
     * If it is already loaded, returns. Otherwise, will insert and update the queue accordingly.
     */
    public void insert(int pageNumber) {
        // Is the item already in the buffer?
        if(this.buffer.contains(pageNumber)) {
            return;
        }
        // Is the buffer even full yet?
        else if(buffer.size()<this.pageFrameCount){
            buffer.add(pageNumber);
        }
        // It is full? Aw, okay. Just evict some poor soul then.
        else {
            int target = buffer.indexOf(pages.remove());
            buffer.set(target, pageNumber);
        }
        this.pageFaultCount++;
        pages.add(pageNumber);
    }

}
