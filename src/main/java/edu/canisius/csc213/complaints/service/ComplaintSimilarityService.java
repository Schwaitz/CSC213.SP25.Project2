package edu.canisius.csc213.complaints.service;

import edu.canisius.csc213.complaints.model.Complaint;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComplaintSimilarityService {

    private final List<Complaint> complaints;

    public ComplaintSimilarityService(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public List<Complaint> findTop3Similar(Complaint target) {
        // return target.stream()
        //         .filter(book -> !book.id.equals(target.id))
        //         .sorted(Comparator.comparingDouble(book -> -cosineSimilarity(target.embedding, book.embedding)))
        //         .limit(3)
        //         .collect(Collectors.toList());
        return List.of();
    }

    private double cosineSimilarity(double[] a, double[] b) {
        double dot = 0, normA = 0, normB = 0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
    }

    class ComplaintWithScore {
        Complaint complaint;
        double score;

        ComplaintWithScore(Complaint c, double s) {
            this.complaint = c;
            this.score = s;
        }
    }
