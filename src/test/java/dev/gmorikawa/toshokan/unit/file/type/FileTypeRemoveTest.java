package dev.gmorikawa.toshokan.unit.file.type;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.file.type.FileType;

@SpringBootTest
public class FileTypeRemoveTest extends FileTypeTestEnvironment {

    @Test
    public void testRemoveFileType() {
        FileType fileType = new FileType();

        fileType.setName("Document Open XML");
        fileType.setExtension("_docx");
        fileType.setMimeType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        FileType savedFileType = service.create(fileType);

        assertThat(savedFileType).isNotNull();
        assertThat(savedFileType.getName()).isEqualTo(fileType.getName());
        assertThat(savedFileType.getExtension()).isEqualTo(fileType.getExtension());
        assertThat(savedFileType.getMimeType()).isEqualTo(fileType.getMimeType());

        service.remove(savedFileType.getId());

        FileType searchedFileType = service.getById(savedFileType.getId());
        assertThat(searchedFileType).isNull();
    }
}
