import java.util.ArrayList;

public class MyFolder implements MyFileSystem {

    private String myFolderName;
    private ArrayList<MyFileSystem> folder;

    public MyFolder(String myFolderName) {
        this.myFolderName = myFolderName;
        folder = new ArrayList<MyFileSystem>();
    }

    @Override
    public void print() {
        System.out.println(myFolderName);
        for (int i = 0; i < folder.size(); i++) {
            folder.get(i).print();
        }
    }

    @Override
    public void add(MyFileSystem myFileSystem) {
        folder.add(myFileSystem);
    }

    @Override
    public void remove(MyFileSystem myFileSystem) {
        folder.remove(myFileSystem);
    }
}
