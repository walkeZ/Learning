package com.example.tflitedemo

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tflitedemo.tflite.Classifier
import com.example.tflitedemo.tflite.HetClassifier
import com.example.tflitedemo.tflite.Recognition
import com.example.tflitedemo.tool.BitmapUtil
import com.example.tflitedemo.tool.Constants
import com.example.tflitedemo.tool.MyLog

/**
 * @author walker
 * @date 2023/2/16
 * @description 仅测试仅部分手机、平板可以。hornor 20 pro。可以。小米12不行。
 */
class ImageClassifierActivity : AppCompatActivity(), View.OnClickListener {

    private val mInputSize = 224
    private val mModelPath = "converted_model.tflite"
    private val mHetModelPath = "het_model.tflite"

    private val mLabelPath = "label.txt"
    private lateinit var classifier: Classifier
    private lateinit var hetClassifier: HetClassifier

    companion object {
        fun startAct(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, ImageClassifierActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_classifier)
//        BitmapUtil.histogram(intArrayOf(255, 128, 200, 50))
//        BitmapUtil.medianFilter2(arrayOf(intArrayOf(255, 128), intArrayOf(200, 255)), 4, 4)
        initClassifier()
        initViews()
        initHetClassifier()
    }

    private fun initClassifier() {
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)
    }

    private fun initHetClassifier() {
        hetClassifier = HetClassifier(assets, mHetModelPath)
        MyLog.e(" $hetClassifier")
    }

    private fun initViews() {
        findViewById<ImageView>(R.id.iv_1).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_2).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_3).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_4).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_5).setOnClickListener(this)
        findViewById<ImageView>(R.id.iv_6).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val bitmap = ((view as ImageView).drawable as BitmapDrawable).bitmap
        if (classifier == null) {
            MyLog.e("onClick: classifier == null")
            return
        }
        val result = classifier.recognizeImage(bitmap)
        MyLog.i("onClick: $bitmap, $result")
        runOnUiThread { Toast.makeText(this, result.get(0).title, Toast.LENGTH_SHORT).show() }
//        var medianFilter = BitmapUtil.medianFilteringForHet(Constants.ps_h, 32, 16)
        var medianFilter = BitmapUtil.medianFilteringForHet(Constants.ps, 16, 32)
        var isValid = BitmapUtil.minFilteringForHet(medianFilter, 15)
        if (!isValid) {
            showPosition(Recognition(0, "无人", 1.0f))
            return
        }
        var histogramForHet = BitmapUtil.histogramForHet(medianFilter)
        var standardization = BitmapUtil.standardization(histogramForHet)
        if (hetClassifier == null) {
            MyLog.e("onClick: hetClassifier == null")
            return
        }
        val hetResult = hetClassifier.recognizePosition(standardization)
        MyLog.i("onClick: $hetResult, $hetResult")
        showPosition(hetResult)
    }

    private fun showPosition(hetResult: Recognition) {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("hetTFLite")
            setMessage("position: $hetResult")
            setNeutralButton("Ok") { diaog, _ ->
                diaog.dismiss()
            }
            show()
        }
    }
}
