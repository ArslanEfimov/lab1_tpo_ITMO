package task3.factory

import task3.abstracts.Hole
import task3.abstracts.HoleState
import task3.abstracts.TypeOfHole
import task3.model.RandomHole

class SpaceTimeFactory {

    fun createHole(type: TypeOfHole): Hole{
        return when(type){
            TypeOfHole.RANDOM -> RandomHole(state = HoleState.OPENED)
        }
    }
}