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

package busymachines.pureharm.json.test.derivetest

import busymachines.pureharm.effects._
import busymachines.pureharm.effects.implicits._
import busymachines.pureharm.json.implicits._
import busymachines.pureharm.json.test._

/** Here we test busymachines.pureharm.json.Encoder derivation
  *
  * See the Melon hierarchy
  *
  * @author
  *   Lorand Szakacs, https://github.com/lorandszakacs
  * @since 11
  *   Jun 2019
  */
final class JsonDefaultSemiAutoEncoderDerivationTest extends JsonTest {

  import melonsDefaultSemiAutoEncoders._

  //-----------------------------------------------------------------------------------------------

  test("... be able to serialize anarchist melon (i.e. not part of any hierarchy)") {
    for {
      anarchistMelon <- AnarchistMelon(noGods = true, noMasters = true, noSuperTypes = true).pure[IO]
      rawJson = anarchistMelon.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """|
                      |{
                      |  "noGods" : true,
                      |  "noMasters" : true,
                      |  "noSuperTypes" : true
                      |}""".stripMargin.trim,
      )
    } yield ()
  }

  //-----------------------------------------------------------------------------------------------

  test("... fail to compile when there is no defined encoder for a type down)the hierarchy") {
    IO {
      val errors = compileErrors(
        """
          val winterMelon: WinterMelon = WinterMelon(fuzzy = true, weight = 45)
          winterMelon.asJson
         """
      )
      assert(
        cond = errors.contains(
          """could not find implicit value for parameter encoder: io.circe.Encoder[busymachines.pureharm.json.test.WinterMelon]"""
        ),
        clue = s"""|Expected a specific compiler error. But it was:
                   |
                   |$errors
                   |
                   |""".stripMargin,
      )
    }
  }

  //-----------------------------------------------------------------------------------------------

  test("... be able to serialize case class from hierarchy when it is referred to as its super-type") {
    for {
      winterMelon <- WinterMelon(fuzzy = true, weight = 45).pure[IO].widen[Melon]
      rawJson = winterMelon.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """
                     |{
                     |  "fuzzy" : true,
                     |  "weight" : 45,
                     |  "_type" : "WinterMelon"
                     |}
      """.stripMargin.trim,
      )
    } yield ()
  }

  //-----------------------------------------------------------------------------------------------

  test("... be able to serialize case objects of the hierarchy") {
    for {
      smallMelon <- SmallMelon.pure[IO].widen[Melon]
      rawJson = smallMelon.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """
                     |{
                     |  "_type" : "SmallMelon"
                     |}
      """.stripMargin.trim,
      )
    } yield ()
  }

  //-----------------------------------------------------------------------------------------------

  test("... serialize hierarchies of case objects as enums (i.e. plain strings)") {
    for {
      taste <- List(SweetTaste, SourTaste).pure[IO].widen[List[Taste]]
      rawJson = taste.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """
                     |[
                     |  "SweetTaste",
                     |  "SourTaste"
                     |]
      """.stripMargin.trim,
      )
    } yield ()
  }

  //-----------------------------------------------------------------------------------------------

  test("... serialize list of all case classes from the hierarchy") {
    for {
      winterMelon <- WinterMelon(fuzzy = true, weight = 45).pure[IO].widen[Melon]
      waterMelon  <- WaterMelon(seeds = true, weight = 90).pure[IO].widen[Melon]
      smallMelon  <- SmallMelon.pure[IO].widen[Melon]
      squareMelon <- SquareMelon(weight = 10, tastes = Seq(SourTaste, SweetTaste)).pure[IO].widen[Melon]
      melons = List[Melon](winterMelon, waterMelon, smallMelon, squareMelon)

      rawJson = melons.asJson.spaces2

      _ = assertEquals(
        obtained = rawJson,
        expected = """
                     |
                     |[
                     |  {
                     |    "fuzzy" : true,
                     |    "weight" : 45,
                     |    "_type" : "WinterMelon"
                     |  },
                     |  {
                     |    "seeds" : true,
                     |    "weight" : 90,
                     |    "_type" : "WaterMelon"
                     |  },
                     |  {
                     |    "_type" : "SmallMelon"
                     |  },
                     |  {
                     |    "weight" : 10,
                     |    "tastes" : [
                     |      "SourTaste",
                     |      "SweetTaste"
                     |    ],
                     |    "_type" : "SquareMelon"
                     |  }
                     |]
                     |
      """.stripMargin.trim,
      )
    } yield ()

  }
  //-----------------------------------------------------------------------------------------------
}
