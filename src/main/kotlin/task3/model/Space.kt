package task3.model

class Space(_galaxies: MutableList<Galaxy> = mutableListOf()) {

    var galaxies = _galaxies
        private set

    fun addGalaxy(galaxy: Galaxy): Boolean{
        return if(galaxies.contains(galaxy))
            false
        else {
            galaxies.add(galaxy)
            true
        }
    }

    fun findGalaxy(coordinates: Coordinates) : Galaxy?{
        return galaxies.find { it.coordinates == coordinates }
    }

    fun removeGalaxy(galaxy: Galaxy): Boolean{
        return galaxies.remove(galaxy)
    }

    fun removeAllGalaxies(){
        galaxies.clear()
    }
}