package com.emr.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import sun.misc.BASE64Encoder;

public class BarCodeUtils {
   public static File generateFiles(String msg, String pathDirFirst, String pathDir, String picName) {
      if (StringUtils.isNotBlank(pathDirFirst)) {
         File fileDirFirst = new File(pathDirFirst);
         if (!fileDirFirst.exists()) {
            fileDirFirst.mkdir();
         }
      }

      File fileDir = new File(pathDirFirst + pathDir);
      if (!fileDir.exists()) {
         fileDir.mkdir();
      }

      String path = pathDirFirst + pathDir + picName;
      File file = new File(path);

      try {
         generates(msg, new FileOutputStream(file));
         return file;
      } catch (FileNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   public byte[] generates(String msg) throws IOException {
      ByteArrayOutputStream ous = new ByteArrayOutputStream();
      generates(msg, ous);
      ous.close();
      return ous.toByteArray();
   }

   public static void generates(String msg, OutputStream ous) {
      if (!StringUtils.isEmpty(msg) && ous != null) {
         Code128Bean bean = new Code128Bean();
         int dpi = 150;
         double moduleWidth = UnitConv.in2mm((double)0.02F);
         bean.setModuleWidth(moduleWidth);
         bean.doQuietZone(false);
         String format = "image/png";

         try {
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, 150, 12, false, 0);
            bean.generateBarcode(canvas, msg);
            canvas.finish();
            ous.close();
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
   }

   public static String convertFileToBase64(String imgPath) {
      byte[] data = null;

      try {
         InputStream in = new FileInputStream(imgPath);
         data = new byte[in.available()];
         in.read(data);
         in.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

      BASE64Encoder encoder = new BASE64Encoder();
      String base64Str = encoder.encode(data);
      return base64Str;
   }

   public static void main(String[] args) {
      String base64Str = convertFileToBase64("D://nurseBarCode/202005145/210309104556965.png");
      System.out.println(base64Str);
   }

   public static String generateBarCode128(String strBarCode, String dimension, String barheight) {
      try {
         ByteArrayOutputStream outputStream = null;
         BufferedImage bi = null;
         JBarcode productBarcode = new JBarcode(Code128Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
         productBarcode.setXDimension(Double.valueOf(dimension));
         productBarcode.setBarHeight(Double.valueOf(barheight));
         productBarcode.setWideRatio(Double.valueOf((double)30.0F));
         productBarcode.setShowText(false);
         bi = productBarcode.createBarcode(strBarCode);
         outputStream = new ByteArrayOutputStream();
         ImageIO.write(bi, "jpg", outputStream);
         BASE64Encoder encoder = new BASE64Encoder();
         return encoder.encode(outputStream.toByteArray());
      } catch (Exception e) {
         e.printStackTrace();
         return "encodeError";
      }
   }

   public static String getNumStr(int num) {
      String numStr = String.valueOf(num);
      if (num < 10) {
         numStr = "0" + numStr;
      }

      return numStr;
   }
}
