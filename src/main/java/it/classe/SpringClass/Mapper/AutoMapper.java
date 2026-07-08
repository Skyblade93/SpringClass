package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.AutoDto;
import it.classe.SpringClass.Model.Auto;
import org.hibernate.boot.internal.Abstract;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AutoMapper extends AbstractConverter<Auto,AutoDto>{

final private ModelMapper mapper=new ModelMapper();
    @Override
    public Auto toEntity(AutoDto dto) {
        return mapper.map(dto,Auto.class);
    }

    @Override
    public AutoDto toDTO(Auto entity) {
        return mapper.map(entity,AutoDto.class);
    }
}

