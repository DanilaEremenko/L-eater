import GameMap.*
import Bot.*

import org.junit.AfterClass
import robot.*
import java.io.BufferedWriter
import java.io.FileWriter

import java.lang.StringBuilder

internal class BotsComparingTest {

    companion object {


        val outputAddress = "BattleOfBots/Battle.txt"
        val TIME_LIMIT = 10
        val divisor = "\n------------------------------------------\n"

        var resultBuilder = StringBuilder()
        var numberOfLeaterWin = 0
        var numberOfLestwaldWin = 0
        var numberOfDraw = 0
        var numberOfGames = 0
        var leaterSummaryScore = 0
        var lestwaldSummaryScore = 0
        var lestwaldWinsBuilder = StringBuilder("Lestwald wins :\n")
        var leaterWinsBuilder = StringBuilder("Leater wins :\n")
        var drawsBuilder = StringBuilder("Draws :\n")


        @AfterClass
        @JvmStatic
        fun reWriteOfLeaterResults() {
            resultBuilder.append("\nLeaterBot win = ").append(numberOfLeaterWin).append(" / ").append(numberOfGames)
            resultBuilder.append("\nLestwald win = ").append(numberOfLestwaldWin).append(" / ").append(numberOfGames)
            resultBuilder.append("\nDraw = ").append(numberOfDraw)
            resultBuilder.append(divisor).append(leaterWinsBuilder)
            resultBuilder.append(divisor).append(lestwaldWinsBuilder)
            resultBuilder.append(divisor).append(drawsBuilder)
            resultBuilder.append(divisor)
            resultBuilder.append("\nleater score = ").append(leaterSummaryScore)
            resultBuilder.append("\nlestwald score = ").append(lestwaldSummaryScore)
            val outputBuffer = BufferedWriter(FileWriter(outputAddress))
            outputBuffer.write(resultBuilder.toString())
            outputBuffer.flush()
            outputBuffer.close()

        }
    }

    fun testBotsOnMap(mapAddress: String, testName: String, humanScore: Int) {
        val gameMapLeater = GameMap.cutNormalMap(mapAddress)
        val leaterBot = LeaterBot(gameMapLeater)
        val thread = Thread(leaterBot)
        thread.start()

        try {
            Thread.sleep((TIME_LIMIT * 1000).toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        thread.stop()

        gameMapLeater.moveAllObjects(leaterBot.bestStepsString)


        //val gameboard = Gameboard(mapAddress)
        val gameMapLestwald = GameMap.cutNormalMap(mapAddress)
        val lestwaldBot = Robot(mapAddress)
        val path = lestwaldBot.go()
        //gameboard.act(path)
        gameMapLestwald.moveAllObjects(path)


        if (gameMapLeater.score < gameMapLestwald.score) {
            lestwaldWinsBuilder.append(testName).append("\tbenefit = ").append(Math.abs(gameMapLeater.score - gameMapLestwald.score)).append("\n")
            numberOfLestwaldWin++
        } else if (gameMapLeater.score > gameMapLestwald.score) {
            leaterWinsBuilder.append(testName).append("\tbenefit = ").append(Math.abs(gameMapLeater.score - gameMapLestwald.score)).append("\n")
            numberOfLeaterWin++
        } else {
            drawsBuilder.append(testName).append("\n")
            numberOfDraw++
        }

        leaterSummaryScore += gameMapLeater.score
        lestwaldSummaryScore += gameMapLestwald.score

        numberOfGames++

        resultBuilder.append(testName)
        resultBuilder.append("\nLestwaldScore = ").append(gameMapLestwald.score)
        resultBuilder.append("\nLeaterBotScore = ").append(gameMapLeater.score)
        resultBuilder.append(divisor)


    }

    //CONFIRMED
    @org.junit.Test
    fun beard0() {
        testBotsOnMap("maps/beard0.map", "beard0", 858)
    }

    //CONFIRMED
    @org.junit.Test
    fun beard1() {
        testBotsOnMap("maps/beard1.map", "beard1", 858)
    }

    //CONFIRMED
    @org.junit.Test
    fun beard2() {
        testBotsOnMap("maps/beard2.map", "beard2", 4497)
    }

    //CONFIRMED
    @org.junit.Test
    fun beard3() {
        testBotsOnMap("maps/beard3.map", "beard3", 1162)
    }

    //CONFIRMED
    @org.junit.Test
    fun beard4() {
        testBotsOnMap("maps/beard4.map", "beard4", 2013)
    }

    //CONFIRMED
    @org.junit.Test
    fun beard5() {
        testBotsOnMap("maps/beard5.map", "beard5", 657)
    }

    //CONFIRMED
    @org.junit.Test
    fun contest1() {
        testBotsOnMap("maps/contest1.map", "contest1", 210)
    }

    //CONFIRMED
    @org.junit.Test
    fun contest2() {
        testBotsOnMap("maps/contest2.map", "contest2", 280)
    }

    //CONFIRMED
    @org.junit.Test
    fun contest3() {
        testBotsOnMap("maps/contest3.map", "contest3", 275)
    }

    //CONFIRMED
    @org.junit.Test
    fun contest4() {
        testBotsOnMap("maps/contest4.map", "contest4", 575)
    }

    //CONFIRMED
    @org.junit.Test
    fun contest5() {
        testBotsOnMap("maps/contest5.map", "contest5", 1297)
    }

    //CONFIRMED
    @org.junit.Test
    //Не ест дальнюю лямбду, а надо бы
    fun contest6() {
        testBotsOnMap("maps/contest6.map", "contest6", 1177)
    }

    //CONFIRMED
    @org.junit.Test
    fun contest7() {
        testBotsOnMap("maps/contest7.map", "contest7", 869)
    }

    //Мало шагов
    @org.junit.Test
    fun contest8() {
        testBotsOnMap("maps/contest8.map", "contest8", 1952)
    }

    //Мало шагов
    @org.junit.Test
    fun contest9() {
        testBotsOnMap("maps/contest9.map", "contest9", 3088)
    }

    //Мало шагов
    @org.junit.Test
    fun contest10() {
        testBotsOnMap("maps/contest10.map", "contest10", 3594)
    }

    @org.junit.Test
    fun ems1() {
        testBotsOnMap("maps/ems1.map", "ems1", 334)
    }

    //TODO Шаг влево и было бы на лямбду лучше
    @org.junit.Test
    fun flood1() {
        testBotsOnMap("maps/flood1.map", "flood1", 937)
    }

    //CONFIRMED
    @org.junit.Test
    fun flood2() {
        testBotsOnMap("maps/flood2.map", "flood2", 281)
    }

    //CONFIRMED
    @org.junit.Test
    fun flood3() {
        testBotsOnMap("maps/flood3.map", "flood3", 1293)
    }

    //Мало шагов
    @org.junit.Test
    fun flood4() {
        testBotsOnMap("maps/flood4.map", "flood4", 826)
    }

    //TODO не хочет искать открытый лифт
    @org.junit.Test
    fun flood5() {
        testBotsOnMap("maps/flood5.map", "flood5", 567)
    }


    //CONFIRMED
    @org.junit.Test
    fun trampoline1() {
        testBotsOnMap("maps/trampoline1.map", "trampoline1", 407)
    }

    //CONFIRMED
    @org.junit.Test
    fun trampoline2() {
        testBotsOnMap("maps/trampoline2.map", "trampoline2", 1724)
    }

    //CONFIRMED
    @org.junit.Test
    fun trampoline3() {
        testBotsOnMap("maps/trampoline3.map", "trampoline3", 5467)
    }

    //CONFIRMED
    @org.junit.Test
    fun horock1() {
        testBotsOnMap("maps/horock1.map", "horock1", 735)
    }

    //CONFIRMED
    @org.junit.Test
    fun horock2() {
        testBotsOnMap("maps/horock2.map", "horock2", 735)
    }

    //CONFIRMED
    @org.junit.Test
    fun horock3() {
        testBotsOnMap("maps/horock3.map", "horock3", 2365)
    }


}