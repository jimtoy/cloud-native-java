package personal.toy.jim.cnj;


import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;


@RestController
@RequestMapping(value = "/customers/{id}/photo")
public class CustomerProfilePhotoRestController {

    @GetMapping
    ResponseEntity<Resource> read(@PathVariable Long id) throws FileNotFoundException {
        Resource fsr = new FileSystemResource(ResourceUtils.getFile("classpath:static/dog.jpg"));

        System.out.println("is file " + fsr.isFile());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fsr);
    }
}
