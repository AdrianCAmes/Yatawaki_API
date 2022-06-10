package com.orchestrator.orchestrator.expose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestrator.orchestrator.business.UserService;
import com.orchestrator.orchestrator.configuration.WebSecurity;
import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.user.request.UserChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserRegisterRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserUpdateRequestDto;
import com.orchestrator.orchestrator.model.dto.user.response.UserProfileResponseDto;
import com.orchestrator.orchestrator.model.dto.user.response.UserResumeResponseDto;
import com.orchestrator.orchestrator.repository.UserRepository;
import com.orchestrator.orchestrator.utils.UserUtils;
import com.orchestrator.orchestrator.utils.constants.UserRoleConstants;
import com.orchestrator.orchestrator.utils.constants.UserStatusConstants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

}
