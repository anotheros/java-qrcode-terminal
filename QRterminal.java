import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class QRterminal {


    public static String toAsciiBitmap(BufferedImage bim) {
        StringBuilder sb = new StringBuilder();
        for (int rows = 0; rows < bim.getHeight(); rows++) {
            for (int cols = 0; cols < bim.getWidth(); cols++) {
                int x = bim.getRGB(rows, cols);
                if (x == -1) {
                    // white
                    sb.append("\033[47m  \033[0m");
                } else {
                    sb.append("\033[40m  \033[0m");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String text = "https://github.com/zhangshanhai/java-qrcode-terminal ";
        int width = 80;
        int height = 80;
        // 用于设置QR二维码参数
        Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();
// 设置QR二维码的纠错级别——这里选择最高H级别
        qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        qrParam.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, qrParam);
        String s = toAsciiBitmap(MatrixToImageWriter.toBufferedImage(bitMatrix));
        System.out.println(s);
    }
}
