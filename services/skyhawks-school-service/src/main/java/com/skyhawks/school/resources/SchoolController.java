/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.school.resources;

import com.skyhawks.dtos.school.requests.SchoolRequest;
import com.skyhawks.dtos.school.responses.SchoolResponse;
import com.skyhawks.school.exception.SchoolNotFountException;
import com.skyhawks.school.exception.UserNotFountException;
import com.skyhawks.school.resources.validator.ManagerValidator;
import com.skyhawks.school.services.SchoolService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
public class SchoolController {

    private final SchoolService schoolService;
    private final ManagerValidator managerValidator;

    @PostMapping(path = "/school")
    public ResponseEntity<String> saveSchool(@Valid @RequestBody SchoolRequest request) throws UserNotFountException {
        if (managerValidator.isValid(request.getMangerId())) {
            schoolService.insert(request);
            return ResponseEntity.ok("School Created successfully");
        }
        throw new UserNotFountException("Manger is not fount in application");
    }

    @PutMapping(path = "/school")
    public ResponseEntity<String> updateSchool(@Valid @RequestBody SchoolRequest request)
            throws SchoolNotFountException, UserNotFountException {
        if (managerValidator.isValid(request.getMangerId())) {
            schoolService.save(request);
            return ResponseEntity.ok("School Updated successfully");
        }
        throw new UserNotFountException("Manger is not fount in application");
    }

    @GetMapping(path = "/schools")
    public List<SchoolResponse> getAllSchool() {
        return schoolService.getAllSchool();
    }

    @GetMapping(path = "/school/{schoolCode}")
    public SchoolResponse getSchool(@PathVariable String schoolCode)
            throws SchoolNotFountException {
        return schoolService.getSchool(schoolCode);
    }

    @Hidden
    @GetMapping(path = "/valid/{schoolCode}")
    public Boolean validate(@PathVariable String schoolCode)
            throws SchoolNotFountException {
        return schoolService.validate(schoolCode);
    }

    @GetMapping(path = "/set-status/{schoolCode}")
    public ResponseEntity<String>  setStatus(@PathVariable String schoolCode,@RequestParam boolean status)
            throws SchoolNotFountException {
        schoolService.setStatus(schoolCode,status);
        return ResponseEntity.ok("School "+(status?"enabled":"disabled")+" successfully");
    }
}
