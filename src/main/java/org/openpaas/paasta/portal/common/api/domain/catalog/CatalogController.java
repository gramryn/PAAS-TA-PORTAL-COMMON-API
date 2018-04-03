package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.openpaas.paasta.portal.common.api.entity.portal.BuildpackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.Catalog;
import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterCategory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-06.
 */
@RestController
@RequestMapping(value = {"/catalogs"})
public class CatalogController {
    private static final Logger logger = getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;


    /**
     * 앱 템플릿 카탈로그를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping("/oneStarterCatalog")
    public Map<String, Object> getOneStarterCatalog(@ModelAttribute Catalog param) {
        return catalogService.getOneStarterCatalog(param);
    }


    /**
     * 앱 템플릿 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping("/starterpacks")
    public Map<String, Object> getStarterNames(@ModelAttribute Catalog param) {
        return catalogService.getStarterNamesList(param);
    }

    /**
     * 앱 템플릿 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping("/starterpacks/{no}")
    public Map<String, Object> getStarterNames(@PathVariable("no") int no, @ModelAttribute Catalog param) {
        param.setNo(no);
        return catalogService.getStarterNamesList(param);
    }


    /**
     * 앱 개발환경 카탈로그 목록을 조회한다.
     */
    @GetMapping("/buildpacks")
    public Map<String, Object> getBuildPackCatalogList(@ModelAttribute Catalog param) {
        return catalogService.getBuildPackCatalogList(param);
    }

    /**
     * 앱 개발환경 카탈로그 목록을 조회한다.
     */
    @GetMapping("/buildpacks/{no}")
    public Map<String, Object> getBuildPackCatalogList(@PathVariable("no") int no, @ModelAttribute Catalog param) {
        param.setNo(no);
        return catalogService.getBuildPackCatalogList(param);
    }


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping("/servicepacks")
    public Map<String, Object> getServicePackCatalogList(@ModelAttribute Catalog param) {
        return catalogService.getServicePackCatalogList(param);
    }


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping("/servicepacks/{no}")
    public Map<String, Object> getServicePackCatalogList(@PathVariable("no") int no, @ModelAttribute Catalog param) {
        param.setNo(no);
        return catalogService.getServicePackCatalogList(param);
    }

    /**
     * 앱 템플릿 카탈로그 개수를 조회한다.
     */
    @GetMapping("/starterpacks/count")
    public Map<String, Object> getStarterCatalogCount() throws Exception {
        int buildPackCnt = catalogService.getStarterCatalogCount();
        logger.info("getStarterCatalogCount : " + buildPackCnt);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("getStarterCatalogCount", buildPackCnt);

        return resultMap;
    }


    /**
     * 앱 개발환경 목록 개수를 조회한다.
     */
    @GetMapping("/buildpacks/count")
    public Map<String, Object> getBuildPackCatalogCount() throws Exception {
        int buildPackCnt = catalogService.getBuildPackCatalogCount();
        logger.info("getBuildPackCatalogCount : " + buildPackCnt);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("getBuildPackCatalogCount", buildPackCnt);

        return resultMap;
    }


    /**
     * 서비스 목록 개수를 조회한다.
     */
    @GetMapping("/servicepacks/count")
    public Map<String, Object> getServicePackCatalogCount() throws Exception {
        int servicePackCnt = catalogService.getServicePackCatalogCount();
        logger.info("getServicePackCatalogCount : " + servicePackCnt);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("getServicePackCatalogCount", servicePackCnt);

        return resultMap;
    }



    /**
     * 스타터 개발환경 카탈로그를 생성한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping("/starterpacks")
    public Map<String, Object> insertStarterPacks(@RequestBody StarterCategory param) {
        return catalogService.insertStarterCatalog(param);
    }


    /**
     * 앱 개발환경 카탈로그를 생성한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping("/buildpacks")
    public Map<String, Object> insertBuildPackCatalog(@RequestBody BuildpackCategory param) {
        return catalogService.insertBuildPackCatalog(param);
    }


    /**
     * 서비스 카탈로그를 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping("/servicepacks")
    public Map<String, Object> insertServicePackCatalog(@RequestBody ServicepackCategory param) {
        return catalogService.insertServicePackCatalog(param);
    }


    /**
     * 서비스 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(value = {"/starterpack/{no}"})
    public StarterCategory updatesStarterPackCatalog(@PathVariable int no, @RequestBody StarterCategory param) {
        param.setNo(no);
        StarterCategory resultStr = catalogService.updateStarterCatalog(param);
        return resultStr;
    }


    /**
     * 앱 개발환경 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(value = {"/buildpacks/{no}"})
    public BuildpackCategory updateBuildPackCatalog(@PathVariable int no, @RequestBody BuildpackCategory param) {
        param.setNo(no);
        BuildpackCategory resultStr = catalogService.updateBuildPackCatalog(param);
        return resultStr;
    }


    /**
     * 서비스 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(value = {"/servicepacks/{no}"})
    public ServicepackCategory updateServicePackCatalog(@PathVariable int no, @RequestBody ServicepackCategory param) {
        param.setNo(no);
        ServicepackCategory resultStr = catalogService.updateServicePackCatalog(param);
        return resultStr;
    }

    /**
     * 앱 템플릿 카탈로그를 삭제한다.
     *
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {"/starterpacks/{no}"})
    public Map<String, Object> deleteStarterCatalog(@PathVariable int no) {
        return catalogService.deleteStarterCatalog(no);
    }


    /**
     * 앱 개발환경 카탈로그를 삭제한다.
     *
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {"buildpacks/{no}"})
    public Map<String, Object> deleteBuildPackCatalog(@PathVariable int no) {
        return catalogService.deleteBuildPackCatalog(no);
    }

    /**
     * 서비스 카탈로그를 삭제한다.
     *
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {"servicepacks/{no}"})
    public Map<String, Object> deleteServicePackCatalog(@PathVariable int no) {
        return catalogService.deleteServicePackCatalog(no);
    }

}
