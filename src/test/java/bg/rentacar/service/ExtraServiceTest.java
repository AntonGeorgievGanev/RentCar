package bg.rentacar.service;

import bg.rentacar.model.dto.AllExtrasDTO;
import bg.rentacar.model.dto.ExtraDTO;
import bg.rentacar.model.entity.Extra;
import bg.rentacar.repository.ExtraRepository;
import bg.rentacar.service.impl.ExtraServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExtraServiceTest {

    private ExtraService toTest;

    @Mock
    private ExtraRepository mockExtraRepository;

    @Captor
    private ArgumentCaptor<Extra> extraCaptor;

    @BeforeEach
    void setUp() {

        toTest = new ExtraServiceImpl(
                mockExtraRepository,
                new ModelMapper()

        );
    }

    @Test
    void testAddExtra_isAdded() {
        ExtraDTO extraDTO = new ExtraDTO();
        extraDTO.setName("extra");
        extraDTO.setPrice(BigDecimal.valueOf(100));

        toTest.addExtra(extraDTO);
        verify(mockExtraRepository).save(extraCaptor.capture());
        Extra extra = extraCaptor.getValue();
        Assertions.assertEquals(extraDTO.getName(), extra.getName());
        Assertions.assertEquals(extraDTO.getPrice(), extra.getPrice());
    }

    @Test
    void testGetAllExtra_isReturned() {
        Extra firstExtra = new Extra();
        Extra secondExtra = new Extra();
        List<Extra> extras = List.of(firstExtra, secondExtra);

        when(mockExtraRepository.findAll()).thenReturn(extras);
        AllExtrasDTO allExtrasDTO = toTest.getAllExtras();
        Assertions.assertEquals(extras.size(), allExtrasDTO.getAllExtrasDTO().size());

    }
}
