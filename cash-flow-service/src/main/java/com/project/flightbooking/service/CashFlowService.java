package com.project.flightbooking.service;


import com.perfectial.study.domain.CashFlow;
import com.perfectial.study.repository.CashFlowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CashFlowService {

    private final CashFlowRepository cashFlowRepository;

    public CashFlowServiceImpl(CashFlowRepository cashFlowRepository) {
        this.cashFlowRepository = cashFlowRepository;
    }


    public Optional<CashFlow> findFirstByUserNameOrderByUpdatedDateDesc(String userName) {
        return cashFlowRepository.findFirstByUserNameOrderByUpdatedDateDesc(userName);
    }


    public List<CashFlow> findByUserNameOrderByUpdatedDateDesc(String userName) {
        return cashFlowRepository.findByUserNameOrderByUpdatedDateDesc(userName);
    }


    public CashFlow addCashFlow(CashFlow cashFlow) {

        Optional<CashFlow> existedCashFlowOptional = findFirstByUserNameOrderByUpdatedDateDesc(cashFlow.getUserName());

        if(!existedCashFlowOptional.isPresent()){
            return cashFlowRepository.save(cashFlow);
        } else{
            CashFlow existedCashFlow = existedCashFlowOptional.get();
            CashFlow toSave = new CashFlow();
            toSave.setUserName(cashFlow.getUserName());
            toSave.setUpdatedDate(LocalDateTime.now());
            toSave.setStake(cashFlow.getStake());
            toSave.setCurrentBalance(existedCashFlow.getCurrentBalance().add(cashFlow.getCurrentBalance()));
            toSave.setPreviousBalance(existedCashFlow.getCurrentBalance());
            return cashFlowRepository.save(toSave);
        }
    }


    public Optional<CashFlow> getById(Long id) {
        return cashFlowRepository.findById(id);
    }
}
