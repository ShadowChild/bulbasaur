
import co.uk.innoxium.bulbasaur.Theme
import csstype.*
import mui.icons.material.Add
import mui.icons.material.Check
import mui.icons.material.LightMode
import mui.icons.material.Remove
import mui.material.*
import mui.material.Size
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.dom.aria.ariaLabel

external interface MainAppProps : Props {
    var name: String
}

val MainApp = FC<MainAppProps> { _ ->
//    var name by useState(props.name)

    AppBar {

        position = AppBarPosition.fixed
        sx {

            gridArea = ident("header")
//            zIndex = integer(1_500)
        }

        Toolbar {

            sx {

                backgroundColor = Color(Theme.currentTheme.styling.backgroundColourHex)
            }

            Tooltip {

                title = ReactNode("Add Torrent")
                IconButton {

                    size = Size.small

                    Add()
                }
            }
            Tooltip {

                title = ReactNode("Remove Torrent")
                IconButton {

                    size = Size.small

                    Remove()
                }
            }

            // For some reason this doesn't work, boo
//            Checkbox {
//
//                ariaLabel = ""
//
//                icon = ReactNode("light_mode")
//                checkedIcon = ReactNode("e51c")
//                defaultChecked = true
//            }
        }
    }
}