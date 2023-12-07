import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageResponse;
import com.opencagedata.jopencage.model.JOpenCageResult;

import java.util.ArrayList;

public class Opencage_example {
    public static void main(String[] args) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("60cdd0cbd0ff48ad84bcdd75f39d7c01");
        JOpenCageForwardRequest request = new JOpenCageForwardRequest("goldring");
        request.setRestrictToCountryCode("ca");
        // request.setBounds(-79.0, 43.0, -80.0, 44.0);

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        ArrayList<String> words = new ArrayList<>();
        if (response.getResults().size() < 6) {
            for (JOpenCageResult re : response.getResults()) {
                words.add(re.getFormatted());
            }} else {
            for (JOpenCageResult re : response.getResults().subList(0, 5)) {
                words.add(re.getFormatted());
            }
        }
        for (String word: words) {
            System.out.println(word);
        }
    }

}
