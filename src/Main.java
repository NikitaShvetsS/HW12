import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileNavigator fileNavigator = new FileNavigator();


        FileData file1 = new FileData("files.txt", 1024, "/path/to/file");
        FileData file2 = new FileData("firstApp.java", 2048, "/path/to/file");
        FileData file3 = new FileData("image.jpg", 3072, "/path/to/image");
        FileData file4 = new FileData("document.docx", 4096, "/path/to/document");

        fileNavigator.add(file1);
        fileNavigator.add(file2);
        fileNavigator.add(file3);
        fileNavigator.add(file4);

        String path = "/path/to/file";
        List<FileData> foundFiles = fileNavigator.find(path);
        System.out.println("Files at path " + path + ":");
        for (FileData file : foundFiles) {
            System.out.println(file.getName() + " - " + file.getSize() + " bytes");
        }

        long maxSize = 2000;
        List<FileData> filteredFiles = fileNavigator.filterBySize(maxSize);
        System.out.println("Files at path " + path + " with size <= " + maxSize + " bytes:");
        for (FileData file : filteredFiles) {
            System.out.println(file.getName() + " - " + file.getSize() + " bytes");
        }

        fileNavigator.remove(path);
        System.out.println("Files at path " + path + " after removal:");
        List<FileData> remainingFiles = fileNavigator.find(path);
        for (FileData file : remainingFiles) {
            System.out.println(file.getName() + " - " + file.getSize() + " bytes");
        }

        List<FileData> sortedFiles = fileNavigator.sortBySize();
        System.out.println("All files sorted by size: ");
        for (FileData file : sortedFiles) {
            System.out.println(file.getName() + " - " + file.getSize() + " bytes");
        }
    }
}