
import co.uk.innoxium.bulbasaur.Theme
import csstype.*
import mui.icons.material.Add
import mui.icons.material.Remove
import mui.material.*
import mui.material.Size
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.dom.aria.ariaDescribedBy
import react.dom.aria.ariaLabelledBy
import react.useState

external interface MainAppProps : Props {
}
val MainApp = FC<MainAppProps> { properties ->

    val (removeTorrentErrorModalOpen, setRemoveTorrentErrorModalOpen) = useState(false)

    AppBar {

        position = AppBarPosition.fixed
        sx {

            gridArea = ident("header")
            zIndex = integer(1_500)
        }

        Toolbar {

            sx {

                backgroundColor = Color(Theme.currentTheme.styling.backgroundColourHex)
            }

            Tooltip {

                title = ReactNode("Add Torrent")
                Fab {

                    sx {

                        marginRight = 10.px
                    }
                    size = Size.small

                    Add()
                }
            }
            Tooltip {

                title = ReactNode("Remove Torrent")
                Fab {

                    size = Size.small

                    onClick = {
                        setRemoveTorrentErrorModalOpen(true)
                    }
                    Remove()
                }
            }
            Modal {

                open = removeTorrentErrorModalOpen
                onClose = {

                    setRemoveTorrentErrorModalOpen(false)
                }
                ariaLabelledBy = "modal-modal-title"
                ariaDescribedBy = "modal-modal-description"

                Box {

                    sx {

                        position = Position.absolute
                        top = 50.pct
                        left = 50.pct
                        transform = translate((-50).pct, (-50).pct)
                        width = 400.px
                        border = 2.px
                        borderStyle = LineStyle.solid
                        borderColor = Color("#000")
                        boxShadow = BoxShadow(24.px, 24.pct, Color("#000")) // TODO: Fix size of box shadow
                    }
                    Typography {

                        id = "modal-modal-title"
                        variant = TypographyVariant.h6
                        + "Alert!"
                    }
                    Typography {

                        id = "modal-modal-description"
                        variant = TypographyVariant.h6
                        sx {
                            marginTop = 2.px
                        }
                        + "You have not selected a torrent to remove!"
                    }
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