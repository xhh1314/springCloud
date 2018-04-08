package com.example.myoauth;

import com.example.myoauth.common.dao.OauthAccessTokenMapper;
import com.example.myoauth.common.entity.AccessTokenDO;
import com.example.myoauth.common.manage.AccessTokenManage;
import com.example.myoauth.dto.AccessTokenDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = OauthServerApplication.class)
public class OauthServerApplicationTests {

    @Autowired
    private AccessTokenManage accessTokenManage;

    @Autowired
    private OauthAccessTokenMapper accessTokenMapper;

    @Test
    public void accessTokenInsertTest() {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientKey("lihao");
        accessTokenDTO.setToken("rrrrrrrrrexd");
        accessTokenDTO.setCreateTime(new Date());
        // accessTokenDTO.setRefreshTokenId(333);
        accessTokenManage.saveAccessToken(accessTokenDTO);

    }

    @Test
    public void listAccessTokenTest() {
        List<AccessTokenDO> accessTokenDOList = accessTokenMapper.listAccessTokenByClientKey("haiwainet");
    }


}
