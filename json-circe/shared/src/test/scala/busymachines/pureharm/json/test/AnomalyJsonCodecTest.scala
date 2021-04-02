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

/** @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 11 Jun 2019
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
    IO {
      val failure: AnomalyBase = UnauthorizedAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      )

      val rawJson = failure.asJson.spaces2
      assert(
        rawJson ==
          """{
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
            |""".stripMargin.trim
      )

      val read = rawJson.unsafeDecodeAs[AnomalyBase]

      assert(read.id.name == failure.id.name, "id")
      assert(read.message == failure.message, "message")
      assert(read.parameters == failure.parameters, "parameters")
    }

  }

  test("... encode a ForbiddenAnomaly") {
    IO {
      val failure: AnomalyBase = ForbiddenAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      )

      val rawJson = failure.asJson.spaces2
      assert(
        rawJson ==
          """{
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
            |""".stripMargin.trim
      )

      val read = rawJson.unsafeDecodeAs[AnomalyBase]
      assert(read.id.name == failure.id.name, "id")
      assert(read.message == failure.message, "message")
      assert(read.parameters == failure.parameters, "parameters")
    }

  }

  test("... encode a DeniedAnomaly") {
    IO {
      val failure: AnomalyBase = DeniedAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      )

      val rawJson = failure.asJson.spaces2
      assert(
        rawJson ==
          """{
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
            |""".stripMargin.trim
      )

      val read = rawJson.unsafeDecodeAs[AnomalyBase]
      assert(read.id.name == failure.id.name, "id")
      assert(read.message == failure.message, "message")
      assert(read.parameters == failure.parameters, "parameters")
    }
  }

  test("... encode a InvalidInputAnomaly") {
    IO {
      val failure: AnomalyBase = InvalidInputAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      )

      val rawJson = failure.asJson.spaces2
      assert(
        rawJson ==
          """{
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
            |""".stripMargin.trim
      )

      val read = rawJson.unsafeDecodeAs[AnomalyBase]
      assert(read.id.name == failure.id.name, "id")
      assert(read.message == failure.message, "message")
      assert(read.parameters == failure.parameters, "parameters")
    }
  }

  test("... encode a ConflictAnomaly") {
    IO {
      val failure: AnomalyBase = ConflictAnomaly(
        "test message",
        Anomaly.Parameters(
          "one" -> "one",
          "two" -> List("one", "two"),
        ),
      )

      val rawJson = failure.asJson.spaces2
      assert(
        rawJson ==
          """{
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
            |""".stripMargin.trim
      )

      val read = rawJson.unsafeDecodeAs[AnomalyBase]
      assert(read.id.name == failure.id.name, "id")
      assert(read.message == failure.message, "message")
      assert(read.parameters == failure.parameters, "parameters")
    }

  }

  test("... encode Anomalies") {
    IO {
      val failure: AnomalyBase = Anomalies(
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
      )
      val rawJson = failure.asJson.spaces2
      assert(
        rawJson ==
          """
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
            |""".stripMargin.trim
      )

      val read = rawJson.unsafeDecodeAs[AnomalyBase]
      read match {
        case as: AnomaliesBase =>
          assert(read.id.name == failure.id.name, "id")
          assert(read.message == failure.message, "message")
          assert(read.parameters == failure.parameters, "parameters")
          assert(as.messages.length == 2, "# of messages")
        case _ => fail("should have decoded as AnomaliesBase")
      }
    }

  }

  test("... encode Throwable") {
    IO {
      val failure: Throwable = Anomalies(
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
      )
      val rawJson = failure.asJson.spaces2
      assert(
        rawJson ==
          """
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
            |""".stripMargin.trim
      )

      val anomaly = failure.asInstanceOf[Anomalies]
      val read    = rawJson.unsafeDecodeAs[Throwable]
      read match {
        case as: AnomaliesBase =>
          assert(as.id.name == anomaly.id.name, "id")
          assert(as.message == anomaly.message, "message")
          assert(as.parameters == anomaly.parameters, "parameters")
          assert(as.messages.length == 2, "# of messages")
        case _ => fail("should have decoded as AnomaliesBase")
      }
    }

  }

  test("... fail when decoding and empty Anomalies") {
    IO {
      val rawJson =
        """
          |{
          |  "id" : "test",
          |  "message" : "test message",
          |  "messages" : []
          |}
          |""".stripMargin.trim

      interceptFailure[Throwable](rawJson.decodeAs[AnomalyBase])
    }

  }
}
