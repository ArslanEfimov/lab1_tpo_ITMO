package task3.model

enum class WarState{
    PEACE,
    TENSION,
    WAR

}
enum class AlienType{
    PEACEFUL,
    NEUTRAL,
    AGGRESSIVE
}

class Alien(_type: AlienType, _warState: WarState) {

    var warState: WarState = _warState
        private set

    var type: AlienType = _type
        private set

    fun reactToMessage(message: Message){
        when(Pair(message.type, type)){

            Pair(TypeOfMessage.KINDLY, AlienType.PEACEFUL) -> warState = WarState.PEACE
            Pair(TypeOfMessage.KINDLY, AlienType.NEUTRAL) -> warState = WarState.PEACE
            Pair(TypeOfMessage.KINDLY, AlienType.AGGRESSIVE) -> warState = WarState.TENSION

            Pair(TypeOfMessage.PASSIVE_AGGRESSIVE, AlienType.PEACEFUL) -> warState = WarState.PEACE
            Pair(TypeOfMessage.PASSIVE_AGGRESSIVE, AlienType.NEUTRAL) -> warState = WarState.TENSION
            Pair(TypeOfMessage.PASSIVE_AGGRESSIVE, AlienType.AGGRESSIVE) -> warState = WarState.WAR

            Pair(TypeOfMessage.AGGRESSIVE, AlienType.PEACEFUL) -> warState = WarState.TENSION
            Pair(TypeOfMessage.AGGRESSIVE, AlienType.NEUTRAL) -> warState = WarState.WAR
            Pair(TypeOfMessage.AGGRESSIVE, AlienType.AGGRESSIVE) -> warState = WarState.WAR

        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Alien

        if (type != other.type) return false
        if (warState != other.warState) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + warState.hashCode()
        return result
    }
}