package model.dsl

context(Model)
class Material(
    private val id: String,
    Name: String,
    Ml: String = "ДСП",
    kr: Int = 48,
    km: Int = 15,
    t: String = "Img/dsp.jpg",
    material: () -> Int,
) {
    init {
        addTexture("""
            <S id="$id" Name="$Name" Ml="$Ml" kr="$kr" km="$km" t="$t" >${material.invoke()}</S>
        """.trimIndent())
    }

    override fun toString() = id
}
