package domain;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class ToughJetResponseTest {

    private ToughJetResponse toughJetResponse;

    @Before
    public void before(){
        buildToughJetResponse();
    }

    private void buildToughJetResponse() {
        toughJetResponse = new ToughJetResponse();
        toughJetResponse.setBasePrice(25);
        toughJetResponse.setTax(1.2);
        toughJetResponse.setDiscount(25);
    }

    @Test
    public void getFare_checkCalculation(){
        Assertions.assertThat(toughJetResponse.getFare()).isEqualTo("41.25");
    }
}