package edu.canisius.csc213.complaints.app;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.canisius.csc213.complaints.model.Complaint;
import edu.canisius.csc213.complaints.storage.ComplaintLoader;

public class Testing {
    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = ComplaintWebApp.class.getResourceAsStream("/config.properties")) {
            if (in == null) {
                throw new IllegalStateException("Missing config.properties in resources directory!");
            }
            config.load(in);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        String csvPath = config.getProperty("complaints.csv");
        String jsonlPath = config.getProperty("embeddings.jsonl");

        if (csvPath == null || jsonlPath == null) {
            throw new IllegalArgumentException("Missing file paths in config.properties.");
        }

        System.out.println("📥 Loading complaints from: " + csvPath);
        System.out.println("📥 Loading embeddings from: " + jsonlPath);

        try {
            List<Complaint> complaints = ComplaintLoader.loadComplaintsWithEmbeddings(csvPath, jsonlPath);

            for(Complaint c : complaints) {
                System.out.println(c);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }
}
