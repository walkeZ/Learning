package com.example.tflitedemo.tflite

import android.content.res.AssetManager
import android.graphics.Bitmap
import com.example.tflitedemo.tool.MyLog
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

/**
 * @author walker
 * @date 2023/2/9
 * @description
 */
class Classifier(assetManager: AssetManager, modelPath: String, labelPath: String, inputSize: Int) {
    private lateinit var interpreter: Interpreter
    private lateinit var labelList: List<String>
    private val INPUT_SIZE: Int = inputSize
    private val PIXEL_SIZE: Int = 3
    private val IMAGE_MEAN = 0
    private val IMAGE_STD = 255.0f
    private val MAX_RESULTS = 3
    private val THRESHOLD = 0.4f

    data class Recognition(
        var id: String = "",
        var title: String = "",
        var confidence: Float = 0F
    ) {
        override fun toString(): String {
            return "Title = $title, Confidence = $confidence)"
        }
    }

    init {
        val options = Interpreter.Options()
        options.setNumThreads(5)
        options.setUseNNAPI(true)
        try {
            MyLog.i(": init0 $modelPath, assetManager $assetManager")
            interpreter = Interpreter(loadModelFile(assetManager, modelPath), options)
            MyLog.i(": init1 $modelPath, interpreter $interpreter")
            labelList = loadLabelList(assetManager, labelPath)
            MyLog.i(": init2 $labelPath, labelList $labelList")
            // 输入的数据类型
            MyLog.i("onCreate: Input DataType " + interpreter.getInputTensor(0).dataType().toString())
            // 输入的维度
            MyLog.i("onCreate: Input TensorShape " + interpreter.getInputTensor(0).numDimensions().toString())
            for (i in interpreter.getInputTensor(0).shape().indices) {
                MyLog.i("onCreate: Input TensorShape Dimensions " + (i + 1) + ", " + interpreter.getInputTensor(0).shape()[i].toString())
            }
            // 2023-02-16 11:08:50.348 I/MyLog: (Classifier.kt:52).<init> onCreate: Input TensorShape 4
            // 2023-02-16 11:08:50.348 I/MyLog: (Classifier.kt:54).<init> onCreate: Input TensorShape Dimensions 1, 1
            // 2023-02-16 11:08:50.348 I/MyLog: (Classifier.kt:54).<init> onCreate: Input TensorShape Dimensions 2, 224
            // 2023-02-16 11:08:50.348 I/MyLog: (Classifier.kt:54).<init> onCreate: Input TensorShape Dimensions 3, 224
            // 2023-02-16 11:08:50.349 I/MyLog: (Classifier.kt:54).<init> onCreate: Input TensorShape Dimensions 4, 3
        } catch (e: Exception) {
            MyLog.e(": init " + interpreter + ", labelList " + labelList + ", error " + e.message)
        }
    }

    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun loadLabelList(assetManager: AssetManager, labelPath: String): List<String> {
        return assetManager.open(labelPath).bufferedReader().useLines { it.toList() }
    }

    /**
     * Returns the result after running the recognition with the help of interpreter
     * on the passed bitmap
     */
    fun recognizeImage(bitmap: Bitmap): List<Recognition> {
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false)
        val byteBuffer = convertBitmapToByteBuffer(scaledBitmap)
        MyLog.d("byteBuffer  $byteBuffer")
        val result = Array(1) { FloatArray(labelList.size) }
        interpreter.run(byteBuffer, result)
        return getSortedResult(result)
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        // 4 * 224 * 224 * 3
        val byteBuffer = ByteBuffer.allocateDirect(4 * INPUT_SIZE * INPUT_SIZE * PIXEL_SIZE)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(INPUT_SIZE * INPUT_SIZE)
        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        var pixel = 0
        for (i in 0 until INPUT_SIZE) {
            for (j in 0 until INPUT_SIZE) {
                val input = intValues[pixel++]
                byteBuffer.putFloat((((input.shr(16) and 0xFF) - IMAGE_MEAN) / IMAGE_STD))
                byteBuffer.putFloat((((input.shr(8) and 0xFF) - IMAGE_MEAN) / IMAGE_STD))
                byteBuffer.putFloat((((input and 0xFF) - IMAGE_MEAN) / IMAGE_STD))
            }
        }
        return byteBuffer
    }

    private fun getSortedResult(labelProbArray: Array<FloatArray>): List<Recognition> {
        MyLog.d(
            "List Size:(%d, %d, %d)".format(
                labelProbArray.size,
                labelProbArray[0].size,
                labelList.size
            )
        )

        MyLog.d("labelProbArray  $labelProbArray")
        val pq = PriorityQueue(
            MAX_RESULTS,
            Comparator<Recognition> { (_, _, confidence1), (_, _, confidence2)
                ->
                java.lang.Float.compare(confidence1, confidence2) * -1
            })

        for (i in labelList.indices) {
            val confidence = labelProbArray[0][i]
            if (confidence >= THRESHOLD) {
                pq.add(
                    Recognition(
                        "" + i,
                        if (labelList.size > i) labelList[i] else "Unknown", confidence
                    )
                )
            }
        }
        MyLog.d("pqsize:(%d)".format(pq.size))
        val recognitions = ArrayList<Recognition>()
        val recognitionsSize = Math.min(pq.size, MAX_RESULTS)
        for (i in 0 until recognitionsSize) {
            recognitions.add(pq.poll())
        }
        return recognitions
    }
}