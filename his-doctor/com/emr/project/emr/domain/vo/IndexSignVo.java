package com.emr.project.emr.domain.vo;

import com.emr.project.qc.domain.vo.EmrQcListVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndexSignVo {
   private Long id;
   private String base64Str;
   private String xmlStr;
   private String textStr;
   private Long subFileIndexId;
   private String subFileBase64Str;
   private String subFileXmlStr;
   private String subFileTextStr;
   private List signElemList;
   private String signText;
   private String signSnameOld;
   private String oldText;
   private String certInfo;
   private String certSn;
   private String nextDocCd;
   private String nextDeptCd;
   private String nextDocName;
   private String nextDeptName;
   private String type;
   private String password;
   private String indexName;
   private String subIndexName;
   private List emrQcListVoList = new ArrayList();
   private String emrFileUrl;
   private String emrIsUpdate;
   private String saveType;
   private String freeMoveType;
   private Boolean keepEdit;
   private Map elemBase64Map;

   public Map getElemBase64Map() {
      return this.elemBase64Map;
   }

   public void setElemBase64Map(Map elemBase64Map) {
      this.elemBase64Map = elemBase64Map;
   }

   public String getSignSnameOld() {
      return this.signSnameOld;
   }

   public void setSignSnameOld(String signSnameOld) {
      this.signSnameOld = signSnameOld;
   }

   public Boolean getKeepEdit() {
      return this.keepEdit;
   }

   public void setKeepEdit(Boolean keepEdit) {
      this.keepEdit = keepEdit;
   }

   public String getFreeMoveType() {
      return this.freeMoveType;
   }

   public void setFreeMoveType(String freeMoveType) {
      this.freeMoveType = freeMoveType;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getNextDeptCd() {
      return this.nextDeptCd;
   }

   public void setNextDeptCd(String nextDeptCd) {
      this.nextDeptCd = nextDeptCd;
   }

   public String getNextDeptName() {
      return this.nextDeptName;
   }

   public void setNextDeptName(String nextDeptName) {
      this.nextDeptName = nextDeptName;
   }

   public String getSaveType() {
      return this.saveType;
   }

   public void setSaveType(String saveType) {
      this.saveType = saveType;
   }

   public String getEmrIsUpdate() {
      return this.emrIsUpdate;
   }

   public void setEmrIsUpdate(String emrIsUpdate) {
      this.emrIsUpdate = emrIsUpdate;
   }

   public String getEmrFileUrl() {
      return this.emrFileUrl;
   }

   public void setEmrFileUrl(String emrFileUrl) {
      this.emrFileUrl = emrFileUrl;
   }

   public String getSubIndexName() {
      return this.subIndexName;
   }

   public void setSubIndexName(String subIndexName) {
      this.subIndexName = subIndexName;
   }

   public String getIndexName() {
      return this.indexName;
   }

   public void setIndexName(String indexName) {
      this.indexName = indexName;
   }

   public List getEmrQcListVoList() {
      return this.emrQcListVoList;
   }

   public void setEmrQcListVoList(List emrQcListVoList) {
      this.emrQcListVoList = emrQcListVoList;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getBase64Str() {
      return this.base64Str;
   }

   public void setBase64Str(String base64Str) {
      this.base64Str = base64Str;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }

   public Long getSubFileIndexId() {
      return this.subFileIndexId;
   }

   public void setSubFileIndexId(Long subFileIndexId) {
      this.subFileIndexId = subFileIndexId;
   }

   public String getSubFileBase64Str() {
      return this.subFileBase64Str;
   }

   public void setSubFileBase64Str(String subFileBase64Str) {
      this.subFileBase64Str = subFileBase64Str;
   }

   public String getSubFileXmlStr() {
      return this.subFileXmlStr;
   }

   public void setSubFileXmlStr(String subFileXmlStr) {
      this.subFileXmlStr = subFileXmlStr;
   }

   public List getSignElemList() {
      return this.signElemList;
   }

   public void setSignElemList(List signElemList) {
      this.signElemList = signElemList;
   }

   public String getSignText() {
      return this.signText;
   }

   public void setSignText(String signText) {
      this.signText = signText;
   }

   public String getOldText() {
      return this.oldText;
   }

   public void setOldText(String oldText) {
      this.oldText = oldText;
   }

   public String getCertInfo() {
      return this.certInfo;
   }

   public void setCertInfo(String certInfo) {
      this.certInfo = certInfo;
   }

   public String getCertSn() {
      return this.certSn;
   }

   public void setCertSn(String certSn) {
      this.certSn = certSn;
   }

   public String getNextDocCd() {
      return this.nextDocCd;
   }

   public void setNextDocCd(String nextDocCd) {
      this.nextDocCd = nextDocCd;
   }

   public String getNextDocName() {
      return this.nextDocName;
   }

   public void setNextDocName(String nextDocName) {
      this.nextDocName = nextDocName;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getTextStr() {
      return this.textStr;
   }

   public void setTextStr(String textStr) {
      this.textStr = textStr;
   }

   public String getSubFileTextStr() {
      return this.subFileTextStr;
   }

   public void setSubFileTextStr(String subFileTextStr) {
      this.subFileTextStr = subFileTextStr;
   }
}
