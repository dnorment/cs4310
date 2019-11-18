public abstract class PageReplacer {

    protected String refString;
    protected PageFrame[] pages;
    int currIndex;
    protected int pageFaults;

    public PageReplacer(String ref, int frames) {
        this.refString = ref;
        this.pages = new PageFrame[frames];
        this.currIndex = 0;
        this.pageFaults = 0;
    }

    public int run() {
        for (int i=0; i<refString.length(); i++) { //for length of reference string (30)
            int currPageNum = Integer.parseInt(refString.substring(i,i+1)); //get page number requested by string
            if (!this.contains(currPageNum)) { //if page not in page table
                this.replacePage(currPageNum);
                this.printStep(true);
            } else {
                this.printStep(false);
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
        int dist = 1;
        for (int i=currIndex-1; i>=0; i--) {
            if (Integer.parseInt(refString.substring(i,i+1)) == num) {
                return dist;
            }
            dist++;
        }
        return Integer.MAX_VALUE;
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