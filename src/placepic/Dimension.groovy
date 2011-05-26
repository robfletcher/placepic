package placepic

import groovy.transform.Immutable
import groovy.transform.TupleConstructor

@Immutable
@TupleConstructor
class Dimension implements Serializable {
	int width
	int height
	
	String toString() { "${width}x${height}" }
}