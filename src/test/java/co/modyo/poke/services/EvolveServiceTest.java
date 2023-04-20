package co.modyo.poke.services;

import static co.modyo.poke.utils.JsonFileUtils.readJsonFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import co.modyo.poke.adapter.dto.EvolutionDetail;
import co.modyo.poke.adapter.services.PokeApiAdapterServiceImpl;
import co.modyo.poke.dto.Evolution;
import co.modyo.poke.services.impl.EvolveServiceImpl;
import co.modyo.poke.services.impl.PokeServiceImpl;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test of {@link PokeServiceImpl}
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(
  classes = { PokeServiceImpl.class, PokeApiAdapterServiceImpl.class }
)
public class EvolveServiceTest {

  /**
   * Instance of the class under test
   */
  @InjectMocks
  public EvolveServiceImpl evolveService;

  @MockBean
  public PokeApiAdapterServiceImpl apiAdapterService;

  @Before
  public void init() {
    evolveService = new EvolveServiceImpl(apiAdapterService);
  }

  @Test
  public void getEvolutionInfo_whenCallWithValidID_shouldReturnValidEvolutionDetail() {
    Integer givenID = 900;
    EvolutionDetail expectedEvolutionInfo = readJsonFile(
      "/evolution-chain.json",
      EvolutionDetail.class
    );

    when(apiAdapterService.getEvolutionDetail(givenID))
      .thenReturn(expectedEvolutionInfo);

    List<Evolution> evolutionReturned = evolveService.getEvolutionInfo(givenID);

    assertThat(evolutionReturned).isNotNull().doesNotContainNull();
  }
}
