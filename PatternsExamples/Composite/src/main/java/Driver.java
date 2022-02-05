public class Driver {
    public static void main(String[] args) {
        MyFileSystem f2 = new MyFile("File2");
        MyFileSystem f3 = new MyFile("File3");
        MyFileSystem c2 = new MyFolder("Folder2");
        c2.add(f2);
        c2.add(f3);

        MyFileSystem f1 = new MyFile("File1");
        MyFileSystem c1 = new MyFolder("Folder1");
        c1.add(f1);
        c1.add(c2);

        c1.print();
    }
}
