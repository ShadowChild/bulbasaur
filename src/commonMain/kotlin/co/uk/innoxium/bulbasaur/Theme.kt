package co.uk.innoxium.bulbasaur

enum class Theme(val styling: Styling) {

    LIGHT(LightStyling()), DARK(DarkStyling());

    companion object {
        var currentTheme: Theme = DARK
    }
}

interface Styling {

    val backgroundColourHex: String

}

class DarkStyling : Styling {

    override val backgroundColourHex: String
        get() = "#7d7d7d"

}

class LightStyling : Styling {

    override val backgroundColourHex: String
        get() = "#d1d1d1"


}