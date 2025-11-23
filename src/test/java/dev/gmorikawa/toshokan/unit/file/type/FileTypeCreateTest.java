package dev.gmorikawa.toshokan.unit.file.type;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.file.type.FileType;

@SpringBootTest
public class FileTypeCreateTest extends FileTypeTestEnvironment {

    @Test
    public void testCreateFileType() {
        FileType fileType = new FileType();

        fileType.setName("Portable Document Format");
        fileType.setExtension("_pdf");
        fileType.setMimeType("application/pdf");

        FileType savedFileType = service.create(fileType);

        assertThat(savedFileType).isNotNull();
        assertThat(savedFileType.getName()).isEqualTo(fileType.getName());
        assertThat(savedFileType.getExtension()).isEqualTo(fileType.getExtension());
        assertThat(savedFileType.getMimeType()).isEqualTo(fileType.getMimeType());

        // clean-up
        clean(savedFileType);
    }
}
