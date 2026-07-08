package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlunniMapper extends AbstractConverter<Alunno, AlunnoDto>{


    final private ModelMapper mapper = new ModelMapper();

    @Override
    public Alunno toEntity(AlunnoDto Dto) {
        return mapper.map(Dto, Alunno.class); //faccio associazione, inserisco cosa deve essere convertito.
        //quindi converto il dto e poi a cosa lo devo convertire, quindi alunno.class. fatta conversione
    }

    @Override
    public AlunnoDto toDTO(Alunno entity) {
        return mapper.map(entity, AlunnoDto.class);
        //faccio la stessa cosa di sopra, ma non L'ENTITY MA IL DTO
    }
}
