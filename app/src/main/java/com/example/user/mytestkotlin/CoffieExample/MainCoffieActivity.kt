package com.example.user.mytestkotlin.CoffieExample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.user.mytestkotlin.R

class MainActivityCoffie : AppCompatActivity(), View.OnClickListener {
    private val COFFEE_COUNT: String = "coffee_count"
    private val DEFAULT_COFFEE_PRICE: Float = 5.0f
    private lateinit var mCoffeePrice: TextView
    private lateinit var mTotalPrice: TextView
    private lateinit var mCoffeeCount: TextView
    private lateinit var mOrder: CoffeeOrder
    private lateinit var incrementCoffieBtn:Button
    private lateinit var coffeeDecrement:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coffie_activity_main)
        mCoffeePrice=findViewById(R.id.coffee_price)
        mTotalPrice=findViewById(R.id.total_price)
        mCoffeeCount=findViewById(R.id.coffee_count)

        mCoffeePrice.setText(String.format(getString(R.string.coffee_price),DEFAULT_COFFEE_PRICE))
        mTotalPrice.setText(String.format(getString(R.string.total_price),0.0f))


        incrementCoffieBtn= findViewById(R.id.coffee_increment)
        coffeeDecrement = findViewById(R.id.coffee_decrement)
        incrementCoffieBtn.setOnClickListener(this);
        coffeeDecrement.setOnClickListener(this);

        mOrder =  CoffeeOrder(DEFAULT_COFFEE_PRICE)

    }





    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        mOrder.setCoffeeCount(savedInstanceState!!.getInt(COFFEE_COUNT));
        updateCoffeeCount();
        updateTotalPrice();
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putInt(COFFEE_COUNT,mOrder.getCoffeeCount());
    }
    override fun onClick(v: View?) {
     //   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        if (v != null) {
            when(v.id) {
                R.id.coffee_increment -> {
                    mOrder.incrementCoffeeCount();
                    updateCoffeeCount();
                    updateTotalPrice();
                }
                R.id.coffee_decrement -> {
                    mOrder.decrementCoffeeCount();
                    updateCoffeeCount();
                    updateTotalPrice();
                    /* you can omit the braces if there is only a single expression */}
            }
        }
    }


    private fun updateCoffeeCount()
    {
        mCoffeeCount.setText(mOrder.getCoffeeCount().toString());
    }
    private fun updateTotalPrice()
    {
        mTotalPrice.setText(String.format(getString(R.string.total_price),mOrder.getTotalPrice()));
    }

}