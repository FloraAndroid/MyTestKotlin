package com.example.user.mytestkotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    private final var KEY_HISTORY_DATA: String = "KEY_HISTORY_DATA";


    private lateinit var  mLogHistory: LogHistory
    private  var mIsHistoryEmpty  : Boolean = true
    private lateinit var mHistoryTextView: TextView
    private lateinit var mSimpleDateFormatter:DateFormat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLogHistory = LogHistory()

        mHistoryTextView =  findViewById(R.id.history)
        mSimpleDateFormatter = SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault())


        if (savedInstanceState != null) {
            // We've got a past state, apply it to the UI.
            mLogHistory = savedInstanceState.getParcelable(KEY_HISTORY_DATA);
            for ( entry  in mLogHistory.getmData()) {
                appendEntryToView(entry.first, entry.second);
            }
        }
        val  btn_click_me:Button = findViewById(R.id.button) as Button
        // set on-click listener
        btn_click_me.setOnClickListener {
            // your code to perform when the user clicks on the button
            updateHistory(it);
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }


    }
    private fun  appendEntryToView(text:String ,timestamp:Long) {
      var date: Date  = Date(timestamp)
        // Add a newline if needed or clear the text view (to get rid of the hint).
        if (!mIsHistoryEmpty) {
            mHistoryTextView.append("\n");
        } else {
            mHistoryTextView.setText("");
        }
        // Add the representation of the new entry to the text view.
        mHistoryTextView.append(String.format("%s [%s]", text, mSimpleDateFormatter.format(date)))

        mIsHistoryEmpty = false;

    }



      private fun updateHistory(view : View)
    {
        var editText: EditText = view.getRootView().findViewById(R.id.editText)
        var textToAdd:CharSequence  = editText.getText();
        var timestamp: Long  = System.currentTimeMillis();

        // Show it back to the user.
        appendEntryToView(textToAdd.toString(), timestamp);

        // Update the history.
        mLogHistory.addEntry(textToAdd.toString(), timestamp);

        // Reset the EditText.
        editText.setText("")

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
