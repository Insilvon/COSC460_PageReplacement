import java.util.LinkedList;

/**
 * Custom "stack" ADT which uses a LinkedList to store the loaded pages in order
 * of a custom priority. (LRU)
 */
public class PageStack {
    //The core data structure
    private LinkedList<Integer> stack = new LinkedList<>();

    /**
     * Method which locates a pre-existing page in the stack, removing it and reinserting it. (No faults)
     * @param page - page to reset
     */
    public void reset(int page){
        stack.removeFirstOccurrence(page);
        insert(page);
    }

    /**
     * Method which will remove the least-recently-used page from the stack.
     * @param page - page to insert after eviction
     * @return value - integer representing the least recently used page to remove from the buffer.
     */
    public int evict(int page){
        int value = stack.removeLast();
        insert(page);
        return value;
    }

    /**
     * Method which simply adds the page to the top of the "Stack."
     * @param page = page to insert
     */
    public void insert(int page){
        stack.addFirst(page);
    }

}
