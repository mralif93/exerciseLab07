package com.my.muhammadaliftajudin.exerciselab07

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.my.muhammadaliftajudin.exerciselab07.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {

    private lateinit var binding:ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val myBrowser: WebView = binding.myWeb
        myBrowser.webViewClient = WebViewClient()

        // Shows the URL
        myBrowser.loadUrl("https://www.androidatc.com")

        // set the web view to have a transparent border
        myBrowser.setBackgroundColor(Color.TRANSPARENT)

        // To enable javascript for the web browser
        myBrowser.settings.javaScriptEnabled = true

        // To load images automatically
        myBrowser.settings.loadsImagesAutomatically = true

        // Enable scrolling
        myBrowser.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
    }
}