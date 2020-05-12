1
https://raw.githubusercontent.com/wojciechgalach/PositionInternationalSpaceStation/master/src/test/java/pl/com/nur/internationalspacestation/internationalspacestation/service/IssDataTest.java
package pl.com.nur.internationalspacestation.internationalspacestation.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
class IssDataTest {


    @Test
    public void should_set_DstOffset_NotCountry(){
         //given
        IssData issData = new IssData();
        String EX_TERYTORY = "tereny Eksterytorialne";
        //when
        issData.setCountryName(EX_TERYTORY);
        issData.setLon("-50");
        issData.setDstOffset(0L);
        Long result = issData.getDstOffset();
        //then
        assertEquals(result, -3L);
    }

    @Test
    public void should_set_DstOffset_NotCountry_NotEquals(){
        //given
        IssData issData = new IssData();
        String EX_TERYTORY = "tereny Eksterytorialne";
        //when
        issData.setCountryName(EX_TERYTORY);
        issData.setLon("-61");
        issData.setDstOffset(0L);
        Long result = issData.getDstOffset();
        //then
        assertNotEquals(result, -3L);
    }


    @Test
    public void should_set_DstOffset_Country(){
        //given
        IssData issData = new IssData();
        //when
        issData.setCountryName("Polish");
        issData.setDstOffset(2L);
        Long result = issData.getDstOffset();
        //then
        assertEquals(result, 2L);
    }

    @Test
    public void should_set_DstOffset_Country_NotEquals(){
        //given
        IssData issData = new IssData();
        //when
        issData.setCountryName("Polish");
        issData.setDstOffset(2L);
        Long result = issData.getDstOffset();
        //then
        assertNotEquals(result, -2L);
    }

}