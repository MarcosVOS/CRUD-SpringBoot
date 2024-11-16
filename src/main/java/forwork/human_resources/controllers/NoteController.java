package forwork.human_resources.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import forwork.human_resources.models.Notes;
import forwork.human_resources.models.Users;
import forwork.human_resources.repositores.NotesRepository;
import forwork.human_resources.repositores.UserRepository;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    private UserRepository usersRepository;

    @GetMapping("/all")
    public List<Notes> getAllNotes() {
        return notesRepository.findAll();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Notes createNewNote(@RequestBody Notes note) {

        Users user = usersRepository.findById(note.getCreatedBy().getId()).orElseThrow();

        Notes createNote = new Notes();
        createNote.setTitle(note.getTitle());
        createNote.setDescription(note.getDescription());
        createNote.setCreatedBy(user);
        createNote.setCreatedAt(LocalDateTime.now());

        return notesRepository.save(createNote);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Notes updateNotes(@RequestBody Notes note) {
        Notes getNote = notesRepository.findById(note.getId()).orElseThrow();

        Notes updateNote = new Notes();
        updateNote.setId(getNote.getId());
        updateNote.setTitle(note.getTitle());
        updateNote.setDescription(note.getDescription());
        updateNote.setCreatedBy(note.getCreatedBy());
        updateNote.setCreatedAt(LocalDateTime.now());

        return notesRepository.save(updateNote);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Notes deleteNote(@PathVariable Long id) {
        Notes getNote = notesRepository.findById(id).orElseThrow();
        notesRepository.delete(getNote);
        return getNote;
    }

    @GetMapping(value = "/getallfromuser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Notes> getAllFromUser(@PathVariable Long id) {
        return notesRepository.findByCreatedById(id);
    }
}
