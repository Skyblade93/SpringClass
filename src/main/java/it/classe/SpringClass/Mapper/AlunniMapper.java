package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.Users;

import java.util.ArrayList;
import java.util.List;

public class AlunniMapper {

    public static AlunnoDto toDto (Alunno alunno) {
        AlunnoDto alunnoDto = new AlunnoDto();
        alunnoDto.setId(alunno.getId());
        alunnoDto.setNome(alunno.getNome());
        alunnoDto.setCognome(alunno.getCognome());
        alunnoDto.setVoto(alunno.getVoto());
        alunnoDto.setScuola(alunno.getScuola());
        alunnoDto.setAutos(AutoMapper.toDtoList(alunno.getAutos()));
        alunnoDto.setTask(TaskMapper.toDtoList(alunno.getTask()));

        return alunnoDto;
    }

    public static Alunno toEntity (AlunnoDto alunnoDto) {
        Alunno entity = new  Alunno();
        entity.setId(alunnoDto.getId());
        entity.setNome(alunnoDto.getNome());
        entity.setCognome(alunnoDto.getCognome());
        entity.setVoto(alunnoDto.getVoto());
        entity.setScuola(alunnoDto.getScuola());
        entity.setAutos(AutoMapper.toEntityList(alunnoDto.getAutos()));
        entity.setTask(TaskMapper.toEntityList(alunnoDto.getTask()));
        return entity;

    }
    public static List<Alunno> toEntityList(List<AlunnoDto> dtos) {
        List<Alunno> list = new ArrayList<>();
        for (AlunnoDto dto : dtos) {
            list.add(toEntity(dto));
        }
        return list;
    }

    public static List<AlunnoDto> toDtoList(List<Alunno> entity) {
        List<AlunnoDto> listdto = new ArrayList<>();
        for (Alunno dto : entity) {
            listdto.add(toDto(dto));
        }
        return listdto;
    }
}
