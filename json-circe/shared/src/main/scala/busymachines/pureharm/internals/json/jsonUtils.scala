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

import busymachines.pureharm.anomaly._
import cats.implicits._
import io.circe._

/** @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 11 Jun 2019
  */
object JsonDecoding {

  def decodeAs[A](json: Json)(implicit decoder: Decoder[A]): Either[Throwable, A] = {
    val r: io.circe.Decoder.Result[A] = decoder.decodeJson(json)
    r.leftMap(df => JsonDecodingAnomaly(df.getMessage))
  }

  def decodeAs[A](json: String)(implicit decoder: Decoder[A]): Either[Throwable, A] = {
    val je = JsonParsing.parseString(json)
    je.flatMap(json => this.decodeAs(json))
  }

  @scala.deprecated("Just use the safe version, and throw errors in user code. Will be removed in 0.3.0", "0.2.0")
  def unsafeDecodeAs[A](json: Json)(implicit decoder: Decoder[A]): A =
    this.decodeAs[A](json)(decoder) match {
      case Left(e)  => throw e
      case Right(v) => v
    }

  @scala.deprecated("Just use the safe version, and throw errors in user code. Will be removed in 0.3.0", "0.2.0")
  def unsafeDecodeAs[A](json: String)(implicit decoder: Decoder[A]): A =
    JsonDecoding.decodeAs(json) match {
      case Left(e)  => throw e
      case Right(v) => v
    }
}

final case class JsonDecodingAnomaly(msg: String) extends InvalidInputAnomaly(msg) {
  override val id: AnomalyID = JsonAnomalyIDs.JsonDecodingAnomalyID
}

/** @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 11 Jun 2019
  */
object JsonParsing {

  def parseString(input: String): Either[Throwable, Json] =
    io.circe.parser.parse(input).leftMap(pf => JsonParsingAnomaly(pf.message))

  @scala.deprecated("Just use the safe version, and throw errors in user code. Will be removed in 0.3.0", "0.2.0")
  def unsafeParseString(input: String): Json =
    JsonParsing.parseString(input) match {
      case Left(e)  => throw e
      case Right(v) => v
    }

}

object PrettyJson {
  val noSpacesNoNulls: Printer = Printer.noSpaces.copy(dropNullValues = true)
  val spaces2NoNulls:  Printer = Printer.spaces2.copy(dropNullValues = true)
  val spaces4NoNulls:  Printer = Printer.spaces4.copy(dropNullValues = true)

  val noSpaces: Printer = Printer.noSpaces
  val spaces2:  Printer = Printer.spaces2
  val spaces4:  Printer = Printer.spaces4

}

final case class JsonParsingAnomaly(msg: String) extends InvalidInputAnomaly(msg) {
  override val id: AnomalyID = JsonAnomalyIDs.JsonParsingAnomalyID
}

/**
  */
object JsonAnomalyIDs {

  private val ID1 = "json_01"
  private val ID2 = "json_02"

  case object JsonParsingAnomalyID extends AnomalyID {
    override val name: String = ID1
  }

  case object JsonDecodingAnomalyID extends AnomalyID {
    override val name: String = ID2
  }

}
