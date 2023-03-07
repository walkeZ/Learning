package com.example.tflitedemo.tflite

/**
 * @author walker
 * @date 2023/2/17
 * @description 识别
 */
data class Recognition(
    var type: Int = -1,
    var title: String = "",
    var confidence: Float = 0F
) {
    override fun toString(): String {
        return "type = $type, Title = $title, Confidence = $confidence)"
    }
}