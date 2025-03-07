package task3Tests

import org.junit.jupiter.api.Test
import task3.model.Human
import task3.model.Message
import task3.model.TypeOfMessage
import kotlin.test.assertEquals

class HumanTest {

    @Test
    fun `speak Human test`(){
        val human: Human = Human("Артур")
        val humanMessage: Message = human.speak("А у меня, кажется, большие проблемы с образом жизни")
        val expectedMessage: Message = Message("А у меня, кажется, большие проблемы с образом жизни",
            TypeOfMessage.KINDLY)
        assertEquals(expectedMessage, humanMessage)
    }

}