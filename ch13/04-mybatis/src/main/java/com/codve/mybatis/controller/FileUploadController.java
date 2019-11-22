package com.codve.mybatis.controller;

import com.codve.mybatis.exception.EX;
import com.codve.mybatis.util.R;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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

    public static final Resource FILE_DIR = new FileSystemResource("./upload");

    @PostMapping("/upload")
    public R upload(@RequestParam MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return R.error(EX.FILE_EMPTY);
        }
        String filename = file.getOriginalFilename();
        String tmpName = String.valueOf(System.currentTimeMillis());
        String suffix = filename.substring(filename.lastIndexOf("."));
        File tmp = File.createTempFile(tmpName, suffix, FILE_DIR.getFile());
        InputStream in = file.getInputStream();
        OutputStream out = new FileOutputStream(tmp);
        IOUtils.copy(in, out);
        return R.success();
    }

//    @PostMapping("/upload")
//    public R upload(@RequestParam MultipartFile[] files) {
//        if (files == null || files.length == 0) {
//            return R.error(EX.FILE_EMPTY);
//        }
//    }
//
//    @GetMapping("/download/{filename}")
//    public ResponseEntity<Resource> download(@PathVariable String filename, HttpServletRequest request) {
//
//    }


}
