import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] Args) throws IOException {
        new Main().init();
    }

    void init() throws IOException {
        System.out.println("Hashcode 2019!");
        ParseFile("a_example.txt");
        

    }

    void ParseFile(String filePath) throws IOException {
        ArrayList<Photo> photos = new ArrayList<>();
        List<String> input = HappyParser.FileToStringArray(filePath);
        for (int i = 0; i < input.size(); i++){
            String[] splitInput = input.get(i).split(" ");
            HashSet<String> photoTags = new HashSet<>();
            for (int j = 2; j < input.size(); j++){
                photoTags.add(input.get(j));
            }
            photos.add(new Photo(i, splitInput[0], photoTags));
            System.out.println(photos.get(i).id + photos.get(i).orientation + photos.get(i).tags);
        }
    }
}
