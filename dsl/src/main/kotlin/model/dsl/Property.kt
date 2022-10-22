package model.dsl

context(Model)
class Property private constructor(
    private val id: String,
    private val Name: String,
    val value: String,
) {
    constructor(id: String, Name: String, min: Number, max: Number, step: Number = 1, current: () -> Int) : this(
        id.replace(" ", ""), Name.replace(" ", "_"), id.replace(" ", "")
    ) {
        addProperty(
            """
            <S id="${id.replace(" ", "")}" Name="${Name.replace(" ", "_")}" min="$min" max="$max" step="$step" >${current.invoke()}</S>
        """.trimIndent()
        )
    }

    internal constructor(value: String) :
            this("?", "?", value)

    override fun equals(other: Any?): Boolean {
        if (other !is Property) return false
        if (other === this) return true
        return other.hashCode() == this.hashCode()
    }

    override fun hashCode(): Int {
        return 31 * (31 * id.hashCode() + value.hashCode()) + Name.hashCode()
    }

    override fun toString() = if (this == 0.toProperty()) {
        0.toString()
    } else {
        this.value
    }

    fun toRadians() = Property("${this.value}/180*3,1416")
    fun toAngle() = Property("${this.value}*180/3,1416")

    operator fun plus(other: Property) =
        Property("(${this.value}+${other.value})")
    operator fun plus(other: Number) =
        Property("(${this.value}+$other)")

    operator fun minus(other: Property) =
        Property("(${this.value}-${other.value})")
    operator fun minus(other: Number) =
        Property("(${this.value}-$other)")

    operator fun times(other: Property) =
        Property("${this.value}*${other.value}")
    operator fun times(other: Number) =
        Property("${this.value}*$other")

    operator fun div(other: Property) =
        Property("${this.value}/${other.value}")
    operator fun div(other: Number) =
        Property("${this.value}/$other")

    operator fun unaryMinus() =
        Property("(-${this.value})")
}

context(Model)
fun cos(x: Property) = Property("""Math.cos(${x.value})""")

context(Model)
fun sin(x: Property) = Property("""Math.sin(${x.value})""")

context(Model)
fun sqrt(x: Property) = Property("""Math.sqrt(${x.value})""")

context(Model)
operator fun Number.plus(other: Property) =
    Property("($this+${other.value})")

context(Model)
operator fun Number.minus(other: Property) =
    Property("($this-${other.value})")

context (Model)
operator fun Number.div(other: Property) =
    Property("$this/${other.value}")

context(Model)
operator fun Number.times(other: Property) =
    Property("$this*${other.value}")

context (Model)
fun Number.toProperty() = Property("$this")



