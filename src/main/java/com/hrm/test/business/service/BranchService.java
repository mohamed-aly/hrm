package com.hrm.test.business.service;

import com.hrm.test.business.dto.PagingDTO;
import com.hrm.test.business.entity.Branch;
import org.springframework.data.domain.Pageable;

public interface BranchService {

    PagingDTO<Branch> getAll(Pageable pageable, String query);

    Branch save(Branch branch);

    Branch update(Branch branch);

    Branch get(long id);

    void delete(long id);
}
