import javax.script.ScriptEngineManager

fun main() {
   val classLoader = Thread.currentThread().contextClassLoader
   val engineManager = ScriptEngineManager(classLoader)
   engineManager.getEngineByExtension("kts").eval("val o = 0")
}