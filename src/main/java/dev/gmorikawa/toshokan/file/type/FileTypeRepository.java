package dev.gmorikawa.toshokan.file.type;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTypeRepository extends JpaRepository<FileType, String> {
    @Query("SELECT ft FROM FileType ft WHERE ft.extension = ?1")
    Optional<FileType> findByExtension(String extension);
}
