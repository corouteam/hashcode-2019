import java.util.HashSet
import kotlin.math.min

class ScoreSystem(var photos: ArrayList<Photo>, var slides: ArrayList<Slide>){
    var parameter = 1
var tempPhoto: Photo? = null
    fun accocchio(ref: Slide): Slide{
        var firstPhoto = bestFit(ref)
        tempPhoto = firstPhoto
        photos.remove(firstPhoto)
        return bestSecondFit(ref, firstPhoto)
    }

    fun bestCompleteFit(ref: Slide): Slide{
        if(photos.count() == 0){
            return getHighestB(ref, slides)
        }else if (slides.count() == 0){
            return  accocchio(ref)
        }

        var bestH = getHighestB(ref, slides)
        if (bestH.peso > parameter) {
            return bestH
        }
        var bestV = accocchio(ref)

        if(bestH.peso > bestV.peso){
            if (tempPhoto != null){
                photos.add(tempPhoto!!)
            }
            return bestH

        }else{
            photos.remove(bestV.photo1)
            photos.remove(bestV.photo2)
            return bestV
        }
    }
    fun remove(slide: Slide){
        slides.remove(slide)
    }

    fun bestFit(ref: Slide): Photo{
        var tempSlides = ArrayList<Slide>()
        photos.forEach {
            var slide = Slide(it, null)
            if(calculateScore(slide, ref)> parameter){
                return it
            }
            tempSlides.add(slide)
        }
        return getHighestB(ref, tempSlides).photo1!!
    }

    fun bestSecondFit(ref: Slide, photo1: Photo): Slide{
        var tempSlides = ArrayList<Slide>()
        photos.forEach {
            var slide = Slide(photo1, it)
            if(calculateScore(slide, ref)> parameter){
                return slide
            }
            tempSlides.add(slide)
        }
        //println("BSF ${tempSlides.count()}")
        //println("BSF tags${photo1.tags}")

        if(tempSlides.count()>1){
            return getHighestB(ref, tempSlides)!!
        }else{
            return  tempSlides.get(0)
        }
    }

    fun getHighest(ref: Slide, slides2: ArrayList<Slide>): Slide{

        slides2.forEach {
            it.peso = calculateScore(ref, it)
        }
        val sortedSlides = slides2.sortedByDescending { it.peso }

        return sortedSlides[0]
    }

    fun getHighestB(ref: Slide, slides: ArrayList<Slide>): Slide{
        //val notCheckedSlides = slides.filter { (!it.checked) }

        slides.forEach {
            it.peso = calculateScore(ref, it)
            if (it.peso > parameter)
                return  it
        }
        val sortedSlides = slides.sortedByDescending { it.peso }

        return sortedSlides[0]
    }

    fun getHighestBH(ref: Slide): Slide{
        var bestV = accocchio(ref)
            photos.remove(bestV.photo1)
            photos.remove(bestV.photo2)
            return bestV

    }

    fun getHighestBV(ref: Slide): Slide{
        //val notCheckedSlides = slides.filter { (!it.checked) }

        slides.forEach {
            it.peso = calculateScore(ref, it)
            if (it.peso > parameter)
                slides.remove(it)
            return  it
        }
        val sortedSlides = slides.sortedByDescending { it.peso }
        slides.remove(sortedSlides[0])
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

        commonTags.addAll(p1.tags!!)
        commonTags.removeAll(p2.tags!!)

        return commonTags

    }

    fun commonTags(p1: Slide, p2: Slide): HashSet<String> {
        val commonTags = HashSet<String>()

        commonTags.addAll(p1.tags!!)
        commonTags.retainAll(p2.tags!!)

        return commonTags
    }
}
