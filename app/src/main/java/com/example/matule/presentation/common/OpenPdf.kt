package com.example.matule.presentation.common

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri

fun Context.openPdf(pdf: Int) {
    try {
        val pdfUri = "android.resource://${packageName}/${pdf}".toUri()
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(pdfUri, "application/pdf")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        startActivity(intent)
    }catch (e: Exception) {
        Toast.makeText(this, "Скачайте приложение для просмотра PDF", Toast.LENGTH_SHORT).show()
    }
}