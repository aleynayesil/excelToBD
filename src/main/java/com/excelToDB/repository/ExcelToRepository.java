package com.excelToDB.repository;

import com.excelToDB.domain.IdCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExcelToRepository<E extends IdCommon> extends JpaRepository<E,Integer>, JpaSpecificationExecutor<E> {
}
