package com.example.tflitedemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tflitedemo.tflite.HetClassifier
import com.example.tflitedemo.tflite.Recognition
import com.example.tflitedemo.tool.BitmapHandle
import com.example.tflitedemo.tool.BitmapUtil
import com.example.tflitedemo.tool.Constants
import com.example.tflitedemo.tool.MyLog

class HetClassifierActivity : AppCompatActivity(), View.OnClickListener {

    private val mHetModelPath = "het_model.tflite"
    private lateinit var hetClassifier: HetClassifier

    companion object {
        fun startAct(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, HetClassifierActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_het_classifier)
//        BitmapUtil.histogram(intArrayOf(255, 128, 200, 50))
//        BitmapUtil.medianFilter2(arrayOf(intArrayOf(255, 128), intArrayOf(200, 255)), 4, 4)
        initViews()
        initHetClassifier()
    }

    private fun initHetClassifier() {
        hetClassifier = HetClassifier(assets, mHetModelPath)
        MyLog.e(" $hetClassifier")
    }

    private fun initViews() {
        findViewById<Button>(R.id.het_test).setOnClickListener(this)
        findViewById<Button>(R.id.het_test0).setOnClickListener(this)
        findViewById<Button>(R.id.het_test1).setOnClickListener(this)
        findViewById<Button>(R.id.het_test2).setOnClickListener(this)
        findViewById<Button>(R.id.het_test3).setOnClickListener(this)
        findViewById<Button>(R.id.het_test4).setOnClickListener(this)
        findViewById<Button>(R.id.het_test5).setOnClickListener(this)
        findViewById<Button>(R.id.het_testHex).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.het_test) {
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
        } else if (view?.id == R.id.het_test0) {
            testData(Constants.ps_0)
        } else if (view?.id == R.id.het_test1) {
            testData(Constants.ps_1)
        } else if (view?.id == R.id.het_test2) {
            testData(Constants.ps_2)
        } else if (view?.id == R.id.het_test3) {
            testData(Constants.ps_3)
        } else if (view?.id == R.id.het_test4) {
            testData(Constants.ps_4)
        } else if (view?.id == R.id.het_test5) {
            testData(Constants.ps_5)
        } else if (view?.id == R.id.het_testHex) {
            var ps = BitmapHandle.formatPressureHex(BitmapHandle.NO_ONE, "No one")
            var hetResult = BitmapUtil.recognizePosition(hetClassifier, ps, 16, 32)
            var hetResult1 = BitmapUtil.recognizePosition(
                hetClassifier,
                BitmapHandle.formatPressureHex(BitmapHandle.SIT, "Sit"),
                16,
                32
            )
            var hetResult2 = BitmapUtil.recognizePosition(
                hetClassifier,
                BitmapHandle.formatPressureHex(BitmapHandle.LEFT, "Left"),
                16,
                32
            )
            var hetResult3 = BitmapUtil.recognizePosition(
                hetClassifier,
                BitmapHandle.formatPressureHex(BitmapHandle.RIGHT, "Right"),
                16,
                32
            )
            var hetResult4 = BitmapUtil.recognizePosition(
                hetClassifier,
                BitmapHandle.formatPressureHex(BitmapHandle.OVER, "Over"),
                16,
                32
            )
            var hetResult5 = BitmapUtil.recognizePosition(
                hetClassifier,
                BitmapHandle.formatPressureHex(BitmapHandle.UNDER, "Under"),
                16,
                32
            )
            var s =
                "$hetResult,\n $hetResult1,\n $hetResult2,\n $hetResult3,\n $hetResult4,\n $hetResult5"
            MyLog.i("onClick: $s")
            showPosition(s)
        }

    }

    private fun testData(ps: IntArray) {
        try {
            var hetResult = BitmapUtil.recognizePosition(hetClassifier, ps, 16, 32)
            MyLog.i("onClick: $hetResult, $hetResult")
            showPosition(hetResult)
        } catch (e: Exception) {
            e.printStackTrace()
            MyLog.i("onClick: $e")
        }
    }

    private fun showPosition(hetResult: Recognition) {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("hetTFLite")
            setMessage("position:\n $hetResult")
            setNeutralButton("Ok") { diaog, _ ->
                diaog.dismiss()
            }
            show()
        }
    }

    private fun showPosition(hetResult: String) {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("hetTFLite")
            setMessage("position:\n $hetResult")
            setNeutralButton("Ok") { diaog, _ ->
                diaog.dismiss()
            }
            show()
        }
    }
}
