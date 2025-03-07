package task3Tests

import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import task3.abstracts.Hole
import task3.abstracts.HoleState
import task3.abstracts.TypeOfHole
import task3.factory.SpaceTimeFactory
import task3.model.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ModelsInteractionTest {

    @Test
    fun `opened Random Hole test`(){
        val spaceTimeFactory: SpaceTimeFactory = SpaceTimeFactory()
        val hole: Hole = spaceTimeFactory.createHole(TypeOfHole.RANDOM)
        assertEquals(HoleState.OPENED, hole.state)
    }

    @Test
    fun `transferred message to Galaxy with open Hole test`() = runBlocking{

        val alien1 = Alien(AlienType.NEUTRAL, WarState.PEACE)
        val alien2 = Alien(AlienType.AGGRESSIVE, WarState.TENSION)
        val alien3 = Alien(AlienType.PEACEFUL, WarState.PEACE)

        val galaxy = Galaxy(mutableListOf(alien1, alien2, alien3), Coordinates(230.5, 125.7))

        val spaceTimeFactory: SpaceTimeFactory = SpaceTimeFactory()
        val hole: Hole = spaceTimeFactory.createHole(TypeOfHole.RANDOM)

        val message: Message = Message("Вы отчислены!", TypeOfMessage.AGGRESSIVE)

        val channel = Channel<Message>(1)

        val transferredJob = async { hole.transferred(message, channel)}
        val receivedJob = async { galaxy.received(channel) }

        val transferredState: Boolean = transferredJob.await()
        val receivedState: Boolean = receivedJob.await()

        assertAll(
            { assertTrue { transferredState } },
            { assertTrue { receivedState } },
            { assertEquals(WarState.WAR, alien1.warState) },
            { assertEquals(WarState.WAR, alien2.warState) },
            { assertEquals(WarState.TENSION, alien3.warState) }
        )


    }

    @Test
    fun `transferred message to Galaxy with closed Hole test`(){

        val galaxy = Galaxy(Coordinates(230.5, 125.7))

        val hole: Hole = RandomHole()

        val message: Message = Message("Вы отчислены!", TypeOfMessage.AGGRESSIVE)

        val channel = Channel<Message>(1)

        assertThrows<IllegalStateException> {
            runBlocking { hole.transferred(message, channel)}

        }

    }
}