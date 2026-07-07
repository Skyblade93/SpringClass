package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.PaymentDto;
import it.classe.SpringClass.Model.Payment;

public class PaymentMapper {
    public static PaymentDto toDto(Payment entity){
        PaymentDto dto = new PaymentDto();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setAmount(entity.getAmount());
        dto.setProcessedAt(entity.getProcessedAt());
        dto.setPayer(UsersMapper.toDto(entity.getPayer()));
        dto.setPayee(UsersMapper.toDto(entity.getPayee()));

        return dto;
    }

    public static Payment toEntity(PaymentDto dto){
        Payment entity = new Payment();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        entity.setPaymentMethod(entity.getPaymentMethod());
        entity.setAmount(dto.getAmount());
        entity.setProcessedAt(dto.getProcessedAt());
        entity.setPayer(UsersMapper.toEntity(dto.getPayer()));
        entity.setPayee(UsersMapper.toEntity(dto.getPayee()));

        return entity;
    }
}
