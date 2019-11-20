/**
 * PageFrame is a class to hold the data of page number, when the page was created, and when the page was last used.
 */
public class PageFrame {
    
    public int num;
    public int created = -1;
    public int lastUsed = -1;

    /**
     * Creates a new page frame.
     * @param num The number of the page.
     * @param created The step at which the page was inserted.
     * @param lastUsed The step at which the page was last accessed.
     */
    public PageFrame(int num, int created, int lastUsed) {
        this.num = num;
        this.created = created;
        this.lastUsed = lastUsed;
    }
}