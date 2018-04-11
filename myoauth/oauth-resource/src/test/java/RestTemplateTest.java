import com.example.base.rest.Rest;
import com.example.myoauth.OauthResourceApplication;
import com.example.myoauth.constants.OauthHeaderConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = OauthResourceApplication.class)
public class RestTemplateTest {


   // @Autowired
   // TestRestTemplate restTemplate;

    @Test
    public void test1(){

        RestTemplate restTemplate=new RestTemplate();
        String checkAccessTokenUrl="http://localhost:8709/oauth/check_access_token?access_token={access_token}";

        String accessToken="9feedef925a24d3cad";
        MultiValueMap<String,String> multiValueMap=new LinkedMultiValueMap();
        multiValueMap.add(OauthHeaderConstants.ACCESS_TOKEN, accessToken);
        Map<String,String> param=new HashMap<>(4);
        param.put(OauthHeaderConstants.ACCESS_TOKEN, accessToken);
        HttpEntity httpEntity=new HttpEntity(multiValueMap);
        String checkUrl = checkAccessTokenUrl;
        Rest rest = restTemplate.getForObject(checkUrl, Rest.class, accessToken);
        System.out.println();

    }


    public static void main(String[] args) {
        RestTemplate restTemplate=new RestTemplate();

    }

}
