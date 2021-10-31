import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

object JsonUtils {

    fun toJsonString(o: Any?): String = Gson().toJson(o)

    fun <T> fromJsonString(json: String?, clazz: Class<T>): T = Gson().fromJson(json ?: "", clazz)

    fun toJsonArrayString(list: List<*>?): String {
        val listType = object : TypeToken<List<*>>() {}.type
        val gson = Gson()
        return gson.toJson(list, listType)
    }

    fun <T> fromJsonArrayString(json: String?, clazz: Class<Array<T>>): MutableList<T> {
        val arr = Gson().fromJson(json ?: "", clazz)
        return ArrayList(listOf(*arr))
    }
}