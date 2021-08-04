/*
 * Copyright 2019 BusyMachines
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package busymachines.pureharm.json.test

import busymachines.pureharm.anomaly._
import busymachines.pureharm.effects._
import busymachines.pureharm.effects.implicits._

/** @author
  *   Lorand Szakacs, https://github.com/lorandszakacs
  * @since 11
  *   Jun 2019
  */
final class AnomalyJsonCodecTest extends JsonTest {

  import busymachines.pureharm.internals.json._
  import busymachines.pureharm.json.implicits._
  import AnomalyJsonCodec._

  test("... encode a NotFoundAnomaly") {
    for {
      failure <- NotFoundAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      ).pure[IO].widen[AnomalyBase]

      rawJson = failure.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """{
                     |  "id" : "0",
                     |  "message" : "test message",
                     |  "parameters" : {
                     |    "one" : "one",
                     |    "two" : [
                     |      "one",
                     |      "two"
                     |    ]
                     |  }
                     |}
                     |""".stripMargin.trim,
      )

      read <- rawJson.decodeAs[AnomalyBase].liftTo[IO]
      _ = assertEquals(obtained = read.id, expected = failure.id, "id")
      _ = assertEquals(obtained = read.message, expected = failure.message, "message")
      _ = assertEquals(obtained = read.parameters, expected = failure.parameters, "parameters")
    } yield ()
  }

  test("... encode a UnauthorizedAnomaly") {
    for {
      failure <- UnauthorizedAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      ).pure[IO].widen[AnomalyBase]

      rawJson = failure.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """|{
                      |  "id" : "1",
                      |  "message" : "test message",
                      |  "parameters" : {
                      |    "one" : "one",
                      |    "two" : [
                      |      "one",
                      |      "two"
                      |    ]
                      |  }
                      |}
                      |""".stripMargin.trim,
      )

      read <- rawJson.decodeAs[AnomalyBase].liftTo[IO]
      _ = assertEquals(obtained = read.id, expected = failure.id, "id")
      _ = assertEquals(obtained = read.message, expected = failure.message, "message")
      _ = assertEquals(obtained = read.parameters, expected = failure.parameters, "parameters")
    } yield ()
  }

  test("... encode a ForbiddenAnomaly") {
    for {
      failure <- ForbiddenAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      ).pure[IO].widen[AnomalyBase]

      rawJson = failure.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """|{
                      |  "id" : "2",
                      |  "message" : "test message",
                      |  "parameters" : {
                      |    "one" : "one",
                      |    "two" : [
                      |      "one",
                      |      "two"
                      |    ]
                      |  }
                      |}
                      |""".stripMargin.trim,
      )

      read <- rawJson.decodeAs[AnomalyBase].liftTo[IO]
      _ = assertEquals(obtained = read.id, expected = failure.id, "id")
      _ = assertEquals(obtained = read.message, expected = failure.message, "message")
      _ = assertEquals(obtained = read.parameters, expected = failure.parameters, "parameters")
    } yield ()
  }

  test("... encode a DeniedAnomaly") {
    for {
      failure <- DeniedAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      ).pure[IO].widen[AnomalyBase]

      rawJson = failure.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """|{
                      |  "id" : "3",
                      |  "message" : "test message",
                      |  "parameters" : {
                      |    "one" : "one",
                      |    "two" : [
                      |      "one",
                      |      "two"
                      |    ]
                      |  }
                      |}
                      |""".stripMargin.trim,
      )

      read <- rawJson.decodeAs[AnomalyBase].liftTo[IO]
      _ = assertEquals(obtained = read.id, expected = failure.id, "id")
      _ = assertEquals(obtained = read.message, expected = failure.message, "message")
      _ = assertEquals(obtained = read.parameters, expected = failure.parameters, "parameters")
    } yield ()
  }

  test("... encode a InvalidInputAnomaly") {
    for {
      failure <- InvalidInputAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      ).pure[IO].widen[AnomalyBase]

      rawJson = failure.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """|{
                      |  "id" : "4",
                      |  "message" : "test message",
                      |  "parameters" : {
                      |    "one" : "one",
                      |    "two" : [
                      |      "one",
                      |      "two"
                      |    ]
                      |  }
                      |}
                      |""".stripMargin.trim,
      )

      read <- rawJson.decodeAs[AnomalyBase].liftTo[IO]
      _ = assertEquals(obtained = read.id, expected = failure.id, "id")
      _ = assertEquals(obtained = read.message, expected = failure.message, "message")
      _ = assertEquals(obtained = read.parameters, expected = failure.parameters, "parameters")
    } yield ()
  }

  test("... encode a ConflictAnomaly") {
    for {
      failure <- ConflictAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      ).pure[IO].widen[AnomalyBase]

      rawJson = failure.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """|{
                      |  "id" : "5",
                      |  "message" : "test message",
                      |  "parameters" : {
                      |    "one" : "one",
                      |    "two" : [
                      |      "one",
                      |      "two"
                      |    ]
                      |  }
                      |}
                      |""".stripMargin.trim,
      )

      read <- rawJson.decodeAs[AnomalyBase].liftTo[IO]
      _ = assertEquals(obtained = read.id, expected = failure.id, "id")
      _ = assertEquals(obtained = read.message, expected = failure.message, "message")
      _ = assertEquals(obtained = read.parameters, expected = failure.parameters, "parameters")
    } yield ()
  }

  test("... encode Anomalies") {
    for {
      failure <- Anomalies(
        AnomalyID("test"),
        "test message",
        NotFoundAnomaly(
          "one",
          Anomaly.Parameters(
            "3" -> "1",
            "4" -> List("1", "2"),
          ),
        ),
        NotFoundAnomaly(
          "two",
          Anomaly.Parameters(
            "5" -> "6",
            "6" -> List("6", "7"),
          ),
        ),
      ).pure[IO].widen[AnomalyBase]

      rawJson = failure.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """
                     |{
                     |  "id" : "test",
                     |  "message" : "test message",
                     |  "messages" : [
                     |    {
                     |      "id" : "0",
                     |      "message" : "one",
                     |      "parameters" : {
                     |        "3" : "1",
                     |        "4" : [
                     |          "1",
                     |          "2"
                     |        ]
                     |      }
                     |    },
                     |    {
                     |      "id" : "0",
                     |      "message" : "two",
                     |      "parameters" : {
                     |        "5" : "6",
                     |        "6" : [
                     |          "6",
                     |          "7"
                     |        ]
                     |      }
                     |    }
                     |  ]
                     |}
                     |""".stripMargin.trim,
      )

      read <- rawJson.decodeAs[AnomalyBase].liftTo[IO]
      _ = assert(read.isInstanceOf[AnomaliesBase], clue = "should have decoded as AnomaliesBase")
      _ = assertEquals(obtained = read.id, expected = failure.id, "id")
      _ = assertEquals(obtained = read.message, expected = failure.message, "message")
      _ = assertEquals(obtained = read.parameters, expected = failure.parameters, "parameters")
      _ = read match {
        case as: AnomaliesBase =>
          assert(as.messages.length == 2, "# of messages")
        case _ => fail("should have decoded as AnomaliesBase")
      }
    } yield ()
  }

  test("... encode Throwable") {
    for {
      failure <- Anomalies(
        AnomalyID("test"),
        "test message",
        NotFoundAnomaly(
          "one",
          Anomaly.Parameters(
            "3" -> "1",
            "4" -> List("1", "2"),
          ),
        ),
        NotFoundAnomaly(
          "two",
          Anomaly.Parameters(
            "5" -> "6",
            "6" -> List("6", "7"),
          ),
        ),
      ).pure[IO].widen[Throwable]

      rawJson = failure.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """
                     |{
                     |  "id" : "test",
                     |  "message" : "test message",
                     |  "messages" : [
                     |    {
                     |      "id" : "0",
                     |      "message" : "one",
                     |      "parameters" : {
                     |        "3" : "1",
                     |        "4" : [
                     |          "1",
                     |          "2"
                     |        ]
                     |      }
                     |    },
                     |    {
                     |      "id" : "0",
                     |      "message" : "two",
                     |      "parameters" : {
                     |        "5" : "6",
                     |        "6" : [
                     |          "6",
                     |          "7"
                     |        ]
                     |      }
                     |    }
                     |  ]
                     |}
                     |""".stripMargin.trim,
      )

      read <- rawJson.decodeAs[AnomalyBase].liftTo[IO]
      _ = read match {
        case as: AnomaliesBase =>
          val anomaly = failure.asInstanceOf[Anomalies]
          assert(as.id.name == anomaly.id.name, "id")
          assert(as.message == anomaly.message, "message")
          assert(as.parameters == anomaly.parameters, "parameters")
          assert(as.messages.length == 2, "# of messages")
        case _ => fail("should have decoded as AnomaliesBase")
      }
    } yield ()
  }

  test("... fail when decoding and empty Anomalies") {
    for {
      rawJson <- """
                   |{
                   |  "id" : "test",
                   |  "message" : "test message",
                   |  "messages" : []
                   |}
                   |""".stripMargin.trim.pure[IO]
      _ = interceptFailure[Throwable](rawJson.decodeAs[AnomalyBase])
    } yield ()
  }
}
