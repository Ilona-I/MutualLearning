package ua.nure.illiashenko.mutuallearning.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.illiashenko.mutuallearning.dto.FileUploadResponse;
import ua.nure.illiashenko.mutuallearning.util.FileUploadUtil;

@RestController
public class FileUploadController {

    @PostMapping("/uploadFile/{fileName}")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile,
        @PathVariable String fileName) throws IOException {
        long size = multipartFile.getSize();
        FileUploadUtil.saveFile(fileName, multipartFile);
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}