package yamen.marcketplace.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yamen.marcketplace.Models.FilesUploaded;

import java.util.UUID;

@Repository
public interface FileUploadedRepo extends JpaRepository<FilesUploaded,UUID> {
}
