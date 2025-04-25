package com.emr.common.utils.xml;

import com.emr.common.utils.StringUtils;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.webEditor.zb.util.ZBXmlElementParseUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jXmlParseUtil {
   public static String getXmlStr() {
      return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\n<Root>\n  <Header>\n    <Paragraph Id=\"22_633\">\n      <ParaRun>\n        <Text>姓名：</Text>\n      </ParaRun>\n      <NewCtrl Id=\"22_2481\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n        <ParaRun>\n          <Text>张培春</Text>\n        </ParaRun>\n      </NewCtrl>\n      <ParaRun>\n        <Text>科室：</Text>\n      </ParaRun>\n      <NewCtrl Id=\"22_2494\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n        <ParaRun>\n          <Text>肿瘤内科</Text>\n        </ParaRun>\n      </NewCtrl>\n      <ParaRun>\n        <Text>床号：</Text>\n      </ParaRun>\n      <NewCtrl Id=\"22_2503\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n        <ParaRun>\n          <Text>555</Text>\n        </ParaRun>\n      </NewCtrl>\n      <ParaRun>\n        <Text>住院号：</Text>\n      </ParaRun>\n      <NewCtrl Id=\"22_2509\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n        <ParaRun>\n          <Text>00564527</Text>\n        </ParaRun>\n      </NewCtrl>\n    </Paragraph>\n  </Header>\n  <DocObjContent>\n    <Paragraph Id=\"22_816\">\n      <ParaRun>\n        <Text>一、病历扩展信息</Text>\n      </ParaRun>\n    </Paragraph>\n    <Paragraph Id=\"22_642\">\n      <ParaRun>\n        <Text>危重病历：</Text>\n      </ParaRun>\n      <NewCtrl Id=\"22_669\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"1.是 2.否\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"000000\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n        <ParaRun>\n          <Text>1.是 2.否</Text>\n        </ParaRun>\n      </NewCtrl>\n      <ParaRun>\n        <Text>1.是 2.否 疑难病历：</Text>\n      </ParaRun>\n      <NewCtrl Id=\"22_694\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"1.是 2.否\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n        <ParaRun>\n          <Text>1.是 2.否</Text>\n        </ParaRun>\n      </NewCtrl>\n      <ParaRun>\n        <Text>1.是 2.否</Text>\n      </ParaRun>\n    </Paragraph>\n    <Paragraph Id=\"22_1130\">\n      <ParaRun>\n        <Text>急症病例：</Text>\n      </ParaRun>\n      <NewCtrl Id=\"22_729\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"1.是 2.否\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"000000\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n        <ParaRun>\n          <Text>1.是 2.否</Text>\n        </ParaRun>\n      </NewCtrl>\n      <ParaRun>\n        <Text>1.是 2.否 科研教学病历：</Text>\n      </ParaRun>\n      <NewCtrl Id=\"22_760\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"1.是 2.否\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"000000\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n        <ParaRun>\n          <Text>1.是 2.否</Text>\n        </ParaRun>\n      </NewCtrl>\n      <ParaRun>\n        <Text>1.是 2.否</Text>\n      </ParaRun>\n    </Paragraph>\n    <NewCtrl Id=\"22_846\" Type=\"100\" TypeName=\"REGION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n      <Paragraph Id=\"22_859\">\n        <ParaRun>\n          <Text>二、诊断附加信息</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_863\">\n        <Region Id=\"22_867\" Type=\"0\" TypeName=\"SECTION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n          <ParaRun>\n            <Text>入院时情况：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_874\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"1.危 2.急 3.一般\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"000000\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>1.危 2.急 3.一般</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>1.危 2.急 3.一般 确认日期：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_916\" Type=\"7\" TypeName=\"DATETIMEBOX\" HelpTip=\"\" PlaceHolder=\"输入日期\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"000000\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" StartDateTime=\"\" EndDateTime=\"\" Style=\"2\">\n            <ParaRun>\n              <Text>2021-09-02</Text>\n            </ParaRun>\n          </NewCtrl>\n        </Region>\n      </Paragraph>\n      <Paragraph Id=\"22_923\">\n        <ParaRun>\n          <Text>门诊与出院诊断符合情况：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_927\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"是否符合\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"000000\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>是否符合</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>0.未做 1.符合 2.不符合 3.不确定</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_969\">\n        <ParaRun>\n          <Text>入院与出院诊断符合情况：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_973\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"是否符合\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>是否符合</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>0.未做 1.符合 2.不符合 3.不确定</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1005\">\n        <ParaRun>\n          <Text>术前与术后诊断符合情况：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1025\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"是否符合\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>是否符合</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>0.未做 1.符合 2.不符合 3.不确定</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1063\">\n        <ParaRun>\n          <Text>临床与病理诊断符合情况：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2312\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>0.未做 1.符合 2.不符合 3.不确定</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1092\">\n        <ParaRun>\n          <Text>放射与病理符合情况：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2336\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>0.未做 1.符合 2.不符合 3.不确定</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1096\">\n        <ParaRun>\n          <Text>恶性肿瘤与术后病理：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2353\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>0.未做 1.符合 2.不符合 3.不确定</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1100\">\n        <ParaRun>\n          <Text>主诊断治疗效果：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2370\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>1.治愈 2.好转 2.未愈 4.死亡 5.其他</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1104\">\n        <ParaRun>\n          <Text>主要诊断依据：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2383\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n      </Paragraph>\n      <Paragraph Id=\"22_1108\">\n        <ParaRun>\n          <Text>是否15天内同一病再入院：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2389\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>1.是 2.否</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1122\">\n        <ParaRun>\n          <Text>是否30天内同一病再入院：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2402\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>1.是 2.否</Text>\n        </ParaRun>\n      </Paragraph>\n    </NewCtrl>\n    <NewCtrl Id=\"22_1135\" Type=\"100\" TypeName=\"REGION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n      <Paragraph Id=\"22_1148\">\n        <ParaRun>\n          <Text>三、重症监护信息</Text>\n        </ParaRun>\n      </Paragraph>\n      <CTable Id=\"22_1160\">\n        <CTableRow Id=\"22_1161\">\n          <CTableCell Id=\"22_1162\">\n            <Paragraph Id=\"22_1164\">\n              <ParaRun>\n                <Text>序号</Text>\n              </ParaRun>\n            </Paragraph>\n          </CTableCell>\n          <CTableCell Id=\"22_1168\">\n            <Paragraph Id=\"22_1170\">\n              <ParaRun>\n                <Text>重症监护室名称</Text>\n              </ParaRun>\n            </Paragraph>\n          </CTableCell>\n          <CTableCell Id=\"22_1174\">\n            <Paragraph Id=\"22_1176\">\n              <ParaRun>\n                <Text>进重症监护室时间</Text>\n              </ParaRun>\n            </Paragraph>\n          </CTableCell>\n          <CTableCell Id=\"22_1180\">\n            <Paragraph Id=\"22_1182\">\n              <ParaRun>\n                <Text>出重症监护室时间</Text>\n              </ParaRun>\n            </Paragraph>\n          </CTableCell>\n          <CTableCell Id=\"22_1186\">\n            <Paragraph Id=\"22_1188\">\n              <ParaRun>\n                <Text>时长(小时)</Text>\n              </ParaRun>\n            </Paragraph>\n          </CTableCell>\n        </CTableRow>\n        <CTableRow Id=\"22_1192\">\n          <CTableCell Id=\"22_1193\">\n            <Paragraph Id=\"22_1195\">\n              <ParaRun>\n                <Text>1,</Text>\n              </ParaRun>\n            </Paragraph>\n          </CTableCell>\n          <CTableCell Id=\"22_1199\">\n            <Paragraph Id=\"22_1201\">\n              <NewCtrl Id=\"22_1473\" Type=\"3\" TypeName=\"DROPDOWN\" HelpTip=\"\" PlaceHolder=\"选择一个项目\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" Code=\"null\" Value=\"false\" CodeArray=\"01,02,03,04,05,06,07,99\" ValueArray=\"CCU - 心脏监护室,RICU - 呼吸监护室,SICU - 外科监护室,NICU - 新生儿监护室,PICU - 儿科监护室,EICU - 急诊重症监护室,MICU - 内科重症监护室,其他\" ShowValue=\"FALSE\">\n                <ParaRun>\n                  <Text>NICU - 新生儿监护室</Text>\n                </ParaRun>\n              </NewCtrl>\n            </Paragraph>\n          </CTableCell>\n          <CTableCell Id=\"22_1205\">\n            <Paragraph Id=\"22_1207\">\n              <NewCtrl Id=\"22_1480\" Type=\"7\" TypeName=\"DATETIMEBOX\" HelpTip=\"\" PlaceHolder=\"输入日期\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" StartDateTime=\"\" EndDateTime=\"\" Style=\"0\">\n                <ParaRun>\n                  <Text>2021-09-02 15:02</Text>\n                </ParaRun>\n              </NewCtrl>\n            </Paragraph>\n          </CTableCell>\n          <CTableCell Id=\"22_1211\">\n            <Paragraph Id=\"22_1213\">\n              <NewCtrl Id=\"22_1487\" Type=\"7\" TypeName=\"DATETIMEBOX\" HelpTip=\"\" PlaceHolder=\"输入日期\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" StartDateTime=\"\" EndDateTime=\"\" Style=\"0\">\n                <ParaRun>\n                  <Text>2021-09-02 15:02</Text>\n                </ParaRun>\n              </NewCtrl>\n            </Paragraph>\n          </CTableCell>\n          <CTableCell Id=\"22_1217\">\n            <Paragraph Id=\"22_1219\">\n              <ParaRun>\n                <Text>2</Text>\n              </ParaRun>\n            </Paragraph>\n          </CTableCell>\n        </CTableRow>\n        <CTableRow Id=\"22_1223\">\n          <CTableCell Id=\"22_1224\">\n            <Paragraph Id=\"22_1226\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1230\">\n            <Paragraph Id=\"22_1232\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1236\">\n            <Paragraph Id=\"22_1238\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1242\">\n            <Paragraph Id=\"22_1244\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1248\">\n            <Paragraph Id=\"22_1250\"/>\n          </CTableCell>\n        </CTableRow>\n        <CTableRow Id=\"22_1254\">\n          <CTableCell Id=\"22_1255\">\n            <Paragraph Id=\"22_1257\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1261\">\n            <Paragraph Id=\"22_1263\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1267\">\n            <Paragraph Id=\"22_1269\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1273\">\n            <Paragraph Id=\"22_1275\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1279\">\n            <Paragraph Id=\"22_1281\"/>\n          </CTableCell>\n        </CTableRow>\n        <CTableRow Id=\"22_1285\">\n          <CTableCell Id=\"22_1286\">\n            <Paragraph Id=\"22_1288\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1292\">\n            <Paragraph Id=\"22_1294\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1298\">\n            <Paragraph Id=\"22_1300\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1304\">\n            <Paragraph Id=\"22_1306\"/>\n          </CTableCell>\n          <CTableCell Id=\"22_1310\">\n            <Paragraph Id=\"22_1312\"/>\n          </CTableCell>\n        </CTableRow>\n      </CTable>\n      <Paragraph Id=\"22_1156\"/>\n      <Paragraph Id=\"22_1503\">\n        <ParaRun>\n          <Text>呼吸机使用时间：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1507\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>天</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1513\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>小时</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1519\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>分钟</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1525\">\n        <ParaRun>\n          <Text>入院生活评分：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1529\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>出院生活评分：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1535\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n      </Paragraph>\n    </NewCtrl>\n    <NewCtrl Id=\"22_1541\" Type=\"100\" TypeName=\"REGION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n      <Paragraph Id=\"22_1554\">\n        <ParaRun>\n          <Text>四、抢救死亡和临床路径信息</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1631\">\n        <Region Id=\"22_1635\" Type=\"0\" TypeName=\"SECTION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n          <ParaRun>\n            <Text>抢救次数：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_1660\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"次数\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>次数</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>次 成功次数：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_1663\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"次数\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>次数</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>次 临床路径病例：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_1666\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"是否\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>是否</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>1.是2.否</Text>\n          </ParaRun>\n        </Region>\n      </Paragraph>\n    </NewCtrl>\n    <NewCtrl Id=\"22_1698\" Type=\"100\" TypeName=\"REGION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n      <Paragraph Id=\"22_1711\">\n        <ParaRun>\n          <Text>五、肿瘤相关信息</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1788\">\n        <Region Id=\"22_1792\" Type=\"0\" TypeName=\"SECTION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n          <ParaRun>\n            <Text>肿瘤分期： T</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_1817\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"分期\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n            <ParaRun>\n              <Text>分期</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>N</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_1820\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"分期\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n            <ParaRun>\n              <Text>分期</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>M</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_1823\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"分期\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n            <ParaRun>\n              <Text>分期</Text>\n            </ParaRun>\n          </NewCtrl>\n        </Region>\n        <ParaRun>\n          <Text></Text>\n        </ParaRun>\n        <Region Id=\"22_1868\" Type=\"0\" TypeName=\"SECTION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n          <ParaRun>\n            <Text>肿瘤分期：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_1880\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"分期\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n            <ParaRun>\n              <Text>分期</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>(0.0期 1.I期 2.期 3.期 4.期 5.不详)</Text>\n          </ParaRun>\n        </Region>\n      </Paragraph>\n    </NewCtrl>\n    <NewCtrl Id=\"22_1895\" Type=\"100\" TypeName=\"REGION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n      <Paragraph Id=\"22_1908\">\n        <ParaRun>\n          <Text>六、手术附加信息</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_1912\">\n        <ParaRun>\n          <Text>术前住院天数：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1923\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"填写天数\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>填写天数</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>HBsAg:</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1946\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"填写HBsAg\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n          <ParaRun>\n            <Text>填写HBsAg</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>HCV-Ab:</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1952\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"填写HCV-Ab\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n          <ParaRun>\n            <Text>填写HCV-Ab</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>0.未做 1.阴性 2.阳性 输血反应：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_1967\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"填写是否有输血反应\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>填写是否有输血反应</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>1.无 2.有</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_2037\">\n        <ParaRun>\n          <Text>输血品种：1.红细胞</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2041\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>单位 2.血小板</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2050\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>袋 3.血浆</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2056\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>ml 5.自体回收</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2062\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>ml 6.其他</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2068\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n      </Paragraph>\n      <Paragraph Id=\"22_2074\">\n        <ParaRun>\n          <Text>是否发生压疮：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2078\" Type=\"1\" TypeName=\"TEXTBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" TextBoxMaxLen=\"0\" TextBoxMinLen=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>1.是2.否 压疮等级：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2084\" Type=\"3\" TypeName=\"DROPDOWN\" HelpTip=\"\" PlaceHolder=\"选择一个项目\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" Code=\"null\" Value=\"false\" CodeArray=\"1,2,3,4\" ValueArray=\"一级,二级,三级,四级\" ShowValue=\"FALSE\">\n          <ParaRun>\n            <Text>选择一个项目</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>压疮原因：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2096\" Type=\"4\" TypeName=\"MULTIDROPDOWN\" HelpTip=\"\" PlaceHolder=\"选择一个项目\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" Code=\"自养老院入住,自其他医院转入\" Value=\"2,3\" CodeArray=\"1,2,3,4,5\" ValueArray=\"自家庭入住,自养老院入住,自其他医院转入,其他来源,住院期间压疮\" ShowValue=\"FALSE\">\n          <ParaRun>\n            <Text>自养老院入住，自其他医院转入</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>压疮部位：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2102\" Type=\"4\" TypeName=\"MULTIDROPDOWN\" HelpTip=\"\" PlaceHolder=\"选择一个项目\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" Code=\"部位二,部位三\" Value=\"2,3\" CodeArray=\"1,2,3,4\" ValueArray=\"部位一,部位二,部位三,部位四\" ShowValue=\"FALSE\">\n          <ParaRun>\n            <Text>部位二，部位三</Text>\n          </ParaRun>\n        </NewCtrl>\n      </Paragraph>\n      <Paragraph Id=\"22_2110\">\n        <ParaRun>\n          <Text>是否跌倒/坠床：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2114\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>1.是 2.否 跌倒等级：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2120\" Type=\"3\" TypeName=\"DROPDOWN\" HelpTip=\"\" PlaceHolder=\"选择一个项目\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" Code=\"null\" Value=\"false\" CodeArray=\"1,2,3\" ValueArray=\"一级,二级,三级\" ShowValue=\"FALSE\">\n          <ParaRun>\n            <Text>选择一个项目</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>跌倒原因：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2126\" Type=\"3\" TypeName=\"DROPDOWN\" HelpTip=\"\" PlaceHolder=\"选择一个项目\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" Code=\"null\" Value=\"false\" CodeArray=\"1,2,3,4\" ValueArray=\"患者因素,药物和(或)治疗因素,环境因素,其他\" ShowValue=\"FALSE\">\n          <ParaRun>\n            <Text>选择一个项目</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>术后是否有并发症：</Text>\n        </ParaRun>\n        <NewCtrl Id=\"22_2132\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n          <ParaRun>\n            <Text>你的文本在此</Text>\n          </ParaRun>\n        </NewCtrl>\n        <ParaRun>\n          <Text>1.无 2.有</Text>\n        </ParaRun>\n      </Paragraph>\n    </NewCtrl>\n    <NewCtrl Id=\"22_2138\" Type=\"100\" TypeName=\"REGION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n      <Paragraph Id=\"22_2151\">\n        <ParaRun>\n          <Text>七、其他</Text>\n        </ParaRun>\n      </Paragraph>\n      <Paragraph Id=\"22_2155\">\n        <Region Id=\"22_2159\" Type=\"0\" TypeName=\"SECTION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n          <ParaRun>\n            <Text>输液情况：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2166\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>1.无 2.有 输液反应：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2172\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>1.无 2.有 是否随诊：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2180\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>1.是 2.否 随诊期限：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2186\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>年</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2227\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>月</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2231\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>天</Text>\n          </ParaRun>\n        </Region>\n      </Paragraph>\n      <Paragraph Id=\"22_2239\">\n        <Region Id=\"22_2243\" Type=\"0\" TypeName=\"SECTION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n          <ParaRun>\n            <Text>使用抗菌药物：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2251\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>1.是 2.否 用药目的：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2257\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>1.预防 2.治疗 使用方案：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2263\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>1.单独 2.联合 使用天数：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2269\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n        </Region>\n      </Paragraph>\n      <Paragraph Id=\"22_2275\">\n        <Region Id=\"22_2279\" Type=\"0\" TypeName=\"SECTION\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" Visible=\"TRUE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\">\n          <ParaRun>\n            <Text>特级护理天数：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2286\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>一级护理天数：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2292\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>二级护理天数：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2298\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n          <ParaRun>\n            <Text>三级护理天数：</Text>\n          </ParaRun>\n          <NewCtrl Id=\"22_2306\" Type=\"2\" TypeName=\"NUMBERBOX\" HelpTip=\"\" PlaceHolder=\"你的文本在此\" IsCtrlHidden=\"FALSE\" DelFlag=\"FALSE\" EditProtect=\"FALSE\" ReverseEdit=\"FALSE\" BackgroundColor=\"dcdcdc\" Printable=\"TRUE\" MustFillContent=\"FALSE\" ViewSecret=\"FALSE\" IsNotRecordInXML=\"FALSE\" MaxValue=\"0\" MinValue=\"0\" Unit=\"\" Precision=\"0\">\n            <ParaRun>\n              <Text>你的文本在此</Text>\n            </ParaRun>\n          </NewCtrl>\n        </Region>\n      </Paragraph>\n    </NewCtrl>\n    <Paragraph Id=\"22_821\"/>\n  </DocObjContent>\n  <Footer/>\n</Root>";
   }

   public static String getXmlStr2() {
      return "";
   }

   public static List getElementListFromXmlStr(String xmlStr) {
      List<Element> elementList = new ArrayList(1);
      Document doc = null;

      try {
         doc = DocumentHelper.parseText(xmlStr);
         Element rootElt = doc.getRootElement();
         Element headerElt = rootElt.element("Header");
         getChildrenElt(headerElt, elementList);
         Element contentElt = rootElt.element("DocObjContent");
         getChildrenElt(contentElt, elementList);
         Element footerElt = rootElt.element("Footer");
         getChildrenElt(footerElt, elementList);
      } catch (DocumentException e) {
         e.printStackTrace();
      }

      return elementList;
   }

   public static void getChildrenElt(Element elt, List list) {
      Element eltTemp;
      for(Iterator headerIter = elt.elementIterator(); headerIter.hasNext(); getChildrenElt(eltTemp, list)) {
         eltTemp = (Element)headerIter.next();
         if (ZBXmlElementParseUtil.isSaveContType(eltTemp.getName())) {
            list.add(eltTemp);
         }
      }

   }

   public static void main(String[] args) {
      Date startDate = new Date();
      TempIndexSaveElemVo tempIndexSaveElemVo = ZBXmlElementParseUtil.getSaveElemFromXml(getXmlStr());
      System.out.println("解析耗费时长：" + ((new Date()).getTime() - startDate.getTime()));
      System.out.println("111111");
   }

   public static void getBBStaElem(List list) {
      new ArrayList();

      for(Map map : list) {
         Document doc = null;
         String elemId = map.get("ELEM_ID").toString();
         String elemName = map.get("ELEM_NAME").toString();
         Clob clob = (Clob)map.get("ELEM_QUA");

         try {
            String xmlStr = ClobToString(clob);
            doc = DocumentHelper.parseText(xmlStr);
            Element rootElt = doc.getRootElement();
            getConType3Elem(rootElt, map);
         } catch (DocumentException e) {
            e.printStackTrace();
         } catch (SQLException throwables) {
            throwables.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

   }

   private static void getConType3Elem(Element rootElt, Map map) {
      boolean flag1 = false;
      boolean flag2 = false;
      String remark = "";
      String conType = "17";
      Iterator headerIter = rootElt.elementIterator();

      while(headerIter.hasNext()) {
         Element eltTemp = (Element)headerIter.next();
         flag1 = getChildrenElt1(eltTemp, conType);
         if (flag1) {
            break;
         }
      }

      if (flag1) {
         printResStr(headerIter, map, "mac_rep", "宏替换字段");
      }

   }

   private static void printResStr(Iterator headerIter, Map map, String elemText, String elemName) {
      String remark = "";

      while(headerIter.hasNext()) {
         Element eltTemp = (Element)headerIter.next();
         remark = getChildrenElt11(eltTemp, elemText);
         if (StringUtils.isNotBlank(remark)) {
            break;
         }
      }

      if (StringUtils.isNotBlank(remark)) {
         System.out.println("元素id： " + map.get("ELEM_ID").toString() + " 元素名称： " + map.get("ELEM_NAME").toString() + " " + elemName + "： " + remark);
      }

   }

   private static String getResStr(Iterator headerIter, Map map, String elemText) {
      String remark = "";

      while(headerIter.hasNext()) {
         Element eltTemp = (Element)headerIter.next();
         remark = getChildrenElt11(eltTemp, elemText);
         if (StringUtils.isNotBlank(remark)) {
            break;
         }
      }

      return remark;
   }

   private static void getConType1Elem(Element rootElt, Map map) {
      boolean flag1 = false;
      boolean flag2 = false;
      String remark = "";
      String conType = "1";
      Iterator headerIter = rootElt.elementIterator();

      while(headerIter.hasNext()) {
         Element eltTemp = (Element)headerIter.next();
         flag1 = getChildrenElt1(eltTemp, conType);
         if (flag1) {
            break;
         }
      }

      if (flag1) {
         while(headerIter.hasNext()) {
            Element eltTemp = (Element)headerIter.next();
            remark = getChildrenElt11(eltTemp, "remark");
            if (StringUtils.isNotBlank(remark)) {
               break;
            }
         }

         System.out.println("元素id： " + map.get("ELEM_ID").toString() + " 元素名称： " + map.get("ELEM_NAME").toString() + " 值域： " + remark);
      }

   }

   public static String ClobToString(Clob clob) throws SQLException, IOException {
      String reString = "";
      Reader is = clob.getCharacterStream();
      BufferedReader br = new BufferedReader(is);
      String s = br.readLine();

      StringBuffer sb;
      for(sb = new StringBuffer(); s != null; s = br.readLine()) {
         sb.append(s);
      }

      reString = sb.toString();
      if (br != null) {
         br.close();
      }

      if (is != null) {
         is.close();
      }

      return reString;
   }

   private static Boolean getChildrenElt1(Element elt, String conType) {
      Boolean flag1 = false;

      Element eltTemp;
      for(Iterator headerIter = elt.elementIterator(); headerIter.hasNext(); flag1 = getChildrenElt1(eltTemp, conType)) {
         eltTemp = (Element)headerIter.next();
         if (eltTemp.getName().equals("qua_code") && eltTemp.getText().equals("con_type")) {
            flag1 = getChildrenElt111(eltTemp.getParent(), conType);
            if (flag1) {
               return flag1;
            }
         }
      }

      return flag1;
   }

   private static String getChildrenElt11(Element elt, String elemText) {
      String remark = "";

      Element eltTemp;
      for(Iterator headerIter = elt.elementIterator(); headerIter.hasNext(); remark = getChildrenElt11(eltTemp, elemText)) {
         eltTemp = (Element)headerIter.next();
         if (eltTemp.getName().equals("qua_code") && eltTemp.getText().equals(elemText)) {
            remark = getChildrenEltText(eltTemp.getParent());
            return remark;
         }
      }

      return remark;
   }

   private static Boolean getChildrenElt111(Element elt, String conType) {
      Iterator headerIter = elt.elementIterator();

      while(headerIter.hasNext()) {
         Element eltTemp = (Element)headerIter.next();
         if (eltTemp.getName().equals("qua_value") && eltTemp.getText().equals(conType)) {
            return true;
         }
      }

      return false;
   }

   private static String getChildrenEltText(Element elt) {
      String remark = null;
      Iterator headerIter = elt.elementIterator();

      while(headerIter.hasNext()) {
         Element eltTemp = (Element)headerIter.next();
         if (eltTemp.getName().equals("qua_value") && eltTemp.getText().contains("1")) {
            remark = eltTemp.getText();
            return remark;
         }
      }

      return remark;
   }

   private static String printPropInfo(Iterator headerIter, Map map) {
      String remark = "";

      Element eltTemp;
      for(int i = 0; headerIter.hasNext(); remark = remark + "," + getPropInfo(eltTemp)) {
         eltTemp = (Element)headerIter.next();
      }

      System.out.println("元素id," + map.get("ELEM_ID").toString() + ",元素名称," + map.get("ELEM_NAME").toString() + "," + remark);
      return remark;
   }

   private static String getPropInfo(Element elt) {
      String remark = "";
      Iterator headerIter = elt.elementIterator();

      while(headerIter.hasNext()) {
         Element eltTemp = (Element)headerIter.next();
         if (eltTemp.getName().equals("qua_name")) {
            remark = remark + eltTemp.getText();
         }

         if (eltTemp.getName().equals("qua_value")) {
            remark = remark + "," + eltTemp.getText();
         }
      }

      return remark;
   }
}
