package it.classe.SpringClass.Service;

import it.classe.SpringClass.Dto.CardsDto;
import it.classe.SpringClass.Mapper.CardsMapper;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Model.Cards;
import it.classe.SpringClass.Repository.CardsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardsService extends AbstractService<Cards, CardsDto>{

    private final CardsMapper cardsMapper;
    private final CardsRepository cardsRepository;

    public CardsService(JpaRepository<Cards, Integer> repository,
                           Converter<Cards, CardsDto> converter, CardsMapper cardsMapper, CardsRepository cardsRepository) {
        super(repository, converter);
        this.cardsMapper = cardsMapper;
        this.cardsRepository = cardsRepository;
    }

    public CardsDto findByTelefono(String telefono){
        return cardsMapper.toDTO(cardsRepository.findByTelefono(telefono));
    }

    public List<CardsDto> findByCartType(String cartType){
        return cardsMapper.toDTOList(cardsRepository.findByCartType(cartType));
    }

    public CardsDto findByCardNumber(String cardNumber){
        return cardsMapper.toDTO(cardsRepository.findByCardNumber(cardNumber));
    }

    public List<CardsDto> findByAmount(int amount){
        return cardsMapper.toDTOList(cardsRepository.findByAmount(amount));
    }

    public List<CardsDto> findByAvailableAmount(int availableAmount){
        return cardsMapper.toDTOList(cardsRepository.findByAvailableAmount(availableAmount));
    }
}