package test.createx.dogsimulator.data.models

data class SimulatorGridItem (
    val img: Int,
    val title: String,
    val soundUrl: String,
    val isSelected: Boolean = false
)