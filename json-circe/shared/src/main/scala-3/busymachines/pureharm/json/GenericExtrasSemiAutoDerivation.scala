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

@scala.deprecated(
  "N.B.! On Scala 3 this is a simple stub w/ ??? implementations! This way of deriving is unsupported in circe 0.14.1. If you are using it for case classes then you can simply use busymachines.pureharm.json.GenericSemiAutoDerivation xor its counterpart busymachines.pureharm.json.derived"
)
trait GenericExtrasSemiAutoDerivation {

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def decoder[A]: Decoder[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def encoder[A]: Encoder.AsObject[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def codec[A]: Codec.AsObject[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def enumerationDecoder[A]: Decoder[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def enumerationEncoder[A]: Encoder[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def enumerationCodec[A]: Codec[A] = ???

}
