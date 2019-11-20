/**
 * The Least Recently Used page replacement algorithm. When replacing a page, the algorithm will replace the page that has not been used for the longest period of time.
 */
public class LeastRecentlyUsed extends PageReplacer {
    
    /**
     * Constructor method for PageReplacer to initialize member variables and PageFrame array.
     * @param ref The reference string.
     * @param frames The number of pages frame.
     * @param print True if desired to print out each step of algorithm.
     */
    public LeastRecentlyUsed(String ref, int frames, boolean print) {
        super(ref, frames, print);
    }
    
    /**
     * The LRU method to replace pages in the page table. It checks when each page was last used, and replaces the page that was used least recently.
     * @param num The number of the page to insert into the page table.
     */
    @Override
    protected void replacePage(int num) {
        int replaceIndex = 0;
        int dist = 0;
        for(int i=0; i<this.pages.length; i++) { //get page that will be used last
            if (this.pages[i] == null) {
                replaceIndex = i;
                break;
            }
            if (this.lastUse(this.pages[i].num) > dist) {
                replaceIndex = i;
                dist = this.lastUse(this.pages[i].num);
            }
        }
        this.pages[replaceIndex] = new PageFrame(num, currIndex, currIndex); //replace page in frame
        this.pageFaults++;
    }
}