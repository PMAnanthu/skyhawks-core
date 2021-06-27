/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.school.services;

import com.skyhawks.dtos.school.requests.SchoolRequest;
import com.skyhawks.dtos.school.responses.SchoolResponse;
import com.skyhawks.school.exception.SchoolNotFountException;
import com.skyhawks.school.model.School;
import com.skyhawks.school.repos.ISchoolRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class SchoolService {

    public static final String WRONG_SCHOOL_CODE = "Wrong school code";
    private final ISchoolRepo iSchoolRepo;

    private final ModelMapper modelMapper;

    public void insert(SchoolRequest request) {
        School school = modelMapper.map(request, School.class);
        iSchoolRepo.save(school);
    }

    public void save(SchoolRequest request) throws SchoolNotFountException {
        School school = modelMapper.map(request, School.class);
        if (school.getUuid() == null) {
            school = iSchoolRepo.findBySchoolCode(school.getSchoolCode());
            if (school == null) {
                throw new SchoolNotFountException(WRONG_SCHOOL_CODE);
            }
        }
        iSchoolRepo.save(school);
    }

    public List<SchoolResponse> getAllSchool() {
        List<SchoolResponse> result = new ArrayList<>();
        iSchoolRepo.findAll().forEach(val -> result.add(modelMapper.map(val, SchoolResponse.class)));
        return result;
    }

    public SchoolResponse getSchool(String schoolCode) throws SchoolNotFountException {
        School school = iSchoolRepo.findBySchoolCode(schoolCode);
        if (school == null) {
            throw new SchoolNotFountException(WRONG_SCHOOL_CODE);
        }
        return modelMapper.map(school, SchoolResponse.class);
    }

    public Boolean validate(String schoolCode) throws SchoolNotFountException {
        getSchool(schoolCode);
        return true;
    }

    public void setStatus(String schoolCode, boolean status) throws SchoolNotFountException {
        School school = iSchoolRepo.findBySchoolCode(schoolCode);
        if (school == null) {
            throw new SchoolNotFountException(WRONG_SCHOOL_CODE);
        }
        school.setActive(status);
        iSchoolRepo.save(school);
    }
}
