package CSVDataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class AddProductData {
    @DataProvider(name = "addProductData")
    public Object[][] addProductData() {
        String csvFile = "src/test/resources/test_data/add-product-data.csv";
        List<Object[]> testData = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Bỏ qua dòng tiêu đề
            br.readLine();
            while ((line = br.readLine()) != null) {
            	String[] data = line.split(",");
            	String id = data[0];
            	String name = data[1];
            	String code = data[2];
            	String image = data[3]; 
            	String description = data[4];
            	String price = data[5];
            	String sale = data[6];
    			String quantity = data[7];
    			String expectedMsg = data[8];
                testData.add(new Object[]{id, name, code, image, description, price, sale, quantity, expectedMsg});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return testData.toArray(new Object[0][]);
    }
}
