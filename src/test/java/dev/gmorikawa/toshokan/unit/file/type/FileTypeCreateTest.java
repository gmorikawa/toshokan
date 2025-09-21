package dev.gmorikawa.toshokan.unit.file.type;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.file.type.FileType;
import dev.gmorikawa.toshokan.domain.file.type.FileTypeService;

@SpringBootTest
public class FileTypeCreateTest {

    @Autowired
    private FileTypeService service;

    @Test
    public void testCreateFileType() {
        FileType fileType = new FileType();

        fileType.setName("Portable Document Format");
        fileType.setExtension("pdf");

        FileType savedFileType = service.create(fileType);

        assertThat(savedFileType).isNotNull();
        assertThat(savedFileType.getName()).isEqualTo(fileType.getName());
        assertThat(savedFileType.getExtension()).isEqualTo(fileType.getExtension());
    }
}
