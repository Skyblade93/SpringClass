package it.classe.SpringClass.mapper;

import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Model.ContoCorrente;

public class ContoCorrenteMapper {


    public static ContoCorrenteDto mapToDto(ContoCorrente contoCorrente)
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

         public static ContoCorrente mapToEntity(ContoCorrenteDto contoCorrenteDto)
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

}
