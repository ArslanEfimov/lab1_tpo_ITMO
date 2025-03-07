package task3.model

import kotlinx.coroutines.channels.Channel
import task3.abstracts.Hole
import task3.abstracts.HoleState
import task3.abstracts.TypeOfHole


class RandomHole(type: TypeOfHole = TypeOfHole.RANDOM, state: HoleState = HoleState.CLOSED) : Hole(type, state) {

    override suspend fun transferred(message: Message, channel: Channel<Message>): Boolean{
        if(state == HoleState.CLOSED) throw IllegalStateException("Дыра закрыта, передача сообщения невозможна")
        channel.send(message)
        return true
    }
}