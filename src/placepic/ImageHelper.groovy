package placepic

import com.google.appengine.api.blobstore.*
import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import static com.google.appengine.api.datastore.Query.FilterOperator.*

class ImageHelper {

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
		def preparedQuery = datastore.prepare(query)
		for (def itr = preparedQuery.asIterator(); itr.hasNext(); ) {
			closure(itr.next())
		}
	}
	
}