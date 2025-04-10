package edu.canisius.csc213.complaints.storage;

import com.opencsv.bean.CsvToBeanBuilder;
import edu.canisius.csc213.complaints.model.Complaint;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import java.io.Reader;

/**
 * Handles loading of complaints and embedding data,
 * and returns a fully hydrated list of Complaint objects.
 */
public class ComplaintLoader {

    /**
     * Loads complaints from a CSV file and merges with embedding vectors from a JSONL file.
     *
     * @param csvPath    Resource path to the CSV file
     * @param jsonlPath  Resource path to the JSONL embedding file
     * @return A list of Complaint objects with attached embedding vectors
     * @throws Exception if file reading or parsing fails
     */
    public static List<Complaint> loadComplaintsWithEmbeddings(String csvPath, String jsonlPath) throws Exception {
        // TODO: Load CSV and JSONL resources, parse, and return hydrated Complaint list

        

        FileInputStream fis1 = new FileInputStream(csvPath);

        Reader reader = new InputStreamReader(fis1);
        List<Complaint> reviews = new CsvToBeanBuilder<Complaint>(reader)
                .withType(Complaint.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();


       // reviews.forEach(System.out::println);
        
        FileInputStream fis2 = new FileInputStream(jsonlPath);
        Map<Long, double[]> embeddings = EmbeddingLoader.loadEmbeddings(fis2);  
        ComplaintMerger.mergeEmbeddings(reviews, embeddings);
        

        return reviews;
    }
}
