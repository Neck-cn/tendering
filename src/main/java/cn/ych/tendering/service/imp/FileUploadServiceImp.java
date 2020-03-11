package cn.ych.tendering.service.imp;

import cn.ych.tendering.service.FileUploadService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class FileUploadServiceImp implements FileUploadService {
    private FastFileStorageClient fastFileStorageClient;

    public FileUploadServiceImp(FastFileStorageClient fastFileStorageClient) {
        this.fastFileStorageClient = fastFileStorageClient;
    }

    @Override
    public String fileUpload(MultipartFile multipartFile) {
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream,
                multipartFile.getSize(),
                Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf(".") + 1).toLowerCase()
                , null);
        return storePath.getFullPath();
    }
}
