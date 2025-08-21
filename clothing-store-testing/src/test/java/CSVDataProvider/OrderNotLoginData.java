package CSVDataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class OrderNotLoginData {
    @DataProvider(name = "orderNotLoginData")
    public Object[][] orderNotLoginData() {
        String csvFile = "src/test/resources/test_data/order-not-login-data.csv";
        List<Object[]> testData = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Bỏ qua dòng tiêu đề
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String phone = data[2];
                String email = data[3];
                String address = data[4];
                String note = data[5];
                String expectedMsg = data[6];
                testData.add(new Object[]{id, name, phone, email, address, note, expectedMsg});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return testData.toArray(new Object[0][]);
    }
}
