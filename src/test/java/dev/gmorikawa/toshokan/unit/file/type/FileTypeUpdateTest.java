package dev.gmorikawa.toshokan.unit.file.type;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.file.type.FileType;

@SpringBootTest
public class FileTypeUpdateTest extends FileTypeTestEnvironment {

    @Test
    public void testUpdateFileType() {
        FileType fileType = new FileType();

        fileType.setName("Electronic Publication");
        fileType.setExtension("_epub");
        fileType.setMimeType("application/epub+zip");

        FileType savedFileType = service.create(fileType);

        assertThat(savedFileType).isNotNull();
        assertThat(savedFileType.getName()).isEqualTo(fileType.getName());
        assertThat(savedFileType.getExtension()).isEqualTo(fileType.getExtension());

        savedFileType.setName("Mobipocket");
        savedFileType.setExtension("_mobi");
        savedFileType.setMimeType("application/x-mobipocket-ebook");

        FileType updatedFileType = service.update(savedFileType.getId(), savedFileType);

        assertThat(updatedFileType).isNotNull();
        assertThat(updatedFileType.getName()).isEqualTo(savedFileType.getName());
        assertThat(updatedFileType.getExtension()).isEqualTo(savedFileType.getExtension());
        assertThat(updatedFileType.getMimeType()).isEqualTo(savedFileType.getMimeType());

        // clean-up
        clean(updatedFileType);
    }
}
