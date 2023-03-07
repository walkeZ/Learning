package com.example.tflitedemo

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.tflitedemo.tool.BitmapHandle
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

/**
 * @author walker
 * @date 2023/2/9
 * @description  https://www.163.com/dy/article/HO25EC9P0519EA27.html
 */
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var tflite: Interpreter
    private lateinit var tfLiteModel: ByteBuffer
    private lateinit var editText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BitmapHandle.formatPressureHex1(BitmapHandle.LEFT)
        BitmapHandle.formatPressureHex(BitmapHandle.LEFT, "Left")
        BitmapHandle.changeHangLieTest1()
        BitmapHandle.changeHangLieTest2()
        try {
            tfLiteModel = loadModelFile(this.assets, "model.tflite")
            tflite = Interpreter(tfLiteModel)
            // 输入的数据类型
            Log.i(TAG, "onCreate: Input DataType " + tflite.getInputTensor(0).dataType().toString())
            // 输入的维度
            Log.i(
                TAG,
                "onCreate: Input TensorShape " + tflite.getInputTensor(0).numDimensions().toString()
            )
            for (i in tflite.getInputTensor(0).shape().indices) {
                Log.i(
                    TAG,
                    "onCreate: Input TensorShape Dimensions " + (i + 1) + ", " + tflite.getInputTensor(
                        0
                    ).shape()[i].toString()
                )
            }
//            2023-02-16 11:04:39.090 15156-15156/com.example.tflitedemo I/MainActivity: onCreate: Input DataType FLOAT32
//            2023-02-16 11:04:39.090 15156-15156/com.example.tflitedemo I/MainActivity: onCreate: Input TensorShape 2
//            2023-02-16 11:04:39.090 15156-15156/com.example.tflitedemo I/MainActivity: onCreate: Input TensorShape Dimensions 1, 1
//            2023-02-16 11:04:39.091 15156-15156/com.example.tflitedemo I/MainActivity: onCreate: Input TensorShape Dimensions 2, 1
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "onCreate: Input DataType " + tflite.getInputTensor(0).dataType().toString())
        }

        editText = findViewById(R.id.main_etData)
        var btConvert: Button = findViewById(R.id.main_btConvert)
        btConvert.setOnClickListener {
            doInference()
        }

        findViewById<Button>(R.id.main_btCatOrDog).setOnClickListener {
            ImageClassifierActivity.startAct(this)
        }
        findViewById<Button>(R.id.main_btHet).setOnClickListener {
            HetClassifierActivity.startAct(this)
        }
    }

    private fun doInference() {
        BitmapHandle.formatPressureHex(BitmapHandle.NO_ONE, "No one")
        BitmapHandle.formatPressureHex(BitmapHandle.SIT, "Sit")
        BitmapHandle.formatPressureHex(BitmapHandle.LEFT, "Left")
        BitmapHandle.formatPressureHex(BitmapHandle.RIGHT, "Right")
        BitmapHandle.formatPressureHex(BitmapHandle.OVER, "Over")
        BitmapHandle.formatPressureHex(BitmapHandle.UNDER, "Under")
        BitmapHandle.changeHangLieTest1()
        BitmapHandle.changeHangLieTest2()
//        var userVal = editText.text.toString().toFloat()
//        var inputVal = floatArrayOf(userVal)
//        Log.i(TAG, "doInference: $inputVal")
//        var outputVal = ByteBuffer.allocateDirect(4)
//        outputVal.order(ByteOrder.nativeOrder())
//        tflite.run(inputVal, outputVal) // 使用模型进行推理。
//        Log.i(TAG, "doInference: $inputVal , $outputVal")
//        outputVal.rewind() // 指针重新指向缓冲区开发
//
//        var float = outputVal.getFloat() // 将结果读取为浮点数
//        val builder = AlertDialog.Builder(this)
//        with(builder) {
//            setTitle("TFLite Interpreter")
//            setMessage("Your Values: $float")
//            setNeutralButton("Ok") { diaog, _ ->
//                diaog.dismiss()
//            }
//            show()
//        }
    }

    private fun loadModelFile(assetManager: AssetManager, modePath: String): ByteBuffer {
        var fileDescriptor = assetManager.openFd(modePath)
        var inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        var channel = inputStream.channel
        var startOffset = fileDescriptor.startOffset
        var declaredLength = fileDescriptor.declaredLength
        return channel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}