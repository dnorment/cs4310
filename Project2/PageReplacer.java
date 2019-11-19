public abstract class PageReplacer {

    protected String refString;
    protected PageFrame[] pages;
    int currIndex;
    protected int pageFaults;
    boolean print;

    public PageReplacer(String ref, int frames, boolean print) {
        this.refString = ref;
        this.pages = new PageFrame[frames];
        this.currIndex = 0;
        this.pageFaults = 0;
        this.print = print;
    }

    public int run() {
        for (int i=0; i<refString.length(); i++) { //for length of reference string (30)
            int currPageNum = Integer.parseInt(refString.substring(i,i+1)); //get page number requested by string
            if (!this.contains(currPageNum)) { //if page not in page table
                this.replacePage(currPageNum);
                if (this.print) this.printStep(true);
            } else {
                if (this.print) this.printStep(false);
            }
            this.currIndex++;
        }
        return this.pageFaults;
    }

    protected abstract void replacePage(int num);
    
    protected boolean contains(int num) {
        for (int i=0; i<pages.length; i++) {
            if (pages[i] != null && pages[i].num == num) {
                return true;
            }
        }
        return false;
    }

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