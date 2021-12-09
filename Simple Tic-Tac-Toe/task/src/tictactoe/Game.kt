package tictactoe

class Game {
    private var itemList = mutableListOf<ItemGame>()
    private var xORo = 1
    var indexX0 = 'X'
    private lateinit var moveCoordinates: MutableList<String>
    fun menu() {
        createItem()
        printXOX()
        moveMenu()
    }

    private fun createItem() {
        val coordinatesList = listOf("1 1", "1 2", "1 3", "2 1", "2 2", "2 3", "3 1", "3 2", "3 3")
        for (i in 0..8) {
            itemList.add(ItemGame(i, '_', coordinatesList[i], false))
        }

    }

    private fun moveMenu() {
        while (true) {
            print("Enter the coordinates: ")
            moveCoordinates = readLine()!!.split(" ".toRegex()).toMutableList()
            when {
                moveCoordinates.isEmpty() -> continue
                else -> errorInput()
            }
        }
    }

    private fun errorInput() {
        val error1 = Regex("[1-3]\\s[1-3]")
        val error2 = Regex("[a-zA-Z]+")
        if (error1.matches(moveCoordinates.joinToString(" "))) {
            movePlayer()
        } else
            if (error2.matches(moveCoordinates.joinToString(" "))) {
                println("You should enter numbers!")
                moveMenu()
            } else {
                println("Coordinates should be from 1 to 3!")
                moveMenu()
            }
    }

    private fun movePlayer() {
        val moveItemList = itemList.filter { it.coordinates == moveCoordinates.joinToString(" ") }
        if (moveItemList[0].status == true) {
            println("This cell is occupied! Choose another one!")
            moveMenu()
        } else {
            if (xORo % 2 == 0) indexX0 = 'O'
            else indexX0 = 'X'
            itemList[moveItemList[0].adress].valueItem = indexX0
            itemList[moveItemList[0].adress].status = true
            xORo += 1
            printXOX()
            gameWin()
        }
        moveMenu()
    }

    private fun gameWin() {
        var inputIndex = ""
        for (i in itemList) { inputIndex += i.valueItem }
        var index = 0
        var indexA = '_'
        var q = 0
        var j = 0
        var k = 0
        for (s in inputIndex) {
            if ((s == 'x') || (s == 'X')) q += 1
            else
                if ((s == '0') || (s == 'o')  || (s == 'O')) j += 1
                else k +=1
        }
        if (q + j < 5) moveMenu()
        else {
            val winList = mutableListOf("012", "345", "678", "036", "147", "258", "246", "048")
            for (i in winList) {
                val a = i.split("").toMutableList()
                if (itemList[a[1].toInt()].valueItem == itemList[a[2].toInt()].valueItem &&
                    itemList[a[1].toInt()].valueItem == itemList[a[3].toInt()].valueItem) {
                    index += 1
                    indexA = itemList[a[1].toInt()].valueItem
                    if ((index == 1 && indexA == 'X') || (index == 1 && indexA == 'O')) {
                        println("$indexA wins")
                        System.exit(-1)
                    } else moveMenu()
                }
            }
        }

        if (index >= 2) {
            println("Impossible")
            System.exit(-1)
        } else
            if ((index == 0) && (q + j) == 9) {
                println("Draw")
                System.exit(-1)
            }
    }
    private fun printXOX() {
        println("---------")
        println("| ${itemList[0].valueItem} ${itemList[1].valueItem} ${itemList[2].valueItem} |\n" +
                "| ${itemList[3].valueItem} ${itemList[4].valueItem} ${itemList[5].valueItem} |\n" +
                "| ${itemList[6].valueItem} ${itemList[7].valueItem} ${itemList[8].valueItem} |")
        println("---------")
    }
}