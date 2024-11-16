package forwork.human_resources.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="notesharing")
@EqualsAndHashCode(of="id")
public class NoteSharing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shared_note_id",referencedColumnName = "id", updatable = false, nullable = false)
    private Notes sharedNoteId;

    @ManyToOne
    @JoinColumn( name = "shared_by_id",referencedColumnName = "id",updatable = false, nullable = false)
    private Users sharedBy;

    @ManyToOne
    @JoinColumn( name = "shared_with_id", referencedColumnName = "id", updatable = false, nullable = false)
    private Users sharedWith;

    @Column(nullable = false)
    private LocalDateTime sharedAt;
}