package task3.interfaces

import task3.model.Message
import task3.model.TypeOfMessage

interface Speakable {
    fun speak(content: String, type: TypeOfMessage = TypeOfMessage.KINDLY): Message
}