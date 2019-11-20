/**
 * The First In First Out page replacement algorithm. When replacing a page, the algorithm will replace the oldest page.
 */
public class FirstInFirstOut extends PageReplacer {

    /**
     * Constructor method for PageReplacer to initialize member variables and PageFrame array.
     * @param ref The reference string.
     * @param frames The number of pages frame.
     * @param print True if desired to print out each step of algorithm.
     */
    public FirstInFirstOut(String ref, int frames, boolean print) {
        super(ref, frames, print);
    }

    /**
     * The FIFO method to replace pages in the page table. It checks when each page was created, and replaces the page that was created first.
     * @param num The number of the page to insert into the page table.
     */
    @Override
    protected void replacePage(int num) {
        int replaceIndex = 0;
        int age = Integer.MAX_VALUE;
        for(int i=0; i<this.pages.length; i++) { //get page that was used last
            if (this.pages[i] == null) {
                replaceIndex = i;
                break;
            }
            if (this.pages[i].created < age) { //replace highest age
                replaceIndex = i;
                age = this.pages[i].created;
            }
        }
        this.pages[replaceIndex] = new PageFrame(num, currIndex, currIndex); //replace page in frame
        this.pageFaults++;
    }
}