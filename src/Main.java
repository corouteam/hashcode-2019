import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    String a = "a_example.txt";
    String b = "b_lovely_landscapes.txt";
    String c = "c_memorable_moments.txt";
    String d = "d_pet_pictures.txt";
    public static void main(String[] Args) throws IOException {
        new Main().init();
    }

    void init() throws IOException {
        System.out.println("Hashcode 2019!");
        ArrayList<Photo> photos = parseFile(b);
        ArrayList<Slide> hSlides = new ArrayList<>();
        ArrayList<Slide> newSlides = new ArrayList<>();
        ArrayList<Slide> orderedSlides = new ArrayList<>();
        ArrayList<Photo> verticalPhotos = new ArrayList<>();

        for (Photo photo : photos) {
            if (photo.orientation.equals("H")) {
                hSlides.add(new Slide(photo, null));
            }else{
                verticalPhotos.add(photo);
            }
        }
        int maxDimention = hSlides.size() + (verticalPhotos.size()/2); //-1 per precauzione, lo possiamo togliere dopo

        //se le verticali sono dispari tolgi il primo
        if(verticalPhotos.size()%2 != 0){
            verticalPhotos.remove(0);
        }
        ScoreSystem scoreSystem = new ScoreSystem(verticalPhotos, hSlides);


                // First one is random
        orderedSlides.add(hSlides.get(0));
        hSlides.get(0).setChecked(true);
        scoreSystem.remove(hSlides.get(0));
        System.out.println("H Photos: "+hSlides.size());
        System.out.println("V Photos: "+verticalPhotos.size());
        System.out.println("max c: "+maxDimention);

        int maxdimen = 10000;
        for (int i=0; i<maxdimen; i++) {
            System.out.println("int: "+i);
            Slide currentSlide = orderedSlides.get(i);
            Slide nextSlide = scoreSystem.bestCompleteFit(currentSlide);
            scoreSystem.remove(nextSlide);
           // nextSlide.setChecked(true);
            orderedSlides.add(nextSlide);

        }

        for (Slide slide: hSlides) {
            if (!slide.getChecked()) {
                orderedSlides.add(slide);
                break;
            }
        }

        OutputGenerator.Companion.generateOutput(orderedSlides);
        System.out.println("ended");
    }



    ArrayList<Photo> parseFile(String filePath) throws IOException {
        ArrayList<Photo> photos = new ArrayList<>();
        List<String> input = HappyParser.FileToStringArray(filePath);
        for (int i = 1; i < input.size(); i++){
            String[] splitInput = input.get(i).split(" ");
            HashSet<String> photoTags = new HashSet<>();
            for (int j = 2; j < splitInput.length; j++){
                photoTags.add(splitInput[j]);
            }
            photos.add(new Photo(i-1, splitInput[0], photoTags));
        }
        return photos;
    }
}
