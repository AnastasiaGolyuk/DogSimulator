package test.createx.dogsimulator.data.models

data class GridItem(
    val img: Int,
    val title: String,
    val soundUrl: String,
    var isSelected: Boolean = false
)