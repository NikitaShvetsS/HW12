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

    public List<FileData> filterBySize(String path, long maxSize) {
        List<FileData> filteredFiles = new ArrayList<>();
        String key = normalizePath(path);

        if (files.containsKey(key)) {
            List<FileData> fileList = files.get(key);
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
        List<FileData> allFiles = getAllFiles();
        Collections.sort(allFiles, Comparator.comparingLong(FileData::getSize));
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
