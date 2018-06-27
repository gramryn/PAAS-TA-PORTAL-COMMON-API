package org.openpaas.paasta.portal.common.api.domain.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.CommonApiApplication;
import org.openpaas.paasta.portal.common.api.config.TestConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.cc.BuildpackLifecyleDataCc;
import org.openpaas.paasta.portal.common.api.repository.cc.BuildpackLifecyleDataCcRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.BuildpackCategoryRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * Created by indra on 2018-06-27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppServiceTest extends TestConfig {
    @Mock
    BuildpackCategoryRepository buildpackCategoryRepository;
    @Mock
    BuildpackLifecyleDataCcRepository buildpackLifecyleDataCcRepository;
    @Mock
    PortalConfig portalConfig;
    @MockBean
    AppService appService;

    String thumbImgPath;

    BuildpackLifecyleDataCc getAppImageUrlResult;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Date d = new Date();

        thumbImgPath = "http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/9614a17898ca41cd9904b61add6f5385-1527590255327-R28uanBn";

        getAppImageUrlResult = new BuildpackLifecyleDataCc();
        getAppImageUrlResult.setId("123");
        getAppImageUrlResult.setGuid("5512880f-bc2a-47b3-bafb-b1524b848623");
        getAppImageUrlResult.setCreatedAt(d);
        getAppImageUrlResult.setUpdatedAt(d);
        getAppImageUrlResult.setAppGuid("126ef83b-5146-4374-baef-4c9ba8d92223");
        getAppImageUrlResult.setDropletGuid("45f17789-cedb-4d49-9cf3-a472335040b7");
        getAppImageUrlResult.setStack("cflinuxfs2");
        getAppImageUrlResult.setEncryptedBuildpackUrl(null);
        getAppImageUrlResult.setEncryptedBuildpackUrlSalt(null);
        getAppImageUrlResult.setAdminBuildpackName("java_buildpack");
        getAppImageUrlResult.setBuildGuid("96f7f60d-448b-47b8-8627-b8607d159f3b");
    }

    @Test
    public void testGetAppImageUrl() throws Exception {
        when(buildpackLifecyleDataCcRepository.findDistinctTopByAppGuid("5512880f-bc2a-47b3-bafb-b1524b848623")).thenReturn(getAppImageUrlResult);
        when(appService.getAppImageUrl("5512880f-bc2a-47b3-bafb-b1524b848623")).thenReturn(thumbImgPath);

        String result = appService.getAppImageUrl("5512880f-bc2a-47b3-bafb-b1524b848623");
        Assert.assertEquals(thumbImgPath, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme