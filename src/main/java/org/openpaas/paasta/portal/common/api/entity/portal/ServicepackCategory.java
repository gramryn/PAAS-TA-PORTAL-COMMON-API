package org.openpaas.paasta.portal.common.api.entity.portal;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by indra on 2018-02-22.
 */
@Entity
@Table(name = "servicepack_category")
public class ServicepackCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private int no;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "classification", nullable = false)
    private String classification;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "description")
    private String description;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "thumb_img_name")
    private String thumbImgName;

    @Column(name = "thumb_img_path")
    private String thumbImgPath;

    @Column(name = "use_yn", nullable = false)
    private String useYn;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "lastmodified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;

    @Column(name = "parameter")
    private String parameter;

    @Column(name = "app_bind_parameter")
    private String appBindParameter;

    @Column(name = "dashboard_use_yn", nullable = false)
    private String dashboardUseYn;

    @Column(name = "app_bind_yn", nullable = false)
    private String appBindYn;


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getThumbImgName() {
        return thumbImgName;
    }

    public void setThumbImgName(String thumbImgName) {
        this.thumbImgName = thumbImgName;
    }

    public String getThumbImgPath() {
        return thumbImgPath;
    }

    public void setThumbImgPath(String thumbImgPath) {
        this.thumbImgPath = thumbImgPath;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getAppBindParameter() {
        return appBindParameter;
    }

    public void setAppBindParameter(String appBindParameter) {
        this.appBindParameter = appBindParameter;
    }

    public String getDashboardUseYn() {
        return dashboardUseYn;
    }

    public void setDashboardUseYn(String dashboardUseYn) {
        this.dashboardUseYn = dashboardUseYn;
    }

    public String getAppBindYn() {
        return appBindYn;
    }

    public void setAppBindYn(String appBindYn) {
        this.appBindYn = appBindYn;
    }
}