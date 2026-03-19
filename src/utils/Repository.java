package utils;
import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Repository<T> {

    private final String filePath;
    private final Function<Map<String, String>, T> mapper;

    public Repository(String filePath, Function<Map<String, String>, T> mapper) {
        this.filePath = filePath;
        this.mapper = mapper;
    }

    // Load all objects from JSON array
    public List<T> loadAll() {
        try {
            String json = readFile();

            List<Map<String, String>> mapList = JsonUtil.parseList(json);
            List<T> result = new ArrayList<>();

            for (Map<String, String> m : mapList) {
                result.add(mapper.apply(m));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Save one new object
    public void save(Map<String, String> map) {
        List<Map<String, String>> list;

        try {
            list = new ArrayList<>(JsonUtil.parseList(readFile()));
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        list.add(map);

        writeFile(JsonUtil.listToJson(list));
    }

    private String readFile() throws Exception {
        File f = new File(filePath);
        if (!f.exists()) return "[]";

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
        }

        return sb.toString();
    }

    private void writeFile(String data) {
        try {
            File f = new File(filePath);

            if (f.getParentFile() != null) {
                f.getParentFile().mkdirs();
            }

            try (FileWriter fw = new FileWriter(f)) {
                fw.write(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}