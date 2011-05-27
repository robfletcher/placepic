package placepic

import com.google.appengine.api.blobstore.*
import com.google.appengine.api.datastore.*
import com.google.appengine.api.images.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import static com.google.appengine.api.datastore.Query.FilterOperator.*
import static com.google.appengine.api.datastore.Query.SortDirection.*

class ImageHelper {
	
	static Image resizeTo(Entity entity, int width, int height) {
		def image = entity.blob.image

		def left = 0.0
		def top = 0.0
		def right = 1.0
		def bottom = 1.0

		def originalAspect = entity.height / entity.width
		def desiredAspect = height / width

		// resize to a square that will overflow the desired size
		if (originalAspect < desiredAspect) {
		    // original is more landscape than desired
		    int resizeWidth = Math.ceil((entity.width / entity.height) * height)
		    image.resize(resizeWidth, height)

		    left = (1 - (width / image.width)) / 2
		   right = 1 - left
		} else {
		    // original is more portrait than desired
		    int resizeHeight = Math.ceil((entity.height / entity.width) * width)
		    image.resize(width, resizeHeight)

		    top = (1 - (height / image.height)) / 2
		    bottom = 1 - top
		}

		image.transform {
		    crop left, top, right, bottom
			feeling lucky
		}
		
		image
	}

	static Entity createFromBlob(datastore, BlobKey blob) {
		def image = blob.image
		// this is a no-op transform required so we can read image data
		image.crop 0.0, 0.0, 1.0, 1.0

		def query = new Query("Image")
		def preparedQuery = datastore.prepare(query)
		def first = preparedQuery.countEntities(withLimit(1)) == 0

		def entity = new Entity("Image")
		entity.filename = blob.info.filename
		entity.blob = blob
		entity.contentType = blob.contentType
		entity.width = image.width
		entity.height = image.height
		entity.rnd = first ? Integer.MAX_VALUE : new Random().nextInt()
		entity.creation = blob.info.creation
		entity.save()

		entity
	}
	
	static Entity get(datastore, id) {
		datastore.get(KeyFactory.createKey("Image", id.toLong()))
	}
	
	static void delete(datastore, id) {
		datastore.delete(KeyFactory.createKey("Image", id.toLong()))
	}
	
	static Entity random(datastore) {
		def query = new Query("Image")
		query.addFilter("rnd", GREATER_THAN_OR_EQUAL, new Random().nextInt())
		query.addSort("rnd")
		def preparedQuery = datastore.prepare(query)
	 	def entities = preparedQuery.asList(withLimit(1))
		entities?.first()
	}
	
	static void each(datastore, Closure closure) {
		def query = new Query("Image")
		query.addSort("creation", DESCENDING)
		def preparedQuery = datastore.prepare(query)
		for (def itr = preparedQuery.asIterator(); itr.hasNext(); ) {
			closure(itr.next())
		}
	}
	
}