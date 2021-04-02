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

package busymachines.pureharm.internals.json

import cats.implicits._
import busymachines.pureharm.sprout._
import io.circe.{Decoder, Encoder}

/** @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 12 Jun 2019
  */
object PureharmJsonInstances {

  trait Implicits {

    implicit final def pureharmSproutCirceEncoder[Old, New](implicit
      oldType: OldType[Old, New],
      encoder: Encoder[Old],
    ): Encoder[New] = encoder.contramap(oldType.oldType)

    implicit final def pureharmSproutCirceDecoder[Old, New](implicit
      newType: NewType[Old, New],
      decoder: Decoder[Old],
    ): Decoder[New] = decoder.map(newType.newType)

    implicit final def pureharmSproutRefinedCirceDecoder[Old, New](implicit
      newType: RefinedTypeThrow[Old, New],
      decoder: Decoder[Old],
    ): Decoder[New] = decoder.emap(u => newType.newType[Either[Throwable, *]](u).leftMap(_.toString()))

  }

}
