package it.test.ControllerTest;


import it.classe.SpringClass.Controller.OrdineController;
import it.classe.SpringClass.Dto.OrdineDto;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Service.OrdineService;
import it.classe.SpringClass.SpringClassApplication;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(OrdineController.class)
@ContextConfiguration(classes = SpringClassApplication.class)
public class OrdineControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private OrdineService ordineService;

    private OrdineDto ordineDto;

    private OrdineDto ordineDto1;

    LocalDateTime data = LocalDateTime.of(2026, 7, 9, 10, 30);

    @BeforeEach
    void setUp() {
        ordineDto = new OrdineDto(
                1,
                "Laptop",
                data,
                1000,
                1,
                null
        );

        ordineDto1= new OrdineDto(
                2,
                "Mouse",
                data,
                1000,
                2,
                null
        );

    }

    @Test
    void read_positive() throws Exception {

        when(ordineService.read(1))
                .thenReturn(ordineDto);

        mockMvc.perform(
                        get("/ordine/read?id=1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(ordineService)
                .read(1);
    }

    @Test
    void read_negative() throws Exception {

        when(ordineService.read(99))
                .thenThrow(new RuntimeException("Ordine non trovato"));

        assertThrows(Exception.class, () ->
                mockMvc.perform(
                        get("/ordine/read?id=99")
                )
        );

        verify(ordineService)
                .read(99);
    }


    @Test
    void insert_positive() throws Exception {

        when(ordineService.insert(any(OrdineDto.class)))
                .thenReturn(ordineDto);


        mockMvc.perform(
                        post("/ordine/insert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{"
                                        + "\"id\":1,"
                                        + "\"prodotti\":\"Laptop\","
                                        + "\"dataCreazione\":\"2026-07-09T10:30:00\","
                                        + "\"importo\":1000,"
                                        + "\"quantita\":1"
                                        + "}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));


        verify(ordineService)
                .insert(any(OrdineDto.class));
    }

    @Test
    void insert_negative() throws Exception {

        when(ordineService.insert(any(OrdineDto.class)))
                .thenThrow(new RuntimeException("Errore inserimento"));


        assertThrows(Exception.class, () ->
                mockMvc.perform(
                        post("/ordine/insert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{"
                                        + "\"id\":1,"
                                        + "\"prodotti\":\"Laptop\","
                                        + "\"dataCreazione\":\"2026-07-09T10:30:00\","
                                        + "\"importo\":1000,"
                                        + "\"quantita\":1"
                                        + "}")
                )
        );


        verify(ordineService)
                .insert(any(OrdineDto.class));
    }


    @Test
    void update_positive() throws Exception {

        when(ordineService.update(any(OrdineDto.class)))
                .thenReturn(ordineDto);


        mockMvc.perform(
                        put("/ordine/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":1,\"prodotti\":\"Laptop\",\"dataCreazione\":\"2026-07-09T10:30:00\",\"importo\":1000,\"quantita\":1}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));


        verify(ordineService)
                .update(any(OrdineDto.class));
    }

    @Test
    void update_negative() throws Exception {

        when(ordineService.update(any(OrdineDto.class)))
                .thenThrow(new RuntimeException("Errore modifica"));

        assertThrows(Exception.class, () ->
                mockMvc.perform(
                        put("/ordine/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{"
                                        + "\"id\":99,"
                                        + "\"prodotti\":\"Tablet\","
                                        + "\"dataCreazione\":\"2026-07-09T10:30:00\","
                                        + "\"importo\":500,"
                                        + "\"quantita\":2"
                                        + "}")
                )
        );

        verify(ordineService)
                .update(any(OrdineDto.class));
    }


    @Test
    void delete_positive() throws Exception {

        doNothing()
                .when(ordineService)
                .delete(1);


        mockMvc.perform(
                        delete("/ordine/delete?id=1")
                )
                .andExpect(status().isOk());


        verify(ordineService)
                .delete(1);
    }

    @Test
    void delete_negative() throws Exception {

        doThrow(new RuntimeException("Ordine non trovato"))
                .when(ordineService)
                .delete(99);


        assertThrows(
                Exception.class,
                () -> mockMvc.perform(
                        delete("/ordine/delete?id=99")
                )
        );


        verify(ordineService)
                .delete(99);
    }


    @Test
    void getAll_positive() throws Exception {

        List<OrdineDto> lista = List.of(ordineDto);

        when(ordineService.getAll())
                .thenReturn(lista);

        mockMvc.perform(
                        get("/ordine/getall")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(ordineService)
                .getAll();
    }

    @Test
    void getAll_negative() throws Exception {

        when(ordineService.getAll())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(
                        get("/ordine/getall")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        verify(ordineService)
                .getAll();
    }

    @Test
    void findByDataCreazione_positive() throws Exception {

        OrdineDto dto = ordineDto;

        when(ordineService.findByDataCreazione(data))
                .thenReturn(dto);

        mockMvc.perform(
                        get("/ordine/data-creazione/2026-07-09T10:30:00")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(ordineService)
                .findByDataCreazione(data);
    }

    @Test
    void findByDataCreazione_negative() throws Exception {

        when(ordineService.findByDataCreazione(data))
                .thenReturn(null);

        mockMvc.perform(
                        get("/ordine/data-creazione/2026-07-09T10:30:00")
                )
                .andExpect(status().isOk());

        verify(ordineService)
                .findByDataCreazione(data);
    }

    @Test
    void findByImporto_positive() throws Exception {

        OrdineDto dto = ordineDto;

        List<OrdineDto> lista = List.of(dto);

        when(ordineService.findByImporto(1000.0))
                .thenReturn(lista);

        mockMvc.perform(
                        get("/ordine/importo/1000")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1));



        verify(ordineService)
                .findByImporto(1000.0);
    }

    @Test
    void findByImporto_negative() throws Exception {

        when(ordineService.findByImporto(9999.0))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(
                        get("/ordine/importo/9999")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        verify(ordineService)
                .findByImporto(9999.0);
    }

    @Test
    void findByUsers_positive() throws Exception {

        when(ordineService.findByUsers(any(Users.class)))
                .thenReturn(List.of(ordineDto));

        mockMvc.perform(
                        get("/ordine/users/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(ordineService)
                .findByUsers(any(Users.class));
    }

    @Test
    void findByUsers_negative() throws Exception {

        when(ordineService.findByUsers(any(Users.class)))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(
                        get("/ordine/users/99")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(ordineService)
                .findByUsers(any(Users.class));
    }

    @Test
    void ordiniDalPiuCostoso_positive() throws Exception {

        when(ordineService.ordiniDalPiuCostoso())
                .thenReturn(List.of(ordineDto));

        mockMvc.perform(
                        get("/ordine/piu-costosi")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(ordineService)
                .ordiniDalPiuCostoso();
    }

    @Test
    void ordiniDalPiuCostoso_negative() throws Exception {

        when(ordineService.ordiniDalPiuCostoso())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(
                        get("/ordine/piu-costosi")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(ordineService)
                .ordiniDalPiuCostoso();
    }

    @Test
    void cercaPerQuantitaMaggiore_positive() throws Exception {

        when(ordineService.cercaPerQuantitaMaggiore(5))
                .thenReturn(List.of(ordineDto));

        mockMvc.perform(
                        get("/ordine/quantita-maggiore/5")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(ordineService)
                .cercaPerQuantitaMaggiore(5);
    }

    @Test
    void cercaPerQuantitaMaggiore_negative() throws Exception {

        when(ordineService.cercaPerQuantitaMaggiore(100))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(
                        get("/ordine/quantita-maggiore/100")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(ordineService)
                .cercaPerQuantitaMaggiore(100);
    }

    @Test
    void ultimiOrdini_positive() throws Exception {

        when(ordineService.ultimiOrdini(1))
                .thenReturn(List.of(ordineDto));

        mockMvc.perform(
                        get("/ordine/ultimi/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(ordineService)
                .ultimiOrdini(1);
    }

    @Test
    void ultimiOrdini_negative() throws Exception {

        when(ordineService.ultimiOrdini(99))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(
                        get("/ordine/ultimi/99")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(ordineService)
                .ultimiOrdini(99);
    }

    @Test
    void findByQuantita_positive() throws Exception {

        List<OrdineDto> lista = List.of(ordineDto);

        when(ordineService.findByQuantita(10))
                .thenReturn(lista);

        mockMvc.perform(
                        get("/ordine/quantita/10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(ordineService)
                .findByQuantita(10);
    }

    @Test
    void findByQuantita_negative() throws Exception {

        when(ordineService.findByQuantita(100))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(
                        get("/ordine/quantita/100")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(ordineService)
                .findByQuantita(100);
    }

    @Test
    void cercaProdotti_positive() throws Exception {

        when(ordineService.cercaProdotti("Laptop"))
                .thenReturn(List.of(ordineDto));

        mockMvc.perform(
                        get("/ordine/prodotti/Laptop")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(ordineService)
                .cercaProdotti("Laptop");
    }

    @Test
    void cercaProdotti_negative() throws Exception {

        when(ordineService.cercaProdotti("Tablet"))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(
                        get("/ordine/prodotti/Tablet")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(ordineService)
                .cercaProdotti("Tablet");
    }

    @Test
    void url_positive() throws Exception {

        when(ordineService.getDatabaseUrl())
                .thenReturn("jdbc:postgresql://localhost:5432/assetadesso");

        mockMvc.perform(
                        get("/ordine/url")
                )
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("jdbc:postgresql://localhost:5432/assetadesso"));

        verify(ordineService)
                .getDatabaseUrl();
    }

    @Test
    void url_negative() throws Exception {

        when(ordineService.getDatabaseUrl())
                .thenReturn("");

        mockMvc.perform(
                        get("/ordine/url")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(ordineService)
                .getDatabaseUrl();
    }

    @Test
    void findByImporto_badRequest() throws Exception {

        mockMvc.perform(
                        get("/ordine/importo/abc")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void findByImporto_multipleResults() throws Exception {

        List<OrdineDto> lista = List.of(ordineDto, ordineDto1);

        when(ordineService.findByImporto(1000.0))
                .thenReturn(lista);

        mockMvc.perform(
                        get("/ordine/importo/1000")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(ordineService)
                .findByImporto(1000.0);
    }



}
