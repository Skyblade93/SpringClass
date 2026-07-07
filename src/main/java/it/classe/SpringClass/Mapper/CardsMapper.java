package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.CardsDto;
import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Model.Cards;

import java.util.List;

public class CardsMapper {

    public static CardsDto toDto(Cards entity){
        CardsDto dto=new CardsDto();
        dto.setId(entity.getId());
        dto.setTelefono(entity.getTelefono());
        dto.setCardNumber(entity.getCardNumber());
        dto.setCartType(entity.getCartType());
        dto.setAmount(entity.getAmount());
        dto.setAvailableAmount(entity.getAvailableAmount());
//        dto.setUsers(UsersMapper.toDtoList(entity.getUsers()));
//        dto.setContoCorrente(ContoCOrrenteMapper.toDto(entity.getContoCorrente()));
        return dto;
    }

    public static Cards toEntity(CardsDto dto){
        Cards entity=new Cards();
        entity.setId(dto.getId());
        entity.setTelefono(dto.getTelefono());
        entity.setCardNumber(dto.getCardNumber());
        entity.setCartType(dto.getCartType());
        entity.setAmount(dto.getAmount());
        entity.setAvailableAmount(dto.getAvailableAmount());
//        entity.setUsers(UsersMapper.toEntityList(dto.getUsers()));
//        entity.setContoCorrente(ContoCorrenteMapper.toEntity(dto.getContoCorrente()));
        return entity;
    }
}
