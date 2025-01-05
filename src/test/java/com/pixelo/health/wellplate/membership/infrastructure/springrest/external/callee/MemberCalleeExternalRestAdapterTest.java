package com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixelo.health.wellplate.core.filter.SecurityConfig;
import com.pixelo.health.wellplate.core.rest.ControllerAdvice;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.membership.application.in.command.MemberCommandInputPort;
import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
import com.pixelo.health.wellplate.membership.domain.MemberType;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee.request.RegisterMemberRequest;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee.response.RegisteredMemberResponse;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@Import(SecurityConfig.class)
@WebMvcTest(MemberCalleeExternalRestAdapter.class)
class MemberCalleeExternalRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ControllerAdvice controllerAdvice;
    @MockBean
    private MemberCommandInputPort memberCommandInputPort;
    @MockBean
    private MemberResponseStruct memberResponseStruct;
    @MockBean
    private MemberRequestStruct memberRequestStruct;
    @Autowired
    private ObjectMapper objectMapper;

    private final String loginId = "test";
    private final String email = "test@naver.com";
    private final String password = "1234";
    private final String memberType = "WELLNESS_CHALLENGER";
    @Ignore
//    @Test
    @DisplayName("회원가입 - 성공 케이스 - 삭제 예정")
    void registerMember_Success() throws Exception {
        // Given
        var request = createRegisterMemberRequest();
        var command = createRegisterMemberCommand(request);

        var memberId = UUID.randomUUID();
        var memberVo = createMemberVo(memberId);
        var expectedResponse = createRegisteredMemberResponse(memberVo);

        // Mock 객체 동작 설정
        Mockito.when(memberRequestStruct.toRegisterMemberCommand(any(RegisterMemberRequest.class)))
                .thenReturn(command);
        Mockito.when(memberCommandInputPort.registerMemberCommand(any(RegisterMemberCommand.class)))
                .thenReturn(memberVo);
        Mockito.when(memberResponseStruct.toRegisteredMemberResponse(any(MemberVo.class)))
                .thenReturn(expectedResponse);

        // When & Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andReturn();
        //then
        String responseBody = result.getResponse().getContentAsString();
        var typeReference = new TypeReference<ResultResponse<RegisteredMemberResponse>>() {};
        var actualResponse = objectMapper.readValue(responseBody, typeReference);

        Assertions.assertThat(actualResponse.data()).isNotNull();
        var data = actualResponse.data();
        Assertions.assertThat(data.getMemberId()).isEqualTo(memberId);
        Assertions.assertThat(data.getEmail()).isEqualTo(email);
        Assertions.assertThat(data.getPassword()).isEqualTo(password);
        Assertions.assertThat(data.getMemberType()).isEqualTo(memberType);

        // Mock 객체 메서드 호출 검증
        verify(memberRequestStruct).toRegisterMemberCommand(any(RegisterMemberRequest.class));
        verify(memberCommandInputPort).registerMemberCommand(any(RegisterMemberCommand.class));
        verify(memberResponseStruct).toRegisteredMemberResponse(any(MemberVo.class));
    }

    // 헬퍼 메서드들
    private RegisterMemberRequest createRegisterMemberRequest() {
        return RegisterMemberRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

    private RegisterMemberCommand createRegisterMemberCommand(RegisterMemberRequest request) {
        return RegisterMemberCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    private MemberVo createMemberVo(UUID memberId) {
        return new MemberVo(
                memberId,
                loginId,
                email,
                password,
                MemberType.ROLE_WELLNESS_CHALLENGER.code()
        );
    }

    private RegisteredMemberResponse createRegisteredMemberResponse(MemberVo memberVo) {
        return RegisteredMemberResponse.builder()
                .memberId(memberVo.memberId())
                .email(memberVo.email())
                .password(memberVo.password())
                .memberType(memberVo.memberType())
                .build();
    }
}