import java.util.*;

public class FileNavigator {

    private Map<String, List<FileData>> files;

    public FileNavigator() {
        files = new HashMap<>();
    }

    public void add(FileData file) {
        String path = file.getPath();
        String key = normalizePath(path);

        if (!files.containsKey(key)) {
            files.put(key, new ArrayList<>());
        }

        List<FileData> fileList = files.get(key);
        fileList.add(file);
    }

    public List<FileData> find(String path) {
        String key = normalizePath(path);
        return files.getOrDefault(key, new ArrayList<>());
    }

    public List<FileData> filterBySize(long maxSize) {
         List<FileData> filteredFiles = new ArrayList<>();

        for (List<FileData> fileList : files.values()) {
            for (FileData file : fileList) {
                if (file.getSize() <= maxSize) {
                    filteredFiles.add(file);
            }
        }
    }

         return filteredFiles;
    }

    public void remove(String path) {
        String key = normalizePath(path);
        files.remove(key);
    }

    public List<FileData> sortBySize() {
        List<FileData> allFiles = new ArrayList<>();
        for (List<FileData> files : files.values()) {
            allFiles.addAll(files);
        }

        Collections.sort(allFiles, (file1, file2) -> Long.compare(file1.getSize(), file2.getSize()));

        return allFiles;
    }

    private List<FileData> getAllFiles() {
        List<FileData> allFiles = new ArrayList<>();
        for (List<FileData> fileList : files.values()) {
            allFiles.addAll(fileList);
        }
        return allFiles;
    }

    private String normalizePath(String path) {
        return path.endsWith("/") ? path : path + "/";
    }

}
