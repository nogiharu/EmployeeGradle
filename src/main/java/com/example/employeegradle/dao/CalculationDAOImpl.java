package com.example.employeegradle.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Path;

import com.example.employeegradle.dto.CalculationDTO;
import com.example.employeegradle.entity.Department;
import com.example.employeegradle.entity.Department_;
import com.example.employeegradle.entity.Task;
import com.example.employeegradle.entity.Task_;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CalculationDAOImpl implements CalculationDAO {

    private final EntityManager em;

    @Override
    public List<CalculationDTO> findByCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // クエリの結果型を生成
        CriteriaQuery<CalculationDTO> query = cb.createQuery(CalculationDTO.class);
        Root<Department> root = query.from(Department.class);
        // 「From Department d LEFT JOIN Task t ON d.id = t.department」と同じ
        Join<Department, Task> join = root.join(Department_.TASK_LIST, JoinType.LEFT);
        // ------------------------------------------------------
        Path<String> departmentName = root.get(Department_.NAME);
        Path<Integer> departmentId = root.get(Department_.ID);
        Path<Integer> sales = join.get(Task_.SALES);
        Path<Boolean> deleteFlg = join.get(Task_.DELETE_FLG);
        // -------------------------------------------------------
        // 【CASE】
        CriteriaBuilder.Case<Integer> caseThenSalesElse0 = cb.selectCase();
        caseThenSalesElse0.when(cb.equal(deleteFlg, true), sales).otherwise(0);
        CriteriaBuilder.Case<Boolean> caseThenDeleteFlgElseNull = cb.selectCase();
        caseThenDeleteFlgElseNull.when(cb.equal(deleteFlg, true), deleteFlg);
        // ------------------------------------------------------
        query.select(cb.construct(CalculationDTO.class,
                departmentName,
                departmentId,
                cb.sum(caseThenSalesElse0),
                cb.avg(caseThenSalesElse0),
                cb.count(caseThenDeleteFlgElseNull)))
                .groupBy(departmentId)
                .orderBy(cb.asc(departmentId));
        return em.createQuery(query).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CalculationDTO> findByJPQL() {

        String query = "SELECT NEW com.example.employeegradle.dto.CalculationDTO("
                + " d.name, d.id,"
                + " SUM(CASE WHEN t.deleteFlg = true THEN t.sales ELSE 0 END),"
                + " AVG(CASE WHEN t.deleteFlg = true THEN t.sales ELSE 0 END),"
                + " COUNT(CASE WHEN t.deleteFlg = true THEN t.deleteFlg ELSE NULL END))"
                + " FROM Department d"
                + " LEFT JOIN Task t ON d.id = t.department"
                + " GROUP BY d.id"
                + " ORDER BY d.id ASC";
        return em.createQuery(query).getResultList();

    }
}