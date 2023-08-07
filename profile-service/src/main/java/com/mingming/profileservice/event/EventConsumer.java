package com.mingming.profileservice.event;

import com.google.gson.Gson;
import com.mingming.commonservice.utils.Constant;
import com.mingming.profileservice.model.ProfileDTO;
import com.mingming.profileservice.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;


@Service
@Slf4j
public class EventConsumer {

    Gson gson = new Gson();
    @Autowired
    private ProfileService profileService;

    public EventConsumer(ReceiverOptions<String, String> receiverOptions){
        KafkaReceiver.create(receiverOptions.subscription(Collections.singleton(Constant.PROFILE_ONBOARDED_TOPIC)))
                .receive().subscribe(this::profileOnboarded);
    }
    private void profileOnboarded(ReceiverRecord<String, String> receiverRecord){
        log.info("Profile Onboarded Event!");
        ProfileDTO profileDTO = gson.fromJson(receiverRecord.value(), ProfileDTO.class);
        profileService.updateStatusProfile(profileDTO).subscribe();
    }
}
