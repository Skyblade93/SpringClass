package it.test.ServiceTest;

import it.classe.SpringClass.Dto.PaymentDto;
import it.classe.SpringClass.Mapper.PaymentMapper;
import it.classe.SpringClass.Model.Payment;
import it.classe.SpringClass.Model.PaymentMethod;
import it.classe.SpringClass.Model.PaymentStatus;
import it.classe.SpringClass.Repository.PaymentRepository;
import it.classe.SpringClass.Service.PaymentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    private  final Logger log = org.slf4j.LoggerFactory.getLogger(UsersServiceTest.class);

        @Mock
        private PaymentRepository repository;

        @Mock
        private PaymentMapper converter;

        @InjectMocks
        private PaymentService paymentService;

    private Payment createPayment() {
        return new Payment(
                1,
                PaymentStatus.SUCCESSFUL,
                PaymentMethod.VISA,
                BigDecimal.valueOf(150),
                LocalDateTime.now(),
                null,
                null
        );
    }

    private Payment payment;
    private PaymentDto paymentDto;

    private List<Payment> payments;
    private List<PaymentDto> paymentDtos;



    @BeforeEach
    void setUp() {

        payment =createPayment();

        paymentDto = new PaymentDto();

        paymentDto.setId(1);
        paymentDto.setStatus(PaymentStatus.SUCCESSFUL);
        paymentDto.setPaymentMethod(PaymentMethod.VISA);
        paymentDto.setAmount(BigDecimal.valueOf(150));

        payments = List.of(payment);

        paymentDtos = List.of(paymentDto);
    }

    @Test
    void insert_shouldSavePayment() {

        when(converter.toEntity(paymentDto))
                .thenReturn(payment);

        when(repository.save(payment))
                .thenReturn(payment);

        when(converter.toDTO(payment))
                .thenReturn(paymentDto);

        PaymentDto result =
                paymentService.insert(paymentDto);

        assertNotNull(result);
        assertEquals(paymentDto, result);

        verify(converter).toEntity(paymentDto);
        verify(repository).save(payment);
        verify(converter).toDTO(payment);
    }

    @Test
    void getAll_shouldReturnPayments() {

        when(repository.findAll())
                .thenReturn(payments);

        when(converter.toDTOList(payments))
                .thenReturn(paymentDtos);

        List<PaymentDto> actual = new java.util.ArrayList<>();

        paymentService.getAll()
                .forEach(actual::add);

        assertEquals(paymentDtos, actual);

        verify(repository).findAll();
        verify(converter).toDTOList(payments);
    }

    @Test
    void read_shouldReturnPayment() {

        when(repository.findById(1))
                .thenReturn(Optional.of(payment));

        when(converter.toDTO(payment))
                .thenReturn(paymentDto);

        PaymentDto result =
                paymentService.read(1);

        assertNotNull(result);
        assertEquals(paymentDto, result);

        verify(repository).findById(1);
        verify(converter).toDTO(payment);
    }

    @Test
    void read_shouldThrowWhenPaymentNotFound() {

        when(repository.findById(999))
                .thenReturn(Optional.empty());

        assertThrows(
                NoSuchElementException.class,
                () -> paymentService.read(999)
        );
    }

    @Test
    void update_shouldUpdatePayment() {

        when(converter.toEntity(paymentDto))
                .thenReturn(payment);

        when(repository.save(payment))
                .thenReturn(payment);

        when(converter.toDTO(payment))
                .thenReturn(paymentDto);

        PaymentDto result =
                paymentService.update(paymentDto);

        assertNotNull(result);
        assertEquals(paymentDto, result);

        verify(converter).toEntity(paymentDto);
        verify(repository).save(payment);
        verify(converter).toDTO(payment);
    }

    @Test
    void delete_shouldDeletePayment() {

        paymentService.delete(1);

        verify(repository).deleteById(1);
    }

    @Test
    void getPaymentsByPayer() {

        when(repository.findByPayerId(1))
                .thenReturn(payments);

        when(converter.toDTOList(payments))
                .thenReturn(paymentDtos);

        List<PaymentDto> actual =
                paymentService.getPaymentsByPayer(1);

        assertEquals(paymentDtos, actual);

        verify(repository).findByPayerId(1);
        verify(converter).toDTOList(payments);
    }

    @Test
    void getPaymentsByPayer_shouldReturnEmptyList() {

        when(repository.findByPayerId(99))
                .thenReturn(List.of());

        when(converter.toDTOList(List.of()))
                .thenReturn(List.of());

        List<PaymentDto> result =
                paymentService.getPaymentsByPayer(99);

        assertTrue(result.isEmpty());
    }

    @Test
    void getPaymentsByPayee() {

        when(repository.findByPayeeId(1))
                .thenReturn(payments);

        when(converter.toDTOList(payments))
                .thenReturn(paymentDtos);

        List<PaymentDto> actual =
                paymentService.getPaymentsByPayee(1);

        assertEquals(paymentDtos, actual);

        verify(repository).findByPayeeId(1);
        verify(converter).toDTOList(payments);
    }

    @Test
    void getPaymentsByUsername_shouldReturnEmptyList() {

        when(repository.findPaymentsByPayerUsername("ghost"))
                .thenReturn(List.of());

        when(converter.toDTOList(List.of()))
                .thenReturn(List.of());

        List<PaymentDto> result =
                paymentService.getPaymentsByUsername("ghost");

        assertTrue(result.isEmpty());
    }

    @Test
    void getPaymentsByPayerUsername() {

        when(repository.findPaymentsByPayerUsername("Alfions"))
                .thenReturn(payments);

        when(converter.toDTOList(payments))
                .thenReturn(paymentDtos);

        List<PaymentDto> actual =
                paymentService.getPaymentsByUsername("Alfions");

        assertEquals(paymentDtos, actual);

        verify(repository)
                .findPaymentsByPayerUsername("Alfions");

        verify(converter)
                .toDTOList(payments);
    }

    @Test
    void getPaymentsByStatusAndMinAmount_shouldReturnPayments() {

        when(repository.findByStatusAndMinAmount(
                PaymentStatus.SUCCESSFUL,
                BigDecimal.valueOf(100)
        )).thenReturn(payments);

        when(converter.toDTOList(payments))
                .thenReturn(paymentDtos);

        List<PaymentDto> actual =
                paymentService.getPaymentsByStatusAndMinAmount(
                        PaymentStatus.SUCCESSFUL,
                        BigDecimal.valueOf(100)
                );

        assertEquals(paymentDtos, actual);

        verify(repository).findByStatusAndMinAmount(
                PaymentStatus.SUCCESSFUL,
                BigDecimal.valueOf(100)
        );

        verify(converter).toDTOList(payments);
    }

    @Test
    void getTotalReceivedByUser_shouldReturnAmount() {

        BigDecimal expected = BigDecimal.valueOf(750);

        when(repository.getTotalReceivedByUser(1))
                .thenReturn(expected);

        BigDecimal result =
                paymentService.getTotalReceivedByUser(1);

        assertEquals(expected, result);

        verify(repository).getTotalReceivedByUser(1);
    }

    @Test
    void getTotalSpentByUser_shouldReturnAmount() {

        BigDecimal expected = BigDecimal.valueOf(500);

        when(repository.getTotalSpentByUser(1))
                .thenReturn(expected);

        BigDecimal result =
                paymentService.getTotalSpentByUser(1);

        assertEquals(expected, result);
    }


    @Test
    void patch_shouldUpdateAmount() {

        when(repository.findById(1))
                .thenReturn(Optional.of(payment));

        when(repository.save(any(Payment.class)))
                .thenReturn(payment);

        when(converter.toDTO(any(Payment.class)))
                .thenReturn(paymentDto);

        PaymentDto result =
                paymentService.patch(
                        1,
                        null,
                        BigDecimal.valueOf(200)
                );

        assertNotNull(result);

        assertEquals(
                BigDecimal.valueOf(200),
                payment.getAmount()
        );
    }

    @Test
    void patch_shouldNotModifyAnythingWhenAllFieldsAreNull() {

        PaymentStatus originalStatus = payment.getStatus();
        BigDecimal originalAmount = payment.getAmount();

        when(repository.findById(1))
                .thenReturn(Optional.of(payment));

        when(repository.save(any(Payment.class)))
                .thenReturn(payment);

        when(converter.toDTO(any(Payment.class)))
                .thenReturn(paymentDto);

        paymentService.patch(
                1,
                null,
                null
        );

        assertEquals(
                originalStatus,
                payment.getStatus()
        );

        assertEquals(
                originalAmount,
                payment.getAmount()
        );
    }

    @Test
    void patch_shouldUpdateStatus() {

        payment.setStatus(PaymentStatus.BLOCKED);

        when(repository.findById(1))
                .thenReturn(Optional.of(payment));

        when(repository.save(any(Payment.class)))
                .thenReturn(payment);

        when(converter.toDTO(any(Payment.class)))
                .thenReturn(paymentDto);

        paymentService.patch(
                1,
                PaymentStatus.SUCCESSFUL,
                null
        );

        assertEquals(
                PaymentStatus.SUCCESSFUL,
                payment.getStatus()
        );
    }

    @Test
    void patch_shouldUpdateStatusAndAmount() {

        payment.setStatus(PaymentStatus.BLOCKED);
        payment.setAmount(BigDecimal.valueOf(100));

        when(repository.findById(1))
                .thenReturn(Optional.of(payment));

        when(repository.save(any(Payment.class)))
                .thenReturn(payment);

        when(converter.toDTO(any(Payment.class)))
                .thenReturn(paymentDto);

        paymentService.patch(
                1,
                PaymentStatus.SUCCESSFUL,
                BigDecimal.valueOf(500)
        );

        assertEquals(
                PaymentStatus.SUCCESSFUL,
                payment.getStatus()
        );

        assertEquals(
                BigDecimal.valueOf(500),
                payment.getAmount()
        );
    }

    @Test
    void patch_shouldThrowWhenPaymentNotFound(){
        when(repository.findById(999))
                .thenReturn(Optional.empty());

        assertThrows(
                NoSuchElementException.class,
                () -> paymentService.patch(
                        999,
                        PaymentStatus.SUCCESSFUL,
                        null
                )
        );
    }

}
