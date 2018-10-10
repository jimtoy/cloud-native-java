package personal.toy.jim.cnj;


import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping(value = "/customers/{id}/photo")
public class CustomerProfilePhotoRestController {

    @GetMapping
    ResponseEntity<Resource> read(@PathVariable Long id) throws FileNotFoundException {
        Resource fsr = new FileSystemResource(ResourceUtils.getFile("classpath:static/dog.jpg"));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fsr);
    }

    @RequestMapping(method = RequestMethod.POST)
    Callable<ResponseEntity<?>> write(@PathVariable Long id, @RequestParam MultipartFile file) {
        return () -> {
            System.out.println("in write");
            int nano = LocalDateTime.now().getNano();
            InputStream in = file.getInputStream();
            OutputStream out = new FileOutputStream(new File("c:/temp/dog" + nano + ".jpg"));
            FileCopyUtils.copy(in, out);
            URI location = fromCurrentRequest().buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        };
    }


}
