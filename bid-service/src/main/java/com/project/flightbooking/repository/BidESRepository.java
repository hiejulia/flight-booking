package com.project.flightbooking.repository;


import com.perfectial.study.domain.BidES;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource
public interface BidESRepository  extends ElasticsearchRepository<BidES, Long> {

    List<BidES> findAll();

    List<BidES> findByUserName(String userName);
    Page<BidES> findByUserName(String userName, Pageable pageable);

    List<BidES> findByStake(BigDecimal stake);
    Page<BidES> findByStake(BigDecimal stake, Pageable pageable);

    List<BidES> findByAddedDate(LocalDateTime localDateTime);
    Page<BidES> findByAddedDate(LocalDateTime localDateTime, Pageable pageable);
}