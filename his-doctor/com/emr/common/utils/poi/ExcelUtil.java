package com.emr.common.utils.poi;

import com.emr.common.core.text.Convert;
import com.emr.common.exception.CustomException;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.DictUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.file.FileTypeUtils;
import com.emr.common.utils.file.ImageUtils;
import com.emr.common.utils.reflect.ReflectUtils;
import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.aspectj.lang.annotation.Excels;
import com.emr.framework.config.EMRConfig;
import com.emr.framework.web.domain.AjaxResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {
   private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
   public static final int sheetSize = 65536;
   private String sheetName;
   private Excel.Type type;
   private Workbook wb;
   private Sheet sheet;
   private Map styles;
   private List list;
   private List fields;
   private short maxHeight;
   private Map statistics = new HashMap();
   private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("######0.00");
   public Class clazz;

   public ExcelUtil(Class clazz) {
      this.clazz = clazz;
   }

   public void init(List list, String sheetName, Excel.Type type) {
      if (list == null) {
         list = new ArrayList();
      }

      this.list = list;
      this.sheetName = sheetName;
      this.type = type;
      this.createExcelField();
      this.createWorkbook();
   }

   public List importExcel(InputStream is) throws Exception {
      return this.importExcel("", is);
   }

   public List importExcel(String sheetName, InputStream is) throws Exception {
      this.type = Excel.Type.IMPORT;
      this.wb = WorkbookFactory.create(is);
      List<T> list = new ArrayList();
      Sheet sheet = null;
      if (StringUtils.isNotEmpty(sheetName)) {
         sheet = this.wb.getSheet(sheetName);
      } else {
         sheet = this.wb.getSheetAt(0);
      }

      if (sheet == null) {
         throw new IOException("文件sheet不存在");
      } else {
         int rows = sheet.getPhysicalNumberOfRows();
         if (rows > 0) {
            Map<String, Integer> cellMap = new HashMap();
            Row heard = sheet.getRow(0);

            for(int i = 0; i < heard.getPhysicalNumberOfCells(); ++i) {
               Cell cell = heard.getCell(i);
               if (StringUtils.isNotNull(cell)) {
                  String value = this.getCellValue(heard, i).toString();
                  cellMap.put(value, i);
               } else {
                  cellMap.put((Object)null, i);
               }
            }

            Field[] allFields = this.clazz.getDeclaredFields();
            Map<Integer, Field> fieldsMap = new HashMap();

            for(int col = 0; col < allFields.length; ++col) {
               Field field = allFields[col];
               Excel attr = (Excel)field.getAnnotation(Excel.class);
               if (attr != null && (attr.type() == Excel.Type.ALL || attr.type() == this.type)) {
                  field.setAccessible(true);
                  Integer column = (Integer)cellMap.get(attr.name());
                  if (column != null) {
                     fieldsMap.put(column, field);
                  }
               }
            }

            for(int i = 1; i < rows; ++i) {
               Row row = sheet.getRow(i);
               T entity = (T)null;

               for(Map.Entry entry : fieldsMap.entrySet()) {
                  Object val = this.getCellValue(row, (Integer)entry.getKey());
                  entity = (T)(entity == null ? this.clazz.newInstance() : entity);
                  Field field = (Field)fieldsMap.get(entry.getKey());
                  Class<?> fieldType = field.getType();
                  if (String.class == fieldType) {
                     String s = Convert.toStr(val);
                     if (StringUtils.endsWith(s, ".0")) {
                        val = StringUtils.substringBefore(s, ".0");
                     } else {
                        String dateFormat = ((Excel)field.getAnnotation(Excel.class)).dateFormat();
                        if (StringUtils.isNotEmpty(dateFormat)) {
                           val = DateUtils.parseDateToStr(dateFormat, (Date)val);
                        } else {
                           val = Convert.toStr(val);
                        }
                     }
                  } else if ((Integer.TYPE == fieldType || Integer.class == fieldType) && StringUtils.isNumeric(Convert.toStr(val))) {
                     val = Convert.toInt(val);
                  } else if (Long.TYPE != fieldType && Long.class != fieldType) {
                     if (Double.TYPE != fieldType && Double.class != fieldType) {
                        if (Float.TYPE != fieldType && Float.class != fieldType) {
                           if (BigDecimal.class == fieldType) {
                              val = Convert.toBigDecimal(val);
                           } else if (Date.class == fieldType) {
                              if (val instanceof String) {
                                 val = DateUtils.parseDate(val);
                              } else if (val instanceof Double) {
                                 val = DateUtil.getJavaDate((Double)val);
                              }
                           } else if (Boolean.TYPE == fieldType || Boolean.class == fieldType) {
                              val = Convert.toBool(val, false);
                           }
                        } else {
                           val = Convert.toFloat(val);
                        }
                     } else {
                        val = Convert.toDouble(val);
                     }
                  } else {
                     val = Convert.toLong(val);
                  }

                  if (StringUtils.isNotNull(fieldType)) {
                     Excel attr = (Excel)field.getAnnotation(Excel.class);
                     String propertyName = field.getName();
                     if (StringUtils.isNotEmpty(attr.targetAttr())) {
                        propertyName = field.getName() + "." + attr.targetAttr();
                     } else if (StringUtils.isNotEmpty(attr.readConverterExp())) {
                        val = reverseByExp(Convert.toStr(val), attr.readConverterExp(), attr.separator());
                     } else if (StringUtils.isNotEmpty(attr.dictType())) {
                        val = reverseDictByExp(Convert.toStr(val), attr.dictType(), attr.separator());
                     }

                     ReflectUtils.invokeSetter(entity, propertyName, val);
                  }
               }

               list.add(entity);
            }
         }

         return list;
      }
   }

   public AjaxResult exportExcel(List list, String sheetName) {
      this.init(list, sheetName, Excel.Type.EXPORT);
      return this.exportExcel();
   }

   public AjaxResult importTemplateExcel(String sheetName) {
      this.init((List)null, sheetName, Excel.Type.IMPORT);
      return this.exportExcel();
   }

   public AjaxResult exportExcel() {
      OutputStream out = null;

      AjaxResult var23;
      try {
         double sheetNo = Math.ceil((double)(this.list.size() / 65536));

         for(int index = 0; (double)index <= sheetNo; ++index) {
            this.createSheet(sheetNo, index);
            Row row = this.sheet.createRow(0);
            int column = 0;

            for(Object[] os : this.fields) {
               Excel excel = (Excel)os[1];
               this.createCell(excel, row, column++);
            }

            if (Excel.Type.EXPORT.equals(this.type)) {
               this.fillExcelData(index, row);
               this.addStatisticsRow();
            }
         }

         String filename = this.sheetName + ".xlsx";
         out = new FileOutputStream(this.getAbsoluteFile(filename));
         this.wb.write(out);
         var23 = AjaxResult.success(filename);
      } catch (Exception e) {
         log.error("导出Excel异常{}", e.getMessage());
         throw new CustomException("导出Excel失败，请联系网站管理员！");
      } finally {
         if (this.wb != null) {
            try {
               this.wb.close();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }

         if (out != null) {
            try {
               out.close();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }

      }

      return var23;
   }

   public void fillExcelData(int index, Row row) {
      int startNo = index * 65536;
      int endNo = Math.min(startNo + 65536, this.list.size());

      for(int i = startNo; i < endNo; ++i) {
         row = this.sheet.createRow(i + 1 - startNo);
         T vo = (T)this.list.get(i);
         int column = 0;

         for(Object[] os : this.fields) {
            Field field = (Field)os[0];
            Excel excel = (Excel)os[1];
            field.setAccessible(true);
            this.addCell(excel, row, vo, field, column++);
         }
      }

   }

   private Map createStyles(Workbook wb) {
      Map<String, CellStyle> styles = new HashMap();
      CellStyle style = wb.createCellStyle();
      style.setAlignment(HorizontalAlignment.CENTER);
      style.setVerticalAlignment(VerticalAlignment.CENTER);
      style.setBorderRight(BorderStyle.THIN);
      style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
      style.setBorderLeft(BorderStyle.THIN);
      style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
      style.setBorderTop(BorderStyle.THIN);
      style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
      style.setBorderBottom(BorderStyle.THIN);
      style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
      Font dataFont = wb.createFont();
      dataFont.setFontName("Arial");
      dataFont.setFontHeightInPoints((short)10);
      style.setFont(dataFont);
      styles.put("data", style);
      style = wb.createCellStyle();
      style.cloneStyleFrom((CellStyle)styles.get("data"));
      style.setAlignment(HorizontalAlignment.CENTER);
      style.setVerticalAlignment(VerticalAlignment.CENTER);
      style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      Font headerFont = wb.createFont();
      headerFont.setFontName("Arial");
      headerFont.setFontHeightInPoints((short)10);
      headerFont.setBold(true);
      headerFont.setColor(IndexedColors.WHITE.getIndex());
      style.setFont(headerFont);
      styles.put("header", style);
      style = wb.createCellStyle();
      style.setAlignment(HorizontalAlignment.CENTER);
      style.setVerticalAlignment(VerticalAlignment.CENTER);
      Font totalFont = wb.createFont();
      totalFont.setFontName("Arial");
      totalFont.setFontHeightInPoints((short)10);
      style.setFont(totalFont);
      styles.put("total", style);
      style = wb.createCellStyle();
      style.cloneStyleFrom((CellStyle)styles.get("data"));
      style.setAlignment(HorizontalAlignment.LEFT);
      styles.put("data1", style);
      style = wb.createCellStyle();
      style.cloneStyleFrom((CellStyle)styles.get("data"));
      style.setAlignment(HorizontalAlignment.CENTER);
      styles.put("data2", style);
      style = wb.createCellStyle();
      style.cloneStyleFrom((CellStyle)styles.get("data"));
      style.setAlignment(HorizontalAlignment.RIGHT);
      styles.put("data3", style);
      return styles;
   }

   public Cell createCell(Excel attr, Row row, int column) {
      Cell cell = row.createCell(column);
      cell.setCellValue(attr.name());
      this.setDataValidation(attr, row, column);
      cell.setCellStyle((CellStyle)this.styles.get("header"));
      return cell;
   }

   public void setCellVo(Object value, Excel attr, Cell cell) {
      if (Excel.ColumnType.STRING == attr.cellType()) {
         cell.setCellValue(StringUtils.isNull(value) ? attr.defaultValue() : value + attr.suffix());
      } else if (Excel.ColumnType.NUMERIC == attr.cellType()) {
         cell.setCellValue(StringUtils.contains(Convert.toStr(value), ".") ? Convert.toDouble(value) : (double)Convert.toInt(value));
      } else if (Excel.ColumnType.IMAGE == attr.cellType()) {
         ClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short)cell.getColumnIndex(), cell.getRow().getRowNum(), (short)(cell.getColumnIndex() + 1), cell.getRow().getRowNum() + 1);
         String imagePath = Convert.toStr(value);
         if (StringUtils.isNotEmpty(imagePath)) {
            byte[] data = ImageUtils.getImage(imagePath);
            getDrawingPatriarch(cell.getSheet()).createPicture(anchor, cell.getSheet().getWorkbook().addPicture(data, this.getImageType(data)));
         }
      }

   }

   public static Drawing getDrawingPatriarch(Sheet sheet) {
      if (sheet.getDrawingPatriarch() == null) {
         sheet.createDrawingPatriarch();
      }

      return sheet.getDrawingPatriarch();
   }

   public int getImageType(byte[] value) {
      String type = FileTypeUtils.getFileExtendName(value);
      if ("JPG".equalsIgnoreCase(type)) {
         return 5;
      } else {
         return "PNG".equalsIgnoreCase(type) ? 6 : 5;
      }
   }

   public void setDataValidation(Excel attr, Row row, int column) {
      if (attr.name().indexOf("注：") >= 0) {
         this.sheet.setColumnWidth(column, 6000);
      } else {
         this.sheet.setColumnWidth(column, (int)((attr.width() + 0.72) * (double)256.0F));
      }

      if (StringUtils.isNotEmpty(attr.prompt())) {
         this.setXSSFPrompt(this.sheet, "", attr.prompt(), 1, 100, column, column);
      }

      if (attr.combo().length > 0) {
         this.setXSSFValidation(this.sheet, attr.combo(), 1, 100, column, column);
      }

   }

   public Cell addCell(Excel attr, Row row, Object vo, Field field, int column) {
      Cell cell = null;

      try {
         row.setHeight(this.maxHeight);
         if (attr.isExport()) {
            cell = row.createCell(column);
            int align = attr.align().value();
            cell.setCellStyle((CellStyle)this.styles.get("data" + (align >= 1 && align <= 3 ? align : "")));
            Object value = this.getTargetValue(vo, field, attr);
            String dateFormat = attr.dateFormat();
            String readConverterExp = attr.readConverterExp();
            String separator = attr.separator();
            String dictType = attr.dictType();
            if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value)) {
               cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date)value));
            } else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value)) {
               cell.setCellValue(convertByExp(Convert.toStr(value), readConverterExp, separator));
            } else if (StringUtils.isNotEmpty(dictType) && StringUtils.isNotNull(value)) {
               cell.setCellValue(convertDictByExp(Convert.toStr(value), dictType, separator));
            } else if (value instanceof BigDecimal && -1 != attr.scale()) {
               cell.setCellValue(((BigDecimal)value).setScale(attr.scale(), attr.roundingMode()).toString());
            } else {
               this.setCellVo(value, attr, cell);
            }

            this.addStatisticsData(column, Convert.toStr(value), attr);
         }
      } catch (Exception e) {
         log.error("导出Excel失败{}", e);
      }

      return cell;
   }

   public void setXSSFPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
      DataValidationHelper helper = sheet.getDataValidationHelper();
      DataValidationConstraint constraint = helper.createCustomConstraint("DD1");
      CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
      DataValidation dataValidation = helper.createValidation(constraint, regions);
      dataValidation.createPromptBox(promptTitle, promptContent);
      dataValidation.setShowPromptBox(true);
      sheet.addValidationData(dataValidation);
   }

   public void setXSSFValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {
      DataValidationHelper helper = sheet.getDataValidationHelper();
      DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
      CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
      DataValidation dataValidation = helper.createValidation(constraint, regions);
      if (dataValidation instanceof XSSFDataValidation) {
         dataValidation.setSuppressDropDownArrow(true);
         dataValidation.setShowErrorBox(true);
      } else {
         dataValidation.setSuppressDropDownArrow(false);
      }

      sheet.addValidationData(dataValidation);
   }

   public static String convertByExp(String propertyValue, String converterExp, String separator) {
      StringBuilder propertyString = new StringBuilder();
      String[] convertSource = converterExp.split(",");

      for(String item : convertSource) {
         String[] itemArray = item.split("=");
         if (StringUtils.containsAny(separator, propertyValue)) {
            for(String value : propertyValue.split(separator)) {
               if (itemArray[0].equals(value)) {
                  propertyString.append(itemArray[1] + separator);
                  break;
               }
            }
         } else if (itemArray[0].equals(propertyValue)) {
            return itemArray[1];
         }
      }

      return StringUtils.stripEnd(propertyString.toString(), separator);
   }

   public static String reverseByExp(String propertyValue, String converterExp, String separator) {
      StringBuilder propertyString = new StringBuilder();
      String[] convertSource = converterExp.split(",");

      for(String item : convertSource) {
         String[] itemArray = item.split("=");
         if (StringUtils.containsAny(separator, propertyValue)) {
            for(String value : propertyValue.split(separator)) {
               if (itemArray[1].equals(value)) {
                  propertyString.append(itemArray[0] + separator);
                  break;
               }
            }
         } else if (itemArray[1].equals(propertyValue)) {
            return itemArray[0];
         }
      }

      return StringUtils.stripEnd(propertyString.toString(), separator);
   }

   public static String convertDictByExp(String dictValue, String dictType, String separator) {
      return DictUtils.getDictLabel(dictType, dictValue, separator);
   }

   public static String reverseDictByExp(String dictLabel, String dictType, String separator) {
      return DictUtils.getDictValue(dictType, dictLabel, separator);
   }

   private void addStatisticsData(Integer index, String text, Excel entity) {
      if (entity != null && entity.isStatistics()) {
         Double temp = (double)0.0F;
         if (!this.statistics.containsKey(index)) {
            this.statistics.put(index, temp);
         }

         try {
            temp = Double.valueOf(text);
         } catch (NumberFormatException var6) {
         }

         this.statistics.put(index, (Double)this.statistics.get(index) + temp);
      }

   }

   public void addStatisticsRow() {
      if (this.statistics.size() > 0) {
         Cell cell = null;
         Row row = this.sheet.createRow(this.sheet.getLastRowNum() + 1);
         Set<Integer> keys = this.statistics.keySet();
         cell = row.createCell(0);
         cell.setCellStyle((CellStyle)this.styles.get("total"));
         cell.setCellValue("合计");

         for(Integer key : keys) {
            cell = row.createCell(key);
            cell.setCellStyle((CellStyle)this.styles.get("total"));
            cell.setCellValue(DOUBLE_FORMAT.format(this.statistics.get(key)));
         }

         this.statistics.clear();
      }

   }

   public String encodingFilename(String filename) {
      filename = UUID.randomUUID().toString() + "_" + filename + ".xlsx";
      return filename;
   }

   public String getAbsoluteFile(String filename) {
      String downloadPath = EMRConfig.getDownloadPath() + filename;
      File desc = new File(downloadPath);
      if (desc.exists()) {
         desc.delete();
      }

      desc.getParentFile().mkdirs();
      return downloadPath;
   }

   private Object getTargetValue(Object vo, Field field, Excel excel) throws Exception {
      Object o = field.get(vo);
      if (StringUtils.isNotEmpty(excel.targetAttr())) {
         String target = excel.targetAttr();
         if (target.indexOf(".") > -1) {
            String[] targets = target.split("[.]");

            for(String name : targets) {
               o = this.getValue(o, name);
            }
         } else {
            o = this.getValue(o, target);
         }
      }

      return o;
   }

   private Object getValue(Object o, String name) throws Exception {
      if (StringUtils.isNotNull(o) && StringUtils.isNotEmpty(name)) {
         Class<?> clazz = o.getClass();
         Field field = clazz.getDeclaredField(name);
         field.setAccessible(true);
         o = field.get(o);
      }

      return o;
   }

   private void createExcelField() {
      this.fields = new ArrayList();
      List<Field> tempFields = new ArrayList();
      tempFields.addAll(Arrays.asList(this.clazz.getSuperclass().getDeclaredFields()));
      tempFields.addAll(Arrays.asList(this.clazz.getDeclaredFields()));

      for(Field field : tempFields) {
         if (field.isAnnotationPresent(Excel.class)) {
            this.putToField(field, (Excel)field.getAnnotation(Excel.class));
         }

         if (field.isAnnotationPresent(Excels.class)) {
            Excels attrs = (Excels)field.getAnnotation(Excels.class);
            Excel[] excels = attrs.value();

            for(Excel excel : excels) {
               this.putToField(field, excel);
            }
         }
      }

      this.fields = (List)this.fields.stream().sorted(Comparator.comparing((objects) -> ((Excel)objects[1]).sort())).collect(Collectors.toList());
      this.maxHeight = this.getRowHeight();
   }

   public short getRowHeight() {
      double maxHeight = (double)0.0F;

      for(Object[] os : this.fields) {
         Excel excel = (Excel)os[1];
         maxHeight = maxHeight > excel.height() ? maxHeight : excel.height();
      }

      return (short)((int)(maxHeight * (double)20.0F));
   }

   private void putToField(Field field, Excel attr) {
      if (attr != null && (attr.type() == Excel.Type.ALL || attr.type() == this.type)) {
         this.fields.add(new Object[]{field, attr});
      }

   }

   public void createWorkbook() {
      this.wb = new SXSSFWorkbook(500);
   }

   public void createSheet(double sheetNo, int index) {
      this.sheet = this.wb.createSheet();
      this.styles = this.createStyles(this.wb);
      if (sheetNo == (double)0.0F) {
         this.wb.setSheetName(index, this.sheetName);
      } else {
         this.wb.setSheetName(index, this.sheetName + index);
      }

   }

   public Object getCellValue(Row row, int column) {
      if (row == null) {
         return row;
      } else {
         Object val = "";

         try {
            Cell cell = row.getCell(column);
            if (StringUtils.isNotNull(cell)) {
               if (cell.getCellType() != CellType.NUMERIC && cell.getCellType() != CellType.FORMULA) {
                  if (cell.getCellType() == CellType.STRING) {
                     val = cell.getStringCellValue();
                  } else if (cell.getCellType() == CellType.BOOLEAN) {
                     val = cell.getBooleanCellValue();
                  } else if (cell.getCellType() == CellType.ERROR) {
                     val = cell.getErrorCellValue();
                  }
               } else {
                  Object var6 = cell.getNumericCellValue();
                  if (DateUtil.isCellDateFormatted(cell)) {
                     val = DateUtil.getJavaDate((Double)var6);
                  } else if ((Double)var6 % (double)1.0F != (double)0.0F) {
                     val = new BigDecimal(var6.toString());
                  } else {
                     val = (new DecimalFormat("0")).format(var6);
                  }
               }
            }

            return val;
         } catch (Exception var5) {
            return val;
         }
      }
   }
}
