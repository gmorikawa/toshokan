package dev.gmorikawa.toshokan.unit.file.type;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.file.type.FileType;
import dev.gmorikawa.toshokan.domain.file.type.FileTypeService;

@SpringBootTest
public class FileTypeRemoveTest {
    @Autowired
    private FileTypeService service;

    @Test
    public void testRemoveFileType() {
        FileType fileType = new FileType();

        fileType.setName("Document Open XML");
        fileType.setExtension("docx");

        FileType savedFileType = service.create(fileType);

        assertThat(savedFileType).isNotNull();
        assertThat(savedFileType.getName()).isEqualTo(fileType.getName());
        assertThat(savedFileType.getExtension()).isEqualTo(fileType.getExtension());

        service.remove(savedFileType.getId());

        FileType searchedFileType = service.getById(savedFileType.getId());
        assertThat(searchedFileType).isNull();
    }
}
