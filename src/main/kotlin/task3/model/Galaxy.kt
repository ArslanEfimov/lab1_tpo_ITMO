package task3.model

import kotlinx.coroutines.channels.Channel

data class Galaxy(val coordinates: Coordinates) {

    var aliens: MutableList<Alien> = mutableListOf()
        private set

    constructor(_aliens: MutableList<Alien>, coordinates: Coordinates): this(coordinates){
        aliens = _aliens
    }

    fun addAlien(alien: Alien){
        aliens.add(alien)
    }

    fun removeAlien(alien: Alien): Boolean{
        return aliens.remove(alien)
    }

    fun removeIdenticalAliens(alien: Alien): Boolean{
        val iterator = aliens.iterator()
        val collectionsSize = aliens.size
        while (iterator.hasNext()){
            val alienIt: Alien = iterator.next()
            if(alienIt == alien){
                iterator.remove()
            }
        }
        return collectionsSize > aliens.size
    }

    fun removeAllAliens(){
        aliens.clear()
    }




    suspend fun received(channel: Channel<Message>): Boolean{
        val message = channel.receive()
        if(aliens.isNotEmpty()) {
            for (alien in aliens) {
                alien.reactToMessage(message)
            }
        }
        return true

    }
}