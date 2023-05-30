package ua.nure.illiashenko.mutuallearning.controller;

import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.util.FileDownloadUtil;

@RestController
public class FileDownloadController {

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileCode") String fileCode) throws IOException {
       FileDownloadUtil downloadUtil = new FileDownloadUtil();
        Resource resource = null;
        try {
            resource = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        if (resource == null) {
            throw new RuntimeException();
        }
     //   String contentType = "application/octet-stream";
    //    String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
            .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(resource.getFilename()).build().toString())
            .body(resource.getInputStream().readAllBytes());
    }
}