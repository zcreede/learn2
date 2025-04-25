package com.emr.grid.service;

import com.emr.grid.enums.ExportType;
import com.emr.grid.enums.GenerateStyle;
import com.emr.grid.enums.ImageType;
import java.util.List;

public interface RubylongCore {
   String getVersion();

   void release();

   void fnPrintPreview(boolean showModal);

   void fnPrintEx(GenerateStyle generateStyle, boolean showPrintDialog);

   void fnPrint(boolean showPrintDialog);

   boolean fnLoadDataFromURL(String url);

   boolean fnLoadDataFromXML(String data);

   void fnLoadDataListDatas(List datas, Json json);

   boolean fnLoadFromURL(String url);

   boolean fnLoadFromFile(String fileName);

   boolean fnLoadFromStr(String template);

   boolean fnExportDirect(ExportType exportType, String fileName, boolean showOptionDlg, boolean doneOpen);

   void fnExportImg(ImageType imageType, String fileName, boolean allInOne, boolean doneOpen);

   void setPrinterName(String printName);

   String getPrinterName();

   void about();
}
