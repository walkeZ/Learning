package com.example.tflitedemo.tflite

import android.content.res.AssetManager
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
class HetClassifier(
    assetManager: AssetManager,
    modelPath: String
) {
    private lateinit var interpreter: Interpreter
    private lateinit var positionList: List<String>

    init {
        val options = Interpreter.Options()
        options.numThreads = 5
//        options.useNNAPI = true
        try {
            MyLog.i(": init0 $modelPath, assetManager $assetManager")
            interpreter = Interpreter(loadModelFile(assetManager, modelPath), options)
            MyLog.i(": init1 $modelPath, interpreter $interpreter")

            // 输入的数据类型
            MyLog.i(
                "onCreate: Input DataType " + interpreter.getInputTensor(0).dataType().toString()
            )
            // 输入的维度
            MyLog.i(
                "onCreate: Input TensorShape " + interpreter.getInputTensor(0).numDimensions()
                    .toString()
            )
            for (i in interpreter.getInputTensor(0).shape().indices) {
                MyLog.i(
                    "onCreate: Input TensorShape Dimensions " + (i + 1) + ", " + interpreter.getInputTensor(
                        0
                    ).shape()[i].toString()
                )
            }
            // 2023-02-16 11:05:24.264 I/MyLog: (HetClassifier.kt:46).<init> onCreate: Input DataType FLOAT32
            // 2023-02-16 11:05:24.264 I/MyLog: (HetClassifier.kt:48).<init> onCreate: Input TensorShape 4
            // 2023-02-16 11:05:24.264 I/MyLog: (HetClassifier.kt:50).<init> onCreate: Input TensorShape Dimensions 1, 1
            // 2023-02-16 11:05:24.265 I/MyLog: (HetClassifier.kt:50).<init> onCreate: Input TensorShape Dimensions 2, 32
            // 2023-02-16 11:05:24.265 I/MyLog: (HetClassifier.kt:50).<init> onCreate: Input TensorShape Dimensions 3, 16
            // 2023-02-16 11:05:24.265 I/MyLog: (HetClassifier.kt:50).<init> onCreate: Input TensorShape Dimensions 4, 1

            // 无人：0 ；坐立：1； 仰睡：2 ；左侧卧：3 ；右侧卧：4 ；趴睡：5
            positionList = arrayListOf("无人", "坐立", "仰睡", "左侧卧", "右侧卧", "趴睡")
        } catch (e: Exception) {
            MyLog.e(": init " + interpreter + ", error " + e.message)
        }
    }

    /**
     * 加载模式文件
     */
    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    /**
     * 识别姿势
     *
     * bug:【use ByteBuffer】
     * 2023-02-14 13:45:14.888 17278-17278/com.example.cats_vs_dogs D/MyLog: (HetClassifier.kt:65).recognizePosition byteBuffer  java.nio.DirectByteBuffer[pos=2048 lim=2048 cap=2048]
     *   2023-02-14 13:45:14.893 17278-17278/com.example.cats_vs_dogs E/AndroidRuntime: FATAL EXCEPTION: main
     *   Process: com.example.cats_vs_dogs, PID: 17278
     *   java.lang.IllegalArgumentException: Cannot copy from a TensorFlowLite tensor (StatefulPartitionedCall:0) with shape [1, 6] to a Java object with shape [1, 512].
     *       at org.tensorflow.lite.TensorImpl.throwIfDstShapeIsIncompatible(TensorImpl.java:454)
     *       at org.tensorflow.lite.TensorImpl.copyTo(TensorImpl.java:216)
     *       at org.tensorflow.lite.NativeInterpreterWrapper.run(NativeInterpreterWrapper.java:261)
     *       at org.tensorflow.lite.InterpreterImpl.runForMultipleInputsOutputs(InterpreterImpl.java:107)
     *       at org.tensorflow.lite.Interpreter.runForMultipleInputsOutputs(Interpreter.java:80)
     *       at org.tensorflow.lite.InterpreterImpl.run(InterpreterImpl.java:100)
     *       at org.tensorflow.lite.Interpreter.run(Interpreter.java:80)
     *       at com.example.cats_vs_dogs.tflite.HetClassifier.recognizePosition(HetClassifier.kt:67)
     *       at com.example.cats_vs_dogs.ImageClassifierActivity.onClick(ImageClassifierActivity.kt:68)
     *       at android.view.View.performClick(View.java:7317)
     *
     * 【use FloatArray】
     * 2023-02-14 13:49:58.575 17885-17885/com.example.cats_vs_dogs E/AndroidRuntime: FATAL EXCEPTION: main
     *   Process: com.example.cats_vs_dogs, PID: 17885
     *   java.lang.IllegalStateException: Internal error: Unexpected failure when preparing tensor allocations: tensorflow/lite/kernels/conv.cc:343 input->dims->size != 4 (1 != 4)
     *   Node number 0 (CONV_2D) failed to prepare.
     *       at org.tensorflow.lite.NativeInterpreterWrapper.allocateTensors(Native Method)
     *       at org.tensorflow.lite.NativeInterpreterWrapper.allocateTensorsIfNeeded(NativeInterpreterWrapper.java:300)
     *       at org.tensorflow.lite.NativeInterpreterWrapper.run(NativeInterpreterWrapper.java:240)
     *       at org.tensorflow.lite.InterpreterImpl.runForMultipleInputsOutputs(InterpreterImpl.java:107)
     *       at org.tensorflow.lite.Interpreter.runForMultipleInputsOutputs(Interpreter.java:80)
     *       at org.tensorflow.lite.InterpreterImpl.run(InterpreterImpl.java:100)
     *       at org.tensorflow.lite.Interpreter.run(Interpreter.java:80)
     *       at com.example.cats_vs_dogs.tflite.HetClassifier.recognizePosition(HetClassifier.kt:81)
     *       at com.example.cats_vs_dogs.ImageClassifierActivity.onClick(ImageClassifierActivity.kt:68)
     *
     * 【参数二result用  FloatArray(6)】
     * 2023-02-14 16:55:14.743 28741-28741/com.example.cats_vs_dogs D/MyLog: (HetClassifier.kt:98).recognizePosition byteBuffer  java.nio.DirectByteBuffer[pos=2048 lim=2048 cap=2048]
     *  2023-02-14 16:55:14.760 28741-28741/com.example.cats_vs_dogs E/AndroidRuntime: FATAL EXCEPTION: main
     *      Process: com.example.cats_vs_dogs, PID: 28741
     *      java.lang.IllegalArgumentException: Cannot copy from a TensorFlowLite tensor (StatefulPartitionedCall:0) with shape [1, 6] to a Java object with shape [6].
     *      at org.tensorflow.lite.TensorImpl.throwIfDstShapeIsIncompatible(TensorImpl.java:454)
     *      at org.tensorflow.lite.TensorImpl.copyTo(TensorImpl.java:216)
     *      at org.tensorflow.lite.NativeInterpreterWrapper.run(NativeInterpreterWrapper.java:261)
     *      at org.tensorflow.lite.InterpreterImpl.runForMultipleInputsOutputs(InterpreterImpl.java:107)
     *      at org.tensorflow.lite.Interpreter.runForMultipleInputsOutputs(Interpreter.java:80)
     *      at org.tensorflow.lite.InterpreterImpl.run(InterpreterImpl.java:100)
     *      at org.tensorflow.lite.Interpreter.run(Interpreter.java:80)
     *      at com.example.cats_vs_dogs.tflite.HetClassifier.recognizePosition(HetClassifier.kt:99)
     *      at com.example.cats_vs_dogs.ImageClassifierActivity.onClick(ImageClassifierActivity.kt:69)
     *      at android.view.View.performClick(View.java:7317)
     */
    fun recognizePosition(dataArray: FloatArray): Recognition {
//        val result = FloatArray(6)
        val result = Array(1) { FloatArray(6) }
//        interpreter.run(dataArray, result)
        val byteBuffer = convertByteBuffer(dataArray)
        MyLog.d("byteBuffer  $byteBuffer")
        val input = Array(1) { dataArray }
        interpreter.run(byteBuffer, result)
        MyLog.i("byteBuffer  ${result.contentToString()}")
        return parseResult(result)
    }

    fun recognizePositionNew(dataArray: FloatArray): Recognition {
//        val result = FloatArray(6)
        val result = Array(1) { FloatArray(6) }
//        interpreter.run(dataArray, result)
        val byteBuffer = convertByteBufferNew(dataArray)
        MyLog.d("byteBuffer  $byteBuffer")

        val input = Array(1) { dataArray }
        interpreter.run(byteBuffer, result)
        MyLog.i("byteBuffer  ${result.contentToString()}")
        return parseResult(result)
    }

    /**
     * 数组转换
     */
    private fun convertByteBuffer(dataArray: FloatArray): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * dataArray.size)
        byteBuffer.order(ByteOrder.nativeOrder())
        for (element in dataArray) {
            byteBuffer.putFloat(element)
        }
        return byteBuffer
    }

    /**
     * 数组转换
     */
    private fun convertByteBufferNew(dataArray: FloatArray): ByteBuffer {
        val hang = 32;
        val lie = 16;
        val byteBuffer = ByteBuffer.allocateDirect(4 * hang * lie * 1)
        byteBuffer.order(ByteOrder.nativeOrder())
        var pixel = 0
        for (i in 0 until hang) {
            for (j in 0 until lie) {
                val input = dataArray[pixel++]
                byteBuffer.putFloat(input)
            }
        }
        return byteBuffer
    }

    /**
     * 解析结果。
     */
    private fun parseResult(labelProbArray: Array<FloatArray>): Recognition {
        MyLog.d(
            "List Size:(%d, %d)".format(
                labelProbArray.size,
                labelProbArray[0].size
            )
        )

        MyLog.d("labelProbArray  $labelProbArray")
        val pq = PriorityQueue(
            3,
            Comparator<Recognition> { (_, _, confidence1), (_, _, confidence2)
                ->
                java.lang.Float.compare(confidence1, confidence2) * -1
            })

        for (i in positionList.indices) {
            val confidence = labelProbArray[0][i]
            if (confidence >= 0.1) {  // 准确率
                pq.add(
                    Recognition(
                        i,
                        if (positionList.size > i) positionList[i] else "Unknown",
                        confidence
                    )
                )
            }
        }
        MyLog.d("pqsize:(%d)".format(pq.size))
        if (pq.size < 1) {
            return Recognition(
                0,
                if (positionList.isNotEmpty()) positionList[0] else "Unknown",
                -1f
            )
        }
        return pq.poll()
    }
}