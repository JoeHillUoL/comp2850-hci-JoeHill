package routes

import data.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.pebbletemplates.pebble.PebbleEngine
import java.io.StringWriter

fun Route.taskRoutes() {

    val pebble = PebbleEngine.Builder()
        .loader(
            io.pebbletemplates.pebble.loader.ClasspathLoader().apply {
                prefix = "templates/"
            }
        ).build()

    fun ApplicationCall.isHtmx(): Boolean =
        request.headers["HX-Request"]?.equals("true", ignoreCase = true) == true


    /* ---------------------------------------------------------
       GET /tasks  (Full page)
    --------------------------------------------------------- */
    get("/tasks") {
        val model = mapOf(
            "title" to "Tasks",
            "tasks" to TaskRepository.all(),
            "editingId" to null,
            "errorMessage" to null
        )

        val template = pebble.getTemplate("tasks/index.peb")
        val writer = StringWriter()
        template.evaluate(writer, model)

        call.respondText(writer.toString(), ContentType.Text.Html)
    }


    /* ---------------------------------------------------------
       POST /tasks (Add Task)
    --------------------------------------------------------- */
    post("/tasks") {
        val title = call.receiveParameters()["title"].orEmpty().trim()

        if (title.isBlank()) {
            if (call.isHtmx()) {
                val error = """
                    <div id="status" hx-swap-oob="true" role="alert">
                        Title is required.
                    </div>
                """.trimIndent()

                return@post call.respondText(error, ContentType.Text.Html, HttpStatusCode.BadRequest)
            }

            return@post call.respondRedirect("/tasks")
        }

        val task = TaskRepository.add(title)

        if (call.isHtmx()) {
            val template = pebble.getTemplate("tasks/_item.peb")
            val w = StringWriter()
            template.evaluate(w, mapOf("task" to task))

            val status = """<div id="status" hx-swap-oob="true">Task added.</div>"""

            return@post call.respondText(w.toString() + status, ContentType.Text.Html)
        }

        call.respondRedirect("/tasks")
    }


    /* ---------------------------------------------------------
       POST /tasks/delete?id=123
    --------------------------------------------------------- */
    post("/tasks/delete") {
        val id = call.request.queryParameters["id"]?.toIntOrNull()
        val removed = id?.let { TaskRepository.delete(it) } ?: false

        if (call.isHtmx()) {
            val msg = if (removed) "Task deleted." else "Could not delete task."
            val status = """<div id="status" hx-swap-oob="true">$msg</div>"""

            return@post call.respondText(status, ContentType.Text.Html)
        }

        call.respondRedirect("/tasks")
    }


    /* ---------------------------------------------------------
       GET /tasks/edit?id=123   (Show edit form)
    --------------------------------------------------------- */
    get("/tasks/edit") {
        val id = call.request.queryParameters["id"]?.toIntOrNull()
            ?: return@get call.respond(HttpStatusCode.NotFound)

        val task = TaskRepository.get(id)
            ?: return@get call.respond(HttpStatusCode.NotFound)

        val errorParam = call.request.queryParameters["error"]
        val error = if (errorParam == "blank") "Title is required." else null

        if (call.isHtmx()) {
            val template = pebble.getTemplate("tasks/_edit.peb")
            val w = StringWriter()
            template.evaluate(w, mapOf("task" to task, "error" to error))

            return@get call.respondText(w.toString(), ContentType.Text.Html)
        }

        val model = mapOf(
            "title" to "Tasks",
            "tasks" to TaskRepository.all(),
            "editingId" to id,
            "errorMessage" to error
        )

        val template = pebble.getTemplate("tasks/index.peb")
        val w = StringWriter()
        template.evaluate(w, model)

        call.respondText(w.toString(), ContentType.Text.Html)
    }


    /* ---------------------------------------------------------
       POST /tasks/edit?id=123   (Save Edit)
    --------------------------------------------------------- */
    post("/tasks/edit") {
        val id = call.request.queryParameters["id"]?.toIntOrNull()
            ?: return@post call.respond(HttpStatusCode.NotFound)

        val task = TaskRepository.get(id)
            ?: return@post call.respond(HttpStatusCode.NotFound)

        val newTitle = call.receiveParameters()["title"].orEmpty().trim()

        if (newTitle.isBlank()) {
            if (call.isHtmx()) {
                val template = pebble.getTemplate("tasks/_edit.peb")
                val w = StringWriter()
                template.evaluate(w, mapOf("task" to task, "error" to "Title is required."))

                return@post call.respondText(w.toString(), ContentType.Text.Html, HttpStatusCode.BadRequest)
            }

            return@post call.respondRedirect("/tasks/edit?id=$id&error=blank")
        }

        val updated = TaskRepository.update(id, newTitle)
            ?: return@post call.respond(HttpStatusCode.NotFound)

        if (call.isHtmx()) {
            val template = pebble.getTemplate("tasks/_item.peb")
            val w = StringWriter()
            template.evaluate(w, mapOf("task" to updated))

            val status = """<div id="status" hx-swap-oob="true">Task updated.</div>"""

            return@post call.respondText(w.toString() + status, ContentType.Text.Html)
        }

        call.respondRedirect("/tasks")
    }


    /* ---------------------------------------------------------
       GET /tasks/view?id=123   (Cancel Edit)
    --------------------------------------------------------- */
    get("/tasks/view") {
        val id = call.request.queryParameters["id"]?.toIntOrNull()
            ?: return@get call.respond(HttpStatusCode.NotFound)

        val task = TaskRepository.get(id)
            ?: return@get call.respond(HttpStatusCode.NotFound)

        val template = pebble.getTemplate("tasks/_item.peb")
        val w = StringWriter()
        template.evaluate(w, mapOf("task" to task))

        call.respondText(w.toString(), ContentType.Text.Html)
    }
}