package sn.educations.vacataire.vacataires_ws.services;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.educations.vacataire.dao.modele.Auditeur;
import sn.educations.vacataire.dao.modele.ChefDepartement;
import sn.educations.vacataire.vacataires_ws.actions.ChefDepartementServiceImpl;

import java.util.List;

/**
 * Created by pacheikhcisse(a615213) on 21/09/2016 at 23:01.
 */
@RestController
@Slf4j
public class ChefDepartementServiceWS {

    @Autowired
    private ChefDepartementServiceImpl chefDepartementService;

    @ApiOperation(value = "Create a chef departement")
    @RequestMapping(value="/chef-departements", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody ChefDepartement chefDepartement) {
        log.info("Create chefDepartement [{}]" + chefDepartement);
        chefDepartementService.save(chefDepartement);
        return new ResponseEntity(chefDepartement, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retrieve all chef departements")
    @RequestMapping(value="/chef-departements", method = RequestMethod.GET)
    public List<ChefDepartement> findAll() {
        log.info("Find all chef departement");
        return chefDepartementService.findAll();
    }

    @ApiOperation(value = "Retrieve chef departement by it's ID")
    @RequestMapping(value = "/chef-departements/{id}", method = RequestMethod.GET)
    public ResponseEntity find(@PathVariable(value = "id") Long id) {
        log.info("Find chef departement with id = [{}]", id);
        ChefDepartement chefDepartement = chefDepartementService.findById(id);
        if (chefDepartement == null) {
            return new ResponseEntity("There is no chef departement with ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(chefDepartement, HttpStatus.OK);
    }

}
