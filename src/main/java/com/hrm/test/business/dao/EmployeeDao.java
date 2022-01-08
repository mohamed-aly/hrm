package com.hrm.test.business.dao;

import com.hrm.test.business.dto.PagingDTO;
import com.hrm.test.business.entity.Employee;
import com.hrm.test.shared.exception.ResourceNotFoundException;
import com.hrm.test.shared.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphParser;
import org.hibernate.graph.RootGraph;
import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

    private final SessionFactory sessionFactory;

    public EmployeeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public PagingDTO<Employee> getEmployees(String query, Pageable pageable) {
        Session currentSession = getSession();
        query = Util.buildQuery(query);

        Query<Long> countQuery = currentSession.createQuery("Select count (e.id) from Employee e "+
                "where :query is null " +
                "or e.name like :query " +
                "or e.nationalId like :query " +
                "or e.branch.name like :query ", Long.class);
        countQuery.setParameter("query", query);

        long totalElements = countQuery.uniqueResult();
        int totalPages = (int) (Math.ceil(totalElements / (pageable.getPageSize() * 1.0)));
        int pageNumber = pageable.getPageNumber();


        Query<Employee> sessionQuery = currentSession.createQuery("select e from Employee e " +
                        "where :query is null " +
                        "or e.name like :query " +
                        "or e.nationalId like :query " +
                        "or e.branch.name like :query ",
                Employee.class);

        RootGraph<Employee> graph = GraphParser.parse(Employee.class, "branch", currentSession);
        sessionQuery.applyFetchGraph(graph);

        sessionQuery.setFirstResult(pageNumber * pageable.getPageSize());
        sessionQuery.setMaxResults(pageable.getPageSize());
        sessionQuery.setParameter("query", query);


        return Util.buildPagingDto(sessionQuery.getResultList(), pageNumber, totalPages, totalElements);
    }


    public Employee save(Employee employee) {
        Session currentSession = getSession();
        employee.setId((long) currentSession.save(employee));
        return employee;
    }

    public Employee update(Employee employee) {
        Session currentSession = getSession();
        return (Employee) currentSession.merge(employee);
    }

    public Employee get(long id) {
        Session currentSession = getSession();
        Employee employee = currentSession.get(Employee.class, id);
        if(employee == null){
            throw new ResourceNotFoundException("Not Found!");
        }

        return employee;
    }

    public void delete(long id) {
        Employee employee = get(id);
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(employee);
    }

}







