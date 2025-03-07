package task3Tests

import org.junit.jupiter.api.Test
import task3.model.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class AlienTest {

    @Test
    fun `react Alien to Message test`(){
        val alien = Alien(AlienType.NEUTRAL, WarState.PEACE)
        alien.reactToMessage(Message("Не пора бы вам писать ПСЖ?", TypeOfMessage.PASSIVE_AGGRESSIVE))
        assertEquals(WarState.TENSION, alien.warState)

    }

    @Test
    fun `hashCode should be equal for equal objects test`() {
        val alien1 = Alien(AlienType.NEUTRAL, WarState.TENSION)
        val alien2 = Alien(AlienType.NEUTRAL, WarState.TENSION)

        assertEquals(alien1, alien2)
        assertEquals(alien1.hashCode(), alien2.hashCode())
    }

    @Test
    fun `hashCode should be different for different objects test`() {
        val alien1 = Alien(AlienType.NEUTRAL, WarState.TENSION)
        val alien2 = Alien(AlienType.AGGRESSIVE, WarState.TENSION)

        assertNotEquals(alien1, alien2)
        assertNotEquals(alien1.hashCode(), alien2.hashCode())
    }

    @Test
    fun `hashCode should be consistent test`() {
        val alien = Alien(AlienType.PEACEFUL, WarState.PEACE)
        val hash1 = alien.hashCode()
        val hash2 = alien.hashCode()

        assertEquals(hash1, hash2)
    }
}
