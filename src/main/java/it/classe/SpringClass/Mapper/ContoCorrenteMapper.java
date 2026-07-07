package it.classe.SpringClass.mapper;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.ContoCorrente;

import java.util.ArrayList;
import java.util.List;

public class ContoCorrenteMapper {


    public static ContoCorrenteDto toDto(ContoCorrente contoCorrente)
         {
             ContoCorrenteDto contoDto=new ContoCorrenteDto();

             contoDto.setIdConto(contoCorrente.getIdConto());
             contoDto.setNome(contoCorrente.getNome());
             contoDto.setCognome(contoCorrente.getCognome());
             contoDto.setIban(contoCorrente.getIban());
             contoDto.setEmail(contoCorrente.getEmail());
             contoDto.setCcv(contoCorrente.getCcv());

                    return contoDto;
         }

         public static ContoCorrente toEntity(ContoCorrenteDto contoCorrenteDto)
         {
             ContoCorrente contoCorrente= new ContoCorrente();
             contoCorrente.setIdConto(contoCorrenteDto.getIdConto());
             contoCorrente.setNome(contoCorrente.getNome());
             contoCorrente.setCognome(contoCorrenteDto.getCognome());
             contoCorrente.setEmail(contoCorrenteDto.getEmail());
             contoCorrente.setIban(contoCorrenteDto.getIban());
             contoCorrente.setCcv(contoCorrenteDto.getCcv());

                return contoCorrente;
         }

    public static List<ContoCorrente> toEntityList(List<ContoCorrenteDto> dtos) {
        List<ContoCorrente> list = new ArrayList<>();
        for (ContoCorrenteDto dto : dtos) {
            list.add(toEntity(dto));
        }
        return list;
    }

    public static List<ContoCorrenteDto> toDtoList(List<ContoCorrente> entity) {
        List<ContoCorrenteDto> listdto = new ArrayList<>();
        for (ContoCorrente dto : entity) {
            listdto.add(toDto(dto));
        }
        return listdto;
    }

}
