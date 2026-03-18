
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
            File f = new File(filePath);
            if (!f.exists()) return new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) sb.append(line);
            br.close();

            List<Map<String, String>> mapList = JsonUtil.parseList(sb.toString());
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

    // Save one new object to the JSON array
    public void save(Map<String, String> map) {
        List<Map<String, String>> list;

        // Load existing
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

        BufferedReader br = new BufferedReader(new FileReader(f));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) sb.append(line);
        br.close();

        return sb.toString();
    }

    private void writeFile(String data) {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();

            FileWriter fw = new FileWriter(f);
            fw.write(data);
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}