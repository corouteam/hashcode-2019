import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] Args) throws IOException {
        new Main().init();
    }

    void init() throws IOException {
        System.out.println("Hashcode 2019!");

        ArrayList<Photo> photos;
        List<String> input = HappyParser.FileToStringArray("a_example.txt");
                for (int i = 0; i < input.size(); i++){
                    String[] splitInput = input.get(i).split(" ");
                    for (int i = 2; )
                    photos[i] = new Photo(splitInput.)

        }
    }
}
