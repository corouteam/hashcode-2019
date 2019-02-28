import java.util.HashSet
import kotlin.math.min

object ScoreCalculator {

    fun getHighest(ref: Slide, slides: ArrayList<Slide>): Slide{
        slides.forEach {
            it.peso  = calculateScore(ref, it)
        }
        val sortedSlides = slides.sortedBy { it.peso }

        return sortedSlides[0]
    }

    fun calculateScore(p1: Slide, p2: Slide): Int {
     val i1 = commonTags(p1, p2).size
        val i2 = differentTags(p1, p2).size
        val i3 = differentTags(p2, p1).size
        var ifinal = min(i1, i2)
        ifinal = min(ifinal, i3)
        return  ifinal
    }


    fun differentTags(p1: Slide, p2: Slide): HashSet<String> {
        val commonTags = HashSet<String>()

        p1.tags?.forEach {
            if (!p2.tags!!.contains(it)) {
                commonTags.add(it)
            }
        }

        return commonTags

    }

    fun commonTags(p1: Slide, p2: Slide): HashSet<String> {
        val commonTags = HashSet<String>()

       p1.tags?.forEach {
           if (p2.tags!!.contains(it)) {
               commonTags.add(it)
           }
       }

        return commonTags

    }
}

