public class Main {

    public static void main(String[] args) {
        Common common = new Common();
        while (true){
            common.selectRoot();
            common.createNewFile();
            common.deleteFile();
            common.openFile();
            common.comeBackFile();
            common.help();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
