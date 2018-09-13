package com.project.flightbooking.repository;

import com.perfectial.study.domain.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

    // Find by username
    Optional<CashFlow> findFirstByUserNameOrderByUpdatedDateDesc(String userName);

    List<CashFlow> findByUserNameOrderByUpdatedDateDesc(String userName);

}
