package it.classe.SpringClass.Mapper;


import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Model.ContoCorrente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ContoCorrenteMapper extends AbstractConverter<ContoCorrente ,ContoCorrenteDto>{

    final private ModelMapper mapper=new ModelMapper();

    @Override
    public ContoCorrente toEntity(ContoCorrenteDto dto) {
        return mapper.map(dto ,ContoCorrente.class);
    }

    @Override
    public ContoCorrenteDto toDTO(ContoCorrente entity) {
        return mapper.map(entity , ContoCorrenteDto.class);
    }
}
