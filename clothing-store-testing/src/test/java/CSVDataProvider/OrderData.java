package CSVDataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class OrderData {
    @DataProvider(name = "orderData")
    public Object[][] orderData() {
        String csvFile = "src/test/resources/test_data/order-data.csv";
        List<Object[]> testData = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Bỏ qua dòng tiêu đề
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String phone = data[1];
                String address = data[2];
                String note = data[3];
                String expectedMsg = data[4];
                testData.add(new Object[]{id, phone, address, note, expectedMsg});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return testData.toArray(new Object[0][]);
    }
}
