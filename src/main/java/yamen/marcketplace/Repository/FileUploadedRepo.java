package yamen.marcketplace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yamen.marcketplace.Entity.FilesUploaded;

import java.util.UUID;

@Repository
public interface FileUploadedRepo extends JpaRepository<FilesUploaded,UUID> {
}
