package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.PaymentDto;
import it.classe.SpringClass.Model.Payment;
import org.modelmapper.ModelMapper;

public class PaymentMapper extends AbstractConverter<Payment, PaymentDto> {

    ModelMapper mapper = new ModelMapper();

    @Override
    public Payment toEntity(PaymentDto dto) {
        return mapper.map(dto, Payment.class);
    }

    @Override
    public PaymentDto toDTO(Payment entity) {
        return mapper.map(entity, PaymentDto.class);
    }
}
