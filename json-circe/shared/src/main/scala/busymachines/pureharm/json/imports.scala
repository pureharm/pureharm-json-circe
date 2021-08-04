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

/** @author
  *   Lorand Szakacs, https://github.com/lorandszakacs
  * @since 11
  *   Jun 2019
  */
@scala.deprecated(
  "Use derived instead which DOES NOT SUPPORT deriving sealed traits w/ _type discriminator, since this feature is not available on Scala 3 + circe 0.14.1, so you have to manually migrate all codecs of sealed traits to not break your pre-existing JSON in APIs and DBs. Good luck.",
  "0.3.0",
)
object derive    extends GenericExtrasSemiAutoDerivation
object derived   extends GenericSemiAutoDerivation
object implicits extends PureharmJsonImplicits
