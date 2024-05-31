package com.my.muhammadaliftajudin.exerciselab07

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.my.muhammadaliftajudin.exerciselab07.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Component
        val snackBarBtn = binding.snackBarBtn;
        val alertDialogBtn = binding.alertDialogBtn;
        val notificationBtn = binding.notificationBtn;
        val webViewBtn = binding.webviewBtn;

        snackBarBtn.setOnClickListener {
            val snackBarMsg = Snackbar.make(
                it,
                "Your Email has been sent successfully",
                Snackbar.LENGTH_SHORT
            );
            snackBarMsg.show();
        }

        alertDialogBtn.setOnClickListener {
            val alertMsg = AlertDialog.Builder(this)
            alertMsg.setTitle("Save")
            alertMsg.setMessage("Are you sure you want to save your changes?")
            alertMsg.setPositiveButton("Yes") {
                _: DialogInterface, _:Int ->
                Snackbar.make(it, "Saved", Snackbar.LENGTH_SHORT).show()
            }
            alertMsg.setNegativeButton("No") {
                    _: DialogInterface, _:Int ->
                Snackbar.make(it, "Not Saved", Snackbar.LENGTH_SHORT).show()
            }
            alertMsg.setNeutralButton("Remind me later", null)
            alertMsg.show()
        }

        notificationBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create Notification Channel
                val channel_id = "channel_01"
                val channel_name = "notification"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(channel_id, channel_name, importance)
                mChannel.description = "Test Description"
                mChannel.enableLights(true)
                mChannel.lightColor = Color.RED
                mChannel.enableVibration(true)

                // Use Notification.Builder to generate your notification message
                val notification: Notification = Notification.Builder(this, channel_id,)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Android ATC Notification")
                    .setContentText("Check Android ATC New Course !!")
                    .build()

                // Register the channel with your android system
                val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

                if (mNotificationManager != null) {
                    mNotificationManager.createNotificationChannel(mChannel)
                    // show the notification
                    mNotificationManager.notify(1, notification)
                }
            } else {
                print("Not Display!")
            }
        }

        webViewBtn.setOnClickListener {
            intent = Intent(this, WebviewActivity::class.java)
            startActivity(intent)
        }
    }
}