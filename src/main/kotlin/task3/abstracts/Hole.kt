package task3.abstracts

import kotlinx.coroutines.channels.Channel
import task3.model.Coordinates
import task3.model.Message
import task3.model.Space



enum class TypeOfHole{
    RANDOM
//    NOT_RANDOM
}

enum class HoleState{
    OPENED,
    CLOSED
}

abstract class Hole(val type: TypeOfHole, val state: HoleState = HoleState.CLOSED) {

   abstract suspend fun transferred(message: Message, channel: Channel<Message>): Boolean

}