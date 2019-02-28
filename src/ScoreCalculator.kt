import java.lang.Exception
import java.util.HashSet
import kotlin.math.min

class ScoreCalculator {

    companion object {

        //per favo4re passami solo quelle verticali
        fun accocchio(ref: Slide, photos: ArrayList<Photo>): Slide{
            var notCheckedPhotos = photos.filter { !it.checked }
            var firstPhoto = bestFit(ref, notCheckedPhotos as ArrayList<Photo>)
            //togli la prima foto
            firstPhoto.checked = true
            return bestSecondFit(ref, photos, firstPhoto)


        }

        fun bestCompleteFit(ref: Slide, photos: ArrayList<Photo>, slides: ArrayList<Slide> ): Slide{
            var bestV = accocchio(ref, photos)
            var bestH = getHighest(ref,slides)
            if(bestH.peso > bestV.peso){
                return bestH
            }else{
                bestV.photo1!!.finalChecked = true
                bestV.photo2!!.finalChecked = true
                return bestV
            }
        }

        fun bestFit(ref: Slide, photos: ArrayList<Photo>): Photo{
            var notCheckedPhotos = photos.filter { !it.checked }
            var firstPhoto: Photo
            var tempSlides = ArrayList<Slide>()
            notCheckedPhotos.forEach {
                var slide = Slide(it, null)
                tempSlides.add(slide)
            }
            return getHighest(ref, tempSlides).photo1!!
        }

        fun bestSecondFit(ref: Slide, photos: ArrayList<Photo>, photo1: Photo): Slide{
            var firstPhoto: Photo
            var notCheckedPhotos = photos.filter { !it.checked }

            var tempSlides = ArrayList<Slide>()
            notCheckedPhotos.forEach {
                var slide = Slide(it, photo1)
                tempSlides.add(slide)
            }
            return getHighest(ref, tempSlides)!!
        }

        fun getHighest(ref: Slide, slides: ArrayList<Slide>): Slide{
            val notCheckedSlides = slides.filter { (!it.checked) }

            notCheckedSlides.forEach {
                    it.peso = calculateScore(ref, it)
            }
            val sortedSlides = notCheckedSlides.sortedByDescending { it.peso }

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


}

