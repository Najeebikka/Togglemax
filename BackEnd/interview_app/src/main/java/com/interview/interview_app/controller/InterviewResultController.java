package com.interview.interview_app.controller;

import com.interview.interview_app.dto.InterviewResultDTO;
import com.interview.interview_app.dto.InterviewSummaryDTO;
import com.interview.interview_app.service.InterviewResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.nio.file.Paths;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/interview-results")
@RequiredArgsConstructor
public class InterviewResultController {

    private final InterviewResultService interviewResultService;

    @PostMapping("/upload")
    public ResponseEntity<InterviewResultDTO> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("interviewId") UUID interviewId) {
        try {

            String folder = new File("uploads").getAbsolutePath();
            new File(folder).mkdirs();

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath = Paths.get(folder, fileName).toString();


            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(file.getBytes());
            }

            String publicPath = "uploads/" + fileName;

            InterviewResultDTO dto = new InterviewResultDTO();
            dto.setInterviewId(interviewId);
            dto.setVideoUrl(publicPath);  
            dto.setUploadedAt(LocalDateTime.now());

            InterviewResultDTO savedDto = interviewResultService.createResult(dto);

            return ResponseEntity.ok(savedDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<InterviewSummaryDTO>> getAllResults() {
        return ResponseEntity.ok(interviewResultService.getAllSummaries());
    }

    @GetMapping("/interview/{interviewId}")
    public ResponseEntity<List<InterviewSummaryDTO>> getResultsByInterviewId(@PathVariable UUID interviewId) {
        return ResponseEntity.ok(interviewResultService.getResultsByInterviewId(interviewId));
    }

}
