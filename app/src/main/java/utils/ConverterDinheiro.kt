package utils

import java.text.NumberFormat
import java.util.*

/**
 * Created by 16254868 on 24/05/2018.
 */
fun converterDinheiro(valor:String):String{
    val formato = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    return formato.format(java.lang.Double.parseDouble(valor))
}