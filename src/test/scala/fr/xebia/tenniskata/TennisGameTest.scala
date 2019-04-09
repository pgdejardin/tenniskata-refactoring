package fr.xebia.tenniskata

import org.scalatest.{FlatSpec, Matchers}

class TennisGameTest extends FlatSpec with Matchers {

  implicit val show: Show[TennisScore] = new ShowScore()
  implicit val showState: Show[GameState] = new ShowState()

  "A new game " should "return Love all" in {
    // Given / When
    val game = TennisGame("Boris Becker", "Bjørn Borg")

    // Then
    game.score should equal("Love all")
  }

  "PlayerOne" should "wins first ball" in {
    // Given / When
    val game = TennisGame("Boris Becker", "Bjørn Borg").playerOneScores

    // Then
    game.score should equal("Fifteen,Love")
  }

  it should "wins first three balls" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(3)),
      Player("Bjørn Borg", Score(0))
    )

    // Then
    game.score should equal("Forty,Love")
  }

  it should "wins game" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(4)),
      Player("Bjørn Borg", Score(0))
    )

    // Then
    game.score should equal("Boris Becker wins")
  }

  it should "have Advantage" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(5)),
      Player("Bjørn Borg", Score(4))
    )

    // Then
    game.score should equal("Advantage Boris Becker")
  }

  it should "wins after advantage" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(8)),
      Player("Bjørn Borg", Score(6))
    )

    // Then
    game.score should equal("Boris Becker wins")
  }

  "2 players scores" should "Fifteen all" in {
    // Given / When
    val game =
      TennisGame("Boris Becker", "Bjørn Borg").playerOneScores.playerTwoScores

    // Then
    game.score should equal("Fifteen all")
  }

  "PlayerTwo" should "wins first two balls" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(0)),
      Player("Bjørn Borg", Score(2))
    )

    // Then
    game.score should equal("Love,Thirty")
  }

  it should "wins game" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(1)),
      Player("Bjørn Borg", Score(4))
    )

    // Then
    game.score should equal("Bjørn Borg wins")
  }

  it should "have Advantage" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(4)),
      Player("Bjørn Borg", Score(5))
    )

    // Then
    game.score should equal("Advantage Bjørn Borg")
  }

  it should "wins" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(2)),
      Player("Bjørn Borg", Score(4))
    )

    // Then
    game.score should equal("Bjørn Borg wins")
  }

  it should "wins after advantage" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(6)),
      Player("Bjørn Borg", Score(8))
    )

    // Then
    game.score should equal("Bjørn Borg wins")
  }

  "Players" should "Deuce" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(3)),
      Player("Bjørn Borg", Score(3))
    )

    // Then
    game.score should equal("Deuce")
  }

  it should "Deuce 4" in {
    // Given / When
    val game = TennisGame(
      Player("Boris Becker", Score(4)),
      Player("Bjørn Borg", Score(4))
    )

    // Then
    game.score should equal("Deuce")
  }

}
