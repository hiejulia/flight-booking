package com.project.flightbooking.service;


import com.perfectial.study.domain.BidES;
import com.perfectial.study.repository.BidESRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ESServiceImpl implements ESService {
    private final BidESRepository bidESRepository;

    public ESServiceImpl(BidESRepository bidESRepository) {
        this.bidESRepository = bidESRepository;
    }

    @Override
    public List<BidES> findByUserName(String userName) {
        return bidESRepository.findByUserName(userName);
    }

    @Override
    public Page<BidES> findByUserName(String userName, PageRequest pageRequest) {
        return bidESRepository.findByUserName(userName, pageRequest);
    }

    @Override
    public List<BidES> findByStake(BigDecimal stake) {
        return bidESRepository.findByStake(stake);
    }

    @Override
    public Page<BidES> findByStake(BigDecimal stake, PageRequest pageRequest) {
        return bidESRepository.findByStake(stake, pageRequest);
    }

    @Override
    public List<BidES> findByAddedDate(LocalDateTime localDateTime) {
        return bidESRepository.findByAddedDate(localDateTime);
    }

    @Override
    public Page<BidES> findByAddedDate(LocalDateTime localDateTime, PageRequest pageRequest) {
        return bidESRepository.findByAddedDate(localDateTime, pageRequest);
    }

    @Override
    public BidES add(BidES bidES) {
        return bidESRepository.save(bidES);
    }
}