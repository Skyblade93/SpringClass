package it.test.ControllerTest;

import it.classe.SpringClass.Controller.PaymentController;
import it.classe.SpringClass.Dto.PaymentDto;
import it.classe.SpringClass.Model.PaymentMethod;
import it.classe.SpringClass.Model.PaymentStatus;
import it.classe.SpringClass.Service.PaymentService;
import it.classe.SpringClass.SpringClassApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.MediaType;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
@ContextConfiguration(classes = SpringClassApplication.class)
public class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private PaymentDto paymentDto;
    private List<PaymentDto> paymentDtos;

    @MockitoBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        paymentDto = new PaymentDto();

        paymentDto.setId(1);
        paymentDto.setStatus(PaymentStatus.SUCCESSFUL);
        paymentDto.setPaymentMethod(PaymentMethod.VISA);
        paymentDto.setAmount(BigDecimal.valueOf(150));

        paymentDtos = List.of(paymentDto);
    }

    @Test
    void getPaymentsByPayer_shouldReturn200() throws Exception {

        when(paymentService.getPaymentsByPayer(1))
                .thenReturn(paymentDtos);

        mockMvc.perform(
                        get("/payments/payer/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].amount").value(150));

        verify(paymentService)
                .getPaymentsByPayer(1);
    }

    @Test
    void getPaymentsByPayee_shouldReturn200() throws Exception {

        when(paymentService.getPaymentsByPayee(1))
                .thenReturn(paymentDtos);

        mockMvc.perform(
                        get("/payments/payee/1")
                )
                .andExpect(status().isOk());

        verify(paymentService)
                .getPaymentsByPayee(1);
    }

    @Test
    void getPaymentsByUsername_shouldReturn200() throws Exception {

        when(paymentService.getPaymentsByUsername("Alfions"))
                .thenReturn(paymentDtos);

        mockMvc.perform(
                        get("/payments/username/Alfions")
                )
                .andExpect(status().isOk());

        verify(paymentService)
                .getPaymentsByUsername("Alfions");
    }

    @Test
    void getPaymentsByStatusAndMinAmount_shouldReturn200() throws Exception {

        when(paymentService.getPaymentsByStatusAndMinAmount(
                PaymentStatus.SUCCESSFUL,
                BigDecimal.valueOf(100)
        )).thenReturn(paymentDtos);

        mockMvc.perform(
                        get("/payments/filter")
                                .param("status", "SUCCESSFUL")
                                .param("amount", "100")
                )
                .andExpect(status().isOk());

        verify(paymentService)
                .getPaymentsByStatusAndMinAmount(
                        PaymentStatus.SUCCESSFUL,
                        BigDecimal.valueOf(100)
                );
    }

    @Test
    void getTotalSpentByUser_shouldReturn200() throws Exception {

        when(paymentService.getTotalSpentByUser(1))
                .thenReturn(BigDecimal.valueOf(500));

        mockMvc.perform(
                        get("/payments/spent/1")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("500"));

        verify(paymentService)
                .getTotalSpentByUser(1);
    }

    @Test
    void getTotalReceivedByUser_shouldReturn200() throws Exception {

        when(paymentService.getTotalReceivedByUser(1))
                .thenReturn(BigDecimal.valueOf(750));

        mockMvc.perform(
                        get("/payments/received/1")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("750"));

        verify(paymentService)
                .getTotalReceivedByUser(1);
    }

    @Test
    void updateStatus_shouldReturn200() throws Exception {

        when(paymentService.patch(
                1,
                PaymentStatus.SUCCESSFUL,
                null
        )).thenReturn(paymentDto);

        mockMvc.perform(
                        patch("/payments/1/status")
                                .param("status", "SUCCESSFUL")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(paymentService)
                .patch(
                        1,
                        PaymentStatus.SUCCESSFUL,
                        null
                );
    }

    @Test
    void updateAmount_shouldReturn200() throws Exception {

        when(paymentService.patch(
                1,
                null,
                BigDecimal.valueOf(500)
        )).thenReturn(paymentDto);

        mockMvc.perform(
                        patch("/payments/1/amount")
                                .param("amount", "500")
                )
                .andExpect(status().isOk());

        verify(paymentService)
                .patch(
                        1,
                        null,
                        BigDecimal.valueOf(500)
                );
    }

    @Test
    void getAll_shouldReturn200() throws Exception {

        when(paymentService.getAll())
                .thenReturn(paymentDtos);

        mockMvc.perform(get("/payments/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void read_shouldReturn200() throws Exception {

        when(paymentService.read(1))
                .thenReturn(paymentDto);

        mockMvc.perform(
                        get("/payments/read")
                                .param("id", "1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(paymentService).read(1);
    }

    @Test
    void insert_shouldReturn200() throws Exception {

        when(paymentService.insert(any()))
                .thenReturn(paymentDto);

        mockMvc.perform(
                        post("/payments/insert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(paymentDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void update_shouldReturn200() throws Exception {

        when(paymentService.update(any()))
                .thenReturn(paymentDto);

        mockMvc.perform(
                        put("/payments/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(paymentDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void delete_shouldReturn200() throws Exception {

        doNothing()
                .when(paymentService)
                .delete(1);

        mockMvc.perform(
                        delete("/payments/delete")
                                .param("id", "1")
                )
                .andExpect(status().isOk());

        verify(paymentService).delete(1);
    }

    @Test
    void read_shouldReturn400WhenIdMissing() throws Exception {

        mockMvc.perform(
                        get("/payments/read")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_shouldReturn400WhenIdMissing() throws Exception {

        mockMvc.perform(
                        delete("/payments/delete")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void filter_shouldReturn400WhenStatusMissing() throws Exception {

        mockMvc.perform(
                        get("/payments/filter")
                                .param("amount", "100")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void filter_shouldReturn400WhenAmountMissing() throws Exception {

        mockMvc.perform(
                        get("/payments/filter")
                                .param("status", "SUCCESSFUL")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateStatus_shouldReturn400WhenStatusInvalid() throws Exception {

        mockMvc.perform(
                        patch("/payments/1/status")
                                .param("status", "PIPPO")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateAmount_shouldReturn400WhenAmountInvalid() throws Exception {

        mockMvc.perform(
                        patch("/payments/1/amount")
                                .param("amount", "ciao")
                )
                .andExpect(status().isBadRequest());
    }
}
