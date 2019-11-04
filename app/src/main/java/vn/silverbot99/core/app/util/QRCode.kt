package vn.silverbot99.core.app.util

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

class QRCode {

    companion object {
        @Throws(WriterException::class)
        fun TextToImageEncode(Value: String): Bitmap? {
            val bitMatrix: BitMatrix
            try {
                bitMatrix = MultiFormatWriter().encode(
                        Value,
                        BarcodeFormat.QR_CODE,
                        qrCodeWidth, qrCodeWidth, null
                )

            } catch (Illegalargumentexception: IllegalArgumentException) {

                return null
            }

            val bitMatrixWidth = bitMatrix.getWidth()

            val bitMatrixHeight = bitMatrix.getHeight()

            val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)

            for (y in 0 until bitMatrixHeight) {
                val offset = y * bitMatrixWidth

                for (x in 0 until bitMatrixWidth) {

                    pixels[offset + x] = if (bitMatrix.get(x, y))
                        Color.BLACK
                    else
                        Color.WHITE
                }
            }
            val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)

            bitmap.setPixels(pixels, 0, 300, 0, 0, bitMatrixWidth, bitMatrixHeight)
            return bitmap
        }

        val qrCodeWidth = 300
    }

}