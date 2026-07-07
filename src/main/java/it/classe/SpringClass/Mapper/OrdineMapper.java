package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.OrdineDto;
import it.classe.SpringClass.Model.Ordine;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineMapper  extends AbstractConverter<Ordine, OrdineDto> {

    final private ModelMapper mapper = new ModelMapper();

    @Override
    public OrdineDto toDTO(Ordine entity) { return mapper.map(entity, OrdineDto.class);}

    @Override
    public Ordine toEntity(OrdineDto dto) { return mapper.map(dto, Ordine.class);}

}




