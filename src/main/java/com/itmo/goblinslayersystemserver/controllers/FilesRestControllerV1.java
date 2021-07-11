package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.dto.FileDto;
import com.itmo.goblinslayersystemserver.models.FileInfo;
import com.itmo.goblinslayersystemserver.models.GetFileResult;
import com.itmo.goblinslayersystemserver.services.IFilesService;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(Endpoints.FilesRestControllerV1)
public class FilesRestControllerV1 {
    @Autowired
    private IFilesService filesService;

    @Autowired
    private IUserService userService;

    /**
     * Загрузить файл
     *
     * @param file файл
     * @return информация о загруженном файле
     */
    @PostMapping(consumes = {"multipart/form-data"}, produces = {"application/json"})
    public FileDto uploadFile(@RequestParam("file") MultipartFile file) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDao uploader = userService.get(username);

        FileInfo fileInfo = filesService.upload(file, uploader);
        FileDto fileDto = new FileDto();
        fileDto.setId(fileInfo.getId());
        fileDto.setFileName(fileInfo.getFileName());
        fileDto.setCreateTime(fileInfo.getCreateTime());

        return fileDto;

        //return new AdventurerDto(userService.create(adventurer));
    }

    /**
     * Get запрос для получения файла по его ID
     **/
    @GetMapping(value = "/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer id,
                                            HttpServletRequest request) {
        GetFileResult fileInfo = null;

        try {
            fileInfo = filesService.get(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(fileInfo.getFileResource().getFile().getAbsolutePath());
        } catch (IOException ex) {
            //logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getOriginalName() + "\"")
                .body(fileInfo.getFileResource());


    }

}
