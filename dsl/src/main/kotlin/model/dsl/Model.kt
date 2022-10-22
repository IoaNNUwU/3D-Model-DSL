package model.dsl

import java.io.File

@ModelMaker
fun model(id: String, Name: String, block: Model.() -> Unit) {
    val model = Model(id, Name)
    model.block()
    model.build()
}

class Model(private val id: String, private val name: String) {
    private val properties = ArrayList<String>()
    private val textures = ArrayList<String>()
    private val elements = ArrayList<String>()

    fun addElement(element: String) = elements.add(element)
    fun addTexture(material: String) = textures.add(material)
    fun addProperty(property: String) = properties.add(property)

    fun build() {
        val text =
            StringBuilder("""<?xml version="1.0" encoding="UTF-8"?>""")
                .append("\n\n")
                .append("""<M Name="$name">""".trimIndent())
                .append('\n')

        textures.forEach { text.append("\n  $it") }
        text.append("\n")
        properties.forEach { text.append("\n  $it") }
        text.append("\n")
        elements.forEach { text.append("\n  $it")}

        text.append("\n</M>")

        val dirName = "model/models"
        File(dirName).mkdir()
        val file = File("$dirName/$id.xml")
        file.createNewFile()

        file.writeText(text.toString())
    }
}