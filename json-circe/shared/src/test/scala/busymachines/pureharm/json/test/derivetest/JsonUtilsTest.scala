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
import busymachines.pureharm.json._
import busymachines.pureharm.internals.json.{JsonDecoding, JsonParsing}
import busymachines.pureharm.json.test._

/** @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 11 Jun 2019
  */
final class JsonUtilsTest extends JsonTest {

  import melonsDefaultSemiAutoCodecs._

  //-----------------------------------------------------------------------------------------------

  test("JsonParsing.safe - parse correct json") {
    for {
      rawJson <-
        """
          |{
          |  "noGods" : true,
          |  "noMasters" : true,
          |  "noSuperTypes" : true
          |}
      """.stripMargin.pure[IO]
      _       <- JsonParsing.parseString(rawJson).liftTo[IO]
    } yield ()

  }

  //-----------------------------------------------------------------------------------------------

  test("JsonParsing.safe - fail on incorrect json") {
    for {
      rawJson <-
        """
          |{
          |  "noGods" : true
          |  "noMasters" : true,
          |  "noSuperTypes" : true
          |}
      """.stripMargin.pure[IO]
      _ = interceptFailure[JsonParsingAnomaly] {
        JsonParsing.parseString(rawJson)
      }
    } yield ()
  }

  //-----------------------------------------------------------------------------------------------

  test("JsonParsing.unsafe - parse correct json") {
    for {
      rawJson <-
        """
          |{
          |  "noGods" : true,
          |  "noMasters" : true,
          |  "noSuperTypes" : true
          |}
      """.stripMargin.pure[IO]
      _       <- IO(JsonParsing.unsafeParseString(rawJson))
    } yield ()
  }

  //-----------------------------------------------------------------------------------------------

  test("JsonParsing.unsafe - throw exception on incorrect json") {
    for {
      rawJson <-
        """
          |{
          |  "noGods" : true
          |  "noMasters" : true,
          |  "noSuperTypes" : true
          |}
      """.stripMargin.pure[IO]
      _ = intercept[JsonParsingAnomaly](JsonParsing.unsafeParseString(rawJson))
    } yield ()
  }

  //-----------------------------------------------------------------------------------------------

  test("JsonDecoding.safe - correctly decode when JSON, and representation are correct") {
    for {
      rawJson <-
        """
          |{
          |  "noGods" : true,
          |  "noMasters" : true,
          |  "noSuperTypes" : true
          |}
      """.stripMargin.pure[IO]

      am <- JsonDecoding.decodeAs[AnarchistMelon](rawJson).liftTo[IO]
      _ = assertEquals(
        obtained = am,
        expected = AnarchistMelon(noGods = true, noMasters = true, noSuperTypes = true),
      )
    } yield ()
  }

  //-----------------------------------------------------------------------------------------------

  test("JsonDecoding.safe - fail with parsing error when JSON has syntax errors") {
    for {
      rawJson <-
        """
          |{
          |  "noGods" : true
          |  "noMasters" : true,
          |  "noSuperTypes" : true
          |}
      """.stripMargin.pure[IO]

      _ = interceptFailure[JsonParsingAnomaly](JsonDecoding.decodeAs[AnarchistMelon](rawJson))
    } yield ()
  }

  //-----------------------------------------------------------------------------------------------

  test("JsonDecoding.safe - fail with decoding error when JSON is syntactically correct, but encoding is wrong") {
    for {
      rawJson <-
        """
          |{
          |  "noMasters" : true,
          |  "noSuperTypes" : true
          |}
      """.stripMargin.pure[IO]

      _ = interceptFailure[JsonDecodingAnomaly](JsonDecoding.decodeAs[AnarchistMelon](rawJson))
    } yield ()

  }

  //-----------------------------------------------------------------------------------------------

}
