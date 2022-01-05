package com.hrm.test.business.service.impl;

import com.hrm.test.business.dao.BranchDao;
import com.hrm.test.business.dto.PagingDTO;
import com.hrm.test.business.entity.Branch;
import com.hrm.test.business.service.BranchService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {
    private final BranchDao branchDao;

    public BranchServiceImpl(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public PagingDTO<Branch> getAll(Pageable pageable, String query) {
        return branchDao.getBranches(query, pageable);
    }

    @Override
    public Branch save(Branch branch) {
        return branchDao.save(branch);
    }

    @Override
    public Branch update(Branch branch) {
        return branchDao.update(branch);
    }

    @Override
    public Branch get(long id) {
        return branchDao.get(id);
    }

    @Override
    public void delete(long id) {
        branchDao.delete(id);
    }
}
