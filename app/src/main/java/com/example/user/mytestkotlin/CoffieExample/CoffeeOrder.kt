package com.example.user.mytestkotlin.CoffieExample

class CoffeeOrder(private val coffiePrice: Float) {
    private var mCoffeeCount: Int = 0
    private var mTotalPrice: Float = 0f;
    private var mCoffeePrice: Float = coffiePrice


    fun setCoffeeCount(count: Int) {
        if (count >= 0) {
            this.mCoffeeCount = count
        }
    }

    fun getCoffeeCount(): Int {
        return mCoffeeCount
    }

    fun incrementCoffeeCount() {
        mCoffeeCount++;
        calculateTotalPrice();
    }

    fun getTotalPrice(): Float {
        calculateTotalPrice();
        return mTotalPrice
    }

    fun decrementCoffeeCount() {
        if (mCoffeeCount > 0) {
            mCoffeeCount--
            calculateTotalPrice()
        }
    }

    private fun calculateTotalPrice() {
        mTotalPrice = mCoffeePrice * mCoffeeCount
    }
}