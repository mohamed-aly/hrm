package com.hrm.test.business.controller;

import com.hrm.test.business.dto.PagingDTO;
import com.hrm.test.business.entity.Branch;
import com.hrm.test.business.service.BranchService;
import com.hrm.test.shared.validation.ValidQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Validated
@RequestMapping("branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<PagingDTO<Branch>> listBranches(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                          @ValidQuery @RequestParam(required = false) String query) {
        return new ResponseEntity<>(branchService.getAll(pageable, query), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Branch> saveBranch(@RequestBody Branch branch){
        return new ResponseEntity<>(branchService.save(branch), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Branch> updateBranch(@RequestBody Branch branch){
        return new ResponseEntity<>(branchService.update(branch), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranch(@PathVariable long id){
        return new ResponseEntity<>(branchService.get(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public void deleteBranch(@PathVariable long id){
        branchService.delete(id);
    }

}
