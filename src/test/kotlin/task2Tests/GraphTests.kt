package task2Tests

import org.junit.jupiter.api.*
import task2.Graph
import task2.RecursiveGraph
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class GraphTests {

    @DisplayName("Check the correct dfs order")
    @Test
    fun checkTheCorrectDfsOrderTest(){
        val graph1 = mapOf(
            0 to listOf(1, 2),
            1 to listOf(0, 3),
            2 to listOf(0, 4, 5),
            3 to listOf(1),
            4 to listOf(2),
            5 to listOf(2)
        )

        val graph2 = mapOf(
            0 to listOf(1, 4),
            1 to listOf(0, 2, 6),
            2 to listOf(1, 5, 6),
            3 to emptyList(),
            4 to listOf(0, 6),
            5 to listOf(2),
            6 to listOf(1, 2, 4, 7),
            7 to listOf(6)
        )

        assertAll(
            { assertEquals(listOf(4, 2, 0, 1, 3, 5), Graph(graph1).dfs(4)) },
            { assertEquals(listOf(1, 0, 2, 4, 5, 3), Graph(graph1).dfs(1)) },
            { assertEquals(listOf(0, 1, 3, 2, 4, 5), Graph(graph1).dfs(0)) },
            { assertEquals(listOf(2, 0, 1, 3, 4, 5), Graph(graph1).dfs(2)) }

        )

        assertAll(
            { assertEquals(listOf(2, 1, 0, 4, 6, 7, 5), Graph(graph2).dfs(2)) },
            { assertEquals(listOf(3), Graph(graph2).dfs(3)) },
            { assertEquals(listOf(0, 1, 2, 5, 6, 4, 7), Graph(graph2).dfs(0)) },
            { assertEquals(listOf(7, 6, 1, 0, 4, 2, 5), Graph(graph2).dfs(7)) }
        )

    }

    @DisplayName("Check for non-existent vertex")
    @Test
    fun checkNonExistentVertexTest(){
        val graph = mapOf(
            0 to listOf(1, 2),
            1 to listOf(0),
            2 to listOf(0)
        )

        val exception = assertThrows<IllegalArgumentException> {
            Graph(graph).dfs(3)
        }
        assertEquals("No such vertex exists", exception.message)
    }

    @DisplayName("Check the recursive graph")
    @Test
    fun checkRecursiveGraphTest(){
        val recursiveGraph = mapOf(
            0 to listOf(1,2),
            1 to listOf(0,2),
            2 to listOf(0, 1)
        )

        assertAll(
            { assertEquals(listOf(0, 1, 2), Graph(recursiveGraph).dfs(0)) },
            { assertEquals(listOf(1, 0, 2), Graph(recursiveGraph).dfs(1)) },
            { assertEquals(listOf(2, 0, 1), Graph(recursiveGraph).dfs(2)) }
        )
    }

    @DisplayName("Check the StackOverflowException")
    @Test
    fun checkStackOverflowTest(){
        val leaves = (1..9700).toMutableList()
        val graph = mutableMapOf(
            0 to leaves
        )
        for(leaf in leaves) {
            graph[leaf] = ((leaf+1)..9700).toMutableList()
        }

        assertDoesNotThrow { Graph(graph).dfs(0) }

        assertThrows<StackOverflowError> {
            RecursiveGraph(graph).dfs(0)
        }

    }
}