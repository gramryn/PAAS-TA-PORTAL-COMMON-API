package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.apache.commons.collections.map.HashedMap;
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
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.TestConfig;
import org.openpaas.paasta.portal.common.api.entity.cc.CatalogCc;
import org.openpaas.paasta.portal.common.api.entity.portal.*;
import org.openpaas.paasta.portal.common.api.repository.cc.CatalogCcRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.*;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Created by indra on 2018-06-27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CommonApiApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogServiceTest extends TestConfig {
    @Mock
    Logger logger;
    @Mock
    StarterCategoryRepository starterCategoryRepository;
    @Mock
    BuildpackCategoryRepository buildpackCategoryRepository;
    @Mock
    ServicepackCategoryRepository servicepackCategoryRepository;
    @Mock
    StarterServicepackRelationRepository starterServicePackRelationRepository;
    @Mock
    StarterBuildPackRelationRepository starterBuildPackRelationRepository;
    @Mock
    CatalogHistoryRepository catalogHistoryRepository;
    @Mock
    JinqSource jinqSource;
    @Mock
    CatalogCcRepository catalogCcRepository;
    @MockBean
    CatalogService catalogService;

    Map getStarterCatalogResultMap;
    StarterCategory starterCategory1;

    Map getStarterNamesListResultMap;
    StarterCategory starterCategoryParam2;
    StarterCategory starterCategory2;
    List<StarterCategory> starterCategoryList2;

    Map getBuildPackCatalogListResultMap;
    BuildpackCategory buildpackCategoryParam3;
    BuildpackCategory buildpackCategory3;
    List<BuildpackCategory> buildpackCategoryList3;

    Map getPacksResultMap;
    StarterCategory starterCategory4;
    BuildpackCategory buildpackCategory4;
    List<StarterCategory> starterCategoryList4;
    List<BuildpackCategory> buildpackCategoryList4;

    Map getServicePackCatalogListResultMap;
    ServicepackCategory servicepackCategoryParam5;
    ServicepackCategory servicepackCategory5;
    List<ServicepackCategory> servicepackCategoryList5;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        //testGetStarterCatalog
        List<Integer> ssrIntList = new ArrayList<>();

        getStarterCatalogResultMap = new HashedMap();
        starterCategory1 = new StarterCategory();
        starterCategory1.setNo(1);
        starterCategory1.setName("Java + Mysql");
        starterCategory1.setClassification("starter_main");
        starterCategory1.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategory1.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategory1.setThumbImgName("test.png");
        starterCategory1.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategory1.setUseYn("Y");
        starterCategory1.setUserId("admin");
        starterCategory1.setCreated(null);
        starterCategory1.setLastmodified(null);
        starterCategory1.setTagsParam("{\"paasta\":\"colors6\"}");
        ssrIntList.add(1);
        starterCategory1.setServicePackCategoryNoList(ssrIntList);
        starterCategory1.setBuildPackCategoryNo(1);
        getStarterCatalogResultMap.put("info", starterCategory1);

        //testGetStarterNamesList
        starterCategoryParam2 = new StarterCategory();
        starterCategoryParam2.setNo(1);
        starterCategoryParam2.setSearchKeyword("test");

        getStarterNamesListResultMap = new HashedMap();
        starterCategoryList2 = new ArrayList<StarterCategory>();
        starterCategory2 = new StarterCategory();
        starterCategory2.setNo(1);
        starterCategory2.setName("Java + Mysql");
        starterCategory2.setClassification("starter_main");
        starterCategory2.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategory2.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategory2.setThumbImgName("test.png");
        starterCategory2.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategory2.setUseYn("Y");
        starterCategory2.setUserId("admin");
        starterCategory2.setCreated(null);
        starterCategory2.setLastmodified(null);
        starterCategory2.setTagsParam("{\"paasta\":\"colors6\"}");
        starterCategoryList2.add(starterCategory2);
        getStarterNamesListResultMap.put("list", starterCategoryList2);

        //testGetBuildPackCatalogList
        buildpackCategoryParam3 = new BuildpackCategory();
        buildpackCategoryParam3.setNo(1);
        buildpackCategoryParam3.setSearchKeyword("test");

        getBuildPackCatalogListResultMap = new HashMap();
        buildpackCategoryList3 = new ArrayList<BuildpackCategory>();
        buildpackCategory3 = new BuildpackCategory();
        buildpackCategory3.setNo(1);
        buildpackCategory3.setName("Java8 온라인 앱 개발환경");
        buildpackCategory3.setClassification("buildpack_system");
        buildpackCategory3.setSummary("Java8 온라인 앱 개발환경");
        buildpackCategory3.setDescription("Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.");
        buildpackCategory3.setBuildPackName("java_buildpack");
        buildpackCategory3.setThumbImgName("java.jpg");
        buildpackCategory3.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D");
        buildpackCategory3.setUseYn("Y");
        buildpackCategory3.setAppSampleFileName("sample-spring.war");
        buildpackCategory3.setAppSampleFilePath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D");
        buildpackCategory3.setAppSampleFileSize("9478983");
        buildpackCategory3.setUserId("admin");
        buildpackCategory3.setCreated(null);
        buildpackCategory3.setLastmodified(null);
        buildpackCategory3.setDocFileUrl("https://www.java.com/ko/download/faq/java8.xml");
        buildpackCategory3.setTagsParam("{\"community\":\"colors7\",\"free\":\"colors1\"}");
        buildpackCategoryList3.add(buildpackCategory3);
        getBuildPackCatalogListResultMap.put("list", buildpackCategoryList3);

        //testGetPacks
        getPacksResultMap = new HashMap();
        starterCategoryList4 = new ArrayList<StarterCategory>();
        starterCategory4 = new StarterCategory();
        starterCategory4.setNo(1);
        starterCategory4.setName("Java + Mysql");
        starterCategory4.setClassification("starter_main");
        starterCategory4.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategory4.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategory4.setThumbImgName("test.png");
        starterCategory4.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategory4.setUseYn("Y");
        starterCategory4.setUserId("admin");
        starterCategory4.setCreated(null);
        starterCategory4.setLastmodified(null);
        starterCategory4.setTagsParam("{\"paasta\":\"colors6\"}");
        starterCategoryList4.add(starterCategory4);

        buildpackCategoryList4 = new ArrayList<BuildpackCategory>();
        buildpackCategory4 = new BuildpackCategory();
        buildpackCategory4.setNo(1);
        buildpackCategory4.setName("Java8 온라인 앱 개발환경");
        buildpackCategory4.setClassification("buildpack_system");
        buildpackCategory4.setSummary("Java8 온라인 앱 개발환경");
        buildpackCategory4.setDescription("Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.");
        buildpackCategory4.setBuildPackName("java_buildpack");
        buildpackCategory4.setThumbImgName("java.jpg");
        buildpackCategory4.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D");
        buildpackCategory4.setUseYn("Y");
        buildpackCategory4.setAppSampleFileName("sample-spring.war");
        buildpackCategory4.setAppSampleFilePath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D");
        buildpackCategory4.setAppSampleFileSize("9478983");
        buildpackCategory4.setUserId("admin");
        buildpackCategory4.setCreated(null);
        buildpackCategory4.setLastmodified(null);
        buildpackCategory4.setDocFileUrl("https://www.java.com/ko/download/faq/java8.xml");
        buildpackCategory4.setTagsParam("{\"community\":\"colors7\",\"free\":\"colors1\"}");
        buildpackCategoryList4.add(buildpackCategory4);

        getPacksResultMap.put("TemplateList", starterCategoryList4);
        getPacksResultMap.put("BuildPackList", buildpackCategoryList4);

        //testGetServicePackCatalogList
        getServicePackCatalogListResultMap = new HashMap();
        servicepackCategoryParam5 = new ServicepackCategory();
        servicepackCategoryParam5.setNo(1);
        servicepackCategoryParam5.setSearchKeyword("test");

        servicepackCategoryList5 = new ArrayList<ServicepackCategory>();
        servicepackCategory5 = new ServicepackCategory();
        servicepackCategory5.setNo(1);
        servicepackCategory5.setName("Redis 서비스");
        servicepackCategory5.setClassification("service_nosql");
        servicepackCategory5.setSummary("Redis NoSQL 및 In memory 서비스");
        servicepackCategory5.setDescription("<p>Redis는 메모리 기반의 Key/Value Store 로써 NoSQL DBMS 및 In memory 솔루션으로 분리된다.</p>\n");
        servicepackCategory5.setServicePackName("redis");
        servicepackCategory5.setThumbImgName("redis.jpg");
        servicepackCategory5.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a34c2adaa7904d38ba0e770c90d85d42-1527673993744-cmVkaXM_Pz8uanBn");
        servicepackCategory5.setUseYn("Y");
        servicepackCategory5.setUserId("admin");
        servicepackCategory5.setCreated(null);
        servicepackCategory5.setLastmodified(null);
        servicepackCategory5.setParameter("{\"test\":\"text\"}");
        servicepackCategory5.setAppBindParameter("{\"sdfsdfsdfsdf\":\"text\"}");
        servicepackCategory5.setDashboardUseYn("N");
        servicepackCategory5.setAppBindYn("Y");
        servicepackCategory5.setDocFileUrl("https://redis.io/");
        servicepackCategory5.setTagsParam("{\"paasta\":\"colors6\",\"pay\":\"colors2\"}");
        servicepackCategoryList5.add(servicepackCategory5);
        getServicePackCatalogListResultMap.put("list", servicepackCategoryList5);

    }

    @Test
    public void testGetStarterCatalog() throws Exception {
        when(catalogService.getStarterCatalog(1)).thenReturn(getStarterCatalogResultMap);

        Map<String, Object> result = catalogService.getStarterCatalog(1);
        Assert.assertEquals(getStarterCatalogResultMap, result);
    }

    @Test
    public void testGetStarterNamesList() throws Exception {
        when(catalogService.getStarterNamesList(starterCategoryParam2)).thenReturn(getStarterNamesListResultMap);

        Map<String, Object> result = catalogService.getStarterNamesList(starterCategoryParam2);
        Assert.assertEquals(getStarterNamesListResultMap, result);
    }

    @Test
    public void testGetBuildPackCatalogList() throws Exception {
        when(catalogService.getBuildPackCatalogList(buildpackCategoryParam3)).thenReturn(getBuildPackCatalogListResultMap);

        Map<String, Object> result = catalogService.getBuildPackCatalogList(buildpackCategoryParam3);
        Assert.assertEquals(getBuildPackCatalogListResultMap, result);
    }

    @Test
    public void testGetPacks() throws Exception {
        when(catalogService.getPacks("test")).thenReturn(getPacksResultMap);

        Map<String, Object> result = catalogService.getPacks("test");
        Assert.assertEquals(getPacksResultMap, result);
    }

    @Test
    public void testGetServicePackCatalogList() throws Exception {
        when(catalogService.getServicePackCatalogList(servicepackCategoryParam5)).thenReturn(getServicePackCatalogListResultMap);

        Map<String, Object> result = catalogService.getServicePackCatalogList(servicepackCategoryParam5);
        Assert.assertEquals(getServicePackCatalogListResultMap, result);
    }

    @Test
    public void testGetStarterCatalogCount() throws Exception {
        when(jinqSource.streamAllPortal(any())).thenReturn(null);

        int result = catalogService.getStarterCatalogCount(new StarterCategory());
        Assert.assertEquals(0, result);
    }

    @Test
    public void testGetBuildPackCatalogCount() throws Exception {
        when(jinqSource.streamAllPortal(any())).thenReturn(null);

        int result = catalogService.getBuildPackCatalogCount(new BuildpackCategory());
        Assert.assertEquals(0, result);
    }

    @Test
    public void testGetServicePackCatalogCount() throws Exception {
        when(jinqSource.streamAllPortal(any())).thenReturn(null);

        int result = catalogService.getServicePackCatalogCount(new ServicepackCategory());
        Assert.assertEquals(0, result);
    }

    @Test
    public void testInsertStarterCatalog() throws Exception {
        Map<String, Object> result = catalogService.insertStarterCatalog(new StarterCategory());
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testInsertBuildPackCatalog() throws Exception {
        Map<String, Object> result = catalogService.insertBuildPackCatalog(new BuildpackCategory());
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testInsertServicePackCatalog() throws Exception {
        Map<String, Object> result = catalogService.insertServicePackCatalog(new ServicepackCategory());
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testUpdateStarterCatalog() throws Exception {
        when(starterServicePackRelationRepository.findByStarterCatalogNo(anyInt())).thenReturn(Arrays.<StarterServicepackRelation>asList(new StarterServicepackRelation()));
        when(starterBuildPackRelationRepository.findByStarterCatalogNo(anyInt())).thenReturn(Arrays.<StarterBuildpackRelation>asList(new StarterBuildpackRelation()));

        Map<String, Object> result = catalogService.updateStarterCatalog(new StarterCategory());
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testUpdateBuildPackCatalog() throws Exception {
        Map<String, Object> result = catalogService.updateBuildPackCatalog(new BuildpackCategory());
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testUpdateServicePackCatalog() throws Exception {
        Map<String, Object> result = catalogService.updateServicePackCatalog(new ServicepackCategory());
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testDeleteStarterCatalog() throws Exception {
        when(starterServicePackRelationRepository.findByStarterCatalogNo(anyInt())).thenReturn(Arrays.<StarterServicepackRelation>asList(new StarterServicepackRelation()));
        when(starterBuildPackRelationRepository.findByStarterCatalogNo(anyInt())).thenReturn(Arrays.<StarterBuildpackRelation>asList(new StarterBuildpackRelation()));

        Map<String, Object> result = catalogService.deleteStarterCatalog(0);
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testDeleteBuildPackCatalog() throws Exception {
        Map<String, Object> result = catalogService.deleteBuildPackCatalog(0);
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testDeleteServicePackCatalog() throws Exception {
        Map<String, Object> result = catalogService.deleteServicePackCatalog(0);
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testGetHistory() throws Exception {
        when(starterCategoryRepository.findByNo(anyInt())).thenReturn(new StarterCategory());
        when(buildpackCategoryRepository.findByNo(anyInt())).thenReturn(new BuildpackCategory());
        when(servicepackCategoryRepository.findByNo(anyInt())).thenReturn(new ServicepackCategory());
        when(catalogHistoryRepository.findAllByUserIdOrderByLastmodifiedDesc(any())).thenReturn(Arrays.<CatalogHistory>asList(new CatalogHistory()));

        Map<String, Object> result = catalogService.getHistory("userid");
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testGetStarterRelation() throws Exception {
        when(starterCategoryRepository.findByNo(anyInt())).thenReturn(new StarterCategory());
        when(buildpackCategoryRepository.findByNo(anyInt())).thenReturn(new BuildpackCategory());
        when(servicepackCategoryRepository.findByNo(anyInt())).thenReturn(new ServicepackCategory());
        when(starterServicePackRelationRepository.findByStarterCatalogNo(anyInt())).thenReturn(Arrays.<StarterServicepackRelation>asList(new StarterServicepackRelation()));
        when(starterBuildPackRelationRepository.findFirstByStarterCatalogNo(anyInt())).thenReturn(new StarterBuildpackRelation());

        Map<String, Object> result = catalogService.getStarterRelation(0);
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testInsertHistroy() throws Exception {
        Map<String, Object> result = catalogService.insertHistroy(new CatalogHistory());
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }

    @Test
    public void testGetListRoutes() throws Exception {
        List<CatalogCc> result = catalogService.getListRoutes();
        Assert.assertEquals(Arrays.<CatalogCc>asList(new CatalogCc()), result);
    }

    @Test
    public void testCheckRoute() throws Exception {
        when(catalogCcRepository.findByHost(any())).thenReturn(new CatalogCc());

        Map<String, Object> result = catalogService.checkRoute("host");
        Assert.assertEquals(new HashMap<String, Object>() {{
            put("String", null);
        }}, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme