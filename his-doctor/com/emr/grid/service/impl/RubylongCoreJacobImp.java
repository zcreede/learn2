package com.emr.grid.service.impl;

import com.emr.grid.enums.ExportType;
import com.emr.grid.enums.GenerateStyle;
import com.emr.grid.enums.GridVersion;
import com.emr.grid.enums.ImageType;
import com.emr.grid.service.Json;
import com.emr.grid.service.RubylongCore;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RubylongCoreJacobImp implements RubylongCore {
   private static String VERSION = "6.5";
   private static String PROGRAMID = "gregn.GridppReport.6";
   private ActiveXComponent com;
   private Dispatch disp;
   private static RubylongCore rlc;

   public static String getVERSION() {
      return VERSION;
   }

   public static String getPROGRAMID() {
      return PROGRAMID;
   }

   public static void setPROGRAMID(String pROGRAMID) {
      PROGRAMID = pROGRAMID;
   }

   protected RubylongCoreJacobImp(GridVersion version) {
      ComThread.InitSTA();
      this.com = new ActiveXComponent("gregn.GridppReport.6");
      this.disp = this.com.getObject();
   }

   public String getVersion() {
      return VERSION;
   }

   public void setPrinterName(String printerName) {
      Dispatch d = Dispatch.get(this.disp, "Printer").getDispatch();
      Dispatch.put(d, "PrinterName", printerName);
   }

   public String getPrinterName() {
      Dispatch d = Dispatch.get(this.disp, "Printer").getDispatch();
      return Dispatch.get(d, "PrinterName").getString();
   }

   public void release() {
      ComThread.Release();
   }

   public void fnPrintPreview(boolean showModal) {
      Dispatch.call(this.disp, "PrintPreview", new Object[]{new Variant(showModal)});
   }

   public void fnPrintEx(GenerateStyle generateStyle, boolean showPrintDialog) {
      Dispatch.call(this.disp, "PrintEx", new Object[]{new Variant(generateStyle.getValue()), new Variant(showPrintDialog)});
   }

   public void fnPrint(boolean showPrintDialog) {
      Dispatch.call(this.disp, "Print", new Object[]{new Variant(showPrintDialog)});
   }

   public boolean fnLoadDataFromURL(String url) {
      Variant v = Dispatch.call(this.disp, "LoadDataFromURL", new Object[]{new Variant(url)});
      return v.getBoolean();
   }

   public boolean fnLoadDataFromXML(String data) {
      Variant v = Dispatch.call(this.disp, "LoadDataFromXML", new Object[]{new Variant(data)});
      return v.getBoolean();
   }

   public void fnLoadDataListDatas(List datas, Json json) {
      Map<String, Object> map = new HashMap();
      map.put("Detail", datas);
      this.fnLoadDataFromXML(json.toJSONString(map));
   }

   public void fnExportImg(ImageType imageType, String fileName, boolean allInOne, boolean doneOpen) {
      Dispatch ExportOption = Dispatch.call(this.disp, "PrepareExport", new Object[]{new Variant(ExportType.gretIMG.getValue())}).getDispatch();
      Dispatch E2IMGOption = Dispatch.get(ExportOption, "AsE2IMGOption").getDispatch();
      Dispatch.put(E2IMGOption, "ImageType", new Variant(imageType.getValue()));
      Dispatch.put(E2IMGOption, "AllInOne", new Variant(allInOne));
      Dispatch.put(E2IMGOption, "FileName", new Variant(fileName));
      Dispatch.call(this.disp, "Export", new Object[]{new Variant(doneOpen)});
      Dispatch.call(this.disp, "UnprepareExport");
   }

   public boolean fnExportDirect(ExportType exportType, String fileName, boolean showOptionDlg, boolean doneOpen) {
      Variant v = Dispatch.call(this.disp, "ExportDirect", new Object[]{new Variant(exportType.getValue()), new Variant(fileName), new Variant(showOptionDlg), new Variant(doneOpen)});
      return v.getBoolean();
   }

   public boolean fnLoadFromURL(String url) {
      Variant v = Dispatch.call(this.disp, "LoadFromURL", new Object[]{new Variant(url)});
      return v.getBoolean();
   }

   public boolean fnLoadFromFile(String fileName) {
      Variant v = Dispatch.call(this.disp, "LoadFromFile", new Object[]{new Variant(fileName)});
      return v.getBoolean();
   }

   public boolean fnLoadFromStr(String template) {
      Variant v = Dispatch.call(this.disp, "LoadFromStr", new Object[]{new Variant(template)});
      return v.getBoolean();
   }

   public void about() {
      Dispatch.call(this.disp, "About");
   }

   public static RubylongCore getRubylongCore(GridVersion gridVersion) {
      if (rlc == null) {
         rlc = new RubylongCoreJacobImp(gridVersion);
      }

      return rlc;
   }
}
