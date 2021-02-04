package eu.tutorials.gingerbread.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val GINGER_BREAD_PRICE = 2.0
private const val SAME_DAY_PRICE = 3.0

class GingerBreadViewModel : ViewModel() {
    private var _amount = MutableLiveData<Int>()
    val amount: LiveData<Int> get() = _amount

    private var _flavour = MutableLiveData<String>()
    val flavour: LiveData<String> get() = _flavour

    val dateOptions = getPickupOptions()

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    private var _total = MutableLiveData<Double>()
    val total: LiveData<String> = Transformations.map(_total) { NumberFormat.getCurrencyInstance().format(it) }

    init {
        resetOrder()
    }

    fun resetOrder() {
        _amount.value = 0
        _total.value = 0.0
        _flavour.value = "Vanilla"
        _date.value = dateOptions[0]
    }

    fun setAmount(inAmount: Int) {
        _amount.value = inAmount
        setTotal()
    }

    fun setFlavour(inFlavour: String) {
        _flavour.value = inFlavour
    }

    fun setDate(position: Int) {
        _date.value = dateOptions[position]
        setTotal()
    }

    private fun setTotal() {
        var calculatedPrice = (amount.value ?: 0) * GINGER_BREAD_PRICE
        if (dateOptions[0] == _date.value) { calculatedPrice += SAME_DAY_PRICE }
        _total.value = calculatedPrice
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }
}