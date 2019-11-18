public class PageFrame {
    
    public int num;
    public int created = -1;
    public int lastUsed = -1;

    public PageFrame(int num, int created, int lastUsed) {
        this.num = num;
        this.created = created;
        this.lastUsed = lastUsed;
    }
}