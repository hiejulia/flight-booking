package com.project.flightbooking.controller;


import com.perfectial.study.domain.CashFlow;
import com.perfectial.study.dto.CashFlowDTO;
import com.perfectial.study.service.CashFlowService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cashFlow/rest")
public class CashFlowController {
    private final CashFlowService cashFlowService;
    private final ModelMapper modelMapper;

    public CashFlowController(CashFlowService cashFlowService, ModelMapper modelMapper) {
        this.cashFlowService = cashFlowService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getCashFlowsByUserName")
    public List<CashFlowDTO> getCashFlowsByUserName(@RequestParam String userName){
        List<CashFlow> cashFlows = cashFlowService.findByUserNameOrderByUpdatedDateDesc(userName);
        List<CashFlowDTO> cashFlowDTOS = cashFlows.stream().map(cashFlow -> modelMapper.map(cashFlow, CashFlowDTO.class)).collect(Collectors.toList());
        return cashFlowDTOS;
    }

    @GetMapping("/getCashFlowByUserName")
    public CashFlowDTO getCashFlowByUserName(@RequestParam String userName){
        CashFlow cashFlow = cashFlowService.findFirstByUserNameOrderByUpdatedDateDesc(userName).get();
        if(cashFlow == null){
            return null;
        } else{
            return modelMapper.map(cashFlow, CashFlowDTO.class);
        }
    }
    @GetMapping("/getCashFlowById")
    public CashFlowDTO getCashFlowByUserName(@RequestParam Long id){
        CashFlow cashFlow = cashFlowService.getById(id).get();
        return modelMapper.map(cashFlow, CashFlowDTO.class);
    }

    @PostMapping("/addCashFlow")
    public CashFlow addCashFlow(@RequestBody CashFlowDTO cashFlowDTO) {
        CashFlow cashFlow = modelMapper.map(cashFlowDTO, CashFlow.class);
        return cashFlowService.addCashFlow(cashFlow);
    }
}