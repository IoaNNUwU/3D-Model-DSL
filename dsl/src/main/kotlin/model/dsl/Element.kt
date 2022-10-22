package model.dsl

context(Model)
fun element(m: Material, kr: Int = 15, block: Element.() -> Unit) {
    val element = Element(m, kr)
    element.block()
    addElement(element.toString())
}

context(Model)
class Element(private val m: Material, private val kr: Int) {
    var x = 0.toProperty()
    var y = 0.toProperty()
    var z = 0.toProperty()
    var dx = 0.toProperty()
    var dy = 0.toProperty()
    var dz = 0.toProperty()
    var px = 0.toProperty()
    var py = 0.toProperty()
    var pz = 0.toProperty()

    override fun toString() = StringBuilder("""<E m= "$m" kr="$kr">""")
        .append(if (dx.value != "0") "\n    <S tip=\"dx\">$dx</S>" else "")
        .append(if (dy.value != "0") "\n    <S tip=\"dy\">$dy</S>" else "")
        .append(if (dz.value != "0") "\n    <S tip=\"dz\">$dz</S>" else "")
        .append(if (x.value != "0") "\n    <S tip=\"x\">$x</S>" else "")
        .append(if (y.value != "0") "\n    <S tip=\"y\">$y</S>" else "")
        .append(if (z.value != "0") "\n    <S tip=\"z\">$z</S>" else "")
        .append(if (px.value != "0") "\n    <S tip=\"px\">$px</S>" else "")
        .append(if (py.value != "0") "\n    <S tip=\"py\">$py</S>" else "")
        .append(if (pz.value != "0") "\n    <S tip=\"pz\">$pz</S>" else "")
        .append("\n  </E>")
        .toString()


}

