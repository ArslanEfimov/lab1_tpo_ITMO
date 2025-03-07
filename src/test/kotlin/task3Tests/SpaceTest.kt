package task3Tests

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import task3.model.*
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SpaceTest {

    private lateinit var space: Space
    private lateinit var galaxy1: Galaxy
    private lateinit var galaxy2: Galaxy
    private lateinit var galaxy3: Galaxy



    @BeforeEach
    fun initSpace(){
        galaxy1 = Galaxy(Coordinates(230.5, 125.7))
        galaxy2 = Galaxy(Coordinates(130.5, 525.7))
        galaxy3 = Galaxy(Coordinates(30.5, 825.7))
        space = Space(mutableListOf(galaxy1, galaxy2, galaxy3))
    }

    @Test
    fun `add galaxy to Space test`(){
        val galaxy4 = Galaxy(Coordinates(765.8, 743.9))
        space.addGalaxy(galaxy4)
        val galaxiesList = listOf(galaxy1, galaxy2, galaxy3, galaxy4)

        assertAll(
            { assertEquals(4, space.galaxies.size) },
            { assertTrue { space.galaxies.containsAll(galaxiesList) } }
        )
    }

    @Test
    fun `add exist galaxy to Space test`(){
        assertTrue{!space.addGalaxy(galaxy1)}
    }

    @Test
    fun `remove galaxy from the Space test`(){
        space.removeGalaxy(galaxy2)
        assertTrue(!space.galaxies.contains(galaxy2))
    }

    @Test
    fun `remove non-exist galaxy from the Space test`(){
        val galaxy4 = Galaxy(Coordinates(765.8, 743.9))
        assertTrue { !space.removeGalaxy(galaxy4) }
    }

    @Test
    fun `clear all Galaxies from the Space test`(){
        space.removeAllGalaxies()
        assertTrue { space.galaxies.isEmpty() }
    }

    @Test
    fun `find exist galaxy from the Space test`(){
        assertEquals(galaxy1, space.findGalaxy(Coordinates(230.5, 125.7)))
    }

    @Test
    fun `try to find non-exist galaxy from the Space test`(){
        assertNull(space.findGalaxy(Coordinates(0.0, 0.0)))
    }

}