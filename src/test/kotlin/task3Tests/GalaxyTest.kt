package task3Tests

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import task3.model.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GalaxyTest {

    private lateinit var galaxy: Galaxy
    private lateinit var alien1: Alien
    private lateinit var alien2: Alien
    private lateinit var alien3: Alien

    @BeforeEach
    fun initGalaxy(){

        alien1 = Alien(AlienType.NEUTRAL, WarState.TENSION)
        alien2 = Alien(AlienType.AGGRESSIVE, WarState.WAR)
        alien3 = Alien(AlienType.PEACEFUL, WarState.PEACE)

        galaxy = Galaxy(mutableListOf(alien1, alien2, alien3), Coordinates(12.5, 11.2))
    }

    @Test
    fun `add alien to Galaxy test`(){

        val alien4 = Alien(AlienType.NEUTRAL, WarState.WAR)
        galaxy.addAlien(alien4)

        val aliens = listOf(alien1, alien2, alien3, Alien(AlienType.NEUTRAL, WarState.WAR))

        assertAll(
            { assertEquals(4, galaxy.aliens.size) },
            { assertTrue {galaxy.aliens.containsAll(aliens)} }
        )
    }

    @Test
    fun `remove alien from the Galaxy test`(){

        galaxy.removeAlien(alien3)

        assertAll(
            { assertEquals(2, galaxy.aliens.size) },
            { assertTrue {!galaxy.aliens.contains(alien3)} }
        )

    }

    @Test
    fun `remove non-existent Alien from the Galaxy test`(){

           assertTrue {!galaxy.removeAlien(Alien(AlienType.PEACEFUL, WarState.WAR)) }
    }

    @Test
    fun `remove identical Aliens from the Galaxy test`(){

        galaxy.addAlien(alien1)
        galaxy.addAlien(alien1)
        galaxy.addAlien(alien1)

        galaxy.removeIdenticalAliens(alien1)
        assertTrue { !galaxy.aliens.contains(alien1) }

    }

    @Test
    fun `try to remove non-identical Aliens from the Galaxy test`(){

        galaxy.addAlien(alien1)
        galaxy.addAlien(alien1)
        galaxy.addAlien(alien1)

        galaxy.removeIdenticalAliens(Alien(AlienType.PEACEFUL, WarState.WAR))

        assertTrue { galaxy.aliens.contains(alien1) }

    }

    @Test
    fun `clear all Aliens from the Galaxy test`(){
        galaxy.removeAllAliens()

        assertTrue { galaxy.aliens.isEmpty() }
    }

}