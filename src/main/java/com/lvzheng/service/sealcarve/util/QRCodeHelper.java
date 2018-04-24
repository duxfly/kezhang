package com.lvzheng.service.sealcarve.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码帮助类
 */
public class QRCodeHelper {


    /**
     * 生成二维码图片
     * @param content 二维码内容
     * @param size 二维码尺寸
     * @return 返回二维码图片
     * @throws Exception
     * BufferedImage
     */
    public static BufferedImage createImageCode(String content, int size) throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        return image;
    }

    /**
     * 生成Base64二维码
     * @param content 二维码内容
     * @param size 二维码尺寸
     * @return 返回Base64二维码
     * @throws Exception error
     */
    public static String createBase64Code(String content, int size) throws Exception {
        BufferedImage img = createImageCode(content, size);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", os);
        return Base64.getEncoder().encodeToString(os.toByteArray());
    }

}