import java.util.ArrayList;

/**
 * LRU: Page Replacement algorithm representing the eviction of the least-recently-used page.
 */
public class LRU extends ReplacementAlgorithm {
    //Core structure which acts like a priority queue.
    private PageStack pages = new PageStack();
    //Buffer which contains the actual frames.
    private ArrayList<Integer> buffer = new ArrayList<>();

    /**
     * Constructor method to create a new LRU algorithm using the given number of buffer pages.
     * @param pageFrameCount - number of frames in the buffer.
     */
    public LRU(int pageFrameCount) {
        super(pageFrameCount);
        this.buffer.ensureCapacity(pageFrameCount);
    }

    @Override
    /**
     * Method which will insert a new page into the buffer, assuming it is not already present.
     * If it is already loaded, it will reset the position of the page in the stack.
     * Otherwise, it will either insert if there is a free frame or evict an existing LRU frame.
     */
    public void insert(int pageNumber) {
        if(this.buffer.contains(pageNumber)) {
            pages.reset(pageNumber);
            return;
        }
        // Is the buffer even full yet?
        else if(buffer.size()<this.pageFrameCount){
            buffer.add(pageNumber);
            pages.insert(pageNumber);
        }
        // It is full? Aw, okay. Just evict some poor soul then.
        else {
            int homelessPage = pages.evict(pageNumber);
            this.buffer.set(buffer.indexOf(homelessPage), pageNumber);
        }
        this.pageFaultCount++;
    }
}
