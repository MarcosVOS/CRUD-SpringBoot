package forwork.human_resources.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import forwork.human_resources.models.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByCreatedById(Long createdBy);
}
