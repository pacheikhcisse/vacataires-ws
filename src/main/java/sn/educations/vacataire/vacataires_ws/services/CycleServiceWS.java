package sn.educations.vacataire.vacataires_ws.services;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.educations.vacataire.dao.modele.Cycle;
import sn.educations.vacataire.vacataires_ws.actions.CycleServiceImp;
import sn.educations.vacataire.vacataires_ws.actions.SurveillantServiceImp;

import java.util.List;

@RestController
@Slf4j
public class CycleServiceWS {

    @Autowired
    private CycleServiceImp cycleService;

    @Autowired
    private SurveillantServiceImp surveillantService;

    @ApiOperation(value = "Create a cycle")
    @RequestMapping(value="/cycles", method = RequestMethod.POST)
    public ResponseEntity saveCycle(@RequestBody Cycle cycle) {
        log.info("Create cycle [{}]" + cycle);
        cycleService.save(cycle);
        return new ResponseEntity(cycle, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a cycle")
    @RequestMapping(value = "/cycles/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCycle(@PathVariable(value = "id") Long id) {
        log.info("Delete cycle with id = [{}]", id);
        Cycle cycle = cycleService.findById(id);
        if (cycle == null) {
            return new ResponseEntity("There is no cycle with ID " + id, HttpStatus.NOT_FOUND);
        }
        cycleService.delete(cycle);
        return new ResponseEntity(cycle, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve a cycle by it's ID")
    @RequestMapping(value = "/cycles/{id}", method = RequestMethod.GET)
    public ResponseEntity findCycle(@PathVariable(value = "id") Long id) {
        log.info("Find cycle with id = [{}]", id);
        Cycle cycle = cycleService.findById(id);
        if (cycle == null) {
            return new ResponseEntity("There is no cycle with ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cycle, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve all cycles")
    @RequestMapping(value="/cycles", method = RequestMethod.GET)
    public List<Cycle> findAll() {
        log.info("Find all cycles");
        return cycleService.findAll();
    }

    @ApiOperation(value = "Update a cycle")
    @RequestMapping(value="/cycles", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Cycle cycle) {
        log.info("Update cycles [{}]", cycle);
        cycle = cycleService.save(cycle);
        return new ResponseEntity(cycle, HttpStatus.OK);
    }

}
