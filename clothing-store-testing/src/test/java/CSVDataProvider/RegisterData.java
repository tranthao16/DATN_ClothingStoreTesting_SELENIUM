package CSVDataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class RegisterData {
    @DataProvider(name = "registerData")
    public Object[][] registerData() {
        String csvFile = "src/test/resources/test_data/register-data.csv";
        List<Object[]> testData = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Bỏ qua dòng tiêu đề
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String email = data[1];
                String name = data[2];
                String password = data[3];
                String address = data[4];
                String expectedMsg = data[5];
                testData.add(new Object[]{id, email, name, password, address, expectedMsg});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return testData.toArray(new Object[0][]);
    }
}
