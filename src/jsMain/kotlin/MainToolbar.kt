
import csstype.Color
import mui.icons.material.Add
import mui.icons.material.Remove
import mui.material.IconButton
import mui.material.Size
import mui.material.Toolbar
import mui.material.Tooltip
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode

external interface MainToolbarProps : Props {
    var name: String
}

val MainToolbar = FC<MainToolbarProps> { _ ->
//    var name by useState(props.name)
    Toolbar {

        sx {

            backgroundColor = Color("#7d7d7d")
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
    }
}