import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class HappyParser {

    static String FileToString(String path)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    static List<String> FileToStringArray(String path)
            throws IOException
    {
        return Files.readAllLines(Paths.get(path));
    }

    static void CreateFile(String filePath, List<String> content) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, content, Charset.forName("UTF-8"));
    }

    static void AddLineToFile(String filePath, String content) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, true));
        output.append(content);
        output.close();
    }

}
