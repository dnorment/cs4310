/**
 * The PageReplacer class has fields and methods needed for running the page replacement algorithms. It is abstract and subclasses (the algorithms) must override the behavior for replacing pages.
 */
public abstract class PageReplacer {

    protected String refString; //reference string
    protected PageFrame[] pages; //page table
    int currIndex; //current index
    protected int pageFaults; //number of page faults
    boolean print; //true if desired to print out the intermediate page tables

    /**
     * Constructor method for PageReplacer to initialize member variables and PageFrame array.
     * @param ref The reference string.
     * @param frames The number of pages frame.
     * @param print True if desired to print out each step of algorithm.
     */
    public PageReplacer(String ref, int frames, boolean print) {
        this.refString = ref;
        this.pages = new PageFrame[frames];
        this.currIndex = 0;
        this.pageFaults = 0;
        this.print = print;
    }

    /**
     * Runs the page replacement algorithm using the reference string.
     * @return The number of page faults that happened during the execution of the algorithm.
     */
    public int run() {
        for (int i=0; i<refString.length(); i++) { //for length of reference string (30)
            int currPageNum = Integer.parseInt(refString.substring(i,i+1)); //get page number requested by string
            if (!this.contains(currPageNum)) { //if page not in page table
                this.replacePage(currPageNum);
                if (this.print) this.printStep(true); //print with page fault
            } else {
                if (this.print) this.printStep(false); //print without page fault
            }
            this.currIndex++;
        }
        return this.pageFaults;
    }

    /**
     * Inserts a page into the page table, behavior must be defined in subclasses. Should only be used when the page is not already in the page table.
     * @param num The number of the page to insert into the page table.
     */
    protected abstract void replacePage(int num);
    
    /**
     * Checks if a given page already exists in the page table.
     * @param num The number of the page to check.
     * @return True if the page exists in the page table.
     */
    protected boolean contains(int num) {
        for (int i=0; i<pages.length; i++) {
            if (pages[i] != null && pages[i].num == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the "distance" between the current step and the index of when the page was last used.
     * @param num The number of the page to check its last use.
     * @return The distance between the current step and when the page was last used.
     */
    protected int lastUse(int num) {
        int dist = 0;
        for (int i=currIndex; i>=0; i--) {
            if (Integer.parseInt(refString.substring(i,i+1)) == num) {
                return dist;
            }
            dist++;
        }
        return -1;
    }

    /**
     * Gets the "distance" between the current step and the index of when the page will be next used.
     * @param num The number of the page to check its next use.
     * @return The distance between the current step and when the page is next used.
     */
    protected int nextUse(int num) {
        int dist = 1;
        for (int i=currIndex+1; i<refString.length(); i++) {
            if (Integer.parseInt(refString.substring(i,i+1)) == num) {
                return dist;
            }
            dist++;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Prints out the current reference string page, page table, and "f" if there was a page fault.
     * @param fault True if there is a page fault at this step.
     */
    protected void printStep(boolean fault) {
        String out = this.refString.substring(this.currIndex,this.currIndex+1) + " [ ";
        for (PageFrame f : this.pages) {
            if (f == null) {
                out += "_ ";
            } else {
                out += f.num + " ";
            }
        }
        out += "]";
        if (fault) out += " f";
        System.out.println(out);
    }
}