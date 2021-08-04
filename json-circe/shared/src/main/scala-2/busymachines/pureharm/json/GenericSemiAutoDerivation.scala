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

import io.circe.generic.decoding
import io.circe.generic.encoding
import io.circe.generic.{codec => circeCodec}
import shapeless.Lazy

/** Simple forwarders from io.circe.generic.semiauto, useful for custom implicit messages and better UX.
  */
trait GenericSemiAutoDerivation {
  final def decoder[A](implicit decode: Lazy[decoding.DerivedDecoder[A]]): Decoder[A] = decode.value

  final def encoder[A](implicit encode: Lazy[encoding.DerivedAsObjectEncoder[A]]): Encoder.AsObject[A] =
    encode.value

  final def codec[A](implicit codec: Lazy[circeCodec.DerivedAsObjectCodec[A]]): Codec.AsObject[A] = codec.value
}
