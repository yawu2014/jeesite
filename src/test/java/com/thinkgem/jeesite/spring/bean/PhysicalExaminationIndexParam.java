package com.thinkgem.jeesite.spring.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author Reticence (liuyang_blue@qq.com)
 * @date 2017年7月19日 下午5:50:21
 * @version 1.0
 * @parameter
 */
public class PhysicalExaminationIndexParam implements Serializable {
    /** serialVersionUID **/
    private static final long serialVersionUID = 6429152711363215777L;

    /** 指标名称 **/
    private String indexName;
    /** 检查结果 **/
    private String checkResult;
    /** 参考范围 **/
    private String referenceRange;
    /** 评估结果 **/
    private String evaluationResult;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getReferenceRange() {
        return referenceRange;
    }

    public void setReferenceRange(String referenceRange) {
        this.referenceRange = referenceRange;
    }

    public String getEvaluationResult() {
        return evaluationResult;
    }

    public void setEvaluationResult(String evaluationResult) {
        this.evaluationResult = evaluationResult;
    }

    public static String getJSONString(Map<String, List<PhysicalExaminationIndexParam>> map) {
        return JSON.toJSONString(map);
    }
}
