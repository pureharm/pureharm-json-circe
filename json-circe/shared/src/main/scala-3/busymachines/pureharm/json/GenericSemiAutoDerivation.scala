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

package busymachines.pureharm.json

import scala.deriving.Mirror

/** Simple forwarders from io.circe.generic.semiauto, useful for custom implicit messages and better UX.
  */
trait GenericSemiAutoDerivation {

  @scala.deprecated("Use Decoder.derived directly", "0.3.0")
  final inline def decoder[A](using inline A: Mirror.Of[A]): Decoder[A] = Decoder.derived[A]

  @scala.deprecated("Use Encoder.AsObject.derived directly", "0.3.0")
  final inline def encoder[A](using inline A: Mirror.Of[A]): Encoder.AsObject[A] = Encoder.AsObject.derived[A]

  @scala.deprecated("Use Codec.AsObject.derived directly", "0.3.0")
  final inline def codec[A](using inline A: Mirror.Of[A]): Codec.AsObject[A] = Codec.AsObject.derived[A]
}
