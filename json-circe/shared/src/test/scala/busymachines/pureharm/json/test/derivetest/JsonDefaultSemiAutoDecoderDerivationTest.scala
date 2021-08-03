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

/** Here we test busymachines.pureharm.json.Decoder derivation
  *
  * See the Melon hierarchy
  *
  * @author
  *   Lorand Szakacs, https://github.com/lorandszakacs
  * @since 11
  *   Jun 2019
  */
final class JsonDefaultSemiAutoDecoderDerivationTest extends JsonTest {
  import melonsDefaultSemiAutoDecoders._

  //-----------------------------------------------------------------------------------------------

  test("... be able to deserialize anarchist melon (i.e. not part of any hierarchy)") {
    for {
      anarchistMelon <- AnarchistMelon(noGods = true, noMasters = true, noSuperTypes = true).pure[IO]
      rawJson =
        """
          |{
          |  "noGods" : true,
          |  "noMasters" : true,
          |  "noSuperTypes" : true
          |}
      """.stripMargin.trim

      read <- rawJson.decodeAs[AnarchistMelon].liftTo[IO]
    } yield assertEquals(obtained = read, expected = anarchistMelon)
  }

  //-----------------------------------------------------------------------------------------------

  test("... fail to compile when there is no explicitly defined decoder for a type down)the hierarchy") {
    IO {
      val errors = compileErrors(
        """
          val rawJson = "{}"
          rawJson.decodeAs[WinterMelon]
         """
      )
      assert(
        cond = errors.contains(
          """could not find implicit value for parameter decoder: io.circe.Decoder[busymachines.pureharm.json.test.WinterMelon]"""
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

  test("... be able to deserialize case class from hierarchy when it is referred to as its super-type") {
    for {
      winterMelon <- WinterMelon(fuzzy = true, weight = 45).pure[IO].widen[Melon]
      rawJson =
        """
          |{
          |  "fuzzy" : true,
          |  "weight" : 45,
          |  "_type" : "WinterMelon"
          |}
      """.stripMargin.trim

      read <- rawJson.decodeAs[Melon].liftTo[IO]
    } yield assertEquals(obtained = read, expected = winterMelon)
  }

  //-----------------------------------------------------------------------------------------------

  test("... be able to deserialize case objects of the hierarchy") {
    for {
      smallMelon <- SmallMelon.pure[IO].widen[Melon]
      rawJson =
        """
          |{
          |  "_type" : "SmallMelon"
          |}
      """.stripMargin.trim

      read <- rawJson.decodeAs[Melon].liftTo[IO]
    } yield assertEquals(obtained = read, expected = smallMelon)
  }

  //-----------------------------------------------------------------------------------------------

  test("... deserialize hierarchies of case objects as enums (i.e. plain strings)") {
    for {
      taste <- List(SweetTaste, SourTaste).pure[IO].widen[List[Taste]]
      rawJson =
        """
          |[
          |  "SweetTaste",
          |  "SourTaste"
          |]
      """.stripMargin.trim

      read <- rawJson.decodeAs[List[Taste]].liftTo[IO]
    } yield assertEquals(obtained = read, expected = taste)
  }

  //-----------------------------------------------------------------------------------------------

  test("... deserialize list of all case classes from the hierarchy") {
    for {
      winterMelon <- WinterMelon(fuzzy = true, weight = 45).pure[IO].widen[Melon]
      waterMelon  <- WaterMelon(seeds = true, weight = 90).pure[IO].widen[Melon]
      smallMelon  <- SmallMelon.pure[IO].widen[Melon]
      squareMelon <- SquareMelon(weight = 10, tastes = Seq(SourTaste, SweetTaste)).pure[IO].widen[Melon]
      melons      <- List[Melon](winterMelon, waterMelon, smallMelon, squareMelon).pure[IO]

      rawJson =
        """
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
        """.stripMargin.trim

      read <- rawJson.decodeAs[List[Melon]].liftTo[IO]
    } yield assertEquals(obtained = read, expected = melons)
  }

  //-----------------------------------------------------------------------------------------------

}
