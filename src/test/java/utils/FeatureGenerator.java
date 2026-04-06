package utils;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class FeatureGenerator {

    public static void generateFeatureFile() {

        List<Map<String, String>> data =
                ExcelUtils.getData("src/test/resources/testdata.xlsx", "Sheet1");

        StringBuilder content = new StringBuilder();

        content.append("Feature: Data Driven Login\n\n");

        int i = 1;

        for (Map<String, String> row : data) {

            content.append("Scenario: Login test user" + i + "\n");
            content.append("  Given user enters \"" + row.get("username") + "\"\n");
            content.append("  Then login should be \"" + row.get("expected") + "\"\n\n");

            i++;
        }

        try {
            FileWriter writer = new FileWriter(
                    "src/test/resources/features/generatedLogin.feature"
            );
            writer.write(content.toString());
            writer.close();

            System.out.println("Feature file generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}