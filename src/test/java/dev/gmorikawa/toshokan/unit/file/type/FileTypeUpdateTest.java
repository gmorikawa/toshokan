package dev.gmorikawa.toshokan.unit.file.type;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.file.type.FileType;
import dev.gmorikawa.toshokan.domain.file.type.FileTypeService;

@SpringBootTest
public class FileTypeUpdateTest {
    @Autowired
    private FileTypeService service;

    @Test
    public void testUpdateFileType() {
        FileType fileType = new FileType();

        fileType.setName("Electronic Publication");
        fileType.setExtension("epub");

        FileType savedFileType = service.create(fileType);

        assertThat(savedFileType).isNotNull();
        assertThat(savedFileType.getName()).isEqualTo(fileType.getName());
        assertThat(savedFileType.getExtension()).isEqualTo(fileType.getExtension());

        savedFileType.setName("Mobipocket");
        savedFileType.setExtension("mobi");

        FileType updatedFileType = service.update(savedFileType.getId(), savedFileType);

        assertThat(updatedFileType).isNotNull();
        assertThat(updatedFileType.getName()).isEqualTo(savedFileType.getName());
        assertThat(updatedFileType.getExtension()).isEqualTo(savedFileType.getExtension());
    }
}
