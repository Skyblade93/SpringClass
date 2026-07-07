package it.classe.SpringClass.Mapper;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Model.Users;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper  extends AbstractConverter<Users, UsersDto> {

        final private ModelMapper mapper = new ModelMapper();


        @Override
        public UsersDto toDTO(Users entity) { return mapper.map(entity, UsersDto.class); }



        @Override
        public Users toEntity(UsersDto dto) { return mapper.map(dto, Users.class);}
    }




}
