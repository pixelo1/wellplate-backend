package com.pixelo.health.wellplate.authentication.infrastructure.springevent;

import com.pixelo.health.wellplate.authentication.application.in.MemberCommandInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthMemberListener {

    private final MemberCommandInputPort memberCommandInputPort;

    //todo 실패시 retry 처리 필요
//    @Retryable(retryFor = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 500))
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void listen(MemberRegisteredEvent event) {
//        log.info("MemberUnregisteredEvent event listen : {}", event.toString());
//        SaveMemberCommand command = SaveMemberCommand.builder()
//                .memberId(event.memberId())
//                .loginId(event.loginId())
//                .password(event.password())
//                .memberType(event.memberType())
//                .build();
//        memberCommandInputPort.saveMember(command);
//    }

    @Recover
    public void recover(Exception e, Object event) {
        log.error("retry fail, event={}, error = {}", event, e);
        throw new IllegalArgumentException("재시도 실패", e);
    }
}
