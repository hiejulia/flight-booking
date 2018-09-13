package com.project.flightbooking.service;


import com.perfectial.study.domain.Bid;
import com.perfectial.study.dto.BidDTO;
import com.perfectial.study.service.redisqueue.PublishRedisQueueService;
import com.perfectial.study.service.redisqueue.PublishRedisQueueServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class SendBidServiceImpl implements SendBidService {

    private final PublishRedisQueueService publishRedisQueueService;
    private final ModelMapper modelMapper;

    public SendBidServiceImpl(PublishRedisQueueServiceImpl publishRedisQueueService, ModelMapper modelMapper) {
        this.publishRedisQueueService = publishRedisQueueService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Bid sendBidToQueue(Bid bid) {
        if(bid != null){
            BidDTO bidDTO = modelMapper.map(bid,BidDTO.class);
            publishRedisQueueService.rightPush(bidDTO);
            publishRedisQueueService.sendMessage();
            return modelMapper.map(bidDTO,Bid.class);
        }
        return bid;
    }
}