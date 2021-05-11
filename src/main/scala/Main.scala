import com.uni.orc.models.raw.RawPlugin
import play.api.libs.json._
import scala.sys.process._

object Main extends App {

	import com.uni.orc.json.JsonImplicits._

	val jsonString =
		"""{
			| "id": "inspector-git",
			| "name": "Inspector Git",
			| "description": "Description",
			| "version": "0.0.1",
			| "lifecycle": [
			| {
			|   "hook": "remove",
			|   "action": {
			|     "actionType": "command",
			|     "instruction": "java -jar inspector-git.jar remove",
			|     "config": {
			|       "image": "image1",
			|       "name": "name1"
			|      }
			|    }
			| },
			| {
			|   "hook": "remove",
			|   "action": {
			|     "actionType": "command",
			|     "instruction": "https://plm",
			|     "config": {
			|       "method": "POST"
			|      }
			|    }
			| }
			| ]
			|}""".stripMargin

//	val configJsonString = """{
//		                       |     "image": "image1",
//		                       |     "name": "name1"
//		                       |    }""".stripMargin

	val json: RawPlugin = Json.parse(jsonString).as[RawPlugin]

//	val configJson = Json.parse(configJsonString).as[Config]

	println(json)

	//
	  val proc = stringToProcess("cmd /C dir")

	  println(proc.!!)
}
