class threadObject extends Thread{
    @Override
    public void run() {
        try {
            String threadString = "Thread "+Thread.currentThread().getId();
            System.out.println(threadString+" is running.");
            System.out.println(threadString+" 1");
            Thread.sleep(2);
            System.out.println(threadString+" 2");
            Thread.sleep(2);
            System.out.println(threadString+" 3");
            Thread.sleep(2);
        } catch (Exception ignored){

        }
    }
}
class runner{
    public static void main(String[] args) {
        int n = 8;
        for(int i =0;i<n;i++){
            threadObject thread = new threadObject();
            thread.start();
        }
    }
}