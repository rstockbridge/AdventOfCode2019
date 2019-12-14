class Wire {
    private val points = mutableListOf<GridPoint2d>()

    fun getPoints(): List<GridPoint2d> {
        return points.toList()
    }

    fun addPoint(point: GridPoint2d) {
        points.add(point);
    }
}
