package com.mingming.accountservice.event;

import com.google.gson.Gson;
import com.mingming.accountservice.model.AccountDTO;
import com.mingming.accountservice.services.AccountService;
import com.mingming.commonservice.model.ProfileDTO;
import com.mingming.commonservice.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;
import java.util.UUID;

@Service
@Slf4j
public class EventConsumer {
    @Autowired
    private AccountService accountService;
    @Autowired
    private EventProducer eventProducer;

    Gson gson = new Gson();

    public EventConsumer(ReceiverOptions<String, String> receiverOptions){
        KafkaReceiver.create(receiverOptions.subscription(Collections.singleton(Constant.PROFILE_ONBOARDING_TOPIC)))
                .receive().subscribe(this::profileOnboarding);
    }

    public void profileOnboarding(ReceiverRecord<String, String> receiverRecord){
        log.info("Profile onboarding event!");
        ProfileDTO profileDTO = gson.fromJson(receiverRecord.value(), ProfileDTO.class);
        AccountDTO accountDTO = new AccountDTO(UUID.randomUUID().toString(), profileDTO.getEmail(), "USD", profileDTO.getInitialBalance(), 0.0);
        accountService.createNewAccount(accountDTO).subscribe(response -> {
            profileDTO.setStatus(Constant.STATUS_PROFILE_ACTIVE);
            eventProducer.send(Constant.PROFILE_ONBOARDED_TOPIC, gson.toJson(profileDTO)).subscribe();
        });
    }
}
