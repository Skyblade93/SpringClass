package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Dto.PaymentDto;
import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.Payment;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Payment> toEntityList(List<PaymentDto> dtos) {
        List<Payment> list = new ArrayList<>();
        for (PaymentDto dto : dtos) {
            list.add(toEntity(dto));
        }
        return list;
    }

    public static List<PaymentDto> toDtoList(List<Payment> entity) {
        List<PaymentDto> listdto = new ArrayList<>();
        for (Payment dto : entity) {
            listdto.add(toDto(dto));
        }
        return listdto;
    }
}
