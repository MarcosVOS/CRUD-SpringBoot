package forwork.human_resources.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import forwork.human_resources.models.NoteSharing;
import forwork.human_resources.models.Notes;
import forwork.human_resources.models.Users;
import forwork.human_resources.repositores.NoteSharingRepository;
import forwork.human_resources.repositores.NotesRepository;
import forwork.human_resources.repositores.UserRepository;

@RestController
@RequestMapping("/noteSharing")
public class NoteSharingController {
    
    @Autowired
    private NoteSharingRepository noteSharingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotesRepository notesRepository;

    @GetMapping(value="/all")
    public List<NoteSharing> getAllNoteSharing(){
        return noteSharingRepository.findAll();
    }

    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public NoteSharing createNewNoteSharing(@RequestBody NoteSharing noteSharing){
        
        NoteSharing createNoteSharing = new NoteSharing();

        Notes noteId = notesRepository.findById(noteSharing.getSharedNoteId().getId()).orElseThrow();
        Users sharedBy = userRepository.findById(noteSharing.getSharedBy().getId()).orElseThrow();
        Users sharedWith = userRepository.findById(noteSharing.getSharedWith().getId()).orElseThrow();

        createNoteSharing.setSharedNoteId(noteId);
        createNoteSharing.setSharedBy(sharedBy);
        createNoteSharing.setSharedWith(sharedWith);
        createNoteSharing.setSharedAt(LocalDateTime.now());

        return noteSharingRepository.save(createNoteSharing);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteSharing deleteNoteSharing(@PathVariable Long id){
        NoteSharing getNoteSharing = noteSharingRepository.findById(id).orElseThrow();
        noteSharingRepository.delete(getNoteSharing);
        return getNoteSharing;
    }

    @GetMapping(value = "/allsharedBy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteSharing> getAllSharedBy(@PathVariable Long id){
 
        Users getNoteSharing = userRepository.findById(id).orElseThrow();

        return noteSharingRepository.findAllSharedBy(getNoteSharing);
    }

    @GetMapping(value = "/allsharedwith/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteSharing> getAllSharedWith(@PathVariable Long id){

        Users getNoteSharing = userRepository.findById(id).orElseThrow();

        return noteSharingRepository.findAllSharedWith(getNoteSharing);
    }
}