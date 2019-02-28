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
        ArrayList<Photo> photos = parseFile("d_pet_pictures.txt");

        ArrayList<Slide> slides = new ArrayList<>();

        for (Photo photo : photos) {
            if (photo.orientation.equals("H")) {
                slides.add(new Slide(photo, null));
            }
        }

        ArrayList<Slide> orderedSlides = new ArrayList<>();

        // First one is random
        orderedSlides.add(slides.get(0));
        slides.get(0).setChecked(true);


        for (int i=0; i<slides.size()-1; i++) {
            Slide currentSlide = orderedSlides.get(i);
            Slide nextSlide = ScoreCalculator.Companion.getHighest(currentSlide, slides);
            nextSlide.setChecked(true);
            orderedSlides.add(nextSlide);
        }

        for (Slide slide: slides) {
            if (!slide.getChecked()) {
                orderedSlides.add(slide);
                break;
            }
        }

        OutputGenerator.Companion.generateOutput(orderedSlides);
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
