package cn.ych.tendering.controller;

import cn.ych.tendering.service.FileUploadService;
import cn.ych.tendering.vo.Result;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@ConfigurationProperties("application.file")
public class FileUploadController {
    @Setter
    private String prefix_host;
    private FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Result> upload(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Result("上传失败"));
//        }
//        String fileName = System.currentTimeMillis() + Base64.encodeBase64String(Objects.requireNonNull(file.getOriginalFilename()).getBytes());
//        File dest = new File(prefix_path + fileName + "."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
//        try {
//            file.transferTo(dest);
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Result(prefix_host + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Result("上传失败"));
        String s = fileUploadService.fileUpload(file);
        return ResponseEntity.status(HttpStatus.OK).body(new Result(s == null ? null : prefix_host + s));
    }

}
