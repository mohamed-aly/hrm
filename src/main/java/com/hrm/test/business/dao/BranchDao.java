package com.hrm.test.business.dao;

import com.hrm.test.business.dto.PagingDTO;
import com.hrm.test.business.entity.Branch;
import com.hrm.test.shared.exception.ResourceNotFoundException;
import com.hrm.test.shared.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class BranchDao {

    private final SessionFactory sessionFactory;

    public BranchDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public PagingDTO<Branch> getBranches(String query, Pageable pageable) {
        Session currentSession = getSession();
        query = Util.buildQuery(query);

        Query<Long> countQuery = currentSession.createQuery("Select count (b.id) from Branch b " +
                "where :query is null " +
                "or b.name like :query ", Long.class);
        countQuery.setParameter("query", query);
        long totalElements = countQuery.uniqueResult();
        int totalPages = (int) (Math.ceil(totalElements / (pageable.getPageSize() * 1.0)));
        int pageNumber = pageable.getPageNumber();

        Query<Branch> sessionQuery = currentSession.createQuery("select b from Branch b " +
                        "where :query is null " +
                        "or b.name like :query ",
                Branch.class);

        sessionQuery.setFirstResult(pageNumber * pageable.getPageSize());
        sessionQuery.setMaxResults(pageable.getPageSize());
        sessionQuery.setParameter("query", query);


        return Util.buildPagingDto(sessionQuery.getResultList(), pageNumber, totalPages, totalElements);
    }


    public Branch save(Branch branch) {
        Session currentSession = getSession();
        branch.setId((long) currentSession.save(branch));
        return branch;
    }

    public Branch update(Branch branch) {
        Session currentSession = getSession();
        return (Branch) currentSession.merge(branch);
    }

    public Branch get(long id) {
        Session currentSession = getSession();
        Branch branch = currentSession.get(Branch.class, id);
        if(branch == null){
            throw new ResourceNotFoundException("Not Found!");
        }

        return branch;
    }

    public void delete(long id) {
        Branch branch = get(id);
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(branch);
    }

}







