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

import busymachines.pureharm.anomaly._
import busymachines.pureharm.effects._
import busymachines.pureharm.effects.implicits._
import busymachines.pureharm.json.implicits._
import busymachines.pureharm.json.test._

/** Here we test busymachines.pureharm.json.Decoder derivation
  *
  * See the Melon hierarchy
  *
  * @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 11 Jun 2019
  */
final class JsonDefaultSemiAutoPhantomMelon extends JsonTest {

  import melonsDefaultSemiAutoCodecs._

  //-----------------------------------------------------------------------------------------------

  test("... encode + decode sproutMelon") {
    for {
      originalPhantomMelon <- PhantomMelon(
        weight        = Weight(42),
        refinedWeight = RefinedWeight[Try](42).get,
        weights       = Weights(List(1, 2)),
        weightsSet    = WeigthsSet(Set(3, 4)),
        duo           = MelonDuo((5, "duo")),
        trio          = MelonTrio((6, "trio", List(1, 2, 3))),
      ).pure[IO].widen[Melon]

      encoded = originalPhantomMelon.asJson
      decoded <- encoded.decodeAs[Melon].liftTo[IO]
    } yield assertEquals(obtained = decoded, expected = originalPhantomMelon)
  }

  test("... encode + fail on decode of wrong safePhantomMelon") {
    for {
      originalPhantomMelon: Melon <- PhantomMelon(
        weight        = Weight(42),
        // this works because sprouts have the same runtime representation as the base type
        refinedWeight = (-1: Int).asInstanceOf[RefinedWeight],
        weights       = Weights(List(1, 2)),
        weightsSet    = WeigthsSet(Set(3, 4)),
        duo           = MelonDuo((5, "duo")),
        trio          = MelonTrio((6, "trio", List(1, 2, 3))),
      ).pure[IO].widen[Melon]

      encoded = originalPhantomMelon.asJson
      attempt = encoded.decodeAs[Melon]
      _       = interceptFailure[InvalidInputAnomaly](attempt)
    } yield ()
  }
}
