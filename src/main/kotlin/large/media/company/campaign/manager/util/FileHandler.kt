package large.media.company.campaign.manager.util

import org.springframework.stereotype.Component
import java.io.File

@Component
class FileHandler {

    fun readFile(path: String): List<String>
        = File(path).readLines()
}
