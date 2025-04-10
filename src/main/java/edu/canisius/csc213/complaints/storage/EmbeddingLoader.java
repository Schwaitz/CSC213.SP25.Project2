package edu.canisius.csc213.complaints.storage;

import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.*;
import java.util.*;

public class EmbeddingLoader {

    /**
     * Loads complaint embeddings from a JSONL (newline-delimited JSON) file.
     * Each line must be a JSON object with:
     * {
     * "complaintId": <long>,
     * "embedding": [<double>, <double>, ...]
     * }
     *
     * @param jsonlStream InputStream to the JSONL file
     * @return A map from complaint ID to its embedding vector
     * @throws IOException if the file cannot be read or parsed
     */
    public static Map<Long, double[]> loadEmbeddings(InputStream jsonlStream) throws IOException {

        final JsonMapper mapper = new JsonMapper();

        Map<Long, double[]> embeddings = new HashMap<>();

        try (MappingIterator<JsonNode> it = mapper.readerFor(JsonNode.class).readValues(jsonlStream)) {
            while (it.hasNextValue()) {
                JsonNode node = it.nextValue();

               // System.out.println(node);
                long id = node.get("id").asLong();

                ArrayNode embeddingArray = (ArrayNode) node.get("embedding");
                double[] embedding = new double[embeddingArray.size()];
                for (int i = 0; i < embeddingArray.size(); i++) {
                    embedding[i] = embeddingArray.get(i).asDouble();
                }

                //System.out.println("ID: " + id);
                //System.out.println("Embedding: " + Arrays.toString(embedding));

                embeddings.put(id, embedding);
            }
        }

        // Scanner scnr = new Scanner(jsonlStream);

        // ArrayList<String> lines = new ArrayList<>();
        // while (scnr.hasNextLine()) {
        // lines.add(scnr.nextLine());
        // }

        // scnr.close();

        // Map<Long, double[]> embeddings = new HashMap<>();

        // for (String line : lines) {
        // ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.enable(Feature.INCLUDE_SOURCE_IN_LOCATION);
        // System.out.println(line);
        // JsonNode jsonNode = objectMapper.readTree(line);
        // long id = jsonNode.get("id").asLong();

        // ArrayNode embeddingArray = (ArrayNode) jsonNode.get("embedding");
        // double[] embedding = new double[embeddingArray.size()];
        // for (int i = 0; i < embeddingArray.size(); i++) {
        // embedding[i] = embeddingArray.get(i).asDouble();
        // }
        // embeddings.put(id, embedding);
        // }


        
        return embeddings;
    }

}
