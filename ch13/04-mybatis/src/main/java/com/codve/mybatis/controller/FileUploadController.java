package com.codve.mybatis.controller;

import com.codve.mybatis.exception.EX;
import com.codve.mybatis.util.R;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @author admin
 * @date 2019/11/22 10:19
 */
@RestController
@RequestMapping(value = "/file")
public class FileUploadController {

    public static final Resource UPLOAD_DIR = new FileSystemResource("./upload");

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public R upload(@RequestParam MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return R.error(EX.FILE_EMPTY);
        }
        save(file);
        return R.success();
    }

    @PostMapping(value = "/multi/upload", consumes = "multipart/form-data")
    public R upload(@RequestParam MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            return R.error(EX.FILE_EMPTY);
        }
        for (MultipartFile file : files) {
            save(file);
        }
        return R.success();
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename, HttpServletRequest request)
            throws IOException {
        File uploadDir = UPLOAD_DIR.getFile();
        Resource resource = new FileSystemResource(uploadDir.getAbsolutePath() + "/" + filename);
        String mime = "application/octet-stream";
        String tmpMime = request.getServletContext().getMimeType(resource.getFilename());
        if (tmpMime != null) {
            mime = tmpMime;
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mime))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private void save(MultipartFile file) throws IOException {
        String filename = Long.toString(System.currentTimeMillis());
        String suffix = "";
        String[] filenames = file.getOriginalFilename().split("\\.");
        int length = filenames.length;
        if (length >= 2) {
            suffix = "." + filenames[length - 1];
        }
        File uploadDir = UPLOAD_DIR.getFile();
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        File tmp = new File(uploadDir, filename + suffix);
        InputStream in = file.getInputStream();
        OutputStream out = new FileOutputStream(tmp);
        IOUtils.copy(in, out);
    }
}
