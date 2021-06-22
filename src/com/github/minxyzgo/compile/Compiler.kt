package com.github.minxyzgo.compile

import mindustry.Vars
import mindustry.mod.Mod
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class Compiler : Mod() {
    val classLoader = this::class.java.classLoader
    val engineManager = ScriptEngineManager(classLoader)

    override fun init() {

        Vars.mods.eachEnabled {
            val all = it.root.child("scripts").findAll { f -> f.extEquals("kts") }
            val main = it.root.child("scripts").child("main.kts")
            if(main.exists() && !main.isDirectory) {
                val ktsEngine: ScriptEngine = engineManager.getEngineByExtension("kts")
               
                try {
                    ktsEngine.eval(main.readString())
                } catch(e: ScriptException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun printList() {
        val manager = ScriptEngineManager()
        for(factory in manager.engineFactories) {
            println("""
                name: ${factory.engineName}
                version: ${factory.engineVersion}
                language: ${factory.languageName}
                languageVersion: ${factory.languageVersion}
            """.trimIndent())
        }
    }
}