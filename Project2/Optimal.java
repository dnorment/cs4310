/**
 * The Optimal page replacement algorithm. When replacing a page, the algorithm will replace the page that will not be used for the longest period of time.
 */
public class Optimal extends PageReplacer {
    
    /**
     * Constructor method for PageReplacer to initialize member variables and PageFrame array.
     * @param ref The reference string.
     * @param frames The number of pages frame.
     * @param print True if desired to print out each step of algorithm.
     */
    public Optimal(String ref, int frames, boolean print) {
        super(ref, frames, print);
    }

    /**
     * The optimal method to replace pages in the page table. It checks when each page will be used next and replaces the page that will not be used for the longest period of time.
     * @param num The number of the page to insert into the page table.
     */
    @Override
    protected void replacePage(int num) {
        int replaceIndex = 0;
        int dist = 0;
        for(int i=0; i<this.pages.length; i++) { //get page that will be used last
            if (this.pages[i] == null) { //if frame is empty replace it
                replaceIndex = i;
                break;
            }
            if (this.nextUse(this.pages[i].num) > dist) { //next use is longer than current next use
                replaceIndex = i;
                dist = this.nextUse(this.pages[i].num);
            }
        }
        this.pages[replaceIndex] = new PageFrame(num, currIndex, currIndex); //replace page in frame
        this.pageFaults++;
    }
}