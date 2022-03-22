package com.example.employeegradle.dao;



import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

import javax.persistence.criteria.Root;
import com.example.employeegradle.entity.Task_;

import com.example.employeegradle.entity.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class TaskDAOImpl implements TaskDAO {
    private final EntityManager em;


    @Override
    public Page<Task> findTaskAll(int id,Boolean flg,Pageable pageable) {
        // クエリの準備
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        Path<Integer> departmentId = root.get(Task_.DEPARTMENT);
        Path<Boolean> deleteFlg = root.get(Task_.DELETE_FLG);
        // ----------------------------------------------------

        if(flg != null){
            query.select(root).where(cb.equal(deleteFlg, flg),cb.equal(departmentId, id));
        }else{
            query.select(root).where(cb.equal(departmentId, id));
        }
        TypedQuery<Task> typedQuery = em.createQuery(query);

        int totalRows = typedQuery.getResultList().size();
        // 検索件数に応じた初期ページ番号をセット
        // 件数５件で１ページのため、６件めのデータなら１ページ（０ページから数える）からスタート
        // ページ番号×データ件数で初期ページ番号を割り出す
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
    
        // １ページ当たりの件数をセット
        typedQuery.setMaxResults(pageable.getPageSize());
        // PageImplコンストラクタにデータ、ページ番号、データ件数を渡す
        Page<Task> page = new PageImpl<>(typedQuery.getResultList(), pageable, totalRows);
        return page;
    }
}