package bg.rentacar.service;

import bg.rentacar.model.entity.Image;
import bg.rentacar.repository.ImageRepository;
import bg.rentacar.service.impl.ImageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {
    private ImageService toTest;

    @Mock
    private ImageRepository mockImageRepository;

    @BeforeEach
    public void setUp() {
        toTest = new ImageServiceImpl(mockImageRepository);
    }


    @Test
    void initDefaultCarImageInDb_SaveDefaultImage() {
        when(mockImageRepository.count()).thenReturn(0L);
        toTest.initDefaultCarImageInDb();

        verify(mockImageRepository, times(1)).save(any(Image.class));
    }
}
