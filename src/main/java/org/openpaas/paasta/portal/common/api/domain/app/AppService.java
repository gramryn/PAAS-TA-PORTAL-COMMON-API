package org.openpaas.paasta.portal.common.api.domain.app;


import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.cc.BuildpackLifecyleDataCc;
import org.openpaas.paasta.portal.common.api.entity.portal.BuildpackCategory;
import org.openpaas.paasta.portal.common.api.repository.cc.BuildpackLifecyleDataCcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

/**
 * Created by indra on 2018-02-06.
 */
@RequestMapping
@Service
public class AppService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppService.class);

    @Autowired
    BuildpackLifecyleDataCcRepository buildpackLifecyleDataCcRepository;

    @Autowired
    PortalConfig portalConfig;

    //@HystrixCommand(commandKey = "getAppImageUrl")
    public String getAppImageUrl(String guid) {
        BuildpackLifecyleDataCc buildPack = buildpackLifecyleDataCcRepository.findDistinctTopByAppGuid(guid);

        EntityManager portalEm = portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager();

        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<BuildpackCategory> from = cq.from(BuildpackCategory.class);

        Predicate predicate = cb.conjunction();
        predicate = cb.and(predicate, cb.equal(from.get("buildpackName"), buildPack.getAdminBuildpackName().toString()));

        Expression maxThumbImgPath = cb.max(from.get("thumbImgPath"));

        cq.multiselect(maxThumbImgPath.alias("thumbImgPath"));
        cq.where(predicate);

        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
        Tuple result = tq.getSingleResult();

        String thumbImgPath = result.get("thumbImgPath", String.class);

        return thumbImgPath;
    }
}
