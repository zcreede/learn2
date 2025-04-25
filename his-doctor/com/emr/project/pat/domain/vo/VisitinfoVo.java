package com.emr.project.pat.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.pat.domain.Visitinfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

public class VisitinfoVo extends Visitinfo {
   private List patientIdList;
   private int inhosDayNum;
   private String searchName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date outTranTime;
   private Date outStartTime;
   private Date outEndTime;
   private String outType;
   private String browsType = "1";
   private Date dateTime;
   private String impDocCd;
   private String impDeptCd;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String age;
   private String sex;
   private String sexCd;
   private String cardType;
   private String marSta;
   private String marStaCd;
   private String idCard;
   private String polStatName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date birDate;
   private String contName;
   private String weight;
   private String patientTel;
   private String contTel;
   private String secLevel;
   private String nowAddrHouseNo;
   private String nowAddrPost;
   private String nowAddrPro;
   private String nowAddrVil;
   private String nowAddrFlagty;
   private String nowAddrCou;
   private String nowAddrTown;
   private String nowAddr;
   private String nowAddrTel;
   private String catFlag;
   private String infectFlag;
   private String alleFlag;
   private String operFlag;
   private String consFlag;
   private String bloodFlag;
   private String interFlag;
   private String anesFlag;
   private String dieFlag;
   private String changeFlag;
   private String bloodTrans;
   private String singleFlag;
   private String medicineFlag;
   private String feverFlag;
   private String patientMainId;
   private String dictLabel;
   private String mainPatName;
   private String impType;
   private String impEndTime;
   private String colorId;
   private String planType;
   private String colorTypeName;
   private String colorTypeCd;
   private String colorValue;
   private String alleInfo;
   private List alleInfoList;
   private String diagInfos;
   private String secLevelName;
   private Integer inHosNum = 0;
   private String docPowerType;
   private String deptCode;
   private String consState;
   private Long consId;
   private Long consEmrId;
   private String workAddr;
   private Integer toInHosNum = 0;
   private Integer turnInNum = 0;
   private Integer turnOutNum = 0;
   private Integer toOutHosNum = 0;
   private Integer operPatNum = 0;
   private Integer babyNum = 0;
   private String qcMrState;
   private String isInpNoOrder;
   private String orderField;
   private String orderDesc;
   private String hospitalMark;
   private String hospitalMarkName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date finalFilingDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date closingDate;
   private String mrState;
   private String mrStateName;
   private String admissionNo;
   private Integer total;
   @Excel(
      name = "就诊科室编码"
   )
   @ApiModelProperty("就诊科室编码")
   private String visitDeptCd;
   @Excel(
      name = "就诊科室名称"
   )
   @ApiModelProperty("就诊科室名称")
   private String visitDeptName;
   @Excel(
      name = "就诊医师代码"
   )
   @ApiModelProperty("就诊医师代码")
   private String visitDocCd;
   @Excel(
      name = "就诊医师姓名"
   )
   @ApiModelProperty("就诊医师姓名")
   private String visitDocName;
   @ApiModelProperty("门诊住院号")
   private String visitNo;

   public String getVisitDeptCd() {
      return this.visitDeptCd;
   }

   public void setVisitDeptCd(String visitDeptCd) {
      this.visitDeptCd = visitDeptCd;
   }

   public String getVisitNo() {
      return this.visitNo;
   }

   public void setVisitNo(String visitNo) {
      this.visitNo = visitNo;
   }

   public String getVisitDeptName() {
      return this.visitDeptName;
   }

   public void setVisitDeptName(String visitDeptName) {
      this.visitDeptName = visitDeptName;
   }

   public String getVisitDocCd() {
      return this.visitDocCd;
   }

   public void setVisitDocCd(String visitDocCd) {
      this.visitDocCd = visitDocCd;
   }

   public String getVisitDocName() {
      return this.visitDocName;
   }

   public void setVisitDocName(String visitDocName) {
      this.visitDocName = visitDocName;
   }

   public Integer getTotal() {
      return this.total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }

   public List getPatientIdList() {
      return this.patientIdList;
   }

   public void setPatientIdList(List patientIdList) {
      this.patientIdList = patientIdList;
   }

   public String getMrStateName() {
      return this.mrStateName;
   }

   public void setMrStateName(String mrStateName) {
      this.mrStateName = mrStateName;
   }

   public Date getFinalFilingDate() {
      return this.finalFilingDate;
   }

   public void setFinalFilingDate(Date finalFilingDate) {
      this.finalFilingDate = finalFilingDate;
   }

   public Date getClosingDate() {
      return this.closingDate;
   }

   public void setClosingDate(Date closingDate) {
      this.closingDate = closingDate;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getHospitalMark() {
      return this.hospitalMark;
   }

   public void setHospitalMark(String hospitalMark) {
      this.hospitalMark = hospitalMark;
   }

   public String getHospitalMarkName() {
      return this.hospitalMarkName;
   }

   public void setHospitalMarkName(String hospitalMarkName) {
      this.hospitalMarkName = hospitalMarkName;
   }

   public String getOrderField() {
      return this.orderField;
   }

   public void setOrderField(String orderField) {
      this.orderField = orderField;
   }

   public String getOrderDesc() {
      return this.orderDesc;
   }

   public void setOrderDesc(String orderDesc) {
      this.orderDesc = orderDesc;
   }

   public String getIsInpNoOrder() {
      return this.isInpNoOrder;
   }

   public void setIsInpNoOrder(String isInpNoOrder) {
      this.isInpNoOrder = isInpNoOrder;
   }

   public String getQcMrState() {
      return this.qcMrState;
   }

   public void setQcMrState(String qcMrState) {
      this.qcMrState = qcMrState;
   }

   public String getMarStaCd() {
      return this.marStaCd;
   }

   public void setMarStaCd(String marStaCd) {
      this.marStaCd = marStaCd;
   }

   public Integer getTurnInNum() {
      return this.turnInNum;
   }

   public void setTurnInNum(Integer turnInNum) {
      this.turnInNum = turnInNum;
   }

   public Integer getTurnOutNum() {
      return this.turnOutNum;
   }

   public void setTurnOutNum(Integer turnOutNum) {
      this.turnOutNum = turnOutNum;
   }

   public Integer getOperPatNum() {
      return this.operPatNum;
   }

   public void setOperPatNum(Integer operPatNum) {
      this.operPatNum = operPatNum;
   }

   public Integer getBabyNum() {
      return this.babyNum;
   }

   public void setBabyNum(Integer babyNum) {
      this.babyNum = babyNum;
   }

   public List getAlleInfoList() {
      return this.alleInfoList;
   }

   public void setAlleInfoList(List alleInfoList) {
      this.alleInfoList = alleInfoList;
   }

   public String getWorkAddr() {
      return this.workAddr;
   }

   public void setWorkAddr(String workAddr) {
      this.workAddr = workAddr;
   }

   public Long getConsEmrId() {
      return this.consEmrId;
   }

   public void setConsEmrId(Long consEmrId) {
      this.consEmrId = consEmrId;
   }

   public Long getConsId() {
      return this.consId;
   }

   public void setConsId(Long consId) {
      this.consId = consId;
   }

   public String getConsState() {
      return this.consState;
   }

   public void setConsState(String consState) {
      this.consState = consState;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDocPowerType() {
      return this.docPowerType;
   }

   public void setDocPowerType(String docPowerType) {
      this.docPowerType = docPowerType;
   }

   public Integer getInHosNum() {
      return this.inHosNum;
   }

   public void setInHosNum(Integer inHosNum) {
      this.inHosNum = inHosNum;
   }

   public Integer getToInHosNum() {
      return this.toInHosNum;
   }

   public void setToInHosNum(Integer toInHosNum) {
      this.toInHosNum = toInHosNum;
   }

   public Integer getToOutHosNum() {
      return this.toOutHosNum;
   }

   public void setToOutHosNum(Integer toOutHosNum) {
      this.toOutHosNum = toOutHosNum;
   }

   public String getSecLevelName() {
      return this.secLevelName;
   }

   public void setSecLevelName(String secLevelName) {
      this.secLevelName = secLevelName;
   }

   public String getDiagInfos() {
      return this.diagInfos;
   }

   public void setDiagInfos(String diagInfos) {
      this.diagInfos = diagInfos;
   }

   public String getAlleInfo() {
      return this.alleInfo;
   }

   public void setAlleInfo(String alleInfo) {
      this.alleInfo = alleInfo;
   }

   public String getColorValue() {
      return this.colorValue;
   }

   public void setColorValue(String colorValue) {
      this.colorValue = colorValue;
   }

   public Long getAgeM() {
      return this.ageM;
   }

   public void setAgeM(Long ageM) {
      this.ageM = ageM;
   }

   public Long getAgeD() {
      return this.ageD;
   }

   public void setAgeD(Long ageD) {
      this.ageD = ageD;
   }

   public Long getAgeH() {
      return this.ageH;
   }

   public void setAgeH(Long ageH) {
      this.ageH = ageH;
   }

   public Long getAgeMi() {
      return this.ageMi;
   }

   public void setAgeMi(Long ageMi) {
      this.ageMi = ageMi;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getNowAddr() {
      return this.nowAddr;
   }

   public void setNowAddr(String nowAddr) {
      this.nowAddr = nowAddr;
   }

   public String getColorId() {
      return this.colorId;
   }

   public void setColorId(String colorId) {
      this.colorId = colorId;
   }

   public String getPlanType() {
      return this.planType;
   }

   public void setPlanType(String planType) {
      this.planType = planType;
   }

   public String getColorTypeName() {
      return this.colorTypeName;
   }

   public void setColorTypeName(String colorTypeName) {
      this.colorTypeName = colorTypeName;
   }

   public String getColorTypeCd() {
      return this.colorTypeCd;
   }

   public void setColorTypeCd(String colorTypeCd) {
      this.colorTypeCd = colorTypeCd;
   }

   public String getImpDeptCd() {
      return this.impDeptCd;
   }

   public void setImpDeptCd(String impDeptCd) {
      this.impDeptCd = impDeptCd;
   }

   public Date getOutTranTime() {
      return this.outTranTime;
   }

   public void setOutTranTime(Date outTranTime) {
      this.outTranTime = outTranTime;
   }

   public String getImpEndTime() {
      return this.impEndTime;
   }

   public void setImpEndTime(String impEndTime) {
      this.impEndTime = impEndTime;
   }

   public String getImpType() {
      return this.impType;
   }

   public void setImpType(String impType) {
      this.impType = impType;
   }

   public String getMainPatName() {
      return this.mainPatName;
   }

   public void setMainPatName(String mainPatName) {
      this.mainPatName = mainPatName;
   }

   public String getDictLabel() {
      return this.dictLabel;
   }

   public void setDictLabel(String dictLabel) {
      this.dictLabel = dictLabel;
   }

   public String getOutType() {
      return this.outType;
   }

   public void setOutType(String outType) {
      this.outType = outType;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getFeverFlag() {
      return this.feverFlag;
   }

   public void setFeverFlag(String feverFlag) {
      this.feverFlag = feverFlag;
   }

   public String getCardType() {
      return this.cardType;
   }

   public void setCardType(String cardType) {
      this.cardType = cardType;
   }

   public String getMarSta() {
      return this.marSta;
   }

   public void setMarSta(String marSta) {
      this.marSta = marSta;
   }

   public String getIdCard() {
      return this.idCard;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }

   public String getPolStatName() {
      return this.polStatName;
   }

   public void setPolStatName(String polStatName) {
      this.polStatName = polStatName;
   }

   public Date getBirDate() {
      return this.birDate;
   }

   public void setBirDate(Date birDate) {
      this.birDate = birDate;
   }

   public String getContName() {
      return this.contName;
   }

   public void setContName(String contName) {
      this.contName = contName;
   }

   public String getWeight() {
      return this.weight;
   }

   public void setWeight(String weight) {
      this.weight = weight;
   }

   public String getPatientTel() {
      return this.patientTel;
   }

   public void setPatientTel(String patientTel) {
      this.patientTel = patientTel;
   }

   public String getContTel() {
      return this.contTel;
   }

   public void setContTel(String contTel) {
      this.contTel = contTel;
   }

   public String getSecLevel() {
      return this.secLevel;
   }

   public void setSecLevel(String secLevel) {
      this.secLevel = secLevel;
   }

   public String getNowAddrHouseNo() {
      return this.nowAddrHouseNo;
   }

   public void setNowAddrHouseNo(String nowAddrHouseNo) {
      this.nowAddrHouseNo = nowAddrHouseNo;
   }

   public String getNowAddrPost() {
      return this.nowAddrPost;
   }

   public void setNowAddrPost(String nowAddrPost) {
      this.nowAddrPost = nowAddrPost;
   }

   public String getNowAddrPro() {
      return this.nowAddrPro;
   }

   public void setNowAddrPro(String nowAddrPro) {
      this.nowAddrPro = nowAddrPro;
   }

   public String getNowAddrVil() {
      return this.nowAddrVil;
   }

   public void setNowAddrVil(String nowAddrVil) {
      this.nowAddrVil = nowAddrVil;
   }

   public String getNowAddrFlagty() {
      return this.nowAddrFlagty;
   }

   public void setNowAddrFlagty(String nowAddrFlagty) {
      this.nowAddrFlagty = nowAddrFlagty;
   }

   public String getNowAddrCou() {
      return this.nowAddrCou;
   }

   public void setNowAddrCou(String nowAddrCou) {
      this.nowAddrCou = nowAddrCou;
   }

   public String getNowAddrTown() {
      return this.nowAddrTown;
   }

   public void setNowAddrTown(String nowAddrTown) {
      this.nowAddrTown = nowAddrTown;
   }

   public String getNowAddrTel() {
      return this.nowAddrTel;
   }

   public void setNowAddrTel(String nowAddrTel) {
      this.nowAddrTel = nowAddrTel;
   }

   public String getCatFlag() {
      return this.catFlag;
   }

   public void setCatFlag(String catFlag) {
      this.catFlag = catFlag;
   }

   public String getInfectFlag() {
      return this.infectFlag;
   }

   public void setInfectFlag(String infectFlag) {
      this.infectFlag = infectFlag;
   }

   public String getAlleFlag() {
      return this.alleFlag;
   }

   public void setAlleFlag(String alleFlag) {
      this.alleFlag = alleFlag;
   }

   public String getOperFlag() {
      return this.operFlag;
   }

   public void setOperFlag(String operFlag) {
      this.operFlag = operFlag;
   }

   public String getConsFlag() {
      return this.consFlag;
   }

   public void setConsFlag(String consFlag) {
      this.consFlag = consFlag;
   }

   public String getBloodFlag() {
      return this.bloodFlag;
   }

   public void setBloodFlag(String bloodFlag) {
      this.bloodFlag = bloodFlag;
   }

   public String getInterFlag() {
      return this.interFlag;
   }

   public void setInterFlag(String interFlag) {
      this.interFlag = interFlag;
   }

   public String getAnesFlag() {
      return this.anesFlag;
   }

   public void setAnesFlag(String anesFlag) {
      this.anesFlag = anesFlag;
   }

   public String getDieFlag() {
      return this.dieFlag;
   }

   public void setDieFlag(String dieFlag) {
      this.dieFlag = dieFlag;
   }

   public String getChangeFlag() {
      return this.changeFlag;
   }

   public void setChangeFlag(String changeFlag) {
      this.changeFlag = changeFlag;
   }

   public String getBloodTrans() {
      return this.bloodTrans;
   }

   public void setBloodTrans(String bloodTrans) {
      this.bloodTrans = bloodTrans;
   }

   public String getSingleFlag() {
      return this.singleFlag;
   }

   public void setSingleFlag(String singleFlag) {
      this.singleFlag = singleFlag;
   }

   public String getMedicineFlag() {
      return this.medicineFlag;
   }

   public void setMedicineFlag(String medicineFlag) {
      this.medicineFlag = medicineFlag;
   }

   public Long getAgeY() {
      return this.ageY;
   }

   public void setAgeY(Long ageY) {
      this.ageY = ageY;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getImpDocCd() {
      return this.impDocCd;
   }

   public void setImpDocCd(String impDocCd) {
      this.impDocCd = impDocCd;
   }

   public Date getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(Date dateTime) {
      this.dateTime = dateTime;
   }

   public int getInhosDayNum() {
      return this.inhosDayNum;
   }

   public void setInhosDayNum(int inhosDayNum) {
      this.inhosDayNum = inhosDayNum;
   }

   public String getSearchName() {
      return this.searchName;
   }

   public void setSearchName(String searchName) {
      this.searchName = searchName;
   }

   public Date getOutStartTime() {
      return this.outStartTime;
   }

   public void setOutStartTime(Date outStartTime) {
      this.outStartTime = outStartTime;
   }

   public Date getOutEndTime() {
      return this.outEndTime;
   }

   public void setOutEndTime(Date outEndTime) {
      this.outEndTime = outEndTime;
   }

   public String getBrowsType() {
      return this.browsType;
   }

   public void setBrowsType(String browsType) {
      this.browsType = browsType;
   }

   public String getSexCd() {
      return this.sexCd;
   }

   public void setSexCd(String sexCd) {
      this.sexCd = sexCd;
   }
}
