package forwork.human_resources.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import forwork.human_resources.models.NoteSharing;
import forwork.human_resources.models.Users;

public interface NoteSharingRepository extends JpaRepository<NoteSharing, Long>{
    @Query("SELECT ns FROM NoteSharing ns WHERE ns.sharedBy = :sharedBy")
    List<NoteSharing> findAllSharedBy(@Param("sharedBy") Users sharedBy);

    @Query("SELECT ns FROM NoteSharing ns WHERE ns.sharedWith = :sharedWith")
    List<NoteSharing> findAllSharedWith(@Param("sharedWith") Users sharedWith);
}
