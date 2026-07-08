package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.CardsDto;
import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Model.Cards;
import it.classe.SpringClass.Model.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardsMapper extends AbstractConverter<Cards, CardsDto> {

    final private ModelMapper mapper = new ModelMapper();

    @Override
    public Cards toEntity(CardsDto dto) {
        return mapper.map(dto, Cards.class);
    }

    @Override
    public CardsDto toDTO(Cards entity) {
        return mapper.map(entity, CardsDto.class);
    }
}

