package com.emr.project.mrhp.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(
   value = "TdCmFeeVo",
   description = "费用明细"
)
public class TdCmFeeVo {
   @ApiModelProperty("病案id")
   private String recordId;
   @ApiModelProperty("患者姓名")
   private String patientName;
   @ApiModelProperty("病案号")
   private String recordNo;
   @ApiModelProperty("住院号")
   private String patientId;
   @ApiModelProperty("总费用")
   private BigDecimal zfy;
   @ApiModelProperty("自付金额")
   private BigDecimal zfje;
   @ApiModelProperty("一般医疗服务费")
   private BigDecimal ybylfwf;
   @ApiModelProperty("一般治疗操作费")
   private BigDecimal ybzlczf;
   @ApiModelProperty("护理费")
   private BigDecimal hlf;
   @ApiModelProperty("其他费用")
   private BigDecimal qtfy;
   @ApiModelProperty("病理诊断费")
   private BigDecimal blzdf;
   @ApiModelProperty("实验室诊断费")
   private BigDecimal syszdf;
   @ApiModelProperty("影像学诊断费")
   private BigDecimal yxxzdf;
   @ApiModelProperty("临床诊断项目费")
   private BigDecimal lczdxmf;
   @ApiModelProperty("非手术治疗项目费")
   private BigDecimal fsszlxmf;
   @ApiModelProperty("手术治疗费")
   private BigDecimal sszlf;
   @ApiModelProperty("康复费")
   private BigDecimal kff;
   @ApiModelProperty("中医治疗费")
   private BigDecimal zyzlf;
   @ApiModelProperty("中医诊断")
   private BigDecimal zyzd;
   @ApiModelProperty("中医其他")
   private BigDecimal zyqt;
   @ApiModelProperty("西药费")
   private BigDecimal xyf;
   @ApiModelProperty("中成药费")
   private BigDecimal zcyf;
   @ApiModelProperty("中草药费")
   private BigDecimal zcyf_zcy;
   @ApiModelProperty("血费")
   private BigDecimal xf;
   @ApiModelProperty("白蛋白类制品费")
   private BigDecimal bdblzpf;
   @ApiModelProperty("球蛋白类制品费")
   private BigDecimal qdblzpf;
   @ApiModelProperty("凝血因子类制品费")
   private BigDecimal nxyzlzpf;
   @ApiModelProperty("细胞因子类制品费")
   private BigDecimal xbyzlzpf;
   @ApiModelProperty("检查用一次性医用材料费")
   private BigDecimal jcyycxyyclf;
   @ApiModelProperty("治疗用一次性医用材料费")
   private BigDecimal zlyycxyyclf;
   @ApiModelProperty("手术用一次性医用材料费")
   private BigDecimal ssyycxyyclf;
   @ApiModelProperty("其他费")
   private BigDecimal qtf;
   @ApiModelProperty("中医辨证论治费")
   private BigDecimal zybzlzf;
   @ApiModelProperty("中医辨证论治会诊费")
   private BigDecimal zybzlzhzf;
   @ApiModelProperty("临床物理治疗费")
   private BigDecimal lcwlzlf;
   @ApiModelProperty("麻醉费")
   private BigDecimal mzf;
   @ApiModelProperty("手术费")
   private BigDecimal ssf;
   @ApiModelProperty("中医外治")
   private BigDecimal zywz;
   @ApiModelProperty("中医骨伤")
   private BigDecimal zygs;
   @ApiModelProperty("针刺与灸法")
   private BigDecimal zcyjf;
   @ApiModelProperty("中医推拿治疗")
   private BigDecimal zytzl;
   @ApiModelProperty("中医肛肠治疗")
   private BigDecimal zygczl;
   @ApiModelProperty("中医特殊治疗")
   private BigDecimal zytszl;
   @ApiModelProperty("中药特殊调配加工")
   private BigDecimal zytstpjg;
   @ApiModelProperty("辨证施膳")
   private BigDecimal bzss;
   @ApiModelProperty("抗菌药物费用")
   private BigDecimal kjywfy;
   @ApiModelProperty("医疗机构中药制剂费")
   private BigDecimal yljgzyzjf;

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public BigDecimal getZfy() {
      return this.zfy;
   }

   public void setZfy(BigDecimal zfy) {
      this.zfy = zfy;
   }

   public BigDecimal getZfje() {
      return this.zfje;
   }

   public void setZfje(BigDecimal zfje) {
      this.zfje = zfje;
   }

   public BigDecimal getYbylfwf() {
      return this.ybylfwf;
   }

   public void setYbylfwf(BigDecimal ybylfwf) {
      this.ybylfwf = ybylfwf;
   }

   public BigDecimal getYbzlczf() {
      return this.ybzlczf;
   }

   public void setYbzlczf(BigDecimal ybzlczf) {
      this.ybzlczf = ybzlczf;
   }

   public BigDecimal getHlf() {
      return this.hlf;
   }

   public void setHlf(BigDecimal hlf) {
      this.hlf = hlf;
   }

   public BigDecimal getQtfy() {
      return this.qtfy;
   }

   public void setQtfy(BigDecimal qtfy) {
      this.qtfy = qtfy;
   }

   public BigDecimal getBlzdf() {
      return this.blzdf;
   }

   public void setBlzdf(BigDecimal blzdf) {
      this.blzdf = blzdf;
   }

   public BigDecimal getSyszdf() {
      return this.syszdf;
   }

   public void setSyszdf(BigDecimal syszdf) {
      this.syszdf = syszdf;
   }

   public BigDecimal getYxxzdf() {
      return this.yxxzdf;
   }

   public void setYxxzdf(BigDecimal yxxzdf) {
      this.yxxzdf = yxxzdf;
   }

   public BigDecimal getLczdxmf() {
      return this.lczdxmf;
   }

   public void setLczdxmf(BigDecimal lczdxmf) {
      this.lczdxmf = lczdxmf;
   }

   public BigDecimal getFsszlxmf() {
      return this.fsszlxmf;
   }

   public void setFsszlxmf(BigDecimal fsszlxmf) {
      this.fsszlxmf = fsszlxmf;
   }

   public BigDecimal getSszlf() {
      return this.sszlf;
   }

   public void setSszlf(BigDecimal sszlf) {
      this.sszlf = sszlf;
   }

   public BigDecimal getKff() {
      return this.kff;
   }

   public void setKff(BigDecimal kff) {
      this.kff = kff;
   }

   public BigDecimal getZyzlf() {
      return this.zyzlf;
   }

   public void setZyzlf(BigDecimal zyzlf) {
      this.zyzlf = zyzlf;
   }

   public BigDecimal getZyzd() {
      return this.zyzd;
   }

   public void setZyzd(BigDecimal zyzd) {
      this.zyzd = zyzd;
   }

   public BigDecimal getZyqt() {
      return this.zyqt;
   }

   public void setZyqt(BigDecimal zyqt) {
      this.zyqt = zyqt;
   }

   public BigDecimal getXyf() {
      return this.xyf;
   }

   public void setXyf(BigDecimal xyf) {
      this.xyf = xyf;
   }

   public BigDecimal getZcyf() {
      return this.zcyf;
   }

   public void setZcyf(BigDecimal zcyf) {
      this.zcyf = zcyf;
   }

   public BigDecimal getZcyf_zcy() {
      return this.zcyf_zcy;
   }

   public void setZcyf_zcy(BigDecimal zcyf_zcy) {
      this.zcyf_zcy = zcyf_zcy;
   }

   public BigDecimal getXf() {
      return this.xf;
   }

   public void setXf(BigDecimal xf) {
      this.xf = xf;
   }

   public BigDecimal getBdblzpf() {
      return this.bdblzpf;
   }

   public void setBdblzpf(BigDecimal bdblzpf) {
      this.bdblzpf = bdblzpf;
   }

   public BigDecimal getQdblzpf() {
      return this.qdblzpf;
   }

   public void setQdblzpf(BigDecimal qdblzpf) {
      this.qdblzpf = qdblzpf;
   }

   public BigDecimal getNxyzlzpf() {
      return this.nxyzlzpf;
   }

   public void setNxyzlzpf(BigDecimal nxyzlzpf) {
      this.nxyzlzpf = nxyzlzpf;
   }

   public BigDecimal getXbyzlzpf() {
      return this.xbyzlzpf;
   }

   public void setXbyzlzpf(BigDecimal xbyzlzpf) {
      this.xbyzlzpf = xbyzlzpf;
   }

   public BigDecimal getJcyycxyyclf() {
      return this.jcyycxyyclf;
   }

   public void setJcyycxyyclf(BigDecimal jcyycxyyclf) {
      this.jcyycxyyclf = jcyycxyyclf;
   }

   public BigDecimal getZlyycxyyclf() {
      return this.zlyycxyyclf;
   }

   public void setZlyycxyyclf(BigDecimal zlyycxyyclf) {
      this.zlyycxyyclf = zlyycxyyclf;
   }

   public BigDecimal getSsyycxyyclf() {
      return this.ssyycxyyclf;
   }

   public void setSsyycxyyclf(BigDecimal ssyycxyyclf) {
      this.ssyycxyyclf = ssyycxyyclf;
   }

   public BigDecimal getQtf() {
      return this.qtf;
   }

   public void setQtf(BigDecimal qtf) {
      this.qtf = qtf;
   }

   public BigDecimal getZybzlzf() {
      return this.zybzlzf;
   }

   public void setZybzlzf(BigDecimal zybzlzf) {
      this.zybzlzf = zybzlzf;
   }

   public BigDecimal getZybzlzhzf() {
      return this.zybzlzhzf;
   }

   public void setZybzlzhzf(BigDecimal zybzlzhzf) {
      this.zybzlzhzf = zybzlzhzf;
   }

   public BigDecimal getLcwlzlf() {
      return this.lcwlzlf;
   }

   public void setLcwlzlf(BigDecimal lcwlzlf) {
      this.lcwlzlf = lcwlzlf;
   }

   public BigDecimal getMzf() {
      return this.mzf;
   }

   public void setMzf(BigDecimal mzf) {
      this.mzf = mzf;
   }

   public BigDecimal getSsf() {
      return this.ssf;
   }

   public void setSsf(BigDecimal ssf) {
      this.ssf = ssf;
   }

   public BigDecimal getZywz() {
      return this.zywz;
   }

   public void setZywz(BigDecimal zywz) {
      this.zywz = zywz;
   }

   public BigDecimal getZygs() {
      return this.zygs;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public void setZygs(BigDecimal zygs) {
      this.zygs = zygs;
   }

   public BigDecimal getZcyjf() {
      return this.zcyjf;
   }

   public void setZcyjf(BigDecimal zcyjf) {
      this.zcyjf = zcyjf;
   }

   public BigDecimal getZytzl() {
      return this.zytzl;
   }

   public void setZytzl(BigDecimal zytzl) {
      this.zytzl = zytzl;
   }

   public BigDecimal getZygczl() {
      return this.zygczl;
   }

   public void setZygczl(BigDecimal zygczl) {
      this.zygczl = zygczl;
   }

   public BigDecimal getZytszl() {
      return this.zytszl;
   }

   public void setZytszl(BigDecimal zytszl) {
      this.zytszl = zytszl;
   }

   public BigDecimal getZytstpjg() {
      return this.zytstpjg;
   }

   public void setZytstpjg(BigDecimal zytstpjg) {
      this.zytstpjg = zytstpjg;
   }

   public BigDecimal getBzss() {
      return this.bzss;
   }

   public void setBzss(BigDecimal bzss) {
      this.bzss = bzss;
   }

   public BigDecimal getKjywfy() {
      return this.kjywfy;
   }

   public void setKjywfy(BigDecimal kjywfy) {
      this.kjywfy = kjywfy;
   }

   public BigDecimal getYljgzyzjf() {
      return this.yljgzyzjf;
   }

   public void setYljgzyzjf(BigDecimal yljgzyzjf) {
      this.yljgzyzjf = yljgzyzjf;
   }
}
