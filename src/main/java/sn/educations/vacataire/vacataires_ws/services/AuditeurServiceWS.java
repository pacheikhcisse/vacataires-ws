package sn.educations.vacataire.vacataires_ws.services;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.educations.vacataire.dao.modele.Auditeur;
import sn.educations.vacataire.vacataires_ws.actions.AuditeurServiceImp;

import java.util.List;

/**
 * Created by a615213 on 17/09/2016.
 */
@RestController
@Slf4j
public class AuditeurServiceWS {

    @Autowired
    private AuditeurServiceImp auditeurService;

    @ApiOperation(value = "Create an auditeur")
    @RequestMapping(value="/auditeurs", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Auditeur auditeur) {
        log.info("Create auditeur [{}]" + auditeur);
        auditeurService.save(auditeur);
        return new ResponseEntity(auditeur, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retrieve all auditeurs")
    @RequestMapping(value="/auditeurs", method = RequestMethod.GET)
    public List<Auditeur> findAll() {
        log.info("Find all auditeurs");
        return auditeurService.findAll();
    }

    @ApiOperation(value = "Retrieve an auditeur by it's ID")
    @RequestMapping(value = "/auditeurs/{id}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable(value = "id") Long id) {
        log.info("Find auditeur with id = [{}]", id);
        Auditeur auditeur = auditeurService.findById(id);
        if (auditeur == null) {
            return new ResponseEntity("There is no auditeur with ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(auditeur, HttpStatus.OK);
    }

    @ApiOperation(value = "Update an auditeur")
    @RequestMapping(value="/auditeurs", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Auditeur auditeur) {
        log.info("Update auditeur [{}]", auditeur);
        auditeur = auditeurService.save(auditeur);
        return new ResponseEntity(auditeur, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an auditeur")
    @RequestMapping(value = "/auditeurs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        log.info("Delete auditeur with id = [{}]", id);
        Auditeur auditeur = auditeurService.findById(id);
        if (auditeur == null) {
            return new ResponseEntity("There is no auditeur with ID " + id, HttpStatus.NOT_FOUND);
        }
        auditeurService.delete(auditeur);
        return new ResponseEntity(auditeur, HttpStatus.OK);
    }

}
