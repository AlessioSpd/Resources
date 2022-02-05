public class MyFile implements MyFileSystem {

    private String myFileName = null;

    public MyFile(String myFileName) {
        this.myFileName = myFileName;
    }

    @Override
    public void print() {
        System.out.println(myFileName);
    }

    @Override
    public void add(MyFileSystem myFileNameSystem) {
        System.out.println("Impossible to add!");
    }

    @Override
    public void remove(MyFileSystem myFileNameSystem) {
        System.out.println("Impossible to remove!");
    }
}