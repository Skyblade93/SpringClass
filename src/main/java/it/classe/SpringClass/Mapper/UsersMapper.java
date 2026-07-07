package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Model.Users;

public class UsersMapper {

    public static UsersDto toDto(Users entity) {
        UsersDto dto = new UsersDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        //dto.setCards(CardsMapper.toDtoList(entity.getCards()));
        //dto.setAuto_possedute(AutoMapper.toDtoList(entity.getAuto_possedute()));
        //dto.setSentPayments(PaymentMapper.toDtoList(entity.getSentPayments()));
        /*dto.setReceivedPayments(PaymentMapper.toDtoList(entity.getReceivedPayments()));
        return dto;
         */
        return dto;
    }

    public static Users toEntity(UsersDto dto) {
        Users entity = new Users();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        //entity.setCards(CardsMapper.toEntityList(dto.getCards()));
        //entity.setAuto_possedute(AutoMapper.toEntityList(dto.getAuto_possedute()));
        //entity.setSentPayments(PaymentMapper.toEntityList(dto.getSentPayments()));
        //entity.setReceivedPayments(PaymentMapper.toEntityList(dto.getReceivedPayments()));

        return entity;
    }
}
