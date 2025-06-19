package com.interview.interview_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewResult {
    @Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "interview_id")
    private Interview interview;

    private String videoUrl;
    private LocalDateTime uploadedAt;

    @PreRemove
    public void deleteVideoFile() {
        if (videoUrl != null && !videoUrl.isBlank()) {
            try {
                File file = new File(videoUrl);
                if (!file.exists()) {
                    file = new File("uploads", new File(videoUrl).getName());
                }
                if (file.exists()) {
                    boolean deleted = file.delete();
                    System.out.println("Video file deleted: " + deleted + " - " + file.getAbsolutePath());
                } else {
                    System.out.println("Video file not found: " + videoUrl);
                }
            } catch (Exception e) {
                System.out.println("Error deleting video file: " + e.getMessage());
            }
        }
    }
}
